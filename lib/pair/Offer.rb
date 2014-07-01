
require 'active_model'
require 'date'


	class Offer
        include ActiveModel::Validations

		attr_accessor :price, :originalPrice, :shippingCost, :description, :availability, :quantity, :gender, :weight, :weightUnits, :offerId

		attr_reader :saleStartDate, :saleEndDate, :expirationDate, :geographicAvailability

        validates :price, presence: true
        validates :description, length: {minimum: 1}
        validates :availability, inclusion: {in: %w(Discontinued, InStock, InStoreOnly, LimitedAvailability, OnlineOnly, OutOfStock, PreOrder, SoldOut) }
        validates :gender, inclusion: {in: %w(male, female, unisex)}
        validates :weight, numericality: {greater_than_or_equal_to: 0}
        validates :quantity, numericality: {only_integer: true, greater_than_or_equal_to: 0} 

		def initialize(attributes = {})
			attributes.each do |name, value|
				send("#{name}=", value)
			end	
		end

		def saleStartDate(saleStartDate)
			self.saleStartDate = Date.strptime(saleStartDate, '%m-%d-%Y')
		end

		def saleEndDate(saleEndDate)
			self.saleEndDate = Date.strptime(saleEndDate, '%m-%d-%Y')
		end

		def expirationDate(expirationDate)
			self.expirationDate = Date.strptime(expirationDate, '%m-%d-%Y')
		end

		def geographicAvailability(geographicAvailability)
			if !geographicAvailability.is_a?(Array)
				errors.add(:geographicAvailability, 'Must be an array!')
			end
		end

		def validateOffer(offer)
			puts "Made it here"
		end

		def writeAsJson
			puts self.to_json
		end 

	end