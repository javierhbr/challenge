package com.challenge.productservice.controller;

import com.challenge.productservice.domain.Product;
import com.challenge.productservice.exception.ProductNotFoundException;
import com.challenge.productservice.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void getProduct_ReturnsProductDetailsWithReview() throws Exception {
        BDDMockito.given(productService.getProductById(ArgumentMatchers.anyString()))
                .willReturn(new Product("M20324", "Stan Smith Shoes", "ION05"));

        mockMvc.perform(MockMvcRequestBuilders.get("/product/M20324")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("Stan Smith Shoes"))
                .andExpect(MockMvcResultMatchers.jsonPath("modelNumber").value("ION05"));
    }

    //TODO finish this
    @Test
    public void getProduct_ReturnsProductDetailsWithOuthReview() throws Exception {
        BDDMockito.given(productService.getProductById(ArgumentMatchers.anyString()))
                .willReturn(new Product("M20324", "Stan Smith Shoes", "ION05"));

        mockMvc.perform(MockMvcRequestBuilders.get("/product/M20324")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("Stan Smith Shoes"))
                .andExpect(MockMvcResultMatchers.jsonPath("modelNumber").value("ION05"));
    }


    @Test
    public void getProduct_productNotFound() throws Exception {
        BDDMockito.given(productService.getProductById(ArgumentMatchers.anyString()))
                .willThrow( new ProductNotFoundException("Could not find any product"));

        mockMvc.perform(MockMvcRequestBuilders.get("/product/M20324")).andExpect(MockMvcResultMatchers.status().isNotFound());

    }

}