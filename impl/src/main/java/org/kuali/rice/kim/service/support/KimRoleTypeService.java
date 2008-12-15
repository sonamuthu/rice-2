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
package org.kuali.rice.kim.service.support;

import java.util.List;

import org.kuali.rice.kim.bo.role.dto.RoleMembershipInfo;
import org.kuali.rice.kim.bo.types.dto.AttributeSet;

/**
 * This is a service interface that must be used for a service related to a role type.
 * 
 * Is it used to interpret the qualifiers which may be attached.
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
public interface KimRoleTypeService extends KimTypeService {

                
    /** Return whether a role assignment with the given qualifier is applicable for the given qualification. 
     * 
     * For example, the qualifier for a role could be as follows:
     *   chartOfAccountsCode = BL
     *   organizationCode = ARSC
     *   descendsHierarchy = true
     *   
     * The qualification could be:
     *   chartOfAccountsCode = BL
     *   organizationCode = PSY    (reports to BL-ARSC)
     *   
     * This method would return true for this set of arguments.  This would require a query of 
     * the KFS organization hierarchy, so an implementation of this sort must be done by
     * a service which lives within KFS and will be called remotely by KIM.
     * 
     * The contents of the passed in attribute sets should not be modified as they may be used in future calls by
     * the role service.
     */
    boolean doesRoleQualifierMatchQualification( AttributeSet qualification, AttributeSet roleQualifier );

    /** Same as {@link #doesRoleQualifierMatchQualification(AttributeSet, AttributeSet)} except that it takes a list of qualifiers to check.
     */
    List<RoleMembershipInfo> doRoleQualifiersMatchQualification( AttributeSet qualification, List<RoleMembershipInfo> roleMemberList );

    /**
     * Returns true if this role type represents an "application" role type.  That is, the members of the 
     * role are known to the host application, not to KIM.  This is needed for cases like the KFS
     * Fiscal Officer, where the members of the role are in the Account table in the KFS database. 
     */
    boolean isApplicationRoleType();
    
    /**
     * Returns a list of principal IDs corresponding to the given application role.  These principal IDs 
     * would be returned from the implementing application.
     * 
     * Continuing the example from {@link #isApplicationRoleType()}, the qualification in that case would be
     * a chart code and account number.  This service would use that information to retrieve the Fiscal Officer
     * from the account table.
     * 
     * The contents of the passed in attribute sets should not be modified as they may be used in future calls by
     * the role service.
     * 
     * @see #isApplicationRoleType()
     */
    List<String> getPrincipalIdsFromApplicationRole( String namespaceCode, String roleName, AttributeSet qualification );

    /**
     * Returns a list of group IDs corresponding to the given application role.  These group IDs 
     * would be returned from the implementing application.
     * 
     * @see #isApplicationRoleType()
     * @see #getPrincipalIdsFromApplicationRole(String, String, AttributeSet)
     */
    List<String> getGroupIdsFromApplicationRole( String namespaceCode, String roleName, AttributeSet qualification );
    
    /**
     * This method can be used to check if the given principal has this application role.  It is designed to be used in case
     * there is a more efficient way to check for whether a principal is in a role rather than retrieving all the
     * members of the role and checking against that.
     * 
     * The groupIds parameter is intended to be the complete list of groups to which the principal belongs.  If either the
     * principalId or the groupIds parameters are blank/empty, that parameter should be ignored.
     * 
     * @see #isApplicationRoleType()
     * @see #getPrincipalIdsFromApplicationRole(String, String, AttributeSet)
     * @see #getGroupIdsFromApplicationRole(String, String, AttributeSet)
     */
    boolean hasApplicationRole( String principalId, List<String> groupIds, String namespaceCode, String roleName, AttributeSet qualification );
    
    /**
     * This method would return all qualifications that the given qualification implies. (down)
     */
    List<AttributeSet> getAllImpliedQualifications( AttributeSet qualification );

    /**
     * This method would return all qualifications that imply this qualification. (up)
     * 
     * The contents of the passed in attribute set should not be modified as they may be used in future calls by
     * the role service.
     * 
     * TODO: 
     * Allowing?
     * Allowed?
     * Granting?
     */
    List<AttributeSet> getAllImplyingQualifications( AttributeSet qualification );
    // TODO: need list versions of the implyed/ing methods?
    
    /**
     * Convert a set of attributes that need to be converted.  For example,
     * this method could take [chart=BL,org=PSY] and return [campus=BLOOMINGTON]
     * if this role was based on the campus and the role assigned to it was based 
     * on organization.
     * 
     * The contents of the passed in attribute set should not be modified as they may be used in future calls by
     * the role service.
     * 
     */
    AttributeSet convertQualificationAttributesToRequired( AttributeSet qualificationAttributes );
    
    /**
     * Takes the passed in qualifications and converts them, if necessary, for any downstream roles which may be present.
     */
    AttributeSet convertQualificationForMemberRoles( String namespaceCode, String roleName, AttributeSet qualification );
    
}
