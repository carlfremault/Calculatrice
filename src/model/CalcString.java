package model;

import control.Control;
import control.Global;

public class CalcString implements Global {
	
	private Control control;
	
	private String string;
	
	private float operand;
	
	public void emptyString() {
		this.string = "";
	}
	
	public void setString(String string) {
		this.string = "";
		this.string = string;
		this.operand = Float.parseFloat(string);
	}
	
	public String getString() {
		return this.string;
	}
	
	public Float getOperand() {
		return this.operand;
	}
	
	public void addString(String string) {
		switch (string) {
		case COMMA :
			this.string = this.string + string;
			this.operand = Float.parseFloat(string+ZERO);
			break;
		default :
			this.string = this.string + string;
			this.operand = Float.parseFloat(string);
			break;
		}
	
	}
	
	public CalcString(Control control) {
		this.control = control;
		this.string = "";
	}

}
