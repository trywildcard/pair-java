package com.trywildcard.pair.util;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by karthiksenthil on 11/1/14.
 */
public class HttpAgent {

    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.120 Safari/537.36";

    public HttpAgent() {
    }

    public String get(String urlStr) throws URISyntaxException, IOException {

        if (urlStr.indexOf(' ') != -1) {
            urlStr = urlStr.replace(" ", "%20");
        }
        int port = 0;
        if (urlStr.contains(":")) {
            URL url = new URL(urlStr);
            port = url.getPort();
        }
        return get(urlStr, port, null, null);

    }

    public String get(String urlStr, int port) throws URISyntaxException, IOException {
        return get(urlStr, port, null, null);
    }

    public String get(String urlStr, int port, String basicAuthUsername, String basicAuthPassword) throws URISyntaxException, IOException {
        URL url = new java.net.URL(urlStr);
        url.getProtocol();
        String host = url.getHost();
        String path = url.getPath();
        String protocol = Strings.isNullOrEmpty(url.getProtocol()) ? "http" : url.getProtocol();
        String query = url.getQuery();
        Map<String, String> params = Maps.newHashMap();
        if (query != null) {
            ArrayList<String> queries = Lists.newArrayList(query.split("&"));
            for (String q : queries) {
                String[] kv = q.split("=");
                if (kv.length == 2) {
                    params.put(kv[0], kv[1]);
                }
            }
        }

        return get(protocol, basicAuthUsername, basicAuthPassword, host, port, path, params);
    }

    protected class urlRedirectCapture extends DefaultRedirectStrategy {
        @Override
        public boolean isRedirected(HttpRequest request, HttpResponse response, HttpContext context) throws ProtocolException {
            boolean superResult = super.isRedirected(request, response, context);
            if (superResult) {
                Header[] headers = response.getHeaders("Location");
                for (Header header : headers) {
                    context.setAttribute("redirectUrl", header.getValue());
                }
            }

            return superResult;
        }
    }

    private static class Response implements HttpAgentResponse {
        private String body;
        private String url;

        public Response(String body, String url) {
            this.body = body;
            this.url = url;
        }

        public String getBody() {
            return this.body;
        }

        public String getUrl() {
            return this.url;
        }
    }

    public interface HttpAgentResponse {
        public String getBody();

        public String getUrl();
    }

    public static HttpAgentResponse makeResponse(String body, String url) {
        return new HttpAgent.Response(body, url);
    }

    public String get(String protocol, String basicAuthUsername, String basicAuthPassword, String host, int port,
                      String path, Map<String, String> params) throws URISyntaxException, IOException {
        HttpAgentResponse resp = getResponse(protocol, basicAuthUsername, basicAuthPassword, host, port, path, params, true);
        return resp.getBody();
    }

    public HttpAgentResponse getResponse(String protocol, String basicAuthUsername, String basicAuthPassword,
                                         String host, int port, String path, Map<String, String> params, Boolean retry) throws URISyntaxException, IOException {
        HttpClient httpclient = null;

        RedirectStrategy redirect = new urlRedirectCapture();
        HttpClientBuilder httpBuilder = HttpClientBuilder.create()
                .setRedirectStrategy(redirect);
        if (!retry) {
            httpBuilder.disableAutomaticRetries();
        }
        httpclient = httpBuilder.build();

        host = host.replaceFirst("(^http://|^https://)", "");
        URIBuilder builder = new URIBuilder()
                .setScheme(protocol)
                .setHost(host);
        if (path != null) {
            builder.setPath(path);
        }

        if (!Strings.isNullOrEmpty(basicAuthPassword) && !Strings.isNullOrEmpty(basicAuthPassword)) {
            builder.setUserInfo(basicAuthUsername, basicAuthPassword);
        }

        if (port > 0) {
            builder.setPort(port);
        }

        if (params != null) {
            for (Map.Entry<String, String> e : params.entrySet()) {
                String val = URLDecoder.decode(e.getValue(), "UTF-8");
                builder.setParameter(e.getKey(), val);
            }
        }

        URI uri = builder.build();
        HttpGet httpget = new HttpGet(uri);

        HttpContext context = new BasicHttpContext();
        try {
            httpget.setHeader("User-Agent", USER_AGENT);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity httpEntity = response.getEntity();

            Header contentEncodingHeader = httpEntity.getContentEncoding();
            String encoding;
            if (contentEncodingHeader != null) {
                encoding = contentEncodingHeader.getValue();
            } else {
                encoding = "UTF-8";
            }

            String responseEntity = EntityUtils.toString(httpEntity, encoding);
            String resultUrl = httpget.getURI().toString();
            String redirectUrl = (String) context.getAttribute("redirectUrl");
            if (redirectUrl != null) {
                resultUrl = redirectUrl;
            }
            return makeResponse(responseEntity, resultUrl);
        } catch (HttpResponseException e) {
            throw new IllegalArgumentException("URL Fetch failed: ", e);
        }
    }

}
