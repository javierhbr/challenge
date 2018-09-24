
package com.challenge.productservice.domain.product;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Callouts {

    @JsonProperty("callout_bottom_stack")
    private List<CalloutBottomStack> calloutBottomStack ;

    @JsonProperty("callout_bottom_stack")
    public List<CalloutBottomStack> getCalloutBottomStack() {
        return calloutBottomStack;
    }

    @JsonProperty("callout_bottom_stack")
    public void setCalloutBottomStack(List<CalloutBottomStack> calloutBottomStack) {
        this.calloutBottomStack = calloutBottomStack;
    }

}
