module HashMappable
  def map_hash(value, target)
    if value.is_a? Hash
      target.attributes = value 
      return target
    else
      return value
    end 
  end
end