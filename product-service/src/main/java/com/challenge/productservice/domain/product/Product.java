
package com.challenge.productservice.domain.product;

import java.util.List;

import com.challenge.productservice.domain.review.ProductReview;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("model_number")
    private String modelNumber;
    @JsonProperty("product_type")
    private String productType;
    @JsonProperty("meta_data")
    private MetaData metaData;
    @JsonProperty("view_list")
    private List<ViewList> viewList;
    @JsonProperty("pricing_information")
    private PricingInformation pricingInformation;
    @JsonProperty("attribute_list")
    private AttributeList attributeList;
    @JsonProperty("callouts")
    private Callouts callouts;
    @JsonProperty("product_description")
    private ProductDescription productDescription;
    @JsonProperty("product_link_list")
    private List<ProductLinkList> productLinkList;
    @JsonProperty("breadcrumb_list")
    private List<BreadcrumbList> breadcrumbList ;
    @JsonProperty("product_review")
    private ProductReview productReview;

    public Product() {
    }

    public Product(String id, String name, String modelNumber) {
        this.id = id;
        this.name = name;
        this.modelNumber = modelNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<ViewList> getViewList() {
        return viewList;
    }

    public void setViewList(List<ViewList> viewList) {
        this.viewList = viewList;
    }

    public PricingInformation getPricingInformation() {
        return pricingInformation;
    }

    public void setPricingInformation(PricingInformation pricingInformation) {
        this.pricingInformation = pricingInformation;
    }

    public AttributeList getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(AttributeList attributeList) {
        this.attributeList = attributeList;
    }

    public Callouts getCallouts() {
        return callouts;
    }

    public void setCallouts(Callouts callouts) {
        this.callouts = callouts;
    }

    public ProductDescription getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
    }

    public List<ProductLinkList> getProductLinkList() {
        return productLinkList;
    }

    public void setProductLinkList(List<ProductLinkList> productLinkList) {
        this.productLinkList = productLinkList;
    }

    public List<BreadcrumbList> getBreadcrumbList() {
        return breadcrumbList;
    }

    public void setBreadcrumbList(List<BreadcrumbList> breadcrumbList) {
        this.breadcrumbList = breadcrumbList;
    }

    public ProductReview getProductReview() {
        return productReview;
    }

    public void setProductReview(ProductReview productReview) {
        this.productReview = productReview;
    }
}
