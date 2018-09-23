package com.challenge.productreview.controller;


import com.challenge.productreview.dto.ProductReviewDTO;
import com.challenge.productreview.exception.ProductReviewNotFoundException;
import com.challenge.productreview.request.ProductReviewRequest;
import com.challenge.productreview.service.ProductReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductReviewController.class)
public class ProductReviewControllerTest {

    private final String ENDPOINT = "/review";

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
        Float averageScore = new Float(99);
        Long numberOfReview = new Long(999);

        String url = ENDPOINT + "/" +productId;

        ProductReviewDTO productReviewDTO_step1 = new ProductReviewDTO(productId, averageScore,  numberOfReview);
        BDDMockito.given(productReviewService.getProductReviewById(ArgumentMatchers.anyString()))
                .willReturn(productReviewDTO_step1);

        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.productId").value(productId))
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.averageScore").value(averageScore))
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.numberOfReview").value(numberOfReview));
    }

    @Test
    public void getProductReview_returnProductReviewNotFound() throws Exception {

        String productId = "C22222";
        String url = ENDPOINT + "/" +productId;
        BDDMockito.given(productReviewService.getProductReviewById(ArgumentMatchers.anyString()))
                .willThrow(new ProductReviewNotFoundException("Could not find any product review"));

        mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void postProductReview_returnProductReview() throws Exception {

        String productId = "C772222";
        Float averageScore = new Float(5.5);
        Long numberOfReview = new Long(300);

        ProductReviewRequest productReviewRequest= new ProductReviewRequest();
        ProductReviewDTO productReviewDTO_step1 = new ProductReviewDTO(productId, averageScore,  numberOfReview);
        productReviewRequest.setProductReview(productReviewDTO_step1);
        final String productReviewRequestJson  =jsonTester.write(productReviewRequest).getJson();;

        BDDMockito.given(productReviewService.createProductReview(ArgumentMatchers.any(ProductReviewDTO.class)))
                .willReturn(productReviewDTO_step1);

        mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT).contentType(MediaType.APPLICATION_JSON_UTF8).content(productReviewRequestJson))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.productId").value(productId))
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.averageScore").value(averageScore.floatValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.numberOfReview").value(numberOfReview.longValue()));
    }


    @Test
    public void putProductReview_returnProductReviewUpdated() throws Exception {

        String productId = "C77999";
        Float averageScore = new Float(8);
        Long numberOfReview = new Long(100);

        ProductReviewRequest productReviewRequest= new ProductReviewRequest();
        ProductReviewDTO productReviewDTO_step1 = new ProductReviewDTO(productId, averageScore,  numberOfReview);
        productReviewRequest.setProductReview(productReviewDTO_step1);
        final String productReviewRequestJson  =jsonTester.write(productReviewRequest).getJson();;

        BDDMockito.given(productReviewService.updateProductReviewById(ArgumentMatchers.any(ProductReviewDTO.class)))
                .willReturn(productReviewDTO_step1);

        mockMvc.perform(MockMvcRequestBuilders.put("/review").contentType(MediaType.APPLICATION_JSON_UTF8).content(productReviewRequestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.productId").value(productId))
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.averageScore").value(averageScore.floatValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.numberOfReview").value(numberOfReview.longValue()));
    }

    @Test
    public void putProductReview_returnProductReviewNotFound() throws Exception {

        String productId = "CCCCCCC";
        Float averageScore = new Float(8);
        Long numberOfReview = new Long(100);

        ProductReviewRequest productReviewRequest= new ProductReviewRequest();
        ProductReviewDTO productReviewDTO_step1 = new ProductReviewDTO(productId, averageScore,  numberOfReview);
        productReviewRequest.setProductReview(productReviewDTO_step1);
        final String productReviewRequestJson  =jsonTester.write(productReviewRequest).getJson();;

        BDDMockito.given(productReviewService.updateProductReviewById(ArgumentMatchers.any(ProductReviewDTO.class)))
                .willThrow(new ProductReviewNotFoundException("Could not find any product review"));

        mockMvc.perform(MockMvcRequestBuilders.put(ENDPOINT).contentType(MediaType.APPLICATION_JSON_UTF8).content(productReviewRequestJson))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteProductReview_returnProductReviewDeleted() throws Exception {

        String productId = "C00000";
        String url = ENDPOINT + "/" +productId;

        Mockito.doNothing().when(productReviewService).deleteProductReviewById(productId);

        mockMvc.perform(MockMvcRequestBuilders.delete(url)).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void deleteProductReview_returnProductReviewNotFound() throws Exception {
        String productId = "C00000";
        String url = ENDPOINT + "/" +productId;
        Mockito.doThrow(new ProductReviewNotFoundException("Could not find any product review"))
                .when(productReviewService).deleteProductReviewById(productId);

        mockMvc.perform(MockMvcRequestBuilders.delete(url)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}