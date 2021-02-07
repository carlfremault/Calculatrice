package model;

import control.Control;
import control.Global;

public class CalcString implements Global {
	
	private Control control;
	
	private String string;
	
	public CalcString(Control control) {
		this.control = control;
		this.string = "";
	}
	
	public void emptyString() {
		this.string = "";
	}
	
	public void setString(String string) {
		this.string = "";
		this.string = string;
	}
	
	public String getString() {
		return removeZero(string);
	}
	
	public String getStringForScreen() {
		return removeDot(removeZero(string));
	}

	public Float getOperand() {
		return Float.parseFloat(this.string);
	}
	
	public void addString(String string) {
			this.string = removeZero(this.string) + string;
	}
	
	public String removeZero(String string) {
		if (string.endsWith(".0")) {
			string = string.substring(0, string.length()-2);
		} 
		return string;
	}
	
	public String removeDot(String string) {
		if (string.endsWith(".")) {
			string = string.substring(0, string.length()-1);
		} 
		return string;
	}


}
