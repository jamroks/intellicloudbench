/*
* This file is part of IntelliCloudBench.
*
* Copyright (c) 2012, Jan Gerlinger <jan.gerlinger@gmx.de>
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
* * Redistributions of source code must retain the above copyright
* notice, this list of conditions and the following disclaimer.
* * Redistributions in binary form must reproduce the above copyright
* notice, this list of conditions and the following disclaimer in the
* documentation and/or other materials provided with the distribution.
* * Neither the name of the Institute of Applied Informatics and Formal
* Description Methods (AIFB) nor the names of its contributors may be used to
* endorse or promote products derived from this software without specific prior
* written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
* ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
* DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package edu.kit.aifb.IntelliCloudBench.ui.tree;

import java.text.DecimalFormatSymbols;
import java.util.Locale;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

import de.essendi.vaadin.ui.component.numberfield.NumberField;
import edu.kit.aifb.libIntelliCloudBench.model.HardwareType;
import edu.kit.aifb.libIntelliCloudBench.model.InstanceType;
import edu.kit.aifb.libIntelliCloudBench.model.json.CostsStore;
import edu.kit.aifb.libIntelliCloudBench.model.json.CostsStore.Costs;

public class HardwareTypeLeafComponent extends CustomComponent {
	private static final long serialVersionUID = 8016963044836992691L;

	@AutoGenerated
	private HorizontalLayout mainLayout;

	@AutoGenerated
	private CheckBox checkBox;

	private final RegionNodeComponent parent;

	private final HardwareType hardwareType;

	private Label cpusLabel;
	private Label ramLabel;
	private Label volumesLabel;

	private NumberField variableCostsField;
	private NumberField fixedCostsField;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	public HardwareTypeLeafComponent(RegionNodeComponent parent, HardwareType hardwareType) {
		this.parent = parent;
		this.hardwareType = hardwareType;

		buildMainLayout();
		setCompositionRoot(mainLayout);

		update();
	}

	private void update() {
		checkBox.setCaption(hardwareType.getName());
		cpusLabel.setValue(hardwareType.getCpusAsString());
		ramLabel.setValue(hardwareType.getRamAsString());
		volumesLabel.setValue(hardwareType.getVolumesAsString());
	}

	@AutoGenerated
	private HorizontalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new HorizontalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(false);
		mainLayout.setSpacing(true);

		// top-level component properties
		setWidth("100.0%");
		setHeight("-1px");

		// checkBox
		checkBox = new CheckBox();
		checkBox.setCaption("CheckBox");
		checkBox.setStyleName("big_checkbox");
		checkBox.setImmediate(false);
		checkBox.setWidth("100%");
		checkBox.setHeight("-1px");
		mainLayout.addComponent(checkBox);
		mainLayout.setExpandRatio(checkBox, 25);

		cpusLabel = new Label();
		cpusLabel.setWidth("100%");
		mainLayout.addComponent(cpusLabel);
		mainLayout.setExpandRatio(cpusLabel, 25);

		ramLabel = new Label();
		ramLabel.setWidth("100%");
		mainLayout.addComponent(ramLabel);
		mainLayout.setExpandRatio(ramLabel, 10);

		volumesLabel = new Label();
		volumesLabel.setWidth("100%");
		mainLayout.addComponent(volumesLabel);
		mainLayout.setComponentAlignment(volumesLabel, Alignment.TOP_LEFT);
		mainLayout.setExpandRatio(volumesLabel, 10);

		Costs costs =
		    CostsStore.getInstance().getCosts(
		        parent.getParentNode().getModelBean().getId(),
		        parent.getModelBean().getId(),
		        hardwareType.getId());

		variableCostsField = new NumberField();
		variableCostsField.setWidth("100%");
		variableCostsField.setCaption("Variable $/hour:");
		variableCostsField.setDecimalAllowed(true);
		variableCostsField.setDecimalPrecision(3);
		variableCostsField.setDecimalSeparator(DecimalFormatSymbols.getInstance(Locale.US).getDecimalSeparator());
		variableCostsField.setDecimalSeparatorAlwaysShown(true);
		variableCostsField.setMinimumFractionDigits(3);
		variableCostsField.setNegativeAllowed(false);
		variableCostsField.setValue(costs.getVariableCosts());
		variableCostsField.setTextChangeEventMode(TextChangeEventMode.LAZY);
		variableCostsField.addListener(new ValueChangeListener() {
			private static final long serialVersionUID = -7088189234848436526L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				CostsStore.getInstance().setVariableCosts(
				    parent.getParentNode().getModelBean().getId(),
				    parent.getModelBean().getId(),
				    hardwareType.getId(),
				    variableCostsField.getDoubleValueDoNotThrow());
			}

		});
		mainLayout.addComponent(variableCostsField);
		mainLayout.setExpandRatio(variableCostsField, 15);

		fixedCostsField = new NumberField();
		fixedCostsField.setWidth("100%");
		fixedCostsField.setCaption("Fixed $/month:");
		fixedCostsField.setDecimalAllowed(true);
		fixedCostsField.setDecimalPrecision(2);
		fixedCostsField.setDecimalSeparator(DecimalFormatSymbols.getInstance(Locale.US).getDecimalSeparator());
		fixedCostsField.setDecimalSeparatorAlwaysShown(true);
		fixedCostsField.setMinimumFractionDigits(2);
		fixedCostsField.setNegativeAllowed(false);
		fixedCostsField.setValue(costs.getFixedCosts());
		fixedCostsField.setTextChangeEventMode(TextChangeEventMode.LAZY);
		fixedCostsField.addListener(new ValueChangeListener() {
			private static final long serialVersionUID = 6441765614783798210L;

			@Override
			public void valueChange(ValueChangeEvent event) {
				CostsStore.getInstance().setFixedCosts(
				    parent.getParentNode().getModelBean().getId(),
				    parent.getModelBean().getId(),
				    hardwareType.getId(),
				    fixedCostsField.getDoubleValueDoNotThrow());
			}

		});
		mainLayout.addComponent(fixedCostsField);
		mainLayout.setExpandRatio(fixedCostsField, 15);

		return mainLayout;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hardwareType == null) ? 0 : hardwareType.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HardwareTypeLeafComponent other = (HardwareTypeLeafComponent) obj;
		if (hardwareType == null) {
			if (other.hardwareType != null)
				return false;
		} else if (!hardwareType.equals(other.hardwareType))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		return true;
	}

	public boolean isChecked() {
		return (Boolean) checkBox.getValue();
	}

	public InstanceType getInstanceType() {
		return new InstanceType(parent.getParentNode().getModelBean(), parent.getModelBean(), hardwareType);
	}

	public void setChecked(boolean checked) {
		checkBox.setValue(checked);
	}

}
