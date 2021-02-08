package model;

import java.math.BigDecimal;

import control.Global;

/**
 * Classe CalcString gère la saisie des opérandes
 * 
 * @author Carl Fremault
 *
 */
public class CalcString implements Global {

	/**
	 * String qui contient le CalcString
	 */
	private String string;

	/**
	 * Constructeur
	 */
	public CalcString() {
		this.string = "";
	}

	/**
	 * Méthode qui vide le contenu du CalcString
	 */
	public void emptyString() {
		this.string = "";
	}

	/**
	 * Setter pour le contenu du CalcString
	 * 
	 * @param string = Valeur à mettre
	 */
	public void setString(String string) {
		this.string = "";
		this.string = string;
	}

	/**
	 * Getter pour le contenu du CalcString
	 * 
	 * @return le contenu du CalcString après avoir appelé la méthode removeZero
	 *         pour enlever des zéros éventuels à la fin d'un nombre décimal
	 */
	public String getString() {
		return removeZero(string);
	}

	/**
	 * Getter pour le contenu du CalcString
	 * 
	 * @return le contenu du CalcString après avoir appelé la méthode removeZero
	 *         pour enlever des zéros éventuels à la fin d'un nombre décimal, ainsi
	 *         que la méthode removeDot pour enlever une virgule éventuelle à la fin
	 *         d'un nombre décimal
	 */
	public String getStringForScreen() {
		return removeDot(removeZero(string));
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
	 * Méthode qui rajoute un string au contenu du CalcString
	 * 
	 * @param string à rajouter
	 */
	public void addString(String string) {
		this.string = this.string + string;
//			this.string = removeZero(this.string) + string;
	}

	/**
	 * Méthode qui vérifie si un string termine par ".0" et l'enlève si c'est le cas
	 * 
	 * @param string à vérifier
	 * @return le string sans ".0" à la fin
	 */
	public String removeZero(String string) {
		if (string.endsWith(".0")) {
			string = string.substring(0, string.length() - 2);
		}
		return string;
	}

	/**
	 * Méthode qui vérifie si un string termine par une virgule et l'enlève si c'est
	 * le cas
	 * 
	 * @param string à vérifier
	 * @return le string sans virgule à la fin
	 */
	public String removeDot(String string) {
		if (string.endsWith(".")) {
			string = string.substring(0, string.length() - 1);
		}
		return string;
	}
}
