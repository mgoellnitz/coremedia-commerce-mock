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

import com.coremedia.blueprint.base.livecontext.ecommerce.common.AbstractStoreContextProvider;
import com.coremedia.blueprint.base.livecontext.ecommerce.common.CommerceConnectionFinder;
import com.coremedia.blueprint.base.livecontext.ecommerce.common.NoCommerceConnectionAvailable;
import com.coremedia.cap.multisite.Site;
import com.coremedia.livecontext.ecommerce.catalog.CatalogId;
import com.coremedia.livecontext.ecommerce.common.CommerceConfigKeys;
import com.coremedia.livecontext.ecommerce.common.CommerceConnection;
import com.coremedia.livecontext.ecommerce.common.InvalidContextException;
import com.coremedia.livecontext.ecommerce.common.StoreContext;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;


/**
 *
 */
public class MockStoreContextProvider extends AbstractStoreContextProvider {

    private final CommerceConnectionFinder commerceConnectionFinder;


    public MockStoreContextProvider(CommerceConnectionFinder commerceConnectionFinder) {
        this.commerceConnectionFinder = commerceConnectionFinder;
    }


    private static class StoreContextValuesHolder {

        private final CommerceConnection connection;

        private String siteId;

        private String storeId;

        private String storeName;

        private CatalogId catalogId;

        private Currency currency;

        private Locale locale;


        private StoreContextValuesHolder(CommerceConnection connection) {
            this.connection = connection;
        }

    }


    private static Currency parseCurrency(String currencyCode) {
        try {
            return Currency.getInstance(currencyCode);
        } catch (IllegalArgumentException e) {
            throw new InvalidContextException(e);
        }
    }


    private StoreContextValuesHolder populateValuesHolder(Map<String, Object> config, Site site) {
        CommerceConnection connection = commerceConnectionFinder.findConnection(site)
                .orElseThrow(() -> new NoCommerceConnectionAvailable(
                String.format("Could not find commerce connection for site '%s'.", site)));

        StoreContextValuesHolder valuesHolder = new StoreContextValuesHolder(connection);

        valuesHolder.siteId = site.getId();
        valuesHolder.storeId = (String) config.get(CommerceConfigKeys.STORE_ID);
        valuesHolder.storeName = (String) config.get(CommerceConfigKeys.STORE_NAME);
        String catalogIdStr = (String) config.get(CommerceConfigKeys.CATALOG_ID);
        valuesHolder.catalogId = catalogIdStr!=null ? CatalogId.of(catalogIdStr) : null;
        String currencyStr = (String) config.get(CommerceConfigKeys.CURRENCY);
        valuesHolder.currency = currencyStr!=null ? parseCurrency(currencyStr) : null;
        valuesHolder.locale = site.getLocale();

        return valuesHolder;
    }


    /**
     * Adds the given values to a store context.
     * <p>
     * All values are optional. You can use a "null" value to omit single values.
     *
     * @return the new built store context
     * @throws com.coremedia.livecontext.ecommerce.common.InvalidContextException if locale or currency has wrong format
     */
    private static StoreContext createStoreContext(StoreContextValuesHolder valuesHolder) {
        CommerceConnection connection = valuesHolder.connection;
        String siteId = valuesHolder.siteId;
        String storeId = valuesHolder.storeId;
        String storeName = valuesHolder.storeName;
        CatalogId catalogId = valuesHolder.catalogId;
        Locale locale = valuesHolder.locale;
        Currency currency = valuesHolder.currency;

        MockStoreContextBuilder builder = MockStoreContextBuilder.from(connection, siteId);

        if (storeId!=null) {
            if (StringUtils.isBlank(storeId)) {
                throw new InvalidContextException("storeId has wrong format: \""+storeId+"\"");
            }

            builder.withStoreId(storeId);
        }

        if (storeName!=null) {
            if (StringUtils.isBlank(storeName)) {
                throw new InvalidContextException("storeName has wrong format: \""+storeId+"\"");
            }

            builder.withStoreName(storeName);
        }

        if (catalogId!=null) {
            builder.withCatalogId(catalogId);
        }

        if (currency!=null) {
            builder.withCurrency(currency);
        }

        if (locale!=null) {
            builder.withLocale(locale);
        }

        return builder.build();
    }


    private StoreContext buildContextFromRepositoryStoreConfig(Site site, Map<String, Object> repositoryStoreConfig) {
        Map<String, Object> targetConfig = new HashMap<>();

        findConfigId(repositoryStoreConfig)
                .map(this::readStoreConfigFromSpring)
                .ifPresent(targetConfig::putAll);

        updateStoreConfigFromRepository(repositoryStoreConfig, targetConfig);

        StoreContextValuesHolder valuesHolder = populateValuesHolder(targetConfig, site);

        return createStoreContext(valuesHolder);
    }


    @Override
    protected Optional<StoreContext> internalCreateContext(Site site) {
        // Only create store context if settings are found for current site.
        return Optional.of(findRepositoryStoreConfig(site))
                .filter(config -> !config.isEmpty())
                .map(config -> buildContextFromRepositoryStoreConfig(site, config));
    }

}
