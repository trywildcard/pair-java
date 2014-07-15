require 'spec_helper'

describe PairSDK::ProductSearchCard do

before :each do
  price = PairSDK::Price.new price: 5
  price2 = PairSDK::Price.new price: 7.99
    @validsearchresult = PairSDK::ProductSearchResult.new price: price, name: 'ProductResult', product_card_url: 'http://brand.com/product/123'
    @validsearchresult2 = PairSDK::ProductSearchResult.new price: price2, name: 'Product Result', product_card_url: 'http://brand.com/product/123', image_url: 'http://brand.com/product/123/image.jpg'
    @validsearchresults = [@validsearchresult, @validsearchresult2]
end

describe '#new' do
  it "takes returns a ProductSearchCard object" do
    product_search_card = PairSDK::ProductSearchCard.new search_results: @validsearchresult, total_results: 2
    product_search_card.should be_an_instance_of PairSDK::ProductSearchCard
    product_search_card.valid?.should eql true
    product_search_card.search_results.is_a?(Array).should eql true
    product_search_card.card_type.should eql 'product_search'
    expect {product_search_card.to_json}.not_to raise_error
  end
end

describe '#new2' do
  it "takes returns a ProductCard object" do
    product_search_card = PairSDK::ProductSearchCard.new search_results: @validsearchresults, total_results: 2
    product_search_card.should be_an_instance_of PairSDK::ProductSearchCard
    product_search_card.valid?.should eql true
    product_search_card.search_results.is_a?(Array).should eql true
    product_search_card.card_type.should eql 'product_search'
    expect {product_search_card.to_json}.not_to raise_error
  end
end

describe '#no_total_results' do
  it "no total results" do
    product_search_card = PairSDK::ProductSearchCard.new search_results: @validsearchresults
    product_search_card2 = PairSDK::ProductSearchCard.new search_results: @validsearchresults, total_results: nil
    
    product_search_card.valid?.should eql false
    product_search_card2.valid?.should eql false
    expect {product_search_card.to_json}.to raise_error(RuntimeError)
  end
end

describe '#invalid_total_results' do
  it "invalid_total_results" do
    product_search_card = PairSDK::ProductSearchCard.new search_results: @validsearchresults, total_results: -10
    
    product_search_card.valid?.should eql false
    expect {product_search_card.to_json}.to raise_error(RuntimeError)
  end
end

describe '#total_results' do
  it "total_results" do
    product_search_card = PairSDK::ProductSearchCard.new search_results: @validsearchresults, total_results: 2
    product_search_card.valid?.should eql true
    product_search_card.total_results.should eql 2
    expect {product_search_card.to_json}.not_to raise_error
  end
end

describe '#nosearchresults' do
  product_search_card = PairSDK::ProductSearchCard.new search_results: nil, total_results: 2
  it "nosearchresults" do
    product_search_card.valid?.should eql false
    expect {product_search_card.to_json}.to raise_error(RuntimeError)
  end
end

describe '#invalidsearchresults' do
  invalidprice = PairSDK::Price.new price: -4.50, currency: "USD"
  invalidSearchResult = PairSDK::ProductSearchResult.new price: invalidprice, name: 'ProductResult', product_card_url: 'http://brand.com/product/123'

  invalidsearchresults1 = [@validsearchresult, @validsearchresult2, invalidSearchResult]
  invalidsearchresults2 = [@validsearchresult, @validsearchresult2, nil]

  product_search_card = PairSDK::ProductSearchCard.new search_results: invalidsearchresults1, total_results: 3
  product_search_card2 = PairSDK::ProductSearchCard.new search_results: invalidsearchresults2, total_results: 3

  it "invalidsearchresults" do
    product_search_card.valid?.should eql false
    product_search_card2.valid?.should eql false
    expect {product_search_card.to_json}.to raise_error(RuntimeError)
    expect {product_search_card2.to_json}.to raise_error(RuntimeError)
  end
end

end