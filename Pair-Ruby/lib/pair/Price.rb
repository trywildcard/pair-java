
require 'active_model'

module PairSDK  
  class Price
        include ActiveModel::Validations
        include ActiveModel::Serializers::JSON

    attr_accessor :price, :currency

    validates :price, presence: true, numericality: {greater_than_or_equal_to: 0}
 
    def initialize(attributes = {})

      #let's set currency to USD by default
      @currency = "USD"

      attributes.each do |name, value|
        send("#{name}=", value)
      end 
    end

    def attributes
      instance_values
    end

    #exclude validation fields in the JSON output
    def as_json(options={})
      super(:except => [:errors, :validation_context])
    end


    def to_json(options = {})
      if self.valid?
        super
      else
        raise "Price is not valid - please remedy the following errors:" << self.errors.messages.to_s
      end   
    end 

  end
end