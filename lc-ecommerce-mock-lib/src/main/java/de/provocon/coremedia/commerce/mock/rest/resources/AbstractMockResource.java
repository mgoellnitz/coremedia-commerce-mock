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

import com.fasterxml.jackson.databind.ObjectMapper;
import de.provocon.coremedia.commerce.mock.rest.documents.AbstractMockDocument;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import lombok.extern.slf4j.Slf4j;


/**
 *
 */
@Slf4j
public abstract class AbstractMockResource {

    private static final String BASE_PATH = "de/provocon/coremedia/commerce/";


    /**
     * Get a document for a given path.
     *
     * @param <T> Type constraint for the result
     * @param path path to look document up
     * @param cls class to map result to
     * @return Instance of class cls mapped from the contents of the path
     */
    protected <T extends AbstractMockDocument> T getDocument(String path, Class<T> cls) {
        String absolutePath = BASE_PATH+path+".json";
        LOG.info("getDocument({})", absolutePath);
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(absolutePath);
        ObjectMapper om = new ObjectMapper();
        T result = null;
        try {
            result = om.readValue(inputStream, cls);
        } catch (IOException ioe) {
            LOG.error("getDocument()", ioe);
        }
        return result;
    }


    /**
     * Get all documents in a given path. TODO: Doesn't work so far.
     */
    protected <T extends AbstractMockDocument> List<T> getDocuments(String path, Class<T> cls) {
        String absolutePath = BASE_PATH+path;
        LOG.info("getDocument({})", absolutePath);
        List<T> result = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            Enumeration<URL> resources = loader.getResources(absolutePath);
            while (resources.hasMoreElements()) {
                URL u = resources.nextElement();
                LOG.info("getDocument() {}", u);
                InputStream inputStream = loader.getResourceAsStream(u.toString());
                T element = mapper.readValue(inputStream, cls);
                result.add(element);
            }
        } catch (IOException ioe) {
            LOG.error("getDocument()", ioe);
        }
        return result;
    }

}
