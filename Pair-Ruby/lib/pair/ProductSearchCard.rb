#ProductCard
#!/usr/bin/env ruby -wKU

require 'json'
require 'active_model'
require 'uri'

	class ProductSearchCard
        include ActiveModel::Validations

		attr_accessor :totalResults
		attr_reader :searchresults

        validates :totalResults, presence: true, numericality: {only_integer: true, greater_than_or_equal_to: 0}

		def initialize(attributes = {})
			attributes.each do |name, value|
				send("#{name}=", value)
			end	

			@cardType = 'product_search'
			#todo tie this into gem config?
			@pairversion = 0.1
		end

		def searchresults=(searchresults)

			if searchresults.nil? || !searchresults.is_a?(Array)
				puts "ERROR: searchresults cannot be nil and must be an array"
				errors.add(:searchresults, 'Cannot be Nil and must be an array!')
				return
			end

			searchresults.each do |searchresult|

				if (!searchresult.is_a?(ProductSearchResult)  || !searchresult.valid?)
					errors.add(:offer, 'One of the searchresults is not a properly constructed ProductSearchResult object and/or is not valid')
					return
				end
			end

			@searchresults = searchresults
		end

		
		def writeAsJson
           if self.valid?
           	   ActiveSupport::JSON.encode(self)	
           else
           	   puts "Product Search Card is not valid - please remedy below errors:"
           	   self.errors
           end    
		end 

	end

