package control;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.CalcString;
import model.Operator;
import view.FrmCalculatrice;

/**
 * Contrôleur, point d'entrée de l'application
 * 
 * @author Carl Fremault
 *
 */
public class Control implements Global {

	/**
	 * La vue associée
	 */
	private FrmCalculatrice frmCalculatrice;
	/**
	 * String pour définir première opérande
	 */
	private CalcString calcString1;
	/**
	 * String pour définir deuxième opérande
	 */
	private CalcString calcString2;

	/**
	 * Getter pour calcString1
	 * 
	 * @return calcString1
	 */
	public CalcString getCalcString1() {
		return this.calcString1;
	}

	/**
	 * Getter pour calcString2
	 * 
	 * @return calcString2
	 */
	public CalcString getCalcString2() {
		return this.calcString2;
	}

	/**
	 * String pour affichage calcul entier
	 */
	private String calculationString;
	/**
	 * Première opérande
	 */
	private BigDecimal operand1;
	/**
	 * Deuxième opérande
	 */
	private BigDecimal operand2;
	/**
	 * Résultat du calcul
	 */
	private BigDecimal result;
	/**
	 * Dernier opérateur entré
	 */
	private Operator operator;
	/**
	 * Avant-dernier opérateur entré
	 */
	private Operator previousOperator;
	/**
	 * Booléen qui définit si un nombre est décimal.
	 */
	private boolean decimalNumber = false;
	/**
	 * Booléen qui définit si la première opérande (et son String associé) est en
	 * état initial (0).
	 */
	private boolean initState = true;

	/**
	 * Main méthode, point d'entrée de l'application
	 * 
	 * @param args -
	 */
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

	/**
	 * Constructeur du contrôleur
	 */
	private Control() {
		this.frmCalculatrice = new FrmCalculatrice(this);
		this.frmCalculatrice.setVisible(true);
		this.initializeCalculator();
	}

	/**
	 * Méthode qui initialise la calculatrice.
	 */
	private void initializeCalculator() {
		this.calcString1 = new CalcString();
		this.calcString1.setString(ZERO);
		this.calcString2 = new CalcString();
		this.operator = new Operator();
		this.operator.setOperator("");
		this.previousOperator = new Operator();
		this.previousOperator.setOperator("");
		this.calculationString = new String();
	}

	/**
	 * Méthode pour réinitialiser la calculatrice.
	 */
	private void resetCalculator() {
		this.calcString1.setString(ZERO);
		this.calcString2.emptyString();
		this.operator.setOperator("");
		this.previousOperator.setOperator("");
		this.updateScreen(calcString1.getString(), LOWER);
		this.updateScreen("", UPPER);
		this.decimalNumber = false;
		this.initState = true;
		this.calculationString = "";
	}

	/**
	 * Méthode qui envoie une demande à la vue frmCalculatrice pour mettre à jour
	 * l'affichage.
	 * 
	 * @param string   = le message à afficher
	 * @param position = la position (UPPER ou LOWER) où afficher le message
	 */
	public void updateScreen(String string, String position) {
		this.frmCalculatrice.setLabelText(string, position);
	}

	/**
	 * Méthode qui reçoit évenement depuis la vue frmCalculatrice puis envoie sur
	 * les méthodes appropriés.
	 * 
	 * @param e = le bouton qui a été cliqué
	 */
	public void evenementFrmCalculatrice(MouseEvent e) {

		String buttonClicked = ((JButton) e.getSource()).getText();

		// CONSOLE OUTPUT activate for debugging
		System.out.println(buttonClicked);

		switch (buttonClicked) {
		// Numbers
		case ZERO:
			pressedZero(buttonClicked);
			this.updateScreen(calcString1.getString(), LOWER);
			break;
		case ONE:
		case TWO:
		case THREE:
		case FOUR:
		case FIVE:
		case SIX:
		case SEVEN:
		case EIGHT:
		case NINE:
			numberPressed(buttonClicked);
			this.updateScreen(calcString1.getString(), LOWER);
			break;
		// Comma
		case COMMA:
			pressedComma();
			this.updateScreen(calcString1.getString(), LOWER);
			break;
		// Plus Minus
		case PLUSMINUS:
			makeNumberNegative();
			this.updateScreen(calcString1.getString(), LOWER);
			break;
		// Backspace
		case BACKSPACE:
			pressedBackspace();
			this.updateScreen(calcString1.getString(), LOWER);
			break;
		// Operators
		case DIVIDE:
		case MULTIPLY:
		case MINUS:
		case PLUS:
			calculate(buttonClicked);
			break;
		case EQUALS:
			equals();
			break;
		// CE and C
		case CE:
			calcString1.setString(ZERO);
			initState = true;
			this.updateScreen(calcString1.getString(), LOWER);
			break;
		case C:
			resetCalculator();
			break;
		}
	}

