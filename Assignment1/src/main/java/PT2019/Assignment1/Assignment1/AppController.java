package PT2019.Assignment1.Assignment1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**Clasa care descrie legatura dinte view si model (clasa Polinom).
 * 
 * @author Cristian Miholca
 *
 */
public class AppController {
	private Polinom result;
	private AppView view;

	public AppController() {
		super();
	}

	public AppController(Polinom model, AppView view) {
		this.result = model;
		this.view = view;
		
		/*Adaugarea ascultatorilor pentru butoane*/
		view.addResetListener(new ResetListener());
		view.addClearListener(new ClearListener());
		view.addEqualsListener(new EqualsListener());
		view.addDerivateListener(new DerivateListener());
		view.addIntegrateListener(new IntegrateListener());
	}

	/**Functia converteste un String la tipul Polinom.
	 * 
	 * @param input - un string care va fi preluat din textField
	 * @return result - un polinom 
	 */
	public Polinom stringToPolinom(String input) {
		/*Daca polinom contine 'X' in loc de 'x'*/
		input=input.replaceAll("X", "x");
		
		Polinom result = new Polinom();
		
		/*Despart stringul in mai multe stringuri delimitate de "+" sau "-"*/
		String[] arrOfStrings = input.split("(?=[+-])");

		double coeff = 0.0;
		int exp = 0;
		
		/*Fiecare substring il convertesc intr-un monom*/
		for (String s : arrOfStrings) {
			/*Caut indicele caracterului 'x'*/
			int indexOfX = s.indexOf('x');

			/*Daca nu exista, inseamna ca monomul este o constanta*/
			if (indexOfX == -1) {
				coeff = Double.parseDouble(s);
				exp = 0;
			} else {
				/*Daca x se afla pe pozitia 0, coeficientul=1*/
				if(indexOfX==0) {
					coeff = 1;
				}
				else {
					String coeffString = s.substring(0, indexOfX);
					/*Daca stringul incepe cu "-x...", coeficientul lui x este -1*/
					if(coeffString.compareTo("-")==0)
						coeff=-1;
					else {
						/*Daca stringul incepe cu "+x...", coeficientul este 1*/
						if(coeffString.compareTo("+")==0)
							coeff=1;
						else
							coeff = Double.parseDouble(coeffString);
					}
				}
				String expString = s.substring(indexOfX + 1);
				
				if (expString.contains("^")) {
					expString = expString.substring(1);
					exp = Integer.parseInt(expString);
				} else {
					exp = 1;
				}
			}

			result.getPolinom().add(new Monom(coeff, exp));
		}
		
		return result;
	}

	class ClearListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			view.reset();
		}
	}

	class EqualsListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String input1 = "";
			String input2 = "";
			try {
				Polinom p1 = new Polinom();
				Polinom p2 = new Polinom();
				Polinom result = new Polinom();
				
				input1 = view.getInput1();
				p1 = stringToPolinom(input1);
				
				if(view.getInput2Tf().isEnabled()) {
					input2 = view.getInput2();
					p2 = stringToPolinom(input2);
				}			
				/*Calcularea si afisarea rezultatului*/
				if(view.getAddBtn().isSelected()) {
					result = p1.addPoly(p2);
					view.setResult(result.toString());
				}
				if(view.getSubBtn().isSelected()) {
					result = p1.subPoly(p2);
					view.setResult(result.toString());
				}
				if(view.getMulBtn().isSelected()) {
					result = p1.mulPoly(p2);
					view.setResult(result.toString());
				}
				if(view.getDivBtn().isSelected()) {
					Polinom[] result2 = new Polinom[2];
					result2=p1.divPoly(p2);
					
					view.setResult("Q="+result2[0].toString()+" R="+result2[1].toString());
				}
				if(view.getDerivateBtn().isSelected()) {
					result = p1.derivPoly();
					view.setResult(result.toString());
				}
				if(view.getIntegrateBtn().isSelected()) {
					result = p1.integrPoly();
					view.setResult(result.toString());
				}
				if(view.getResultTf().getText().compareTo(" ")==0) {
					view.setResult("0");
				}
				
			}catch(Exception e1) {
				view.showError("Bad input!");
			}
		}
	}
	
	class IntegrateListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			view.getInput2Tf().setEnabled(false);
		}
		
	}
	
	class DerivateListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			view.getInput2Tf().setEnabled(false);
		}
		
	}

	class ResetListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			view.reset();
			view.getInput2Tf().setEnabled(true);
			view.getGroup().clearSelection();
		}
		
	}
	
}