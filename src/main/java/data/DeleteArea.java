package data;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "DispTarget",
        "DispAction"
})

public class DeleteArea {
    @JsonProperty("DispTarget")
    private Integer dispTarget;
    @JsonProperty("DispAction")
    private String dispAction;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public DeleteArea() {
    }

    /**
     *
     * @param dispAction
     * @param dispTarget
     */
    public DeleteArea(Integer dispTarget, String dispAction) {
        super();
        this.dispTarget = dispTarget;
        this.dispAction = dispAction;
    }

    @JsonProperty("DispTarget")
    public Integer getDispTarget() {
        return dispTarget;
    }

    @JsonProperty("DispTarget")
    public void setDispTarget(Integer dispTarget) {
        this.dispTarget = dispTarget;
    }

    @JsonProperty("DispAction")
    public String getDispAction() {
        return dispAction;
    }

    @JsonProperty("DispAction")
    public void setDispAction(String dispAction) {
        this.dispAction = dispAction;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
