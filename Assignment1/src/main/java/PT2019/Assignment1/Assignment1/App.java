/*Miholca Cristian-Daniel, Facultatea de Automatica si Calculatoare, CTI, grupa 30223
 * 
 * Tema 1. Calculator polinoame
 */

package PT2019.Assignment1.Assignment1;

import java.util.*;

/**Clasa principala care contine functia main.
 * 
 * @author Cristian Miholca
 *
 */
public class App {
	/**Functia main
	 * 
	 * @param args args
	 */
	public static void main(String[] args) {
		//Model
		Polinom poly = new Polinom();
		//View
		AppView view = new AppView(poly);
		//Controller
		AppController controller = new AppController(poly, view);
	}
}
