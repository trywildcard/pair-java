package com.trywildcard.pair.util;

import com.trywildcard.pair.util.HttpAgent;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by karthiksenthil on 11/1/14.
 */
public class HtmlParserUtil {

    public static Document getHtmlDocumentModel(URL webUrl) {

        try {
            HttpAgent httpAgent = new HttpAgent();
            String htmlContent = httpAgent.get(webUrl.toString());

            TagNode tagNode = new HtmlCleaner().clean(htmlContent);
            Document doc;
            try {
                doc = new DomSerializer(new CleanerProperties()).createDOM(tagNode);
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            }
            return doc;
        } catch (URISyntaxException use) {
            return null;
        } catch (IOException ioe) {
            return null;
        }
    }
}
