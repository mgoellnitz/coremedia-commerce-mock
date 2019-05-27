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

import com.coremedia.blueprint.base.livecontext.ecommerce.common.AbstractCommerceBean;
import com.coremedia.livecontext.ecommerce.common.CommerceId;
import com.coremedia.xml.Markup;
import com.coremedia.xml.MarkupFactory;
import de.provocon.coremedia.commerce.mock.rest.documents.AbstractMockDocument;
import java.util.Locale;
import lombok.Setter;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * Intermediate base class to extend commerce beans with generic methods needed
 * when dealing with the mock catalog commerce backend.
 */
public abstract class AbstractMockCommerceBean extends AbstractCommerceBean {

    @Setter
    private AbstractMockDocument delegate;


    /**
     * Retrieve the locale for this entity from the associated store context.
     *
     * @return locale for this entity
     */
    @Override
    public Locale getLocale() {
        return getContext().getLocale();
    }


    /**
     * Fetch or lookup the ID used externally for this entity.
     *
     * @return the technical ID for the entity in the external - thus commerce - system
     */
    @Override
    public String getExternalId() {
        // Despite the mock character of this implementation go all the way
        // through the infrastrucuter of CoreMedia Content Cloud.
        CommerceId commerceId = getId();
        return commerceId.getExternalId().orElseGet(() -> commerceId.getTechId().orElse(null));
    }


    /**
     * In our simple case the technical external ID can the the same as the external ID.
     *
     * @return the technical ID for the entity in the external - thus commerce - system
     */
    @Override
    public String getExternalTechId() {
        return getExternalId();
    }

    protected static Markup buildRichtextMarkup(String str, boolean escapeXml) {
        StringBuilder sb = new StringBuilder(256);
        sb.append("<div xmlns=\"http://www.coremedia.com/2003/richtext-1.0\" xmlns:xlink=\"http://www.w3.org/1999/xlink\">");  // NOSONAR
        if (StringUtils.isNotBlank(str)) {
            if (escapeXml) {
                // Remove trailing and ending <p> tags!
                if (str.startsWith("<p>")) {
                    str = str.replaceFirst("<p>", "");
                }

                if (str.endsWith("</p>")) {
                    str = str.substring(0, str.lastIndexOf("</p>"));
                }

                sb.append("<p>");
                sb.append(StringEscapeUtils.escapeXml(str));
                sb.append("</p>");
            } else {
                if (!str.startsWith("<p>")) {
                    sb.append("<p>");
                }
                sb.append(str);
                if (!str.endsWith("</p>")) {
                    sb.append("</p>");
                }
            }

        }
        sb.append("</div>");
        return MarkupFactory.fromString(sb.toString());
    }

}
