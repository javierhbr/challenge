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

    private final String ENDPOINT = "review";

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

        ProductReviewDTO productReviewDTO_step1 = new ProductReviewDTO("C77154", new Float(5.5),  new Long(300));
        BDDMockito.given(productReviewService.getProductReviewById(ArgumentMatchers.anyString()))
                .willReturn(productReviewDTO_step1);

        mockMvc.perform(MockMvcRequestBuilders.get("/review/C77154")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.productId").value("C77154"))
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.averageScore").value(5.5))
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.numberOfReview").value(300));
    }

    @Test
    public void getProductReview_returnProductReviewNotFound() throws Exception {

        BDDMockito.given(productReviewService.getProductReviewById(ArgumentMatchers.anyString()))
                .willThrow(new ProductReviewNotFoundException("Could not find any product review"));

        mockMvc.perform(MockMvcRequestBuilders.get("/review/C22222")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void postProductReview_returnProductReview() throws Exception {

        ProductReviewRequest productReviewRequest= new ProductReviewRequest();
        ProductReviewDTO productReviewDTO_step1 = new ProductReviewDTO("C77154", new Float(5.5),  new Long(300));
        productReviewRequest.setProductReview(productReviewDTO_step1);
        final String productReviewRequestJson  =jsonTester.write(productReviewRequest).getJson();;

        BDDMockito.given(productReviewService.createProductReview(ArgumentMatchers.any(ProductReviewDTO.class)))
                .willReturn(productReviewDTO_step1);

        mockMvc.perform(MockMvcRequestBuilders.post("/review").contentType(MediaType.APPLICATION_JSON_UTF8).content(productReviewRequestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.productId").value("C77154"))
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.averageScore").value(5.5))
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.numberOfReview").value(300));
    }


    @Test
    public void putProductReview_returnProductReviewUpdated() throws Exception {

        ProductReviewRequest productReviewRequest= new ProductReviewRequest();
        ProductReviewDTO productReviewDTO_step1 = new ProductReviewDTO("C77999", new Float(8),  new Long(100));
        productReviewRequest.setProductReview(productReviewDTO_step1);
        final String productReviewRequestJson  =jsonTester.write(productReviewRequest).getJson();;

        BDDMockito.given(productReviewService.updateProductReviewById(ArgumentMatchers.any(ProductReviewDTO.class)))
                .willReturn(productReviewDTO_step1);

        mockMvc.perform(MockMvcRequestBuilders.put("/review").contentType(MediaType.APPLICATION_JSON_UTF8).content(productReviewRequestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.productId").value("C77999"))
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.averageScore").value(8))
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.numberOfReview").value(100));
    }

    @Test
    public void putProductReview_returnProductReviewNotFound() throws Exception {

        ProductReviewRequest productReviewRequest= new ProductReviewRequest();
        ProductReviewDTO productReviewDTO_step1 = new ProductReviewDTO("CCCCCCC", new Float(8),  new Long(100));
        productReviewRequest.setProductReview(productReviewDTO_step1);
        final String productReviewRequestJson  =jsonTester.write(productReviewRequest).getJson();;

        BDDMockito.given(productReviewService.updateProductReviewById(ArgumentMatchers.any(ProductReviewDTO.class)))
                .willThrow(new ProductReviewNotFoundException("Could not find any product review"));

        mockMvc.perform(MockMvcRequestBuilders.put("/review").contentType(MediaType.APPLICATION_JSON_UTF8).content(productReviewRequestJson))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteProductReview_returnProductReviewDeleted() throws Exception {

        ProductReviewDTO productReviewDTO_step1 = new ProductReviewDTO("C778888", new Float(8),  new Long(100));

        BDDMockito.given(productReviewService.deleteProductReviewById(ArgumentMatchers.anyString()))
                .willReturn(productReviewDTO_step1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/review/C778888")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.productId").value("C778888"))
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.averageScore").value(8))
                .andExpect(MockMvcResultMatchers.jsonPath("productReview.numberOfReview").value(100));
    }

    @Test
    public void deleteProductReview_returnProductReviewNotFound() throws Exception {

        BDDMockito.given(productReviewService.deleteProductReviewById(ArgumentMatchers.anyString()))
                .willThrow(new ProductReviewNotFoundException("Could not find any product review"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/review/C778888")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}