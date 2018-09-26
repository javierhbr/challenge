package com.challenge.productreview.controller;

import com.challenge.productreview.dto.ProductReviewDTO;
import com.challenge.productreview.exception.ProductReviewNotFoundException;
import com.challenge.productreview.request.ProductReviewRequest;
import com.challenge.productreview.service.ProductReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductReviewController.class)
@AutoConfigureMockMvc(secure = false)
public class ProductReviewControllerTest {

    private final String ENDPOINT = "/review";
    private final String HEADER_AUTH = "Authorization";
    private final String HEADER_TOKEN= "Basic Y2hhbGxlbmdlOmNvZGU=";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductReviewService productReviewService;

    @Autowired
    private ObjectMapper objectMapper;

    private JacksonTester<ProductReviewRequest> jsonTester;

    @Before
    public void setup() {
        JacksonTester.initFields(this, objectMapper);
    }

    @Test
    public void getProductReview_returnProductReview() throws Exception {

        String productId = "C77154";
        Float averageScore = RandomUtils.nextFloat();
        Long numberOfReview = RandomUtils.nextLong();
        String url = ENDPOINT + "/" +productId;
        ProductReviewDTO productReviewDTO_step1 = new ProductReviewDTO(productId, averageScore,  numberOfReview);

        given(productReviewService.getProductReviewById(anyString()))
                .willReturn(productReviewDTO_step1);

        mockMvc.perform(get(url)).andExpect(status().isOk())
                .andExpect(jsonPath("productReview.productId").value(productId))
                .andExpect(jsonPath("productReview.averageScore").value(averageScore))
                .andExpect(jsonPath("productReview.numberOfReview").value(numberOfReview));
    }

    @Test
    public void getProductReview_returnProductReviewNotFound() throws Exception {

        String productId = "C22222";
        String url = ENDPOINT + "/" +productId;
        given(productReviewService.getProductReviewById(anyString()))
                .willThrow(new ProductReviewNotFoundException("Could not find any product review"));

        mockMvc.perform(get(url)).andExpect(status().isNotFound());
    }

    @Test
    public void postProductReview_returnProductReview() throws Exception {

        String productId = "C772222";
        Float averageScore = RandomUtils.nextFloat();
        Long numberOfReview = RandomUtils.nextLong();
        ProductReviewRequest productReviewRequest= new ProductReviewRequest();
        ProductReviewDTO productReviewDTO_step1 = new ProductReviewDTO(productId, averageScore,  numberOfReview);
        productReviewRequest.setProductReview(productReviewDTO_step1);
        final String productReviewRequestJson  =jsonTester.write(productReviewRequest).getJson();;

        given(productReviewService.createProductReview(any(ProductReviewDTO.class)))
                .willReturn(productReviewDTO_step1);

        mockMvc.perform(post(ENDPOINT).contentType(MediaType.APPLICATION_JSON_UTF8).content(productReviewRequestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("productReview.productId").value(productId))
                .andExpect(jsonPath("productReview.averageScore").value(averageScore.floatValue()))
                .andExpect(jsonPath("productReview.numberOfReview").value(numberOfReview.longValue()));
    }

    @Test
    public void putProductReview_returnProductReviewUpdated() throws Exception {

        String productId = "C77999";
        Float averageScore = RandomUtils.nextFloat();
        Long numberOfReview = RandomUtils.nextLong();
        ProductReviewRequest productReviewRequest= new ProductReviewRequest();
        ProductReviewDTO productReviewDTO_step1 = new ProductReviewDTO(productId, averageScore,  numberOfReview);
        productReviewRequest.setProductReview(productReviewDTO_step1);
        final String productReviewRequestJson  =jsonTester.write(productReviewRequest).getJson();;

        given(productReviewService.updateProductReviewById(any(ProductReviewDTO.class)))
                .willReturn(productReviewDTO_step1);

        mockMvc.perform(put("/review").contentType(MediaType.APPLICATION_JSON_UTF8).content(productReviewRequestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("productReview.productId").value(productId))
                .andExpect(jsonPath("productReview.averageScore").value(averageScore.floatValue()))
                .andExpect(jsonPath("productReview.numberOfReview").value(numberOfReview.longValue()));
    }

    @Test
    public void putProductReview_returnProductReviewNotFound() throws Exception {

        String productId = "CCCCCCC";
        Float averageScore = RandomUtils.nextFloat();
        Long numberOfReview = RandomUtils.nextLong();
        ProductReviewRequest productReviewRequest= new ProductReviewRequest();
        ProductReviewDTO productReviewDTO_step1 = new ProductReviewDTO(productId, averageScore,  numberOfReview);
        productReviewRequest.setProductReview(productReviewDTO_step1);
        final String productReviewRequestJson  =jsonTester.write(productReviewRequest).getJson();;

        given(productReviewService.updateProductReviewById(any(ProductReviewDTO.class)))
                .willThrow(new ProductReviewNotFoundException("Could not find any product review"));

        mockMvc.perform(put(ENDPOINT).contentType(MediaType.APPLICATION_JSON_UTF8).content(productReviewRequestJson))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteProductReview_returnProductReviewDeleted() throws Exception {

        String productId = "C00000";
        String url = ENDPOINT + "/" +productId;
        Mockito.doNothing().when(productReviewService).deleteProductReviewById(productId);

        mockMvc.perform(delete(url)).andExpect(status().isOk());
    }

    @Test
    public void deleteProductReview_returnProductReviewNotFound() throws Exception {
        String productId = "C00000";
        String url = ENDPOINT + "/" +productId;
        Mockito.doThrow(new ProductReviewNotFoundException("Could not find any product review"))
                .when(productReviewService).deleteProductReviewById(productId);

        mockMvc.perform(delete(url)).andExpect(status().isNotFound());
    }
}