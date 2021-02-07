package control;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.CalcString;
import model.Operator;
import model.ScreenString;
import view.FrmCalculatrice;

public class Control implements Global {
	
	private FrmCalculatrice frmCalculatrice;
	
	private CalcString calcString1;
	
	private CalcString calcString2;
	
	public CalcString getCalcString1() {
		return this.calcString1;
	}
	
	public CalcString getCalcString2() {
		return this.calcString2;
	}
	
	private String resultString;
	
	private Float operand1;
	
	private Float operand2;
	
	private Float result;
	
	private ScreenString screenString;
	
	private Operator operator;
	
//	private Operator previousOperator;
//	
//	private Operator nextOperator;
	
	
	private boolean decimalNumber = false;
	
	private boolean negativeNumber = false;
	
	private boolean initState = true;
	
//	private boolean equalsPressed = false;
//	
//	private boolean chainCalculation = false;
//	
//	public boolean getChainCalculation() {
//		return chainCalculation;
//	}
	
	
	public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				new Control();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}
	
	private Control() {
		this.frmCalculatrice = new FrmCalculatrice(this);
		this.frmCalculatrice.setVisible(true);
		this.initializeCalculator();
	}
	
	private void initializeCalculator() {
		this.calcString1 = new CalcString(this);
		this.calcString1.setString(ZERO);
		this.calcString2 = new CalcString(this);
//		this.previousOperator = new Operator(this);
//		this.nextOperator = new Operator (this);
		this.operator = new Operator(this);
		this.screenString = new ScreenString(this, this.operator);
		this.resultString = "";
//		this.previousOperator.setOperator("");
	}
	
	private void resetCalculator() {
		this.calcString1.setString(ZERO);
		this.calcString2.emptyString();
		this.operator.setOperator("");
		this.decimalNumber = false;
		this.negativeNumber = false;
		this.initState = true;
//		this.chainCalculation = false;
		this.resultString = "";
		this.screenString.updateScreenString();
	}
	
	public void updateScreen(String string, String position) {
		this.frmCalculatrice.setLabelText(string, position);
	}
	
	public void evenementFrmCalculatrice(MouseEvent e) {
		
		String buttonClicked = ((JButton)e.getSource()).getText();
		
		/**
		 * REMOVE THIS CONSOLE OUTPUT
		 */
		System.out.println(buttonClicked);
		
		
		switch(buttonClicked) {
		// Numbers
		case ZERO :
			pressedZero(buttonClicked);
			this.screenString.updateScreenString();
			break;
		case ONE :
		case TWO :
		case THREE :
		case FOUR :
		case FIVE :
		case SIX :
		case SEVEN :
		case EIGHT :
		case NINE :
			numberPressed(buttonClicked);
			this.screenString.updateScreenString();
			break;
		// Comma
		case COMMA :
			pressedComma();
			this.screenString.updateScreenString();
			break;
		// Plus Minus
		case PLUSMINUS :
			makeNumberNegative(); 
			this.screenString.updateScreenString();
			break;
		// Backspace
		case BACKSPACE :
			pressedBackspace();
			this.screenString.updateScreenString();
			break;
		// Operators
		case DIVIDE :
		case MULTIPLY :
		case MINUS :
		case PLUS :
//			if (!chainCalculation) {
				this.operator.setOperator(buttonClicked);			
				calcString2.setString(calcString1.getString());
//				calcString1.setString(ZERO);
				this.screenString.updateScreenString();
				initState = true;
				negativeNumber = false;
				decimalNumber = false;
//				this.chainCalculation = true;
//			} else {
//				this.nextOperator.setOperator(buttonClicked);
//				calculateEquals();
//				this.screenString.updateScreenString();
//			}
			
			break;
		// Equals
		case EQUALS:
			calculateEquals();
			this.screenString.updateScreenString();
			break;
		// CE and C
		case CE :
			calcString1.setString(ZERO);
			initState = true;
			this.screenString.updateScreenString();
			break;
		case C :
			resetCalculator();
			this.screenString.updateScreenString();
			break;
		}
//		this.screenString.updateScreenString();
	}

	private void calculateEquals() {
		if (!this.calcString2.getString().equals("")) {
//			if (this.operator.getOperator().equals("")) {
//				this.operator = previousOperator;
//			}
			String operation = this.operator.getOperator();
			this.operand1 = this.calcString1.getOperand();
//			if (!chainCalculation) {
				this.operand2 = this.calcString2.getOperand();
//			}
			switch (operation) {
			case DIVIDE:
				if (operand1 != 0) {
					this.result = (operand2 / operand1);
//					if (!chainCalculation) {
						this.resultString = calcString2.getString() + " " + operator.getOperator() + " "
								+ calcString1.getString() + " =";
						
//					} else {
//						this.resultString = calcString2.getString() + " " + operator.getOperator() + " "
//								+ calcString1.getString() + " " + nextOperator.getOperator()+"thisone";
//						System.out.println(resultString);
////						this.calcString1.setString(ZERO);
//						this.operator.setOperator(this.nextOperator.getOperator());
//					}
					this.calcString1.setString(result.toString());
					this.calcString2.setString(resultString);
					
//					this.previousOperator.setOperator(operation);
//					this.operator.setOperator("");
					this.operand2 = result;
				} else {
					JOptionPane.showMessageDialog(null, "Impossible de diviser par zéro");
				}
				break;
			case MULTIPLY:
				this.result = (operand2 * operand1);
				this.resultString = calcString2.getString() + " " + operator.getOperator() + " "
						+ calcString1.getString() + " =";
				this.calcString2.setString(resultString);
				this.calcString1.setString(result.toString());
				this.operator.setOperator("");
				this.operand2 = result;
				break;
			case MINUS:
				this.result = (operand2 - operand1);
				this.resultString = calcString2.getString() + " " + operator.getOperator() + " "
						+ calcString1.getString() + " =";
				this.calcString2.setString(resultString);
				this.calcString1.setString(result.toString());
				this.operator.setOperator("");
				this.operand2 = result;
				break;
			case PLUS:
				this.result = (operand2 + operand1);
				this.resultString = calcString2.getString() + " " + operator.getOperator() + " "
						+ calcString1.getString() + " =";
				this.calcString2.setString(resultString);
				this.calcString1.setString(result.toString());
				this.operator.setOperator("");
				this.operand2 = result;
				break;
			} // end of switch(position)
//			this.chainCalculation = true;
			if (result != null) {
				if (result <= 0) {
					this.negativeNumber = true;
				} else {
					this.negativeNumber = false;
				}
				if (result % 1 != 0) {
					this.decimalNumber = true;
				} else {
					this.decimalNumber = false;
				} 
			}
		}
	}

	private void pressedComma() {
		if (!decimalNumber) {
			if (initState) {
				initState = false;
			}
			calcString1.addString(COMMA);
			decimalNumber = true;
		}
	}

	private void pressedZero(String buttonClicked) {
		if (!initState) {
			calcString1.addString(buttonClicked);
		} 
	}

	private void numberPressed(String buttonClicked) {
		if (initState) {
				calcString1.setString(buttonClicked);
			initState = false;
		} else {
				calcString1.addString(buttonClicked);
		}
	}

	private void makeNumberNegative() {
		
		if (!initState) {
			String number = calcString1.getString();
			
			if (negativeNumber) {
				number = number.substring(1);
				negativeNumber = false;
			} else {
				number = NEGATIVE+number;
				negativeNumber = true;
			}
			calcString1.setString(number);
		}
	}
	
	private void pressedBackspace() {
		if (!initState) {
			String numberToTreat = calcString1.getString();
			if (numberToTreat.length() == 1) {
				calcString1.setString(ZERO);
				initState = true;
			} else {
				if (calcString1.getString().charAt(calcString1.getString().length()-1)== '.') {
					decimalNumber = false;
				}
				calcString1.setString(numberToTreat.substring(0, (calcString1.getString().length() - 1)));
				if (calcString1.getString().equals(ZERO)) {
					initState = true;
				}
			}
		}
	}
}
