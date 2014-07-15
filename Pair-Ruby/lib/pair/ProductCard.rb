#ProductCard
#!/usr/bin/env ruby -wKU

#require 'json'
require 'active_model'

module PairSDK
	class ProductCard
        include ActiveModel::Validations
        include ActiveModel::Serializers::JSON

		attr_accessor :name, :web_url, :product_id, :merchant, :brand, :description, :images, :rating, :rating_scale, :rating_count, :related_items, :referenced_items, :sizes, :options, :model, :app_link_ios, :app_link_android

		attr_reader :offers, :colors, :card_type, :pair_version

		validates :web_url, presence: true
		validates :name, presence: true

		validate :validateOffers
		validate :validateColors

		@@valid_colors = %w(Beige Black Blue Bronze Brown Gold Green Gray Metallic Multicolored OffWhite Orange Pink Purple Red Silver Transparent Turquoise White Yellow)

		def initialize(attributes = {})
			attributes.each do |name, value|
				send("#{name}=", value)
			end

			@card_type = 'product'

			#todo tie this into gem config?
			@pair_version = "0.0.1"
		end

		def attributes
			instance_values
		end

		def offers=(offers)
			if !offers.is_a?(Array)
				@offers = [offers]
			else
				@offers = offers
			end
		end

		def colors=(colors)
			if !colors.is_a?(Array)
				@colors = [colors]
			else
				@colors = colors
			end		
		end

		def validateOffers
			if @offers.nil? || (@offers.is_a?(Array) && !@offers.any?)
				errors.add(:offers, 'Offers cannot be nil or an empty array')
				return
			end

			@offers.each do |offer|
				if (!offer.is_a?(Offer)  || !offer.valid?)
					errors.add(:offer, 'One of the offers is not a properly constructed offer object and/or is not valid')
					return
				end
			end
		end

		def validateColors
			if (!@colors.nil? && @colors.any?)
				@colors.each do |color|
					if (!@@valid_colors.include? color)
						errors.add(:colors, 'Invalid Color Added')
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

