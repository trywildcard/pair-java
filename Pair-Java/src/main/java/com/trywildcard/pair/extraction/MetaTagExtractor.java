package com.trywildcard.pair.extraction;

import com.google.common.collect.ImmutableSet;
import com.trywildcard.pair.exception.CardBuilderException;
import com.trywildcard.pair.util.HtmlParserUtil;
import com.trywildcard.pair.util.HttpAgent;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import static com.trywildcard.pair.extraction.MetaTagModel.*;

/**
 * Created by karthiksenthil on 11/1/14.
 */
public class MetaTagExtractor {

    private static final Set<String> EXTRACTABLE_TWITTER_META_TAG_ATTRIBUTES
            = ImmutableSet.of("twitter:title", "twitter:description", "twitter:image", "twitter:image:src",
            "twitter:app:url:iphone", "twitter:app:url:googleplay", "twitter:player", "twitter:player:width",
            "twitter:player:height");

    private static final Set<String> EXTRACTABLE_OG_META_TAG_ATTRIBUTES
            = ImmutableSet.of("og:title", "og:description", "og:image", "og:price:amount", "product:price:amount",
            "og:video", "og:video:width", "og:video:height");

    private static final Set<String> EXTRACTABLE_AL_META_TAG_ATTRIBUTES
            = ImmutableSet.of("al:android:url", "al:ios:url");

    private static String getMetaTagKey(String attribute) {

        if (attribute == null) {
            return null;
        }

        switch (attribute.toLowerCase()) {
            case "twitter:title":
                return TITLE_DATA_KEY;
            case "twitter:description":
                return DESCRIPTION_DATA_KEY;
            case "twitter:image":
                return IMAGE_URL_DATA_KEY;
            case "twitter:image:src":
                return IMAGE_URL_DATA_KEY;
            case "og:title":
                return TITLE_DATA_KEY;
            case "og:description":
                return DESCRIPTION_DATA_KEY;
            case "og:image":
                return IMAGE_URL_DATA_KEY;
            case "og:price:amount":
                return PRICE_DATA_KEY;
            case "product:price:amount":
                return PRICE_DATA_KEY;
            case "twitter:app:url:iphone":
                return APP_LINK_IOS;
            case "twitter:app:url:googleplay":
                return APP_LINK_ANDROID;
            case "al:ios:url":
                return APP_LINK_IOS;
            case "al:android:url":
                return APP_LINK_ANDROID;
            case "og:video":
                return VIDEO_URL_DATA_KEY;
            case "og:video:width":
                return VIDEO_WIDTH_DATA_KEY;
            case "og:video:height":
                return VIDEO_HEIGHT_DATA_KEY;
            case "twitter:player":
                return VIDEO_URL_DATA_KEY;
            case "twitter:player:width":
                return VIDEO_WIDTH_DATA_KEY;
            case "twitter:player:height":
                return VIDEO_HEIGHT_DATA_KEY;
            default:
                return null;
        }
    }

    public static MetaTagModel getMetaTags(URL webUrl) throws CardBuilderException {
        try {
            Map<String, String> metaTagsAndValues = new HashMap<String, String>();

            HttpAgent httpAgent = new HttpAgent();
            String htmlContent = httpAgent.get(webUrl.toString());

            //lets store htmlcontent - may be used for article or review cards
            metaTagsAndValues.put(HTML_DATA_KEY, htmlContent);

            Document htmlDocumentModel = HtmlParserUtil.getHtmlDocumentModel(htmlContent);
            NodeList metaTags = htmlDocumentModel.getElementsByTagName("meta");

            for (int i = 0; i < metaTags.getLength(); i++) {
                Node node = metaTags.item(i);
                NamedNodeMap attributes = node.getAttributes();

                String key = getMetaKey(attributes);
                if (key == null || metaTagsAndValues.containsKey(getMetaTagKey(key))) {
                    continue;
                }

                if (EXTRACTABLE_OG_META_TAG_ATTRIBUTES.contains(key.toLowerCase())) {
                    String content = getMetaValue(attributes);
                    if (content != null && !content.isEmpty()) {
                        metaTagsAndValues.put(getMetaTagKey(key), content);
                    }
                } else if (EXTRACTABLE_TWITTER_META_TAG_ATTRIBUTES.contains(key.toLowerCase())) {
                    String content = getMetaValue(attributes);
                    if (content != null && !content.isEmpty()) {
                        metaTagsAndValues.put(getMetaTagKey(key), content);
                    }
                } else if (EXTRACTABLE_AL_META_TAG_ATTRIBUTES.contains(key.toLowerCase())) {
                    String content = getMetaValue(attributes);
                    if (content != null && !content.isEmpty()) {
                        metaTagsAndValues.put(getMetaTagKey(key), content);
                    }
                }
            }

            return new MetaTagModel(metaTagsAndValues);
        } catch (URISyntaxException use) {
            return new MetaTagModel(Collections.EMPTY_MAP);
        } catch (IOException ioe) {
            return new MetaTagModel(Collections.EMPTY_MAP);
        } catch (RuntimeException rte) {
            return new MetaTagModel(Collections.EMPTY_MAP);
        }
    }

    /**
     * @param attributes - the node map for the meta tag
     * @return the value of the key
     */
    private static String getMetaKey(NamedNodeMap attributes) {
        Node keyAttribute = attributes.getNamedItem("name");
        if (keyAttribute == null) {
            keyAttribute = attributes.getNamedItem("property");
        }
        if (keyAttribute == null) {
            return null;
        }
        return keyAttribute.getNodeValue();
    }

    /**
     * @param attributes - the node map for the meta tag
     * @return the value of the value
     */
    private static String getMetaValue(NamedNodeMap attributes) {
        Node contentAttribute = attributes.getNamedItem("content");
        if (contentAttribute == null) {
            contentAttribute = attributes.getNamedItem("value");
        }
        if (contentAttribute == null) {
            return null;
        }
        return contentAttribute.getNodeValue();
    }
}
