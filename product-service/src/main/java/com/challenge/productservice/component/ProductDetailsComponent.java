package com.challenge.productservice.component;

import com.challenge.productservice.domain.product.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;


@Component
public class ProductDetailsComponent {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${external.resources.api.endpoint.product-details}")
    private String productDetailsApi;


    private RestTemplate restTemplate;

    private ProductDetailsCookieComponent productDetailsCookieComponent;

    public ProductDetailsComponent(RestTemplate restTemplate, ProductDetailsCookieComponent productDetailsCookieComponent) {
        this.restTemplate = restTemplate;
        this.productDetailsCookieComponent = productDetailsCookieComponent;
    }


    public Product retrieveProductDetails(String productId){



        logger.debug("getting product review for {}", productId);
        Product product = null;
        String url = productDetailsApi + "/" + productId;
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.addAll("Cookie", productDetailsCookieComponent.getCookies());
        //requestHeaders.add("Cookie", "ak_bmsc=AEB2539F719D3853DDF7602C88ADEDBBB818624E594F0000452FA85B16D14B13~plknlh1BvwWYgS4BeDwl9AC7MuxC7EZBkN1T0eHxfJ6jwvCk6uU1Jd7n3hViNmryw8q2UxhKQ0en3BNRQs8FPkcOtdKtCIhoUaaJA8VuL3z/a9lyqIynH/RVUgNxL2BkBm7BBRZW7OZD3neBT8kGcaiMKYX6o9oXLy2IP+lj8uLN76VD9sKbNOzXACGCa8OKLFYUBypeHm4bI2zpoFwGihl7fNzY3iht0wUTVOAMHX43U=; bm_sz=6D7132DB4DE8A2624B99DF0BF48991D2~QAAQTmIYuC4AIe1lAQAAYqn4CMMt9xU8VSRsJ+fjtVW98unGcEUr5oLYZM3UNno/lDN6cuWAXwdSpKTcTemgFGPPrH+CGUem+sfxJtLZ67Zcunl7pVaiIndRrSrdJRsHlBFSuAL+8YBQ629VFHX+P/QK4BqKFfk1Adp52wgrLKPpp0WFrHaWsscOLKgkYqICAg==; ");
        try {

            HttpEntity requestEntity = new HttpEntity(null, requestHeaders);
            ResponseEntity<Product> responseResponseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Product.class);

            if (!responseResponseEntity.getStatusCode().equals(HttpStatus.OK)) {
                logger.error("product details was not found for productId:{} , response :{}"
                        , productId
                        , responseResponseEntity.getBody().toString());
            }

            if(responseResponseEntity.getStatusCode().equals(HttpStatus.OK)){
                productDetailsCookieComponent.addCookies(responseResponseEntity.getHeaders().get("Set-Cookie"));
                responseResponseEntity.getHeaders().get("Set-Cookie").stream().forEach(value -> logger.info(value));
            }

            product = responseResponseEntity.getBody();

        }catch (RestClientException ex){
            logger.error("Error getting product details of {}:", productId, ex);
        }
        return product;
    }


//    private String retrieveCookieFromAdidasUrl(){
//
//        ResponseEntity<String> responseEntity =
//                restTemplate.getForEntity( urlAdidasApiForCookie, String.class);
//
//
//        List<String> cookies= responseEntity.getHeaders().get("cookie");
//
//        cookies.stream().forEach(value -> logger.info(value));
//
//        return "";
//    }


}
