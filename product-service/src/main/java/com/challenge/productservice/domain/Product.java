
package com.challenge.productservice.domain;

import java.util.List;

public class Product {

    private String id;
    private String name;
    private String modelNumber;
    private String productType;
    private MetaData metaData;
    private List<ViewList> viewList;
    private PricingInformation pricingInformation;
    private AttributeList attributeList;
    private Callouts callouts;
    private ProductDescription productDescription;
    private List<ProductLinkList> productLinkList;
    private List<BreadcrumbList> breadcrumbList;

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

}
