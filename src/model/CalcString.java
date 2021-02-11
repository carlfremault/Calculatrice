package model;

import java.math.BigDecimal;

import control.Global;

/**
 * Classe CalcString gère la saisie des opérandes.
 * 
 * @author Carl Fremault
 *
 */
public class CalcString implements Global {

	/**
	 * String qui contient le CalcString.
	 */
	private String string;
	/**
	 * Booléen qui définit si un nombre est décimal.
	 */
	private boolean decimalNumber = false;
	/**
	 * Getter pour booleen decimalNumber
	 * @return decimalNumber
	 */
	public boolean isDecimalNumber() {
		return decimalNumber;
	}
	/**
	 * Setter pour booleen decimalNumber
	 * @param decimalNumber (true ou false)
	 */
	public void setDecimalNumber(boolean decimalNumber) {
		this.decimalNumber = decimalNumber;
	}
	/**
	 * Booléen qui définit si la première opérande (et son String associé) est en
	 * état initial (0).
	 */
	private boolean initState = true;
	/**
	 * Getter pour booleen initState
	 * @return initState
	 */
	public boolean isInitState() {
		return initState;
	}
	/**
	 * Setter pour booleen initState
	 * @param initState (true or false)
	 */
	public void setInitState(boolean initState) {
		this.initState = initState;
	}

	/**
	 * Constructeur
	 */
	public CalcString() {
		this.string = "";
	}

	/**
	 * Méthode qui vide le contenu du CalcString.
	 */
	public void emptyString() {
		this.string = "";
	}

	/**
	 * Setter pour le contenu du CalcString.
	 * 
	 * @param string = Valeur à mettre
	 */
	public void setString(String string) {
		this.string = "";
		this.string = string;
	}

	/**
	 * Getter pour le contenu du CalcString.
	 * 
	 * @return le contenu du CalcString
	 */
	public String getString() {
		return string;
	}

	/**
	 * Getter pour le contenu du CalcString, formaté pour affichage.
	 * 
	 * @return le contenu du CalcString après avoir vérifié s'il s'agit d'un nombre
	 *         décimal. Si c'est le cas, suppression des zéros éventuels à la fin
	 *         (et de la virgule si besoin).
	 */
	public String getStringForScreen() {
		if (this.string.contains(".")) {
			while ((this.string.endsWith("0") || this.string.endsWith(".")) && this.string.length() > 1
					&& this.string.contains(".")) {
				this.string = this.string.substring(0, string.length() - 1);
			}
			return this.string;
		}
		return string;
	}

	/**
	 * Getter pour l'opérande
	 * 
	 * @return l'opérande
	 */
	public BigDecimal getOperand() {
		return new BigDecimal(this.string);
	}

	/**
	 * Méthode qui rajoute un string au contenu du CalcString.
	 * 
	 * @param string à rajouter
	 */
	public void addString(String string) {
		this.string = this.string + string;
	}
}
