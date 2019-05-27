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
package de.provocon.coremedia.commerce.mock.beans;

import com.coremedia.cap.content.Content;
import com.coremedia.livecontext.ecommerce.asset.CatalogPicture;
import com.coremedia.livecontext.ecommerce.catalog.Category;
import com.coremedia.livecontext.ecommerce.catalog.Product;
import com.coremedia.xml.Markup;
import de.provocon.coremedia.commerce.mock.rest.documents.CategoryDocument;
import de.provocon.coremedia.commerce.mock.rest.resources.CatalogResource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 */
public class MockCategory extends AbstractMockCommerceBean implements Category {

    private CatalogResource catalogResource;

    private CategoryDocument categoryDocument;

    private MockCategory parent;


    public MockCategory(CatalogResource catalogResource, CategoryDocument categoryDocument, MockCategory parent) {
        this.parent = parent;
        this.categoryDocument = categoryDocument;
        this.catalogResource = catalogResource;
    }


    @Override
    public boolean isRoot() {
        return categoryDocument.isRoot();
    }


    @Override
    public String getName() {
        return categoryDocument.getName();
    }


    @Override
    public Markup getShortDescription() {
        return buildRichtextMarkup(categoryDocument.getShortDescription(), true);
    }


    @Override
    public Markup getLongDescription() {
        return buildRichtextMarkup(categoryDocument.getLongDescription(), true);
    }


    @Override
    public String getThumbnailUrl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public String getDefaultImageUrl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Category> getChildren() {
        List<Category> result = new ArrayList<>();
        for (String childId : categoryDocument.getChildren()) {
            CategoryDocument document = catalogResource.getCategory(categoryDocument.getCatalog(), childId);
            result.add(new MockCategory(catalogResource, document, this));
        }
        return result;
    }


    @Override
    public List<Product> getProducts() {
        return Collections.emptyList();
    }


    @Override
    public Category getParent() {
        return parent;
    }


    @Override
    public List<Category> getBreadcrumb() {
        return Collections.emptyList();
    }


    @Override
    public String getSeoSegment() {
        return categoryDocument.getSeoSegment();
    }


    @Override
    public String getMetaDescription() {
        return categoryDocument.getMetaDescription();
    }


    @Override
    public String getMetaKeywords() {
        return categoryDocument.getMetaKeywords();
    }


    @Override
    public String getTitle() {
        return categoryDocument.getTitle();
    }


    @Override
    public String getDisplayName() {
        return categoryDocument.getDisplayName();
    }


    @Override
    public CatalogPicture getCatalogPicture() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Content getPicture() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Content> getPictures() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<Content> getVisuals() {
        return Collections.emptyList();
    }


    @Override
    public List<Content> getDownloads() {
        return Collections.emptyList();
    }

}
