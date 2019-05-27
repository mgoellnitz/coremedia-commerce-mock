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

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


/**
 * Document describing a single mock category.
 */
public class CategoryDocument extends AbstractMockDocument {

    @JsonProperty("name")
    @Getter
    @Setter
    private String name;

    @JsonProperty("isRoot")
    @Getter
    @Setter
    private boolean root;

    @JsonProperty("shortDescription")
    @Getter
    @Setter
    private String shortDescription;

    @JsonProperty("longDescription")
    @Getter
    @Setter
    private String longDescription;

    @JsonProperty("children")
    @Getter
    @Setter
    private List<String> children;

    @JsonProperty("seoSegment")
    @Getter
    @Setter
    private String seoSegment;

    @JsonProperty("metaDescription")
    @Getter
    @Setter
    private String metaDescription;

    @JsonProperty("metaKeywords")
    @Getter
    @Setter
    private String metaKeywords;

    @JsonProperty("title")
    @Getter
    @Setter
    private String title;

    @JsonProperty("displayName")
    @Getter
    @Setter
    private String displayName;

    @Getter
    @Setter
    private String catalog;

}
