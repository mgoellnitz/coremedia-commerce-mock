/*
 * Copyright 2019 Martin Goellnitz for Provocon.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.provocon.coremedia.commerce.mock.rest.documents;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;


/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractMockDocument {

    /**
     * ID of the document.
     */
    @JsonProperty("id")
    @Getter
    @Setter
    private String id;

    /**
     * Map containing all custom attributes.
     */
    @JsonProperty("custom_attributes")
    private List<Object> customAttributes = new ArrayList<>();

    /**
     * Map containing all extension attributes.
     */
    private Map<String, Object> extensionAttributes = new HashMap<>();

    /**
     * Map containing all unmapped attributes.
     */
    private Map<String, Object> unmappedAttributes = new HashMap<>();


    public List<Object> getCustomAttributes() {
        return customAttributes;
    }


    public void setCustomAttributes(List<Object> customAttributes) {
        this.customAttributes = customAttributes;
    }


    public Map<String, Object> getExtensionAttributes() {
        return extensionAttributes;
    }


    public void setExtensionAttributes(Map<String, Object> extensionAttributes) {
        this.extensionAttributes = extensionAttributes;
    }


    @JsonAnyGetter
    public Map<String, Object> unmapped() {
        return unmappedAttributes;
    }


    public Object get(String name) {
        return unmappedAttributes.get(name);
    }


    @JsonAnySetter
    public void set(String name, Object value) {
        unmappedAttributes.put(name, value);
    }

}
