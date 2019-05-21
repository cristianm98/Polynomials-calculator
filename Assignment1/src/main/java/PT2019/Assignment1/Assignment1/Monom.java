package PT2019.Assignment1.Assignment1;

/**Clasa descrie un termen al unui polinom
 * 
 * @author Cristian Miholca
 *
 */
public class Monom {
	private int exponent;
	private double coefficient;

	public Monom() {
		this.exponent = 0;
		this.coefficient = 0;
	}

	public Monom(double coefficient, int exponent) {
		super();
		this.exponent = exponent;
		this.coefficient = coefficient;
	}

	public int getExponent() {
		return exponent;
	}

	public void setExponent(int exponent) {
		this.exponent = exponent;
	}

	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
	
	/**Functie care converteste un string intr-o reprezentare exponentiala.
	 * 
	 * @param input - un string de forma x^2
	 * @return rez - un alt string in care cifra 2 va aparea la exponent
	 */
	public String stringToSuperscript(String input) {
		char[] superscriptDigits = { '\u2070', '\u00b9', '\u00b2', '\u00b3', '\u2074', '\u2075', 
				'\u2076', '\u2077', '\u2078', '\u2079' };
		char[] text = input.toCharArray();
		
		for (int i = 0; i < text.length; i++) {
				text[i] = superscriptDigits[text[i] - '0'];
		}

		String rez = String.copyValueOf(text);
		return rez;
	}

	public String toString() {
		/*Stringul returnat*/
		String s = "";
		
		/*Adaug coeficientul la s*/
		s=s+"+"+this.coefficient;
		
		/*Daca exponentul este 1 nu il afisez*/
		if (this.exponent == 1) {
			s = s + "x";
		} else {
			/*Afisez exponentul*/
			if (this.exponent != 0) {
				String t = "";
				t+=exponent;
				s = s + "x" + stringToSuperscript(t);
			}
		}

		return s;
	}
}