	/**
	 * Méthode pour gérer les calculs après appui sur la touche "égale" Effectue les
	 * calculs puis appelle la méthode afterEquals pour gérer les affichages et les
	 * variables Permets calculs à répétition en appuyant "égale" successivement.
	 */
	private void equals() {
		if (!initState) {
			this.operand1 = this.calcString1.getOperand();
			this.previousOperator.setOperator(this.operator.getOperator());
			this.operator.setOperator("");
			switch (this.previousOperator.getOperator()) {
			case PLUS:
				this.result = this.operand2.add(operand1);
				afterEquals();
				break;
			case MINUS:
				this.result = this.operand2.subtract(operand1);
				afterEquals();
				break;
			case MULTIPLY:
				this.result = this.operand2.multiply(operand1);
				afterEquals();
				break;
			case DIVIDE:
				if (this.operand1.compareTo(BigDecimal.ZERO) == 0) {
					JOptionPane.showMessageDialog(null, DIVIDEBYZERO);
				} else {
					this.result = this.operand2.divide(operand1);
					afterEquals();
				}
				break;
			}
		} else {
			switch (this.previousOperator.getOperator()) {
			case PLUS:
				this.result = this.calcString1.getOperand().add(operand1);
				afterEqualsChain();
				break;
			case MINUS:
				this.result = this.calcString1.getOperand().subtract(operand1);
				afterEqualsChain();
				break;
			case MULTIPLY:
				this.result = this.calcString1.getOperand().multiply(operand1);
				afterEqualsChain();
				break;
			case DIVIDE:
				if (this.operand1.compareTo(BigDecimal.ZERO) == 0) {
					JOptionPane.showMessageDialog(null, DIVIDEBYZERO);
				} else {
					this.result = this.calcString1.getOperand().divide(operand1);
					afterEqualsChain();
				}
				break;
			case "":
				if (this.calcString1.getString().equals(ZERO) && !this.operator.getOperator().equals("")) {
					switch (this.operator.getOperator()) {
					case PLUS:
						this.result = this.calcString2.getOperand().add(this.calcString1.getOperand());
						// this.calculationString = this.calculationString+SPACE+this.calcString1.getString()+SPACE+EQUALS;
						afterEquals();
						// afterEqualsChain();
						break;
					case MINUS:
						this.result = this.calcString2.getOperand().subtract(this.calcString1.getOperand());
						afterEqualsChain();
						break;
					case MULTIPLY:
						this.result = this.calcString2.getOperand().multiply(this.calcString1.getOperand());
						afterEqualsChain();
						break;
					case DIVIDE:
						JOptionPane.showMessageDialog(null, DIVIDEBYZERO);
						resetCalculator();
						break;
					}

				}
			}
		}
	}

	/**
	 * Méthode met à jour la variable calcString1. Appelle la méthode updateScreen
	 * pour gérer les affichages du calcul (en haut de l'écran) et du résultat (en
	 * bas de l'écran). Cette méthode est appelé lors d'un enchaînement d'appuis sur
	 * la touche 'égale' et ne réinitialise pas l'état de la machine pour pouvoir
	 * enchaîner les calculs.
	 */
	private void afterEqualsChain() {
		this.calculationString = this.calcString1.getStringForScreen() + SPACE + this.previousOperator.getOperator()
				+ SPACE + this.operand1.toString() + SPACE + EQUALS;
		this.updateScreen(calculationString, UPPER);
		this.calcString1.setString(this.result.toPlainString());
		this.updateScreen(this.calcString1.getStringForScreen(), LOWER);
	}

	/**
	 * Méthode met à jour la variable calcString1. Appelle la méthode updateScreen
	 * pour gérer les affichages du calcul (en haut de l'écran) et du résultat (en
	 * bas de l'écran) puis réinitialise l'état de la machine pour accepter une
	 * nouvelle opérande.
	 */
	private void afterEquals() {
		this.calculationString = this.calculationString + SPACE + this.calcString1.getStringForScreen() + SPACE + EQUALS;
		this.updateScreen(calculationString, UPPER);
		this.calcString1.setString(this.result.toPlainString());
		this.initState = true;
		this.updateScreen(this.calcString1.getStringForScreen(), LOWER);
	}

