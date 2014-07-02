#ProductCard
#!/usr/bin/env ruby -wKU

require 'json'
require 'active_model'
require 'uri'

	class ProductCard
        include ActiveModel::Validations

		attr_accessor :name, :webUrl, :productId, :merchant, :brand, :description, :images, :rating, :ratingScale, :ratingCount, :relatedItems, :referencedItems, :sizes, :options, :model, :appLinkIos, :appLinkAndroid

		attr_reader :offers, :colors

		validates :webUrl, presence: true
		##todo URL regex validation
		#validates :webUrl, format: { with: /\A#{URI::regexp}\z/, on: :create}

		validates :name, presence: true

		#validate :offers_valid
		valid_colors = %w(Beige, Black, Blue, Bronze, Brown, Gold, Green, Gray, Metallic, Multicolored, OffWhite, Orange, Pink, Purple, Red, Silver, Transparent, Turquoise, White, Yellow)

		def initialize(attributes = {})
			attributes.each do |name, value|
				send("#{name}=", value)
			end	

			@cardType = 'product'
			
			#todo tie this into gem config?
			@pairversion = 0.1
		end

		def offers=(inputoffers)

			if !inputoffers.is_a?(Array)
				puts "ERROR: offers must be an array"
				errors.add(:offers, 'Must be an array!')
				return
			end

			if inputoffers.nil? || inputoffers.empty? 
				puts "ERROR: offers must be have atleast one offer"
				errors.add(:offers, 'Must have atleast one offer!!')
				return
			end

			inputoffers.each do |offer|

				if (!offer.is_a?(Offer)  || !offer.valid?)
					errors.add(:offer, 'One of the offers is not a properly constructed offer object and/or is not valid')
					return
				end
			end

			@offers = inputoffers
		end

		def colors=(colors)

			if !colors.is_a?(Array)
				errors.add(:colors, 'Must be an array!')
			end

			colors.each do |color|
				if (!self.valid_colors.include? color)
					errors.add(:colors, 'Invalid Color Added')
				end
			end

			@colors = colors
		
		end

		
		def writeAsJson
           if self.valid?
           	   ActiveSupport::JSON.encode(self)	
           else
           	   puts "Product Card is not valid - please remedy below errors:"
           	   self.errors
           end    
		end 

	end

