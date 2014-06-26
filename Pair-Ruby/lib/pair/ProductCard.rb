#ProductCard
#!/usr/bin/env ruby -wKU

require 'json'
require 'active_model'
require 'uri'

	class ProductCard
        include ActiveModel::Validations

		attr_accessor :cardType, :name, :webUrl, :productId, :offers, :merchant, :brand, :description, :colors, :images, :rating, :ratingScale, :ratingCount, :relatedItems, :referencedItems, :sizes, :options, :model, :appLinkIos, :appLinkAndroid

       # validates_presence_of :cardType
        validates :cardType, presence: true, inclusion: {in: %w(product product_search) }
		validates_presence_of :webUrl
		#validates :webUrl, format: { with: /\A#{URI::regexp}\z/, on: :create}
		#validates_presence_of :name
		validates :name, presence: true

		

		def initialize(attributes = {})
			attributes.each do |name, value|
				send("#{name}=", value)
				puts name
				puts value
			end	
		end

		def writeAsJson
			puts self.to_json
		end 

	end

