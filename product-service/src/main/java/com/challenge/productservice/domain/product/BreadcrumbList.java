
package com.challenge.productservice.domain.product;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BreadcrumbList {

    @JsonProperty("text")
    private String text;
    @JsonProperty("link")
    private String link;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
