
require 'active_model'


	class ProductSearchResult
        include ActiveModel::Validations

		attr_accessor :name, :price, :productURL, :imageURL

        validates :name, presence: true
        validates :price, presence: true
        #todo: URL Validation
        validates :productURL, presence: true
        
		def initialize(attributes = {})
			attributes.each do |name, value|
				send("#{name}=", value)
			end	
		end

	end