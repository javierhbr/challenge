package com.challenge.productservice.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ProductDetailsCookieComponent {


    @Value("${external.resources.api.endpoint.product-details.cookie}")
    private String urlAdidasApiForCookie;

    @PostConstruct
    private void setDefaultCookies(){
        this.addCookies(Collections.singletonList(urlAdidasApiForCookie));
    }

    private List<String> cookies;

    public List<String> getCookies() {
        return cookies;
    }

    public void addCookies(List<String> cookies) {

        if(StringUtils.isEmpty(cookies)){
            return;
        }else if (StringUtils.isEmpty(this.cookies )){
            this.cookies = new ArrayList<>();
        }

        this.cookies = cookies;
    }
}
