package com.trywildcard.pair.model.product;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.util.DummyOffer;
import com.trywildcard.pair.util.DummyProduct;
import com.trywildcard.pair.util.TestUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ProductCardTest {

    ObjectMapper mapper = TestUtil.getObjectMapper();
    private static DummyOffer dummyOffer;
    private static DummyProduct dummyProduct;

    @BeforeClass
    public static void prepare() throws ParseException, CardBuilderException {
        dummyOffer = new DummyOffer();
        dummyProduct = new DummyProduct();
    }

    private void testMinimalCardAttributes(ProductCard card){
        Assert.assertEquals("Product Name should match", dummyProduct.name, card.getProduct().getName());
        Assert.assertEquals("Web url should match", dummyProduct.webUrl, card.getWebUrl().toString());

        Assert.assertEquals("Price should match", dummyOffer.price.getPrice(), card.getOffers().get(0).getPrice().getPrice());
    }

    private ProductCard buildMinimalProductCard() throws CardBuilderException {

        Offer offer = new OfferBuilder(dummyOffer.price).build();
        Product product = new ProductBuilder(dummyProduct.name).build();

        ProductCard productCard = new ProductCard(product, offer, dummyProduct.webUrl);

        return productCard;
    }

    @Test
    public void testMinimalProductCard() throws JsonProcessingException, CardBuilderException {
        ProductCard card = buildMinimalProductCard();
        testMinimalCardAttributes(card);
    }


    @Test
    public void testMinimalProductWithMinimalConstructor() throws CardBuilderException {
        Product product = new ProductBuilder(dummyProduct.name).build();
        ProductCard card = new ProductCard(product, dummyOffer.price.getPrice(), dummyProduct.webUrl);
        testMinimalCardAttributes(card);
    }

    private Offer buildExtensiveOffer() throws CardBuilderException {
        OfferBuilder builder = new OfferBuilder(dummyOffer.price);
        builder.originalPrice(dummyOffer.originalPrice);
        builder.description(dummyOffer.description);
        builder.availability(dummyOffer.availability);
        builder.shippingCost(dummyOffer.shippingCost);
        builder.quantity(dummyOffer.quantity);
        builder.saleStartDate(dummyOffer.saleStartDate);
        builder.saleEndDate(dummyOffer.saleEndDate);
        builder.expirationDate(dummyOffer.expirationDate);
        builder.geographicAvailability(dummyOffer.geographicAvailability);
        builder.gender(dummyOffer.gender);
        builder.weight(dummyOffer.weight);
        builder.weightUnits(dummyOffer.weightUnits);
        builder.offerId(dummyOffer.offerId);

        return builder.build();
    }


    @Test
    public void testExtensiveWriteAsJsonMethod() throws JsonParseException, JsonMappingException, IOException, CardBuilderException {
        String inputString = TestUtil.readResourceAsString("example_product_card.json");
        ProductCard fixtureCard = mapper.readValue(inputString,  ProductCard.class);
        Product generatedProduct = new ProductBuilderTest().buildExtensiveProduct();
        Offer generatedOffer = buildExtensiveOffer();

        ProductCard generatedCard = new ProductCard(generatedProduct, generatedOffer, dummyProduct.webUrl);

        Assert.assertEquals(mapper.writeValueAsString(fixtureCard), generatedCard.writeAsJsonString());
    }

    @Test
    public void testMinimalWriteAsJsonMethod() throws JsonParseException, JsonMappingException, IOException, CardBuilderException {
        String inputString = TestUtil.readResourceAsString("example_minimal_product_card.json");
        ProductCard fixtureCard = mapper.readValue(inputString,  ProductCard.class);
        ProductCard generatedCard = buildMinimalProductCard();

        Assert.assertEquals(mapper.writeValueAsString(fixtureCard), generatedCard.writeAsJsonString());
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithNullName() throws CardBuilderException {
        ProductCard card = new ProductCard(null, dummyOffer.price.getPrice(), dummyProduct.webUrl);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithEmptyOffersList() throws CardBuilderException {
        List<Offer> emptyList = new ArrayList<Offer>();
        Product product = new ProductBuilder(dummyProduct.name).build();
        ProductCard card = new ProductCard(product, emptyList, dummyProduct.webUrl);
    }

    @Test(expected = CardBuilderException.class)
    public void isInvalidWithOnlyNullOffersListItems() throws CardBuilderException {
        List<Offer> offers = new ArrayList<Offer>();
        offers.add(null);
        offers.add(null);

        Product product = new ProductBuilder(dummyProduct.name).build();

        ProductCard card = new ProductCard(product, offers, dummyProduct.webUrl);
    }

    @Test
    public void isValidWithSomeNullOffersListItems() throws CardBuilderException {
        List<Offer> offers = new ArrayList<Offer>();
        offers.add(null);
        offers.add(new OfferBuilder(12.99f).build());
        offers.add(null);

        Product product = new ProductBuilder(dummyProduct.name).build();

        try {
            ProductCard card = new ProductCard(product, offers, dummyProduct.webUrl);
            assertEquals("Offer size should only be 1", 1, card.getOffers().size());
        } catch (CardBuilderException e) {
            e.printStackTrace();
        }
    }
}
