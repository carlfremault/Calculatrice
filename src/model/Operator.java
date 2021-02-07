package model;

import control.Control;

public class Operator {

	private String operator;
	private Control control;
	
	public Operator(Control control) {
		this.control = control;
		this.operator = "";
	}
	
	public String getOperator() {
		return operator;
	}
	
	public void setOperator(String operator) {
		this.operator = operator;
	}
}
