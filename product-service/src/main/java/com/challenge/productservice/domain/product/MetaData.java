
package com.challenge.productservice.domain.product;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MetaData {

    @JsonProperty("page_title")
    private String pageTitle;
    @JsonProperty("site_name")
    private String siteName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("keywords")
    private String keywords;
    @JsonProperty("canonical")
    private String canonical;

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getCanonical() {
        return canonical;
    }

    public void setCanonical(String canonical) {
        this.canonical = canonical;
    }

}
