/*
 * Copyright 2007 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.iu.uis.eden.routetemplate;

import java.sql.Timestamp;
import java.util.Map;

import org.apache.log4j.Logger;

import edu.iu.uis.eden.engine.RouteContext;
import edu.iu.uis.eden.engine.node.RouteNodeInstance;
import edu.iu.uis.eden.engine.node.hierarchyrouting.HierarchyRoutingNode;
import edu.iu.uis.eden.exception.WorkflowException;
import edu.iu.uis.eden.routeheader.DocumentRouteHeaderValue;
import edu.iu.uis.eden.util.Utilities;

/**
 * Derives the rule name to select based on node instance state configured by a governing
 * HierarchyRoutingNode and the HierarchyRoutingNode name.  E.g.:
 * <pre>
 * &lt;dynamic name="hierarchy"&gt;
 *   &lt;type&gt;edu.iu.uis.eden.engine.node.hierarchyrouting.HierarchyRoutingNode&lt;/type&gt;
 *   &lt;hierarchyProvider&gt;edu.iu.uis.eden.engine.node.hierarchyrouting.SimpleHierarchyProvider&lt;/hierarchyProvider&gt;
 *   &lt;ruleSelector&gt;HierarchicalNamed&lt;/ruleSelector&gt;
 * &lt;/dynamic&gt;
 * </pre>
 * If <code>edu.iu.uis.eden.engine.node.hierarchyrouting.SimpleHierarchyProvider</code> supplied stops named 'a', 'b', and 'c', the rules
 * selected would be 'hierarchy-a', 'hierarchy-b', and 'hierarchy-c', respectively.
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 */
public class HierarchicalNamedRuleSelector extends NamedRuleSelector {
    private static final Logger LOG = Logger.getLogger(HierarchicalNamedRuleSelector.class);
    @Override
    protected String getName(RouteContext context, DocumentRouteHeaderValue routeHeader, RouteNodeInstance nodeInstance,
            String selectionCriterion, Timestamp effectiveDate) throws WorkflowException {
        Map<String, String> cfgMap = Utilities.getKeyValueCollectionAsMap(nodeInstance.getState());
        String stop_id = cfgMap.get(HierarchyRoutingNode.STOP_ID);
        if (stop_id == null) {
            LOG.error("STOP ID from nodeInstance was NULL: " + nodeInstance);
            return null;
        }
        LOG.error("STOP ID from nodeInstance: " + nodeInstance.getRouteNodeInstanceId() + ": " + stop_id);
        return nodeInstance.getProcess().getRouteNode().getRouteNodeName() + "-" + stop_id;
    }
}