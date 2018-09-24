package com.challenge.productservice.integration;

import com.challenge.productservice.domain.product.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ProductServiceIT {

    @Autowired

    private TestRestTemplate restTemplate;

    @Test
    public void getProduct_returnsProductWithReview() throws Exception {
        //Arrange

        String productId = "M20324";
        //Act
        ResponseEntity<Product> productResponse = restTemplate.getForEntity("/product/".concat(productId), Product.class);

        //TODO finish this
        //Assert
        Assert.assertTrue(productResponse.getStatusCode().equals(HttpStatus.OK));
//        assertThat(productResponse.getBody().getName()).isEqualTo("prius");
//        assertThat(productResponse.getBody().getType()).isEqualTo("hybrid");
    }

    @Test
    public void getProduct_returnsProductWithOutReview() throws Exception {
        //Arrange

        String productId = "M20324";
        //Act
        ResponseEntity<Product> productResponse = restTemplate.getForEntity("/product/".concat(productId), Product.class);

        //TODO finish this
        //Assert
        Assert.assertTrue(productResponse.getStatusCode().equals(HttpStatus.OK));
//        assertThat(productResponse.getBody().getName()).isEqualTo("prius");
//        assertThat(productResponse.getBody().getType()).isEqualTo("hybrid");
    }

    @Test
    public void getProduct_returnsProductNotFound() throws Exception {
        //Arrange

        String productId = "M203245";
        //Act
        ResponseEntity<Product> productResponse = restTemplate.getForEntity("/product/".concat(productId), Product.class);

        //TODO finish this
        //Assert
        Assert.assertTrue(productResponse.getStatusCode().equals(HttpStatus.NOT_FOUND));
    }

}
