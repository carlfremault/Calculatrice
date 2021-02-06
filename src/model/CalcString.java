package model;

import control.Control;

public class CalcString {
	
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
		this.string = this.string + string;
		this.operand = Float.parseFloat(string);
	}
	
	public CalcString(Control control) {
		this.control = control;
		this.string = "";
	}

}
