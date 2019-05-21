package PT2019.Assignment1.Assignment1;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.event.*;

/**Clasa descrie partea vizuala a aplicatiei.
 * 
 * @author Cristian Miholca
 *
 */
public class AppView extends JFrame {

	private JTextField input1Tf = new JTextField("");
	private JTextField input2Tf = new JTextField("");
	private JTextField resultTf = new JTextField("");

	ButtonGroup group = new ButtonGroup();

	private JRadioButton addBtn = new JRadioButton("Add");
	private JRadioButton subBtn = new JRadioButton("Substract");
	private JRadioButton mulBtn = new JRadioButton("Multiply");
	private JRadioButton divBtn = new JRadioButton("Divide");
	private JRadioButton derivateBtn = new JRadioButton("Derivate");
	private JRadioButton integrateBtn = new JRadioButton("Integrate");

	private JButton clearBtn = new JButton("Clear");
	private JButton equalsBtn = new JButton("=");
	private JButton resetBtn = new JButton("Reset");
	
	private Polinom model;
	
	public AppView(Polinom model) {
		this.model = model;

		this.setBounds(100, 100, 1000, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(0, 1));

		JPanel panel1 = new JPanel();
		GroupLayout gl = new GroupLayout(panel1);
		panel1.setLayout(gl);
		JLabel polinom1Lbl = new JLabel("Polinom 1: ");
		JLabel polinom2Lbl = new JLabel("Polinom 2: ");
		JLabel resultLbl = new JLabel("Result: ");

		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);

		/*Schimb dimensiunea pentru fiecare textField*/
		input1Tf.setMinimumSize(new Dimension(900,30));
		input2Tf.setMinimumSize(new Dimension(900,30));
		resultTf.setMinimumSize(new Dimension(900,30));
		
		/*Schimb fontul pentru fiecare textField*/
		input1Tf.setFont(new Font("Courier",Font.PLAIN,20));
		input2Tf.setFont(new Font("Courier",Font.PLAIN,20));
		resultTf.setFont(new Font("Courier",Font.PLAIN,20));
		
		/*Grupez input1Tf, input2Tf, resultTf*/
		gl.setHorizontalGroup(gl.createSequentialGroup()
				.addGroup(gl.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(polinom1Lbl)
						.addComponent(polinom2Lbl).addComponent(resultLbl))
				.addGroup(gl.createParallelGroup().addComponent(input1Tf).addComponent(input2Tf)
						.addComponent(resultTf)));

		gl.setVerticalGroup(gl.createSequentialGroup()
				.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(polinom1Lbl)
						.addComponent(input1Tf))
				.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(polinom2Lbl)
						.addComponent(input2Tf))
				.addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(resultLbl)
						.addComponent(resultTf)));

		this.add(panel1);
		
		/*Adaug butoanele pentru operatii*/
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.add(addBtn);
		panel2.add(subBtn);
		panel2.add(mulBtn);
		panel2.add(divBtn);
		panel2.add(derivateBtn);
		panel2.add(integrateBtn);

		/*Grupez butoanele pentru operatii*/
		group.add(addBtn);
		group.add(subBtn);
		group.add(mulBtn);
		group.add(divBtn);
		group.add(derivateBtn);
		group.add(integrateBtn);
		
		this.add(panel2);

		/*Adaug butoanele clear, reset, egal*/
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		panel3.add(clearBtn);
		panel3.add(resetBtn);
		panel3.add(equalsBtn);
		
		this.add(panel3);

		this.setTitle("Polinoame");

		this.pack();
		this.setVisible(true);
	}

	public void reset() {
		this.input1Tf.setText("");
		this.input2Tf.setText("");
		this.resultTf.setText("");
	}

	public String getInput1() {
		return this.input1Tf.getText();
	}

	public String getInput2() {
		return this.input2Tf.getText();
	}

	public void setResult(String s) {
		this.resultTf.setText(s);
	}

	public void showError(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "Eroare", JOptionPane.WARNING_MESSAGE);
	}

	public void addClearListener(ActionListener cal) {
		clearBtn.addActionListener(cal);
	}

	public void addResetListener(ActionListener ral) {
		resetBtn.addActionListener(ral);
	}
	
	public void addEqualsListener(ActionListener eal) {
		equalsBtn.addActionListener(eal);
	}

	public void addDerivateListener(ActionListener dal) {
		derivateBtn.addActionListener(dal);
	}
	
	public void addIntegrateListener(ActionListener ial) {
		integrateBtn.addActionListener(ial);
	}

	public void addInput1Listener(KeyListener ial) {
		input1Tf.addKeyListener(ial);
	}
	
	public ButtonGroup getGroup() {
		return group;
	}

	public JTextField getInput1Tf() {
		return input1Tf;
	}
	
	public JTextField getInput2Tf() {
		return input2Tf;
	}

	public JTextField getResultTf() {
		return resultTf;
	}
	
	public JRadioButton getAddBtn() {
		return addBtn;
	}

	public JRadioButton getSubBtn() {
		return subBtn;
	}

	public JRadioButton getMulBtn() {
		return mulBtn;
	}

	public JRadioButton getDivBtn() {
		return divBtn;
	}

	public JRadioButton getDerivateBtn() {
		return derivateBtn;
	}

	public JRadioButton getIntegrateBtn() {
		return integrateBtn;
	}
}
