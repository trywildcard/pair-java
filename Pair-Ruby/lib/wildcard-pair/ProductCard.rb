#!/usr/bin/env ruby -wKU

require 'active_model'
require_relative 'hash_mappable.rb'

module WildcardPair
  class ProductCard
    include ActiveModel::Validations
    include ActiveModel::Serializers::JSON
    include WildcardPair::HashMappable

    attr_accessor :name, :web_url, :product_id, :merchant, :brand, :description, :images, :rating, :rating_scale, :rating_count, :related_items, :referenced_items, :sizes, :options, :model, :app_link_ios, :app_link_android

    private
    attr_accessor :offers, :card_type, :pair_version
    public

    attr_reader :offers, :card_type, :pair_version

    validates :web_url, presence: true
    validates :name, presence: true

    validate :validateOffers

    def initialize(attributes = {})
      attributes.each do |name, value|
        send("#{name}=", value)
      end

      @card_type = 'product'

      if !Gem.loaded_specs['wildcard-pair'].nil?
        @pair_version = Gem.loaded_specs['wildcard-pair'].version.to_s
      else
        @pair_version = "unknown"
      end
    end
    
    def attributes=(hash)
      hash.each do |key, value|
        send("#{key}=", value)
      end
    end

    def attributes
      instance_values
    end

    def add_offer(offer)
      if offer.is_a? Hash
        wc_offer = WildcardPair::Offer.new
        wc_offer.attributes = offer
        offer = wc_offer
      else
        offer = offer
      end

      @offers ||= Array.new
      @offers << offer 
    end

    def offers=(offers)
      if offers.is_a?(Array)
        offers.each do |offer|
          add_offer(offer)
        end
      elsif offers.is_a?(Offer)
        add_offer(offers)
      else
        add_offer(offers)
      end
    end

    def validateOffers
      if @offers.nil? || (@offers.is_a?(Array) && !@offers.any?)
        errors.add(:offers, 'Offers cannot be nil or an empty array')
        return
      end

      @offers.each do |offer|
        if (!offer.is_a?(Offer) || !offer.valid?)
          errors.add(:offer, "One of the offers is not a properly constructed offer object and/or is not valid")
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
        raise "Product Card is not valid - please remedy the following errors:" << self.errors.messages.to_s
      end    
    end 

  end
end

