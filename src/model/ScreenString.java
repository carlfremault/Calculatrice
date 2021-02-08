package model;

import control.Control;
import control.Global;

public class ScreenString implements Global {
	
	private Control control;
	private Operator operator;

	private String stringUpper;
	private String stringLower;
	private String stringOperator;

	public ScreenString(Control control, Operator operator) {
		this.control = control;
		this.operator = operator;
		this.stringUpper = "";
		this.stringLower = "";
		this.stringOperator = this.operator.getOperator();
	}
	
	public void updateScreenString() {
			this.stringLower = control.getCalcString1().getString();
			this.stringUpper = control.getCalcString2().getString();
			this.stringOperator = this.operator.getOperator();
			this.control.updateScreen(stringUpper+" "+stringOperator, UPPER);
			this.control.updateScreen(stringLower, LOWER);
	}
}
