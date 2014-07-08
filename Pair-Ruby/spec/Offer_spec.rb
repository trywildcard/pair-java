require 'spec_helper'

describe Offer do

describe '#new' do
	it "takes returns a Offer object" do
		offer = Offer.new price: 5
		offer.should be_an_instance_of Offer
	end
end

describe '#no_price' do
	offer = Offer.new 
	it "no price" do
		offer.valid?.should eql false
	end
end

describe '#invalid_price' do
	offer = Offer.new price: -1
	it "invalidates negative price" do
		offer.valid?.should eql false
	end
end

describe '#no_original_price' do
	offer = Offer.new price: 5, original_price: nil
	it "no original price" do
		offer.valid?.should eql true
	end
end

describe '#negative_original_price' do
	offer = Offer.new price: 5, original_price: -2
	it "negative original price" do
		offer.valid?.should eql false
	end
end

describe '#original_price' do
	offer = Offer.new price: 5, original_price: 4
	it "original price" do
		offer.valid?.should eql true
		offer.original_price.should eql 4
	end
end

describe '#no_shipping_cost' do
	offer = Offer.new price: 5, shipping_cost: nil
	it "no shipping cost" do
		offer.valid?.should eql true
	end
end

describe '#negative_shipping_cost' do
	offer = Offer.new price: 5, shipping_cost: -2
	it "negative shipping cost" do
		offer.valid?.should eql false
	end
end

describe '#shipping_cost' do
	offer = Offer.new price: 5, shipping_cost: 4
	it "original price" do
		offer.valid?.should eql true
		offer.shipping_cost.should eql 4
	end
end

describe '#nil_description' do
	offer = Offer.new price: 5, description: nil
	it "nil_description" do
		offer.valid?.should eql true
	end
end

describe '#empty_description' do
	offer = Offer.new price: 5, description: ''
	it "empty shipping cost" do
		offer.valid?.should eql false
	end
end

describe '#description' do
	offer = Offer.new price: 5, description: 'offer description'
	it "description" do
		offer.valid?.should eql true
		offer.description.should eql 'offer description'
	end
end

describe '#nil_availability' do
	offer = Offer.new price: 5, availability: nil
	it "nil_availability" do
		offer.valid?.should eql true
	end
end

describe '#invalid_availability' do
	offer = Offer.new price: 5, availability: 'NotExist'
	it "invalid_availability" do
		offer.valid?.should eql false
	end
end

describe '#availability' do
	offer = Offer.new price: 5, availability: 'OnlineOnly'
	it "availability" do
		offer.valid?.should eql true
		offer.availability.should eql 'OnlineOnly'
	end
end

describe '#nil_gender' do
	offer = Offer.new price: 5, gender: nil
	it "nil_gender" do
		offer.valid?.should eql true
	end
end

describe '#invalid_gender' do
	offer = Offer.new price: 5, gender: 'malefemale'
	it "invalid_gender" do
		offer.valid?.should eql false
	end
end

describe '#gender' do
	offer = Offer.new price: 5, gender: 'unisex'
	it "gender" do
		offer.valid?.should eql true
		offer.gender.should eql 'unisex'
	end
end

describe '#no_weight' do
	offer = Offer.new price: 5, weight: nil
	it "no weight" do
		offer.valid?.should eql true
	end
end

describe '#negative_weight' do
	offer = Offer.new price: 5, weight: -2
	it "negative weight" do
		offer.valid?.should eql false
	end
end

describe '#weight' do
	offer = Offer.new price: 5, weight: 4.5
	it "weight" do
		offer.valid?.should eql true
		offer.weight.should eql 4.5
	end
end

describe '#no_quantity' do
	offer = Offer.new price: 5, quantity: nil
	it "no quantity" do
		offer.valid?.should eql true
	end
end

describe '#negative_quantity' do
	offer = Offer.new price: 5, quantity: -2
	it "negative quantity" do
		offer.valid?.should eql false
	end
end

describe '#non_integer_quantity' do
	offer = Offer.new price: 5, quantity: 2.5
	it "non integer quantity" do
		offer.valid?.should eql false
	end
end

describe '#quantity' do
	offer = Offer.new price: 5, quantity: 400
	it "quantity" do
		offer.valid?.should eql true
		offer.quantity.should eql 400
	end
end

#todo - test to_json raising exception

end