package com.trywildcard.pair.model.media;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sun.xml.internal.ws.api.PropertySet;

/** Abstract Class Supporting all Media **/
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Image.class, name = "image") })
public abstract class Media {

    private Media() {}
    public Media(MediaType type) {this.type = type;}

    @PropertySet.Property("type")
    protected MediaType type;

    public abstract MediaType getType();

}
