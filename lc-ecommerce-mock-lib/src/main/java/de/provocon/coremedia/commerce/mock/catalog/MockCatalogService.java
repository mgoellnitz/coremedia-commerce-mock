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

import com.coremedia.livecontext.ecommerce.catalog.Catalog;
import com.coremedia.livecontext.ecommerce.catalog.CatalogAlias;
import com.coremedia.livecontext.ecommerce.catalog.CatalogId;
import com.coremedia.livecontext.ecommerce.catalog.CatalogService;
import com.coremedia.livecontext.ecommerce.catalog.Category;
import com.coremedia.livecontext.ecommerce.catalog.Product;
import com.coremedia.livecontext.ecommerce.catalog.ProductVariant;
import com.coremedia.livecontext.ecommerce.common.CommerceId;
import com.coremedia.livecontext.ecommerce.common.StoreContext;
import com.coremedia.livecontext.ecommerce.search.SearchFacet;
import com.coremedia.livecontext.ecommerce.search.SearchResult;
import de.provocon.coremedia.commerce.mock.beans.MockCategory;
import de.provocon.coremedia.commerce.mock.rest.documents.CatalogDocument;
import de.provocon.coremedia.commerce.mock.rest.documents.CategoryDocument;
import de.provocon.coremedia.commerce.mock.rest.resources.CatalogResource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


/**
 * Mock catalog service transforming the rest simulation of the file based
 * json resources into a CoreMedia Content Cloud CatalogService instance.
 */
@Slf4j
public class MockCatalogService implements CatalogService {

    @Setter
    private CatalogResource catalogResource;


    @Override
    public Product findProductById(CommerceId ci, StoreContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Product findProductBySeoSegment(String string, StoreContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public ProductVariant findProductVariantById(CommerceId ci, StoreContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Product> findProductsByCategory(Category ctgr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Category> findTopCategories(CatalogAlias alias, StoreContext context) {
        List<Category> result = new ArrayList<>();
        LOG.info("findTopCategories({}) {}", alias, context);
        String catalog = catalogResource.getCodeForCatalogAlias(alias.value());
        LOG.info("findTopCategories() catalog for alias {} is {}", alias.value(), catalog);
        CatalogDocument catalogDocument = catalogResource.getCatalog(catalog);
        List<String> topCategories = catalogDocument.getTopCategories();
        for (String category : topCategories) {
            CategoryDocument document = catalogResource.getCategory(catalog, category);
            result.add(new MockCategory(catalogResource, document, null));
        }
        return result;
    }


    @Override
    public Category findRootCategory(CatalogAlias alias, StoreContext context) {
        List<Category> topCategories = findTopCategories(alias, context);
        return topCategories==null ? null : (topCategories.isEmpty() ? null : topCategories.get(0));
    }


    @Override
    public List<Category> findSubCategories(Category ctgr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Category findCategoryById(CommerceId ci, StoreContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Category findCategoryBySeoSegment(String string, StoreContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public SearchResult<Product> searchProducts(String string, Map<String, String> map, StoreContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Map<String, List<SearchFacet>> getFacetsForProductSearch(Category ctgr, StoreContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public SearchResult<ProductVariant> searchProductVariants(String string, Map<String, String> map, StoreContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Catalog> getCatalogs(StoreContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Optional<Catalog> getCatalog(CatalogId ci, StoreContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Optional<Catalog> getCatalog(CatalogAlias ca, StoreContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Optional<Catalog> getDefaultCatalog(StoreContext context) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
