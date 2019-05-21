package PT2019.Assignment1.Assignment1;

import java.util.Comparator;

/**Comparator folosit pentru sortarea polinoamelor in functie de exponent.
 * 
 * @author Cristian Miholca
 *
 */
public class ExponentComparator implements Comparator<Monom> {
	
	public int compare(Monom x, Monom y) {
		if(x.getExponent()>y.getExponent()) {
			return -1;
		}else if(x.getExponent()<y.getExponent()) {
			return 1;
		}else {
			return 0;
		}
	}
}
