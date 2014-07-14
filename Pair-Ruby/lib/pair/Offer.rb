
require 'active_model'

module PairSDK  
  class Offer
        include ActiveModel::Validations
        include ActiveModel::Serializers::JSON

    attr_accessor :price, :original_price, :shipping_cost, :description, :availability, :quantity, :gender, :weight, :weight_units, :offerId, :sale_start_date, :sale_end_date, :expiration_date

    attr_reader :geographic_availability

    validates :price, presence: true, numericality: {greater_than_or_equal_to: 0}
    validates :original_price, allow_nil: true, numericality: {greater_than_or_equal_to: 0}
    validates :shipping_cost, allow_nil: true, numericality: {greater_than_or_equal_to: 0}
    validates :description, allow_nil: true, length: {minimum: 1}
    validates :availability, allow_nil: true, inclusion: {in: %w(Discontinued InStock InStoreOnly LimitedAvailability OnlineOnly OutOfStock PreOrder SoldOut) }
    validates :gender, allow_nil: true, inclusion: {in: %w(male female unisex) }
    validates :weight, allow_nil: true, numericality: {greater_than_or_equal_to: 0}
    validates :quantity, allow_nil: true, numericality: {only_integer: true, greater_than_or_equal_to: 0} 
 
    def initialize(attributes = {})
      attributes.each do |name, value|
        send("#{name}=", value)
      end 
    end

    def attributes
      instance_values
    end

    #todo, this probably needs to be a hash
    def geographic_availability(geographic_availability)
      if !geographic_availability.is_a?(Array)
        @geographic_availability = [geographic_availability]
      else
        @geographic_availability = geographic_availability
      end
    end

    #exclude validation fields in the JSON output
    def as_json(options={})
      super(:except => [:errors, :validation_context])
    end


    def to_json(options = {})
      if self.valid?
        super
      else
        raise "Offer is not valid - please remedy the following errors:" << self.errors.messages.to_s
      end   
    end 

  end
end