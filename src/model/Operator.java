package model;

/**
 * Classe qui contient un opérateur sous forme de String
 * 
 * @author Carl Fremault
 *
 */
public class Operator {

	/**
	 * String qui contient un opérateur
	 */
	private String operator;

	/**
	 * Constructeur
	 */
	public Operator() {
		this.operator = "";
	}

	/**
	 * Getter pour l'opérateur
	 * 
	 * @return l'opérateur
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * Setter pour l'opérateur
	 * 
	 * @param operator à définir
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
}
