/*
 * Copyright 2006-2007 The Kuali Foundation
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
package org.kuali.rice.shareddata.framework.campus;

import org.kuali.rice.core.util.ConcreteKeyValue;
import org.kuali.rice.core.util.KeyValue;
import org.kuali.rice.krad.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.shareddata.api.campus.Campus;
import org.kuali.rice.shareddata.api.campus.CampusService;
import org.kuali.rice.shareddata.api.services.SharedDataApiServiceLocator;

import java.util.ArrayList;
import java.util.List;

/**
 * This class...
 * 
 * 
 */
public class CampusValuesFinder extends KeyValuesBase {
	
    /*
     * @see org.kuali.keyvalues.KeyValuesFinder#getKeyValues()
     */
	@Override
	public List<KeyValue> getKeyValues() {
				
            CampusService campusService = SharedDataApiServiceLocator.getCampusService();
            List<Campus> campuses = campusService.findAllCampuses();
            List<KeyValue> labels = new ArrayList<KeyValue>();
            labels.add(new ConcreteKeyValue("", ""));
            for ( Campus campus : campuses ) {
                labels.add(new ConcreteKeyValue(campus.getCode(), campus.getCode() + " - " + campus.getName()));
            }
            return labels;
    }
}
