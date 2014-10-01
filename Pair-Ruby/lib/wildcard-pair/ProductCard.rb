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

    attr_accessor :name, :web_url, :product_id, :merchant, :brand, :description, :rating, :rating_scale, :rating_count, :sizes, :model, :app_link_ios, :app_link_android
    attr_reader :offers, :card_type, :pair_version, :colors, :images, :related_items, :referenced_items, :options

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

    def images=(images)
      @images ||= Array.new

      if images.is_a?(Array)
        @images = images
      else
        @images << images
      end
    end

    def related_items=(related_items)
      @related_items ||= Array.new

      if related_items.is_a?(Array)
        @related_items = related_items
      else
        @related_items << related_items
      end
    end

    def referenced_items=(referenced_items)
      @referenced_items ||= Array.new

      if referenced_items.is_a?(Array)
        @referenced_items = referenced_items
      else
        @referenced_items << referenced_items
      end
    end

    def options=(options)
      @options ||= Array.new

      if options.is_a?(Array)
        @options = options
      else
        @options << options
      end
    end

    def validateOffers
      if @offers.nil? || (@offers.is_a?(Array) && !@offers.any?)
        errors.add(:offers, 'Offers cannot be nil or an empty array')
        return
      end

      @offers.each do |offer|
        if (!offer.is_a?(Offer) || !offer.valid?)
          errors.add(:offers, "Atleast one of the offers is not a properly constructed offer object and/or is not valid")
          return
        end
      end
    end

    def validateColors
      if (!@colors.nil? && @colors.any?)
        @colors.each do |color|
          if (!color.is_a?(Color) || !color.valid?)
            errors.add(:colors, "Atleast one of the colors is not a properly constructed color object and/or is not valid")
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

