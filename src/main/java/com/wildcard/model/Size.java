package com.wildcard.model;

public class Size {
    private final String value;
    private final String name;
    
    public Size(String name, String value){
        this.name = name;
        this.value = value;
    }
    
    public String getName(){
        return name;
    }
    
    public String getValue(){
        return value;
    }
}
