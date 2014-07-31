require 'spec_helper'

describe WildcardPair::ProductCard do

before :each do
  @price = WildcardPair::Price.new price: 5.00
    @validoffer = WildcardPair::Offer.new price: @price
    @validoffer2 = WildcardPair::Offer.new price: @price, gender: 'male', availability: 'InStock'
    @validoffers = [@validoffer, @validoffer2]
end

describe '#new' do
  it "takes returns a ProductCard object" do
    product_card = WildcardPair::ProductCard.new offers: @validoffer, web_url: 'http://brand.com/product/123', name: 'product test'
    product_card.should be_an_instance_of WildcardPair::ProductCard
    product_card.valid?.should eql true
    product_card.offers.is_a?(Array).should eql true
    product_card.card_type.should eql 'product'
    expect {product_card.to_json}.not_to raise_error
  end
end

describe '#new2' do
  it "takes returns a ProductCard object" do
    product_card = WildcardPair::ProductCard.new offers: @validoffers, web_url: 'http://brand.com/product/123', name: 'product test'
    product_card.should be_an_instance_of WildcardPair::ProductCard
    product_card.valid?.should eql true
    product_card.offers.is_a?(Array).should eql true
    product_card.offers.should eql @validoffers
    product_card.card_type.should eql 'product'
    expect {product_card.to_json}.not_to raise_error
  end
end

describe '#no_web_url' do
  product_card = WildcardPair::ProductCard.new offers: @validoffers, name: 'product test', web_url: nil
  it "no web url" do
    product_card.valid?.should eql false
  end
end

describe '#web_url' do
  price = WildcardPair::Price.new price: 5.0
  offer = WildcardPair::Offer.new price: price
  product_card33 = WildcardPair::ProductCard.new offers: offer, web_url: 'http://brand.com/product/123', name: 'product test' 
  it "web url" do
    product_card33.valid?.should eql true
    product_card33.web_url.should eql 'http://brand.com/product/123'
  end
end


describe '#no_name' do
  product_card = WildcardPair::ProductCard.new offers: @validoffers, web_url: 'http://brand.com/product/123'
  it "no name" do
    product_card.valid?.should eql false
  end
end

describe '#name' do
  price = WildcardPair::Price.new price: 5.0
  offer = WildcardPair::Offer.new price: price
  product_card33 = WildcardPair::ProductCard.new offers: offer, web_url: 'http://brand.com/product/123', name: 'product test' 
  it "name" do
    product_card33.valid?.should eql true
    product_card33.name.should eql 'product test' 
  end
end

describe '#nooffers' do
  product_card = WildcardPair::ProductCard.new offers: nil, web_url: 'http://brand.com/product/123', name: 'product test' 

  it "name" do
    product_card.valid?.should eql false
    expect {product_card.to_json}.to raise_error(RuntimeError)
  end
end

describe '#invalidoffers' do
  invalidprice = WildcardPair::Price.new price: -4
  invalidoffer = WildcardPair::Offer.new price: invalidprice
  product_card = WildcardPair::ProductCard.new offers: invalidoffer, web_url: 'http://brand.com/product/123', name: 'product test' 

  validprice = WildcardPair::Price.new price: 4
  validoffer = WildcardPair::Offer.new price: validprice
  invalidoffers = [validoffer, nil]
  product_card2 = WildcardPair::ProductCard.new offers: invalidoffers, web_url: 'http://brand.com/product/123', name: 'product test' 
  it "name" do
    product_card.valid?.should eql false
    product_card2.valid?.should eql false
    expect {product_card.to_json}.to raise_error(RuntimeError)
    expect {product_card2.to_json}.to raise_error(RuntimeError)
  end
end

describe "product card" do
  it "is built from json" do
    json = File.read("spec/fixtures/example_product_card.json")
    product_card = WildcardPair::ProductCard.new
    product_card.from_json(json)
    product_card.valid?
    expect(product_card.valid?).to be(true)
    expect(JSON.parse(product_card.to_json)).to eq(JSON.parse(json)) 
  end
end

end