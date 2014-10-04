require 'spec_helper'

describe WildcardPair::Product do

describe '#nil_gender' do
  product = WildcardPair::Product.new name: 'product name', gender: nil
  it "nil_gender" do
    product.valid?.should eql true
  end
end

describe '#invalid_gender' do
  product = WildcardPair::Product.new name: 'product name', gender: 'malefemale'
  it "invalid_gender" do
    product.valid?.should eql false
  end
end

describe '#gender' do
  product = WildcardPair::Product.new name: 'product name', gender: 'unisex'
  it "gender" do
    product.valid?.should eql true
    product.gender.should eql 'unisex'
  end
end

end