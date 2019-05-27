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
package de.provocon.coremedia.commerce.mock.common;

import com.coremedia.blueprint.base.livecontext.ecommerce.common.StoreContextBuilderImpl;
import com.coremedia.blueprint.base.livecontext.ecommerce.common.StoreContextImpl;
import com.coremedia.livecontext.ecommerce.catalog.CatalogAlias;
import com.coremedia.livecontext.ecommerce.catalog.CatalogId;
import com.coremedia.livecontext.ecommerce.common.CommerceConnection;
import com.coremedia.livecontext.ecommerce.common.StoreContext;
import com.coremedia.livecontext.ecommerce.common.StoreContextBuilder;
import com.coremedia.livecontext.ecommerce.workspace.WorkspaceId;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Currency;
import java.util.List;
import java.util.Locale;


/**
 *
 */
public class MockStoreContextBuilder implements StoreContextBuilder {

    private final CommerceConnection connection;
    private String siteId;
    private String storeId;
    private String storeName;
    private CatalogId catalogId;
    private String catalogVersion;
    private Currency currency;
    private Locale locale;
    private ZoneId timeZoneId;
    private ZonedDateTime previewDate;
    private String userSegments;


    private MockStoreContextBuilder(CommerceConnection connection, String siteId) {
        this.connection = connection;
        this.siteId = siteId;
    }


    public static MockStoreContextBuilder from(CommerceConnection connection, String siteId) {
        return new MockStoreContextBuilder(connection, siteId);
    }


    public static MockStoreContextBuilder from(StoreContextImpl storeContext) {
        return from(storeContext.getConnection(), storeContext.getSiteId())
                .withStoreId(storeContext.getStoreId())
                .withStoreName(storeContext.getStoreName())
                .withCatalogId(storeContext.getCatalogId().get())
                .withCatalogVersion(storeContext.getCatalogVersion())
                .withCurrency(storeContext.getCurrency())
                .withLocale(storeContext.getLocale())
                .withTimeZoneId(storeContext.getTimeZoneId().orElse(null))
                .withPreviewDate(storeContext.getPreviewDate().orElse(null))
                .withUserSegments(storeContext.getUserSegments().orElse(null));
    }


    @Override
    public MockStoreContextBuilder withSiteId(String siteId) {
        this.siteId = siteId;
        return this;
    }


    @Override
    public MockStoreContextBuilder withStoreId(String storeId) {
        this.storeId = storeId;
        return this;
    }


    @Override
    public MockStoreContextBuilder withStoreName(String storeName) {
        this.storeName = storeName;
        return this;
    }


    @Override
    public MockStoreContextBuilder withCatalogId(CatalogId catalogId) {
        this.catalogId = catalogId;
        return this;
    }


    @Override
    public MockStoreContextBuilder withCatalogAlias(CatalogAlias catalogAlias) {
        // Don't care about catalog alias.
        return this;
    }


    public MockStoreContextBuilder withCatalogVersion(String catalogVersion) {
        this.catalogVersion = catalogVersion;
        return this;
    }


    @Override
    public MockStoreContextBuilder withCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }


    @Override
    public MockStoreContextBuilder withLocale(Locale locale) {
        this.locale = locale;
        return this;
    }


    @Override
    public MockStoreContextBuilder withTimeZoneId(ZoneId timeZoneId) {
        this.timeZoneId = timeZoneId;
        return this;
    }


    @Override
    public MockStoreContextBuilder withPreviewDate(ZonedDateTime previewDate) {
        this.previewDate = previewDate;
        return this;
    }


    @Override
    public MockStoreContextBuilder withWorkspaceId(WorkspaceId workspaceId) {
        // Don't care about the workspace ID.
        return this;
    }


    @Override
    public MockStoreContextBuilder withUserSegments(String userSegments) {
        this.userSegments = userSegments;
        return this;
    }


    @Override
    public MockStoreContextBuilder withContractIds(List<String> contractIds) {
        // Don't care about contract IDs.
        return this;
    }


    @Override
    public MockStoreContextBuilder withContractIdsForPreview(List<String> contractIdsForPreview) {
        // Don't care about contract IDs.
        return this;
    }


    @Override
    public StoreContext build() {
        StoreContextImpl storeContext = StoreContextBuilderImpl.from(connection, siteId)
                .withStoreId(storeId)
                .withStoreName(storeName)
                .withCatalogId(catalogId)
                .withCurrency(currency)
                .withLocale(locale)
                .withTimeZoneId(timeZoneId)
                .withPreviewDate(previewDate)
                .withUserSegments(userSegments)
                .build();

        storeContext.put(StoreContextImpl.CATALOG_VERSION, catalogVersion);

        return storeContext;
    }

}
