#!/usr/bin/env ruby -wKU

require 'active_model'
require_relative 'hash_mappable.rb'

module WildcardPair
  class ProductCard
    private

    attr_accessor :offers, :card_type, :pair_version

    public

    include ActiveModel::Validations
    include ActiveModel::Serializers::JSON
    include WildcardPair::HashMappable

    attr_accessor :name, :web_url, :product_id, :merchant, :brand, :description, :images, :colors, :rating, :rating_scale, :rating_count, :related_items, :referenced_items, :sizes, :options, :model, :app_link_ios, :app_link_android
    attr_reader :offers, :card_type, :pair_version

    validates :web_url, presence: true
    validates :name, presence: true
    validate :validateOffers
    validate :validateColors

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

    def attributes
      instance_values
    end

    def offers=(offers)
      @offers ||= Array.new

      if offers.is_a?(Array)
        offers.each do |offer|
          @offers << map_hash(offer, Offer.new)
        end
      elsif offers.is_a?(Offer)
          @offers << offers
      else
        @offers << map_hash(offers, Offer.new)
      end
    end

    def colors=(colors)
      @colors ||= Array.new

      if colors.is_a?(Array)
        colors.each do |color|
          @colors << map_hash(color, Color.new)
        end
      elsif colors.is_a?(Color)
          @colors << colors
      else
        @colors << map_hash(colors, Color.new)
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

    def validateColors
      if @colors.nil? || (@colors.is_a?(Array) && !@colors.any?)
        #no colors exist which is ok - just return out
        return
      end

      if (!@colors.nil? && @colors.any?)
        @colors.each do |color|
          if (!color.is_a?(Color) || !color.valid?)
            errors.add(:color, "One of the colors is not a properly constructed color object and/or is not valid")
            return
          end
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

