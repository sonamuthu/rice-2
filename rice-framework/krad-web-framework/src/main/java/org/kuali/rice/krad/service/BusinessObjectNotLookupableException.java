/**
 * Copyright 2005-2015 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.krad.service;

import org.kuali.rice.core.api.exception.KualiException;

/**
 * Thrown when a BusinessObject is expected to implement Lookupable, but does not.
 *
 * @see org.kuali.rice.krad.lookup.Lookupable
 */
public class BusinessObjectNotLookupableException extends KualiException {
    public BusinessObjectNotLookupableException(String message) {
        super(message);
    }

    public BusinessObjectNotLookupableException(String message, boolean hideIncidentReport) {
        super(message, hideIncidentReport);
    }

    public BusinessObjectNotLookupableException(String message, Throwable t) {
        super(message, t);
    }

    public BusinessObjectNotLookupableException(Throwable t) {
        super(t);
    }
}