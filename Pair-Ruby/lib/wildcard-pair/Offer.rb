
require 'active_model'
require_relative 'hash_mappable.rb'

module WildcardPair  
  class Offer
    include ActiveModel::Validations
    include ActiveModel::Serializers::JSON
    include WildcardPair::HashMappable

    attr_accessor :price, :original_price, :shipping_cost, :description, :availability, :quantity, :gender, :weight, :weight_units, :offer_id, :sale_start_date, :sale_end_date, :expiration_date

    attr_reader :geographic_availability

    validates :price, presence: true
    validates :description, allow_nil: true, length: {minimum: 1}
    validates :availability, allow_nil: true, inclusion: {in: %w(Discontinued InStock InStoreOnly LimitedAvailability OnlineOnly OutOfStock PreOrder SoldOut) }
    validates :gender, allow_nil: true, inclusion: {in: %w(male female unisex) }
    validates :weight, allow_nil: true, numericality: {greater_than_or_equal_to: 0}
    validates :quantity, allow_nil: true, numericality: {only_integer: true, greater_than_or_equal_to: 0} 

    validate :validatePrices
 
    def initialize(attributes = {})
      attributes.each do |name, value|
        send("#{name}=", value)
      end 
    end

    def price=(price)
      @price = map_hash(price, WildcardPair::Price.new)
    end

    def original_price=(original_price)
      @original_price = map_hash(original_price, WildcardPair::Price.new)
    end

    def shipping_cost=(shipping_cost)
      @shipping_cost = map_hash(shipping_cost, WildcardPair::Price.new)
    end

    def attributes
      instance_values
    end

    def geographic_availability=(geographic_availability)
      if !geographic_availability.is_a?(Array)
        @geographic_availability = [geographic_availability]
      else
        @geographic_availability = geographic_availability
      end
    end

    def validatePrices
      if @price.nil? || !@price.is_a?(Price) || !@price.valid?
        errors.add(:price, "price cannot be nil and must be a valid Price object")
        return
      end

      if !@original_price.nil?
        if !@original_price.is_a?(Price) || !@original_price.valid?
          errors.add(:original_price, "Original Price must be a valid Price object")
          return
        end
      end

      if !@shipping_cost.nil?
        if !@shipping_cost.is_a?(Price) || !@shipping_cost.valid?
          errors.add(:shipping_cost, 'shipping cost must be a valid Price object')
          return
        end
      end  
    end

    #exclude validation fields in the JSON output
    def as_json(options={})
      super(options.merge({:except => [:errors, :validation_context]}))
    end


    def to_json(options = {})
      if self.valid?
        super(options)
      else
        raise "Offer is not valid - please remedy the following errors:" << self.errors.messages.to_s
      end   
    end 

  end
end