package com.trywildcard.pair.extraction;

import java.util.Map;

public class MetaTagModel {

    private Map<String, String> metaTagsAndValues;

    public static final String TITLE_DATA_KEY = "title";
    public static final String DESCRIPTION_DATA_KEY = "description";
    public static final String IMAGE_URL_DATA_KEY = "imageUrl";
    public static final String PRICE_DATA_KEY = "price";

    public MetaTagModel(Map<String, String> metaTagsAndValues) {
        this.metaTagsAndValues = metaTagsAndValues;
    }

    private String checkAndReturnValue(String dataKey) {
        if (metaTagsAndValues.containsKey(dataKey)) {
            return metaTagsAndValues.get(dataKey);
        }

        return null;
    }

    public String getTitle() {
        return checkAndReturnValue(TITLE_DATA_KEY);
    }

    public String getImageUrl() {
        return checkAndReturnValue(IMAGE_URL_DATA_KEY);
    }

    public String getDescription() {
        return checkAndReturnValue(DESCRIPTION_DATA_KEY);
    }

    public String getPrice() {
        return checkAndReturnValue(PRICE_DATA_KEY);
    }

}
