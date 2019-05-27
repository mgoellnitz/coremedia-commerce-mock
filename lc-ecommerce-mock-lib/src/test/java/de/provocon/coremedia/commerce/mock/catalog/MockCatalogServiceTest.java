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
package de.provocon.coremedia.commerce.mock.catalog;

import com.coremedia.livecontext.ecommerce.catalog.CatalogAlias;
import com.coremedia.livecontext.ecommerce.catalog.Category;
import com.coremedia.livecontext.ecommerce.common.StoreContext;
import de.provocon.coremedia.commerce.mock.rest.resources.CatalogResource;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Test the mock catalog service implementation.
 */
public class MockCatalogServiceTest {

    @Test
    public void testThings() {
        CatalogResource catalogResource = new CatalogResource();
        MockCatalogService catalogService = new MockCatalogService();
        catalogService.setCatalogResource(catalogResource);
        CatalogAlias ca = CatalogAlias.of("C");
        StoreContext sc = null;
        List<Category> topCategories = catalogService.findTopCategories(ca, sc);
        Assert.assertNotNull(topCategories, "Expected to find some categories at the top.");
        Assert.assertEquals(topCategories.size(), 2, "Expected to find an exact number of top categories.");
    }

}
