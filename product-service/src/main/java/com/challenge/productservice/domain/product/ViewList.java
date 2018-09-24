
package com.challenge.productservice.domain.product;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ViewList {

    @JsonProperty("type")
    private String type;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("source")
    private String source;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
