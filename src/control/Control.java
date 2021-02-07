package control;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import model.CalcString;
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
	
	private ScreenString screenString;
	
	private String operator = "";
	
	public String getOperator() {
		return this.operator;
	}
	
	// private boolean commaWaiting = false;
	
	private boolean decimalNumber = false;
	
	private boolean negativeNumber = false;
	
	private boolean initState = true;
	
	
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
		this.screenString = new ScreenString(this);
	}
	
	private void resetCalculator() {
		this.calcString1.setString(ZERO);
		this.calcString2.emptyString();
		this.operator = "";
		// this.commaWaiting = false;
		this.decimalNumber = false;
		this.negativeNumber = false;
		this.initState = true;
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
			break;
		// Comma
		case COMMA :
			pressedComma();
			break;
		// Plus Minus
		case PLUSMINUS :
			makeNumberNegative(); 
			break;
		// Backspace
		case BACKSPACE :
			pressedBackspace();
			break;
		// Operators
		case DIVIDE :
		case MULTIPLY :
		case MINUS :
		case PLUS :
			this.operator = buttonClicked;
			calcString2.setString(calcString1.getString());
			calcString1.setString(ZERO);
			initState = true;
			negativeNumber = false;
			decimalNumber = false;
			// commaWaiting = false;
			break;
		
		// CE and C
		case CE :
			calcString1.setString(ZERO);
			initState = true;
			break;
		case C :
			resetCalculator();
			break;
		}
		this.screenString.updateScreenString();
		
	}

	private void pressedComma() {
		// this.commaWaiting = !this.commaWaiting;
		if (initState) {
			initState = false;
		}
		calcString1.addString(COMMA);
		decimalNumber = true;
	}

	private void pressedZero(String buttonClicked) {
		if (!initState) {
			calcString1.addString(buttonClicked);
		} 
//		else if (initState && commaWaiting) {
//			calcString1.setString(ZERO+COMMA+buttonClicked);
//			decimalNumber = true;
//			commaWaiting = false;
//			initState = false;
//		}
	}

	private void numberPressed(String buttonClicked) {
		
		if (initState) {
			if (commaWaiting) {
				calcString1.setString(ZERO+COMMA+buttonClicked);
				commaWaiting = false;
				decimalNumber = true;
			} else {
				calcString1.setString(buttonClicked);
			}
			initState = false;
		} else {
			if (commaWaiting && !decimalNumber) {
				calcString1.addString(COMMA+buttonClicked);
				commaWaiting = false;
				decimalNumber = true;
			} else {
				calcString1.addString(buttonClicked);
			}
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
				System.out.println("CalcString1 = "+calcString1.getString());
				System.out.println("Length = "+calcString1.getString().length());
				System.out.println("***"+calcString1.getString()+"***");
				//if (calcString1.getString().charAt(calcString1.getString().length()-1)== '0') {
				if (calcString1.getString() == ZERO) {
					
					initState = true;
				}
			}
		}
	}
	
//	private void pressedBackspace() {
//		if (!initState) {
//			String numberToTreat = calcString1.getString();
//			if (numberToTreat.length() == 1) {
//				calcString1.setString(ZERO);
//				initState = true;
//			} else {
//				calcString1.setString(numberToTreat.substring(0, (calcString1.getString().length() - 1)));
//				if (calcString1.getString().charAt(calcString1.getString().length()-1)== '.') {
//					calcString1.setString(numberToTreat.substring(0, (calcString1.getString().length() - 1)));
//					decimalNumber = false;
//				}
//			}
//		}
//	}
}
