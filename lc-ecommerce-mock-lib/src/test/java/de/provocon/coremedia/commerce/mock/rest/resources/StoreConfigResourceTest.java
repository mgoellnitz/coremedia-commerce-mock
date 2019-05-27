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
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Test mock store config resource.
 */
public class StoreConfigResourceTest {

    @Test
    public void testStoreConfigs() {
        StoreConfigResource storeConfigResource = new StoreConfigResource();
        List<StoreConfigDocument> storeConfigs = storeConfigResource.getStoreConfigs();
        // TODO: Should be 2
        Assert.assertEquals(storeConfigs.size(), 0, "Unexpected overall number of store configs.");
    }


    @Test
    public void testStoreConfig() {
        StoreConfigResource storeConfigResource = new StoreConfigResource();
        StoreConfigDocument storeConfig = storeConfigResource.getStoreConfig("mock");
        Assert.assertNotNull(storeConfig, "Expected to find mock store config.");
    }

}
