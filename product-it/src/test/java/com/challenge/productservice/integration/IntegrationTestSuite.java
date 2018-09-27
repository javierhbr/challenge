package com.challenge.productservice.integration;

import com.palantir.docker.compose.DockerComposeRule;
import com.palantir.docker.compose.configuration.ProjectName;
import com.palantir.docker.compose.connection.DockerPort;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.palantir.docker.compose.connection.waiting.HealthChecks.*;

@Category(IntegrationTest.class)
public class IntegrationTestSuite {

    @ClassRule
    public static DockerComposeRule docker = DockerComposeRule.builder()
            .file("src/test/resource/docker-compose-integration-test.yml")
            .projectName(ProjectName.random())
            .waitingForService("product-review-service", toHaveAllPortsOpen())
            .waitingForService("product-service", toHaveAllPortsOpen())
            .build();
    /*.waitingForService("java-app",
                       HealthChecks.toRespond2xxOverHttp(19900, (port) ->
            port.inFormat("http://$HOST:$EXTERNAL_PORT/application/health")),
            Duration.standardSeconds(30)
            )
            .shutdownStrategy(ShutdownStrategy.GRACEFUL)
        .build();*/

    private static String productServiceHost;

    private static String productServiceReviewHost;

    @Autowired
    private RestTemplate restTemplate;

    @BeforeClass
    public static void initialize() {


        DockerPort product_service = docker.containers()
                .container("product-service")
                .port(9090);

        DockerPort product_review_service = docker.containers()
                .container("product-review-service")
                .port(9091);
        System.out.println("=============================== initialize :"+product_service.getIp());
        System.out.println("=============================== initialize :"+product_service.getExternalPort());
        System.out.println("=============================== initialize :"+product_review_service.getIp());
        System.out.println("=============================== initialize :"+product_service.getExternalPort());

        productServiceHost = String.format("http://%s:%s", product_service.getIp(), product_service.getExternalPort());
        productServiceReviewHost = String.format("http://%s:%s", product_review_service.getIp(), product_review_service.getExternalPort());

    }

    @Test
    public void getProductReview_returnsProductReview() throws Exception {
        String productId = "AC7836";
        String url = productServiceReviewHost +"/review" + "/" + productId;

        System.out.println("===============================");
        System.out.println("=========productServiceReviewHost:"+productServiceReviewHost);
        System.out.println("=========url:"+url);


        ResponseEntity<String> productResponse = restTemplate.getForEntity(url, String.class);
        System.out.println("=========url:"+productResponse.toString());
        Assert.assertTrue(productResponse.getStatusCode().equals(HttpStatus.OK));
//        Assert.assertTrue(productResponse.getBody().getProductReview()!=null);
//        Assert.assertTrue(productResponse.getBody().getProductReview().getProductId().equals(productId));
        System.out.println("====================================");
    }
}
