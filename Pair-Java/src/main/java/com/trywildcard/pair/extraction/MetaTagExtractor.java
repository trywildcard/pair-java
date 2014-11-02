package com.trywildcard.pair.extraction;

import com.google.common.collect.ImmutableSet;
import com.trywildcard.pair.util.HtmlParserUtil;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URL;
import java.util.*;

import static com.trywildcard.pair.extraction.MetaTagModel.*;

/**
 * Created by karthiksenthil on 11/1/14.
 */
public class MetaTagExtractor {

    private static final Set<String> EXTRACTABLE_META_TAG_ATTRIBUTES
            = ImmutableSet.of("twitter:title", "twitter:description", "twitter:image", "twitter:image:src");

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
            default:
                return null;
        }
    }

    public static MetaTagModel getMetaTags(URL webUrl) {

        Map<String, String> metaTagsAndValues = new HashMap<String, String>();

        Document htmlDocumentModel = HtmlParserUtil.getHtmlDocumentModel(webUrl);
        NodeList metaTags = htmlDocumentModel.getElementsByTagName("meta");

        for (int i = 0; i < metaTags.getLength(); i++) {
            Node node = metaTags.item(i);
            NamedNodeMap attributes = node.getAttributes();

            String key = getMetaKey(attributes);
            if (key == null || metaTagsAndValues.containsKey(getMetaTagKey(key))) {
                continue;
            }

            if (EXTRACTABLE_META_TAG_ATTRIBUTES.contains(key.toLowerCase())) {
                String content = getMetaValue(attributes);
                if (content != null && !content.isEmpty()) {
                    metaTagsAndValues.put(getMetaTagKey(key), content);
                }
            }
        }

        return new MetaTagModel(metaTagsAndValues);
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
