require 'spec_helper'
require 'pp'

describe PairSDK::ProductCard do

before :each do
  @price = PairSDK::Price.new price: 5.00
    @validoffer = PairSDK::Offer.new price: @price
    @validoffer2 = PairSDK::Offer.new price: @price, gender: 'male', availability: 'InStock'
    @validoffers = [@validoffer, @validoffer2]
end

describe '#new' do
  it "takes returns a ProductCard object" do
    product_card = PairSDK::ProductCard.new offers: @validoffer, web_url: 'http://brand.com/product/123', name: 'product test'
    product_card.should be_an_instance_of PairSDK::ProductCard
    product_card.valid?.should eql true
    product_card.offers.is_a?(Array).should eql true
    product_card.card_type.should eql 'product'
    expect {product_card.to_json}.not_to raise_error
  end
end

describe '#new2' do
  it "takes returns a ProductCard object" do
    product_card = PairSDK::ProductCard.new offers: @validoffers, web_url: 'http://brand.com/product/123', name: 'product test'
    product_card.should be_an_instance_of PairSDK::ProductCard
    product_card.valid?.should eql true
    product_card.offers.is_a?(Array).should eql true
    product_card.offers.should eql @validoffers
    product_card.card_type.should eql 'product'
    expect {product_card.to_json}.not_to raise_error
  end
end

describe '#no_web_url' do
  product_card = PairSDK::ProductCard.new offers: @validoffers, name: 'product test', web_url: nil
  it "no web url" do
    product_card.valid?.should eql false
  end
end

describe '#web_url' do
  price = PairSDK::Price.new price: 5.0
  offer = PairSDK::Offer.new price: price
  product_card33 = PairSDK::ProductCard.new offers: offer, web_url: 'http://brand.com/product/123', name: 'product test' 
  it "web url" do
    product_card33.valid?.should eql true
    product_card33.web_url.should eql 'http://brand.com/product/123'
  end
end


describe '#no_name' do
  product_card = PairSDK::ProductCard.new offers: @validoffers, web_url: 'http://brand.com/product/123'
  it "no name" do
    product_card.valid?.should eql false
  end
end

describe '#name' do
  price = PairSDK::Price.new price: 5.0
  offer = PairSDK::Offer.new price: price
  product_card33 = PairSDK::ProductCard.new offers: offer, web_url: 'http://brand.com/product/123', name: 'product test' 
  it "name" do
    product_card33.valid?.should eql true
    product_card33.name.should eql 'product test' 
  end
end

describe '#nooffers' do
  product_card = PairSDK::ProductCard.new offers: nil, web_url: 'http://brand.com/product/123', name: 'product test' 

  it "name" do
    product_card.valid?.should eql false
    expect {product_card.to_json}.to raise_error(RuntimeError)
  end
end

describe '#invalidoffers' do
  invalidprice = PairSDK::Price.new price: -4
  invalidoffer = PairSDK::Offer.new price: invalidprice
  product_card = PairSDK::ProductCard.new offers: invalidoffer, web_url: 'http://brand.com/product/123', name: 'product test' 

  validprice = PairSDK::Price.new price: 4
  validoffer = PairSDK::Offer.new price: validprice
  invalidoffers = [validoffer, nil]
  product_card2 = PairSDK::ProductCard.new offers: invalidoffers, web_url: 'http://brand.com/product/123', name: 'product test' 
  it "name" do
    product_card.valid?.should eql false
    product_card2.valid?.should eql false
    expect {product_card.to_json}.to raise_error(RuntimeError)
    expect {product_card2.to_json}.to raise_error(RuntimeError)
  end
end

describe '#oneinvalidcolor' do
  validprice = PairSDK::Price.new price: 4
  validoffer = PairSDK::Offer.new price: validprice
  invalidcolor = 'Aqua'
  product_card = PairSDK::ProductCard.new offers: validoffer, web_url: 'http://brand.com/product/123', name: 'product test', colors: invalidcolor

  it "oneinvalidcolor" do
    product_card.valid?.should eql false
  end
end

describe '#invalidcolors' do
  validprice = PairSDK::Price.new price: 4
  validoffer = PairSDK::Offer.new price: validprice
  invalidcolors = ['Beige', 'Aqua']
  product_card = PairSDK::ProductCard.new offers: validoffer, web_url: 'http://brand.com/product/123', name: 'product test', colors: invalidcolors

  it "invalidcolors" do
    product_card.valid?.should eql false
    expect {product_card.to_json}.to raise_error(RuntimeError)
  end
end

describe '#validcolors' do
  validprice = PairSDK::Price.new price: 4
  validoffer = PairSDK::Offer.new price: validprice
  validcolor = 'OffWhite'
  validcolors = ['Beige', 'Black', 'Blue', 'Bronze', 'Brown', 'Gold', 'Green', 'Gray', 'Metallic', 'Multicolored', 'OffWhite', 'Orange', 'Pink', 'Purple', 'Red', 'Silver', 'Transparent', 'Turquoise', 'White', 'Yellow']
  
  product_card = PairSDK::ProductCard.new offers: validoffer, web_url: 'http://brand.com/product/123', name: 'product test', colors: validcolor
  product_card2 = PairSDK::ProductCard.new offers: validoffer, web_url: 'http://brand.com/product/123', name: 'product test', colors: validcolors

  it "validcolors" do
    product_card.valid?.should eql true
    product_card2.valid?.should eql true
    expect {product_card.to_json}.not_to raise_error
  end
end

#todo add tests to verify errors and validationcontext are not in the as_json hash

end