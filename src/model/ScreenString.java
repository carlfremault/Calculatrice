package model;

import control.Control;
import control.Global;

public class ScreenString implements Global {
	
	private Control control;

	private String stringUpper;
	private String stringLower;
	private String operator;

	public ScreenString(Control control) {
		this.control = control;
		this.stringUpper = "";
		this.stringLower = "";
		this.operator = "";
	}
	
	public void updateScreenString() {
			this.stringLower = control.getCalcString1().getString();
			this.stringUpper = control.getCalcString2().getString();
			this.operator = control.getOperator();
			
			this.control.updateScreen(stringUpper+" "+operator, UPPER);
			this.control.updateScreen(stringLower, LOWER);
	}
}
