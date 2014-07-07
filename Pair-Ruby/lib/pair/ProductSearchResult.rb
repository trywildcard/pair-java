
require 'active_model'

module PairSDK
	class ProductSearchResult
        include ActiveModel::Validations
        include ActiveModel::Serializers::JSON

		attr_accessor :name, :price, :product_url, :image_url

        validates :name, presence: true
        validates :price, presence: true, numericality: {greater_than_or_equal_to: 0}
        validates :product_url, presence: true
        
		def initialize(attributes = {})
			attributes.each do |name, value|
				send("#{name}=", value)
			end	
		end

		def attributes
			instance_values
		end

		def to_json
			if self.valid?
				ActiveSupport::JSON.encode(self.as_json)
			else
				raise "Offer is not valid - please remedy the following errors:" << self.errors.messages.to_s
			end   
		end 

	end
end