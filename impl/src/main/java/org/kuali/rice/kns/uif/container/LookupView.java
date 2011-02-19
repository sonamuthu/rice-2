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
package org.kuali.rice.kns.uif.container;

import java.util.Arrays;
import java.util.List;

import org.kuali.rice.kns.uif.Component;
import org.kuali.rice.kns.uif.UifConstants.ViewType;
import org.kuali.rice.kns.uif.layout.GridLayoutManager;

/**
 * TODO delyea: Fill in javadocs here
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class LookupView extends FormView {
	private static final long serialVersionUID = 716926008488403616L;

	private Class<?> objectClassName;

	private Group criteriaGroup;
	private CollectionGroup resultsGroup;
	private Group formGroup;

	private List<Component> criteriaFields;
	private List<Component> resultFields;

	public LookupView() {
		super();
		setViewTypeName(ViewType.LOOKUP);
	}

	/**
	 * <p>
	 * The following initialization is performed:
	 * <ul>
	 * <li>Set the abstractTypeClasses map for the lookup object path</li>
	 * </ul>
	 * </p>
	 * 
	 * @see org.kuali.rice.kns.uif.container.ContainerBase#performInitialization(org.kuali.rice.kns.uif.container.View)
	 */
	@Override
	public void performInitialization(View view) {
		initializeGroups();
		if (getItems().isEmpty()) {
			setItems(Arrays.asList(getCriteriaGroup(), getResultsGroup(), getFormGroup()));
		}
		super.performInitialization(view);

		getAbstractTypeClasses().put(getDefaultBindingObjectPath(), objectClassName);
	}

	protected void initializeGroups() {
		if ((getCriteriaGroup() != null) && (getCriteriaGroup().getItems().isEmpty())) {
			getCriteriaGroup().setItems(getCriteriaFields());
		}
		if (getResultsGroup() != null) {
			if ( (getResultsGroup().getItems().isEmpty()) && (getResultFields() != null) ) {
				getResultsGroup().setItems(getResultFields());
				((GridLayoutManager)getResultsGroup().getLayoutManager()).setNumberOfColumns(getResultFields().size());
			}
			if (getResultsGroup().getCollectionObjectClass() == null) {
				getResultsGroup().setCollectionObjectClass(getObjectClassName());
			}
		}
	}

	/**
	 * Class name for the object the lookup applies to
	 * 
	 * <p>
	 * The object class name is used to pick up a dictionary entry which will
	 * feed the attribute field definitions and other configuration. In addition
	 * it is to configure the <code>Lookupable</code> which will carry out the
	 * lookup action
	 * </p>
	 * 
	 * @return Class<?> lookup object class
	 */
	public Class<?> getObjectClassName() {
		return this.objectClassName;
	}

	/**
	 * Setter for the object class name
	 * 
	 * @param objectClassName
	 */
	public void setObjectClassName(Class<?> objectClassName) {
		this.objectClassName = objectClassName;
	}

	public Group getCriteriaGroup() {
		return this.criteriaGroup;
	}

	public void setCriteriaGroup(Group criteriaGroup) {
		this.criteriaGroup = criteriaGroup;
	}

	public CollectionGroup getResultsGroup() {
		return this.resultsGroup;
	}

	public void setResultsGroup(CollectionGroup resultsGroup) {
		this.resultsGroup = resultsGroup;
	}

	public List<Component> getCriteriaFields() {
		return this.criteriaFields;
	}

	public void setCriteriaFields(List<Component> criteriaFields) {
		this.criteriaFields = criteriaFields;
	}

	public Group getFormGroup() {
		return this.formGroup;
	}

	public void setFormGroup(Group formGroup) {
		this.formGroup = formGroup;
	}

	public List<Component> getResultFields() {
		return this.resultFields;
	}

	public void setResultFields(List<Component> resultFields) {
		this.resultFields = resultFields;
	}

}
