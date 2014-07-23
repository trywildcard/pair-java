require 'spec_helper'

describe "product card" do
  it "is built from json" do
    json = File.read("spec/fixtures/example_product_card.json")
    product_card = WildcardPair::ProductCard.new
    product_card.from_json(json)
    product_card.valid?
    expect(product_card.valid?).to be(true)
  end
end