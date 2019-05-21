package PT2019.Assignment1.Assignment1;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

/**Clasa descrie un polinom reprezentat sub forma unei liste de monoame
 * 
 * @author Cristian Miholca
 *
 */
public class Polinom {
	/*Campul polinom reprezinta lista de monoame asupra careia se vor efectua operatiile*/
	private List<Monom> polinom;

	public Polinom() {
		this.polinom = new ArrayList<Monom>();
	}

	public Polinom(List<Monom> polinom) {
		super();
		this.polinom = polinom;
	}

	public List<Monom> getPolinom() {
		return polinom;
	}

	public void setPolinom(List<Monom> polinom) {
		this.polinom = polinom;
	}

	/**Functie care elimina duplicatele.
	 * Exemplu: polinom=x + x + 3x^2 
	 * 			polinom=3x^2 + 2x
	 */
	public void delDuplicates() {
		List<Monom> deleted = new ArrayList<Monom>();
		for (Monom x : this.polinom) {
			for (Monom y : this.polinom.subList(this.polinom.indexOf(x) + 1, this.polinom.size())) {
				if (x.getExponent() == y.getExponent()) {
					x.setCoefficient(x.getCoefficient() + y.getCoefficient());
					deleted.add(y);
				}
			}
		}
		this.polinom.removeAll(deleted);
	}
	
	/**Functie care elimina valorile de zero dintr-un polinom. Folosita pentru a elimina termenii cu valoarea 0 rezultati
	 * in urma unor operatii.
	 * Exemplu: polinomRezultat=3x^2 + 0.00x + 2.02 
	 * 			polinomRezultat=3x^2 + 2.02
	 */
	public void delZeros() {
		List<Monom> deleted = new ArrayList<Monom>();
		for(Monom x: this.polinom) {
			if(Double.compare(x.getCoefficient(), new Double(0.0))==0) {
				deleted.add(x);
			}
		}
		
		this.polinom.removeAll(deleted);
	}

	/**Functie care returneaza gradul polinomului.
	 * 
	 * @return gradulPolinomului
	 */
	public int highestDegree() {
		this.polinom.sort(new ExponentComparator());
		return this.polinom.get(0).getExponent();
	}

	/**Functie care realizeaza adunarea a doua polinoame.
	 * 
	 * @param polinom2 termenul 2
	 * @return result=this.polinom+polinom2
	 */
	public Polinom addPoly(Polinom polinom2) {
		Polinom result = new Polinom();

		this.polinom.sort(new ExponentComparator());
		polinom2.polinom.sort(new ExponentComparator());

		this.delDuplicates();
		polinom2.delDuplicates();

		ListIterator p1 = this.polinom.listIterator();
		ListIterator p2 = polinom2.polinom.listIterator();

		while (p1.hasNext() && p2.hasNext()) {
			Monom x = (Monom) p1.next();
			Monom y = (Monom) p2.next();

			if (x.getExponent() > y.getExponent()) {
				result.polinom.add(x);
				p2.previous();
			} else {
				if (x.getExponent() < y.getExponent()) {
					result.polinom.add(y);
					p1.previous();
				} else {
					x.setCoefficient(x.getCoefficient() + y.getCoefficient());
					result.polinom.add(x);
				}
			}
		}

		while (p1.hasNext() || p2.hasNext()) {
			if (p1.hasNext()) {
				result.polinom.add((Monom) p1.next());
			}
			if (p2.hasNext()) {
				result.polinom.add((Monom) p2.next());
			}
		}
		result.delZeros();

		return result;
	}

	/**Functie care realizeaza scaderea a doua polinoame.
	 * 
	 * @param polinom2 termen 2
	 * @return result=this.polinom-polinom2
	 */
	public Polinom subPoly(Polinom polinom2) {
		int i;
		for (i = 0; i < polinom2.polinom.size(); i++) {
			double oldCoeff = polinom2.polinom.get(i).getCoefficient();
			polinom2.polinom.get(i).setCoefficient(oldCoeff * (-1));
		}
		Polinom result = new Polinom();
		result = this.addPoly(polinom2);

		return result;
	}

