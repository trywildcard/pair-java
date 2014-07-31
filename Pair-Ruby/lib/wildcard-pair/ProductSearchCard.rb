#ProductCard
#!/usr/bin/env ruby -wKU

require 'active_model'
require_relative 'hash_mappable.rb'

module WildcardPair
  class ProductSearchCard

    private

    attr_accessor :search_results, :card_type, :pair_version

    public

      include ActiveModel::Validations
      include ActiveModel::Serializers::JSON
      include WildcardPair::HashMappable

    attr_accessor :total_results

    attr_reader :search_results, :card_type, :pair_version

    validates :total_results, presence: true, numericality: {only_integer: true, greater_than_or_equal_to: 0}
    validate :validateSearchResults

    def initialize(attributes = {})
      attributes.each do |name, value|
        send("#{name}=", value)
      end 

      @card_type = 'product_search'
      
      if !Gem.loaded_specs['wildcard-pair'].nil?
        @pair_version = Gem.loaded_specs['wildcard-pair'].version.to_s
      else
        @pair_version = "unknown"
      end
    end

    def attributes
      instance_values
    end

     def search_results=(search_results)
      @search_results ||= Array.new

      if search_results.is_a?(Array)
        search_results.each do |search_result|
          @search_results << map_hash(search_result, ProductSearchResult.new)
        end
      elsif search_results.is_a?(ProductSearchResult)
          @search_results << search_results
      else
        @search_results << map_hash(search_results, ProductSearchResult.new)
      end
    end

    def validateSearchResults
      if @search_results.nil? || (@search_results.is_a?(Array) && !@search_results.any?)
        errors.add(:search_results, 'Cannot be Nil and must be an array!')
        return
      end

      @search_results.each do |search_result|
        if (!search_result.is_a?(ProductSearchResult)  || !search_result.valid?)
          errors.add(:search_results, 'One of the searchresults is not a properly constructed ProductSearchResult object and/or is not valid')
          return
        end
      end

    end

    #exclude validation fields in the JSON output
    def as_json(options={})
      super(options.merge({:except => [:errors, :validation_context]}))
    end
    
    def to_json(options={})
      if self.valid?
        super(options)
      else
        raise "Product Search Card is not valid - please remedy below errors:" << self.errors.messages.to_s
      end    
    end 

  end
end

