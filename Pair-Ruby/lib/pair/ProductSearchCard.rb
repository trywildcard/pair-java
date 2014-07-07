#ProductCard
#!/usr/bin/env ruby -wKU

require 'json'
require 'active_model'

module PairSDK
	class ProductSearchCard
        include ActiveModel::Validations
        include ActiveModel::Serializers::JSON

		attr_accessor :total_results, :search_results

        validates :total_results, presence: true, numericality: {only_integer: true, greater_than_or_equal_to: 0}
        validate :validateSearchResults

		def initialize(attributes = {})
			attributes.each do |name, value|
				send("#{name}=", value)
			end	

			@cardType = 'product_search'
			#todo tie this into gem config?
			@pairversion = 0.1
		end

		def attributes
			instance_values
		end

		def validateSearchResults
			if self.search_results.nil? || !self.search_results.is_a?(Array)
				puts "ERROR: searchresults cannot be nil and must be an array"
				errors.add(:search_results, 'Cannot be Nil and must be an array!')
				return
			end

			self.search_results.each do |search_result|

				if (!search_result.is_a?(ProductSearchResult)  || !search_result.valid?)
					errors.add(:search_results, 'One of the searchresults is not a properly constructed ProductSearchResult object and/or is not valid')
					return
				end
			end

		end
		
		def to_json
           if self.valid?
           	   ActiveSupport::JSON.encode(self.as_json)	
           else
           	   raise "Product Search Card is not valid - please remedy below errors:" << self.errors.messages.to_s
           end    
		end 

	end
end

