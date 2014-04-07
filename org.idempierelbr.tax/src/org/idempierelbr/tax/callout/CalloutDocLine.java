package org.idempierelbr.tax.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.idempierelbr.tax.model.MLBRDocLineCOFINS;
import org.idempierelbr.tax.model.MLBRDocLineICMS;
import org.idempierelbr.tax.model.MLBRDocLinePIS;

public class CalloutDocLine implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		
		if (mTab.getTableName().equals(MLBRDocLineICMS.Table_Name))
			if (mField.getColumnName().equals(MLBRDocLineICMS.COLUMNNAME_LBR_ICMSRegime)) {
				return resetTaxStatusFields(ctx, mTab, value);
			} else if (mField.getColumnName().equals(MLBRDocLineICMS.COLUMNNAME_LBR_ICMS_TaxStatusSN)) {
				return resetTaxStatusTNField(ctx, mTab, value);
			} else if (mField.getColumnName().equals(MLBRDocLineICMS.COLUMNNAME_LBR_ICMS_TaxStatusTN)) {
				return resetTaxStatusSNField(ctx, mTab, value);
			} else 
				return null;
		else if (mTab.getTableName().equals(MLBRDocLinePIS.Table_Name))
			if (mField.getColumnName().equals(MLBRDocLinePIS.COLUMNNAME_LBR_PIS_TaxStatus)) {
				return resetPISCalcTypeFields(ctx, mTab, value);
			} else 
				return null;
		else if (mTab.getTableName().equals(MLBRDocLineCOFINS.Table_Name))
			if (mField.getColumnName().equals(MLBRDocLineCOFINS.COLUMNNAME_LBR_COF_TaxStatus)) {
				return resetCOFINSCalcTypeFields(ctx, mTab, value);
			} else 
				return null;
		else
			return null;
	}
	
	/**
	 * Reset both Tax Status SN and TN fields. This is necessary to display fields correctly.
	 */
	private String resetTaxStatusFields(Properties ctx, GridTab mTab, Object value) {
		resetTaxStatusSNField(ctx, mTab, value);
		resetTaxStatusTNField(ctx, mTab, value);
		return null;
	}
	
	/**
	 * Reset Tax Status SN field. This is necessary to display fields correctly.
	 */
	private String resetTaxStatusSNField(Properties ctx, GridTab mTab, Object value) {
		mTab.setValue("LBR_ICMS_TaxStatusSN", null);
		return null;
	}
	
	/**
	 * Reset Tax Status TN field. This is necessary to display fields correctly.
	 */
	private String resetTaxStatusTNField(Properties ctx, GridTab mTab, Object value) {
		mTab.setValue("LBR_ICMS_TaxStatusTN", null);
		return null;
	}
	
	/**
	 * Reset both Calculation Type fields for tab PIS. This is necessary to display fields correctly.
	 */
	private String resetPISCalcTypeFields(Properties ctx, GridTab mTab, Object value) {
		mTab.setValue("CalculationType", null);
		mTab.setValue("LBR_PISST_CalcType", null);
		return null;
	}
	
	/**
	 * Reset both Calculation Type fields for tab COFINS. This is necessary to display fields correctly.
	 */
	private String resetCOFINSCalcTypeFields(Properties ctx, GridTab mTab, Object value) {
		mTab.setValue("CalculationType", null);
		mTab.setValue("LBR_COFST_CalcType", null);
		return null;
	}
}