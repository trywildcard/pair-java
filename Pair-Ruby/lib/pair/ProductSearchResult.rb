
require 'active_model'

module PairSDK
    class ProductSearchResult
        include ActiveModel::Validations
        include ActiveModel::Serializers::JSON

        attr_accessor :name, :price, :product_card_url, :image_url

        validates :name, presence: true
        validates :price, presence: true
        validates :product_card_url, presence: true

        validate :validatePrice
        
        def initialize(attributes = {})
            attributes.each do |name, value|
                send("#{name}=", value)
            end 
        end

        def attributes
            instance_values
        end

        def validatePrice
          if @price.nil? || !@price.is_a?(Price) || !@price.valid?
            errors.add(:price, 'price cannot be nil and must be a valid Price object')
            return
          end
        end

        #exclude validation fields in the JSON output
        def as_json(options={})
            super(:except => [:errors, :validation_context])
        end

        def to_json
            if self.valid?
                super
            else
                raise "Offer is not valid - please remedy the following errors:" << self.errors.messages.to_s
            end   
        end 

    end
end