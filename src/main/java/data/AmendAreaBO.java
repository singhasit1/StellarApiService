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
        "area_key",
        "audience_set_key",
        "area_name",
        "area_desc",
        "modify_by"
})
public class AmendAreaBO {
    @JsonProperty("area_key")
    private Integer areaKey;
    @JsonProperty("audience_set_key")
    private Integer audienceSetKey;
    @JsonProperty("area_name")
    private String areaName;
    @JsonProperty("area_desc")
    private String areaDesc;
    @JsonProperty("modify_by")
    private String modifyBy;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public AmendAreaBO() {
    }

    /**
     *
     * @param areaKey
     * @param modifyBy
     * @param areaName
     * @param areaDesc
     * @param audienceSetKey
     */
    public AmendAreaBO(Integer areaKey, Integer audienceSetKey, String areaName, String areaDesc, String modifyBy) {
        super();
        this.areaKey = areaKey;
        this.audienceSetKey = audienceSetKey;
        this.areaName = areaName;
        this.areaDesc = areaDesc;
        this.modifyBy = modifyBy;
    }

    @JsonProperty("area_key")
    public Integer getAreaKey() {
        return areaKey;
    }

    @JsonProperty("area_key")
    public void setAreaKey(Integer areaKey) {
        this.areaKey = areaKey;
    }

    @JsonProperty("audience_set_key")
    public Integer getAudienceSetKey() {
        return audienceSetKey;
    }

    @JsonProperty("audience_set_key")
    public void setAudienceSetKey(Integer audienceSetKey) {
        this.audienceSetKey = audienceSetKey;
    }

    @JsonProperty("area_name")
    public String getAreaName() {
        return areaName;
    }

    @JsonProperty("area_name")
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @JsonProperty("area_desc")
    public String getAreaDesc() {
        return areaDesc;
    }

    @JsonProperty("area_desc")
    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc;
    }

    @JsonProperty("modify_by")
    public String getModifyBy() {
        return modifyBy;
    }

    @JsonProperty("modify_by")
    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
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
