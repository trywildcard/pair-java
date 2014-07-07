#ProductCard
#!/usr/bin/env ruby -wKU

require 'json'
require 'active_model'
require 'uri'

#module PairSDK
	class ProductCard
        include ActiveModel::Validations
        include ActiveModel::Serializers::JSON

		attr_accessor :name, :web_url, :product_id, :merchant, :brand, :description, :images, :rating, :rating_scale, :rating_count, :related_items, :referenced_items, :sizes, :options, :model, :app_link_ios, :app_link_android

		attr_reader :offers, :colors, :card_type, :pair_version

		validates :web_url, presence: true
		validates :name, presence: true

		validate :validateOffers
		validate :validateColors
		valid_colors = %w(Beige, Black, Blue, Bronze, Brown, Gold, Green, Gray, Metallic, Multicolored, OffWhite, Orange, Pink, Purple, Red, Silver, Transparent, Turquoise, White, Yellow)

		def initialize(attributes = {})
			@card_type = 'product'

			#todo tie this into gem config?
			@pair_version = 0.1

			begin
				attributes.each do |name, value|
					send("#{name}=", value)
				end
			rescue NoMethodError => e
				#todo probably handle this better, but for now - do nothing
			end
			
		end

		def attributes
			instance_values
		end

		#todo: support both the singular offer object as well as the array of objects
		def offers=(inputoffers)
			if !inputoffers.is_a?(Array)
				@offers = [inputoffers]
			else
				@offers = inputoffers
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
			if self.offers.nil? || (self.offers.is_a?(Array) && self.offers.empty?)
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
			if (!self.colors.nil?)
				@colors.each do |color|
					if (!self.valid_colors.include? color)
						errors.add(:colors, 'Invalid Color Added')
					end
				end
			end
		end

		#tell active support which fields to include/exclude
		def to_json
           if self.valid?
           	ActiveSupport::JSON.encode(self.as_json)
           else
           	raise "Product Card is not valid - please remedy the following errors:" << self.errors.messages.to_s
           end    
		end 

	end
#end

