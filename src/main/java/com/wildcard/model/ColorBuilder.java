package com.wildcard.model;

public class ColorBuilder {
    protected String displayName;
    protected String swatchLink;
    protected String value;
    protected MappingColor mappingColor;
    
    public ColorBuilder(){
        
    }
    
    public ColorBuilder displayName(String displayName){
        this.displayName = displayName;
        return this;
    }
    
    public ColorBuilder swatchLink(String swatchLink){
        this.swatchLink = swatchLink;
        return this;
    }
    
    public ColorBuilder value(String value){
        this.value = value;
        return this;
    }
    
    public ColorBuilder mappingColor(MappingColor mappingColor){
        this.mappingColor = mappingColor;
        return this;
    }
    
    public Color build(){
        return new Color(this);
    }
}
