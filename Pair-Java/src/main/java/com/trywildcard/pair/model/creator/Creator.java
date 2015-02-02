package com.trywildcard.pair.model.creator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trywildcard.pair.validation.ValidationTool;

import java.net.URL;
import java.util.List;

/**
 * Created by cmcewen on 2/2/15.
 */
public class Creator {

    @JsonIgnore
    protected ValidationTool v = new ValidationTool();

    private String name;
    private URL favicon;
    private String iosAppStoreUrl;
    private String androidAppStoreUrl;
    private URL url;

    /**
     * Needed for Jackson deserialization *
     */
    private Creator() { }

    public Creator(CreatorBuilder builder) {
        this.name = builder.name;
        this.favicon = builder.favicon;
        this.iosAppStoreUrl = builder.iosAppStoreUrl;
        this.androidAppStoreUrl = builder.androidAppStoreUrl;
        this.url = builder.url;
    }

    public String getName() {
        return name;
    }

    public URL getFavicon() {
        return favicon;
    }

    public String getIosAppStoreUrl() {
        return iosAppStoreUrl;
    }

    public String getAndroidAppStoreUrl() {
        return androidAppStoreUrl;
    }

    public URL getUrl() {
        return url;
    }

    /**
     * Get a list of validation errors.
     * @return the list of errors.
     */
    @JsonIgnore
    public List<String> getErrors(){
        return v.getErrors();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Creator creator = (Creator) o;

        if (androidAppStoreUrl != null ? !androidAppStoreUrl.equals(creator.androidAppStoreUrl) : creator.androidAppStoreUrl != null)
            return false;
        if (!favicon.equals(creator.favicon)) return false;
        if (iosAppStoreUrl != null ? !iosAppStoreUrl.equals(creator.iosAppStoreUrl) : creator.iosAppStoreUrl != null)
            return false;
        if (!name.equals(creator.name)) return false;
        if (url != null ? !url.equals(creator.url) : creator.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + favicon.hashCode();
        result = 31 * result + (iosAppStoreUrl != null ? iosAppStoreUrl.hashCode() : 0);
        result = 31 * result + (androidAppStoreUrl != null ? androidAppStoreUrl.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
