require 'spec_helper'

describe ProductCard do

before :each do
    @validoffer = Offer.new price: 5.00
    @validoffer2 = Offer.new price: 10.00, gender: 'male', availability: 'InStock'
    @validoffers = [@validoffer, @validoffer2]
end

describe '#new' do
	it "takes returns a ProductCard object" do
		product_card = ProductCard.new offers: @validoffer, web_url: 'http://brand.com/product/123', name: 'product test'
		product_card.should be_an_instance_of ProductCard
		product_card.valid?.should eql true
		product_card.offers.is_a?(Array).should eql true
		product_card.card_type.should eql 'product'
	end
end

describe '#new2' do
	it "takes returns a ProductCard object" do
		product_card = ProductCard.new offers: @validoffers, web_url: 'http://brand.com/product/123', name: 'product test'
		product_card.should be_an_instance_of ProductCard
		product_card.valid?.should eql true
		product_card.offers.is_a?(Array).should eql true
		product_card.offers.should eql @validoffers
		product_card.card_type.should eql 'product'
	end
end

#todo - test to_json raising exception

describe '#no_web_url' do
	product_card = ProductCard.new offers: @validoffers, name: 'product test'
	it "no web url" do
		product_card.valid?.should eql false
	end
end

describe '#web_url' do
	product_card = ProductCard.new offers: @validoffers, name: 'product test', web_url: 'http://brand.com/product/123'
	it "web_url" do
	#	product_card.valid?.should eql true
	    product_card.errors
		product_card.web_url.should eql 'http://brand.com/product/123'
	end
end



end