	/**
	 * Méthode pour gérer les calculs après appui sur une des touches "opérateur"
	 * Effectue les calculs puis appelle la méthode afterCalculate pour gérer les
	 * affichages et les variables.
	 * 
	 * @param buttonClicked = le bouton appuyé
	 */
	private void calculate(String buttonClicked) {
		this.operand1 = this.calcString1.getOperand();
		this.previousOperator.setOperator(this.operator.getOperator());
		this.operator.setOperator(buttonClicked);
		switch (this.previousOperator.getOperator()) {
		case PLUS:
			this.result = this.operand2.add(operand1);
			afterCalculate();
			break;
		case MINUS:
			this.result = this.operand2.subtract(operand1);
			afterCalculate();
			break;
		case MULTIPLY:
			this.result = this.operand2.multiply(operand1);
			afterCalculate();
			break;
		case DIVIDE:
			if (this.operand1.compareTo(BigDecimal.ZERO) == 0) {
				JOptionPane.showMessageDialog(null, DIVIDEBYZERO);
			} else {
				this.result = this.operand2.divide(operand1);
				afterCalculate();
			}
			break;
		case "":
			this.calcString2.setString(this.calcString1.getString());
			this.operand2 = this.calcString2.getOperand();
			this.initState = true;
			this.calculationString = calcString1.getStringForScreen() + SPACE + this.operator.getOperator();
			this.updateScreen(this.calculationString, UPPER);
			break;
		}
	}

	/**
	 * Methode met à jour les variables calcString1, calcString2 et operand2.
	 * Appelle la méthode updateScreen pour gérer les affichages du calcul (en haut
	 * de l'écran) et du résultat (en bas de l'écran) puis réinitialise l'état de la
	 * machine pour accepter une nouvelle opérande.
	 */
	private void afterCalculate() {
		this.calculationString = this.calculationString + SPACE + this.calcString1.getStringForScreen() + SPACE
				+ this.operator.getOperator();
		this.updateScreen(this.calculationString, UPPER);
		this.calcString2.setString(this.result.toPlainString());
		this.operand2 = this.calcString2.getOperand();
		this.calcString1.setString(this.result.toPlainString());
		this.updateScreen(this.calcString1.getStringForScreen(), LOWER);
		this.initState = true;
	}

	/**
	 * Methode pour gérer les virgules. Vérifie si le nombre est déjà décimal ou
	 * non.
	 */
	private void pressedComma() {
		if (initState) {
			this.calcString1.setString(ZERO + COMMA);
			initState = false;
		} else {
			if (calcString1.getString().contains(COMMA)) {
				decimalNumber = true;
			} else {
				decimalNumber = false;
			}
			if (!decimalNumber) {
				calcString1.addString(COMMA);
				decimalNumber = true;
			}
		}
	}

	/**
	 * Méthode qui gère l'appui sur le bouton zéro. N'accepte les zéros que si la
	 * calculatrice n'est pas en état 'initial'.
	 * 
	 * @param buttonClicked = le bouton appuyé
	 */
	private void pressedZero(String buttonClicked) {
		if (!initState) {
			calcString1.addString(buttonClicked);
		} else {
			calcString1.setString(buttonClicked);
		}
	}

	/**
	 * Méthode qui gère l'appui sur un des boutons des chiffres.
	 * 
	 * @param buttonClicked = le bouton appuyé
	 */
	private void numberPressed(String buttonClicked) {
		if (initState) {
			calcString1.setString(buttonClicked);
			initState = false;
		} else {
			calcString1.addString(buttonClicked);
		}
	}

	/**
	 * Méthode qui gère l'appui sur le bouton "+/-" Vérifie si un nombre est déjà
	 * négatif ou non et applique la demande en fonction.
	 */
	private void makeNumberNegative() {
		String number = calcString1.getString();
		if (number.contains(MINUS)) {
			number = number.substring(1);
		} else if (!number.equals(ZERO)) {
			number = NEGATIVE + number;
		}
		calcString1.setString(number);
	}

	/**
	 * Méthode pour gérer le bouton 'correction' Gère également si on 'corrige' un
	 * nombre décimal (modifie le booleen 'decimalNumber en fonction si on enlève la
	 * virgule).
	 */
	private void pressedBackspace() {
		if (!initState) {
			String numberToTreat = calcString1.getString();
			if (numberToTreat.length() == 1) {
				calcString1.setString(ZERO);
				initState = true;
			} else {
				if (calcString1.getString().charAt(calcString1.getString().length() - 1) == '.') {
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
