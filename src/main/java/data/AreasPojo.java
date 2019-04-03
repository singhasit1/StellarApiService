package data;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by Asit.Singh on 01-04-2019.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "DispTarget",
        "DispAction",
        "areaBO"
})
public class AreasPojo {

    @JsonProperty("DispTarget")
    private Integer dispTarget;
    @JsonProperty("DispAction")
    private String dispAction;
    @JsonProperty("areaBO")
    private AreaBO areaBO;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public AreasPojo() {
    }

    /**
     *
     * @param dispAction
     * @param dispTarget
     * @param areaBO
     */
    public AreasPojo(Integer dispTarget, String dispAction, AreaBO areaBO) {
        super();
        this.dispTarget = dispTarget;
        this.dispAction = dispAction;
        this.areaBO = areaBO;
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

    @JsonProperty("areaBO")
    public AreaBO getAreaBO() {
        return areaBO;
    }

    @JsonProperty("areaBO")
    public void setAreaBO(AreaBO areaBO) {
        this.areaBO = areaBO;
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
