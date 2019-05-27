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

import de.provocon.coremedia.commerce.mock.rest.documents.AliasDocument;
import de.provocon.coremedia.commerce.mock.rest.documents.AliasListDocument;
import de.provocon.coremedia.commerce.mock.rest.documents.CatalogDocument;
import de.provocon.coremedia.commerce.mock.rest.documents.CategoryDocument;
import lombok.extern.slf4j.Slf4j;


/**
 * Access resource for a mock catalog.
 */
@Slf4j
public class CatalogResource extends AbstractMockResource {

    public String getCodeForCatalogAlias(String alias) {
        String result = null;
        LOG.info("getCodeForCatalogAlias({})", alias);
        AliasListDocument aliasList = getDocument("catalogs/aliases", AliasListDocument.class);
        if (aliasList!=null) {
            for (AliasDocument d : aliasList.getAliases()) {
                if (d.getAlias().equals(alias)) {
                    result = d.getRef();
                }
            }
        }
        return result;
    }


    public CatalogDocument getCatalog(String code) {
        CatalogDocument result = getDocument("catalogs/"+code+"/catalog", CatalogDocument.class);
        return result;
    }


    public CategoryDocument getCategory(String catalog, String category) {
        CategoryDocument result = getDocument("catalogs/"+catalog+"/categories/"+category, CategoryDocument.class);
        result.setCatalog(catalog);
        return result;
    }

}
