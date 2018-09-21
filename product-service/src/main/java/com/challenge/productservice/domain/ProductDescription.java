
package com.challenge.productservice.domain;

import java.util.List;

public class ProductDescription {

    private String title;
    private String subtitle;
    private String text;
    private List<String> usps = null;
    private DescriptionAssets descriptionAssets;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getUsps() {
        return usps;
    }

    public void setUsps(List<String> usps) {
        this.usps = usps;
    }

    public DescriptionAssets getDescriptionAssets() {
        return descriptionAssets;
    }

    public void setDescriptionAssets(DescriptionAssets descriptionAssets) {
        this.descriptionAssets = descriptionAssets;
    }

}
