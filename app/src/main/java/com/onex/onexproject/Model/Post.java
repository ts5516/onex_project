package com.onex.onexproject.Model;

public class Post {
    private String uri;
    private String size;
    private String tag;

    public Post(){}
    public Post(String uri, String size, String tag) {
        this.uri = uri;
        this.size = size;
        this.tag = tag;
    }
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


}
