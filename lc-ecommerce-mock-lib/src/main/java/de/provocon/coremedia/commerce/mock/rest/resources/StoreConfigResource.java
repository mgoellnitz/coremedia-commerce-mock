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
package de.provocon.coremedia.commerce.mock.rest.resources;

import de.provocon.coremedia.commerce.mock.rest.documents.StoreConfigDocument;
import java.util.List;


/**
 * Resource representing a mock store configuration.
 */
public class StoreConfigResource extends AbstractMockResource {

    /**
     * Returns a list of store configuration documents.
     *
     * @return complete configuration
     */
    public List<StoreConfigDocument> getStoreConfigs() {
        return getDocuments("configs", StoreConfigDocument.class);
    }


    /**
     * Returns the configuration for a given store code.
     *
     * @param storeCode store code
     * @return store configuration for the civen cde
     */
    public StoreConfigDocument getStoreConfig(String storeCode) {
        return getDocument("configs/"+storeCode, StoreConfigDocument.class);
    }

}
