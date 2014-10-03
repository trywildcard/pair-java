package com.trywildcard.pair.model.product;

import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by karthiksenthil on 10/3/14.
 */
public class Product {

    private final String merchant;
    private final String brand;
    private final String description;
    private final List<ProductColor> colors;
    private final List<URL> images;
    private final Float rating;
    private final Float ratingScale;
    private final Integer ratingCount;
    private final List<URL> relatedItems;
    private final List<URL> referencedItems;
    private final Map<String, String> sizes;
    private final List<String> options;
    private final String model;
    private final String appLinkIos;
    private final String appLinkAndroid;


}
