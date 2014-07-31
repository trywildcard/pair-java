require 'spec_helper'

describe WildcardPair::Color do

describe '#new' do
  it "blank color object returns a Color object" do
    color = WildcardPair::Color.new 
    color.should be_an_instance_of WildcardPair::Color
    color.valid?.should eql true
    expect {color.to_json}.not_to raise_error
  end
end

describe '#invalid_color' do
  color = WildcardPair::Color.new display_name: "orange_red", swatch_link: "http://orange_red.com/image.jpg", value: "RGB(1,1,1)", mapping_color: "orange_red"
  it "invalid_color " do
    color.valid?.should eql false
  end
end

describe '#valid_color' do
  color = WildcardPair::Color.new display_name: "mint", swatch_link: "http://www.examplestore.com/swatches/mint.jpg", value: "RGB(62, 180, 137)", mapping_color: "green"
  it "valid_color " do
    color.valid?.should eql true
  end
end

end