package com.trywildcard.pair.extraction;

import com.trywildcard.pair.exception.CardBuilderException;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.Map;

public class MetaTagModel {

    private Map<String, String> metaTagsAndValues;

    public static final String HTML_DATA_KEY = "html";
    public static final String TITLE_DATA_KEY = "title";
    public static final String DESCRIPTION_DATA_KEY = "description";
    public static final String IMAGE_URL_DATA_KEY = "imageUrl";
    public static final String PRICE_DATA_KEY = "price";
    public static final String APP_LINK_IOS = "appLinkIos";
    public static final String APP_LINK_ANDROID = "appLinkAndroid";
    public static final String VIDEO_URL_DATA_KEY = "videoUrl";
    public static final String VIDEO_WIDTH_DATA_KEY = "videoWidth";
    public static final String VIDEO_HEIGHT_DATA_KEY = "videoHeight";


    public MetaTagModel(Map<String, String> metaTagsAndValues) throws CardBuilderException {

        if (metaTagsAndValues == null) {
            throw new CardBuilderException("Input must not be null and must be a valid map");
        }

        this.metaTagsAndValues = metaTagsAndValues;
    }

    private String checkAndReturnValue(String dataKey) {
        if (metaTagsAndValues.containsKey(dataKey)) {
            return StringEscapeUtils.unescapeHtml4(metaTagsAndValues.get(dataKey));
        }

        return null;
    }

    public String getHtmlContent() {
        return checkAndReturnValue((HTML_DATA_KEY));
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

    public String getAppLinkIos() {
        return checkAndReturnValue(APP_LINK_IOS);
    }

    public String getAppLinkAndroid() {
        return checkAndReturnValue(APP_LINK_ANDROID);
    }

    public String getVideoUrl() {
        return checkAndReturnValue(VIDEO_URL_DATA_KEY);
    }

    public String getVideoWidth() {
        return checkAndReturnValue(VIDEO_WIDTH_DATA_KEY);
    }

    public String getVideoHeight() {
        return checkAndReturnValue(VIDEO_HEIGHT_DATA_KEY);
    }

    Integer getNumberOfMetaTags() {
        return this.metaTagsAndValues.keySet().size();
    }
}
