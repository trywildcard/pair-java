package com.trywildcard.pair.model.search;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.trywildcard.pair.model.product.Price;
import java.net.URL;

/**
 * Product search result. Several of these are embedded in a <code>ProductSearchResults</code> card.
 */
@JsonDeserialize(builder = ProductSearchResultBuilder.class)
public final class ProductSearchResult {
    /* Required fields */
    private final String name;
    private final Price price;
    private final URL productCardAddress;
    private final URL imageUrl;

    /**
     * Construct a product search result using a <code>ProductSearchResultBuilder</code>.
     * @param builder the builder.
     */
    public ProductSearchResult(ProductSearchResultBuilder builder){
        this.name = builder.name;
        this.price = builder.price;
        this.productCardAddress = builder.productCardAddress;
        this.imageUrl = builder.imageUrl;
    }

    public String getName(){
        return name;
    }

    public Price getPrice(){
        return price;
    }

    public URL getProductCardAddress(){
        return productCardAddress;
    }

    public URL getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductSearchResult that = (ProductSearchResult) o;

        if (imageUrl != null ? !imageUrl.equals(that.imageUrl) : that.imageUrl != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (productCardAddress != null ? !productCardAddress.equals(that.productCardAddress) : that.productCardAddress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (productCardAddress != null ? productCardAddress.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        return result;
    }
}