	/**Functie care realizeaza inmultirea a doua polinoame.
	 * 
	 * @param polinom2 factor 2
	 * @return result = this.polinom*polinom2
	 */
	public Polinom mulPoly(Polinom polinom2) {
		Polinom result = new Polinom();
		this.delDuplicates();
		polinom2.delDuplicates();

		this.polinom.sort(new ExponentComparator());
		this.polinom.sort(new ExponentComparator());

		for (Monom x : this.polinom) {
			for (Monom y : polinom2.polinom) {
				double newCoeff;
				int newExp;

				newCoeff = x.getCoefficient() * y.getCoefficient();
				newExp = x.getExponent() + y.getExponent();

				result.polinom.add(new Monom(newCoeff, newExp));
				}
			}
		result.delDuplicates();
		result.delZeros();
		result.polinom.sort(new ExponentComparator());

		return result;
	}
	
	/**Functie care realizeaza impartirea a doua polinoame.
	 * 
	 * @param polinom2 factor 2
	 * @return result[0] = Q(this.polinom/polinom2) - catul
	 * 		   result[1] = R(this.polinom/polinom2) - restul
	 */
	public Polinom[] divPoly(Polinom polinom2) {
		Polinom[] result = new Polinom[2];

		this.delDuplicates();
		polinom2.delDuplicates();

		if (this.highestDegree() < polinom2.highestDegree()) {
			throw new NumberFormatException("Bad input!");
		} else {

			this.polinom.sort(new ExponentComparator());
			this.polinom.sort(new ExponentComparator());

			Polinom q = new Polinom();
			Polinom r = new Polinom(this.polinom);

			while (r.polinom.isEmpty() == false && (r.highestDegree() >= polinom2.highestDegree())) {
				double newCoef = r.polinom.get(0).getCoefficient() / polinom2.polinom.get(0).getCoefficient();
				int newPow = r.polinom.get(0).getExponent() - polinom2.polinom.get(0).getExponent();

				Polinom t = new Polinom();
				t.polinom.add(new Monom(newCoef, newPow));
				q = q.addPoly(t);
				r = r.subPoly(t.mulPoly(polinom2));
			}

			// return qrPair
			q.delDuplicates();
			r.delDuplicates();
			
			if(q.getPolinom().isEmpty()) {
				q.getPolinom().add(new Monom(0,0));
			}
			
			if(r.getPolinom().isEmpty()) {
				r.getPolinom().add(new Monom(0,0));
			}
			
			result[0] = q;
			result[1] = r;
			
			return result;
		}
	}

	/**Functie care realizeaza derivarea unui polinom.
	 * 
	 * @return result = (this.polinom)'
	 */
	public Polinom derivPoly() {
		this.delDuplicates();
		this.polinom.sort(new ExponentComparator());

		Polinom result = new Polinom(this.polinom);

		List<Monom> deleted = new ArrayList<Monom>();

		for (Monom x : result.polinom) {
			if (x.getExponent() == 0) {
				deleted.add(x);
			} else {

				x.setCoefficient(x.getCoefficient() * x.getExponent());
				x.setExponent(x.getExponent() - 1);
			}
		}
		result.polinom.removeAll(deleted);

		return result;
	}

	/**Functie care realizeaza integrarea unui polinom.
	 * 
	 * @return integrala(this.polinom)
	 */
	public Polinom integrPoly() {
		this.delDuplicates();
		this.polinom.sort(new ExponentComparator());

		Polinom result = new Polinom(this.polinom);

		for (Monom x : result.polinom) {
			x.setExponent(x.getExponent() + 1);
			if (x.getExponent() != 0)
				x.setCoefficient(x.getCoefficient() / x.getExponent());
		}
		return result;
	}

	@Override
	public String toString() {
		/*Stringul returnat*/
		String s = "";
		/*Adaug la s monoamele*/
		for (Monom x : this.polinom) {
			s = s + x.toString();
		}

		/*Daca stringul este nul, retin " " in el pentru a evita aruncarea unei exceptii la introducerea datelor*/
		if (s.compareTo("") == 0)
			s = " ";

		/*Daca stringul incepe cu '+' retin stringul ce urmeaza dupa el*/
		if (s.charAt(0) == '+') {
			s = s.substring(1);
		}
		
		/*Daca stringul contine expresii de forma: 3x^2+-3.2x inlocuiesc "+-" cu "-"*/
		s=s.replace("+-", "-");
		
		return s;
	}

}
