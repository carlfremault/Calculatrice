package control;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import model.CalcString;
import model.ScreenString;
import view.FrmCalculatrice;

public class Control {
	
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
	
	private boolean commaWaiting = false;
	
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
		this.calcString1.setString("0");
		this.calcString2 = new CalcString(this);
		this.screenString = new ScreenString(this);
	}
	
	private void resetCalculator() {
		this.calcString1.setString("0");
		this.calcString2.emptyString();
		this.operator = "";
		this.commaWaiting = false;
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
		case "0" :
			pressedZero(buttonClicked);
			break;
		case "1" :
		case "2" :
		case "3" :
		case "4" :
		case "5" :
		case "6" :
		case "7" :
		case "8" :
		case "9" :
			numberPressed(buttonClicked);
			break;
		// Comma
		case "," :
			this.commaWaiting = !this.commaWaiting;
			break;
		// Plus Minus
		case "+/-" :
			makeNumberNegative(); 
			break;
		// Backspace
		case "←" :
			pressedBackspace();
			break;
		// Operators
		case "÷" :
		case "x" :
		case "-" :
		case "+" :
			this.operator = buttonClicked;
			calcString2.setString(calcString1.getString());
			calcString1.setString("0");
			initState = true;
			negativeNumber = false;
			decimalNumber = false;
			commaWaiting = false;
			break;
		
		// CE and C
		case "CE" :
			calcString1.setString("0");
			initState = true;
			break;
		case "C" :
			resetCalculator();
			break;
		}
		this.screenString.updateScreenString();
		
	}

	private void pressedZero(String buttonClicked) {
		if (!initState) {
			calcString1.addString(buttonClicked);
		} else if (initState && commaWaiting) {
			calcString1.setString("0."+buttonClicked);
			decimalNumber = true;
			commaWaiting = false;
			initState = false;
		}
	}

	private void numberPressed(String buttonClicked) {
		
		if (initState) {
			if (commaWaiting) {
				calcString1.setString("0."+buttonClicked);
				commaWaiting = false;
				decimalNumber = true;
			} else {
				calcString1.setString(buttonClicked);
			}
			initState = false;
		} else {
			if (commaWaiting && !decimalNumber) {
				calcString1.addString("."+buttonClicked);
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
				number = "-"+number;
				negativeNumber = true;
			}
			calcString1.setString(number);
		}
	}
	
	private void pressedBackspace() {
		if (!initState) {
			String numberToTreat = calcString1.getString();
			if (numberToTreat.length() == 1) {
				calcString1.setString("0");
				initState = true;
			} else {
				calcString1.setString(numberToTreat.substring(0, (calcString1.getString().length() - 1)));
				if (calcString1.getString().charAt(calcString1.getString().length()-1)== '.') {
					calcString1.setString(numberToTreat.substring(0, (calcString1.getString().length() - 1)));
					decimalNumber = false;
				}
			}
		}
	}
}
