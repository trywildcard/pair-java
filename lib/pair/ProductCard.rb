#ProductCard
#!/usr/bin/env ruby -wKU

require 'json'
require 'active_model'
require 'uri'

	class ProductCard
        include ActiveModel::Validations

		attr_accessor :cardType, :name, :webUrl, :productId, :merchant, :brand, :description, :images, :rating, :ratingScale, :ratingCount, :relatedItems, :referencedItems, :sizes, :options, :model, :appLinkIos, :appLinkAndroid

		attr_reader :offers, :colors
       # validates_presence_of :cardType
        validates :cardType, presence: true, inclusion: {in: %w(product product_search) }
		validates :webUrl, presence: true
		##todo URL regex validation
		#validates :webUrl, format: { with: /\A#{URI::regexp}\z/, on: :create}

		validates :name, presence: true

		#validate :offers_valid

		valid_colors = %w(Beige, Black, Blue, Bronze, Brown, Gold, Green, Gray, Metallic, Multicolored, OffWhite, Orange, Pink, Purple, Red, Silver, Transparent, Turquoise, White, Yellow)

		def initialize(attributes = {})
			attributes.each do |name, value|
				send("#{name}=", value)
				puts name
				puts value
			end	

		end

		def offers=(offers)

			if !offers.is_a?(Array)
				errors.add(:geographicAvailability, 'Must be an array!')
			end

			if offers.nil? || offers.empty? 
				errors.add(:offers, 'Must have atleast one offer!!')
			end

			tempOffer = Offer.new
			for index in 0 ... offers.size
				#it'd be nice to do Offer.new (offers[index]) so activemodel validation can do all the work for us
				#tempOffer.validateOffer(offers[index])
			end

			self.offers = offers

		end

		def colors=(colors)

			if !colors.is_a?(Array)
				errors.add(:colors, 'Must be an array!')
			end

			for index in 0 ... colors.size
				if (self.valid_colors.include? colors[index])
					errors.add(:colors, 'Invalid Color Added')
				end
			end

			self.colors = colors
		
		end

		
		def writeAsJson
			puts self.to_json
		end 

	end

