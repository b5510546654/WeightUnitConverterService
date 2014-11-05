package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.webservicex.ConvertWeightsSoap;
import net.webservicex.WeightUnit;
import controller.WeightUnitConverterController;
/**
 * UI of this program.
 * Receive data from user and sent it to service.
 * Show result from response.
 * @author Rungroj Maipradit 5510546654
 */
public class GUI extends JFrame {
	/** Top panel. */
	private JPanel top;
	/** Middle panel. */
	private JPanel mid;
	/** Bottom panel. */
	private JPanel bot;
	/** input label. */
	private JLabel label;
	/** Contains input value. */
	private JTextField input;
	/** First combobox.*/
	private JComboBox<String> comboBox;
	/** Second combobox.*/
	private JComboBox<String> comboBox2;
	/** First Combobox.*/
	private JLabel from;
	/** Second TextField.*/
	private JLabel to;
	/** Submit button.*/
	private JButton button;
	/** output label.*/
	private JLabel label2;
	/** Contains output value. */
	private JTextArea output;
	/** Service. */
	private ConvertWeightsSoap service;
	/** Contains Weight Converter. */
	private WeightUnitConverterController worker;
	/**
	 * Initialize component and set controller.
	 * @param factory reference to weight unit converter.
	 */
	public GUI(ConvertWeightsSoap proxy){
		this.service = proxy;
		iniComponent();
	}

	/**
	 * Initial UI Component.
	 */
	public void iniComponent() {

		top = new JPanel(new FlowLayout());
		mid = new JPanel(new FlowLayout());
		bot = new JPanel(new FlowLayout());
		label = new JLabel("value : ");
		input = new JTextField();
		input.setPreferredSize(new Dimension(200,30));
		button = new JButton("Search");
		comboBox = new JComboBox<>();
		comboBox2 = new JComboBox<>();
		from = new JLabel("from : ");
		to = new JLabel("to : ");
		label2 = new JLabel("Output : ");
		output = new JTextArea();
		output.setPreferredSize(new Dimension(200,30));
		output.setLineWrap(true);
		output.setEditable(false);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(top,BorderLayout.NORTH);
		getContentPane().add(mid,BorderLayout.CENTER);
		getContentPane().add(bot,BorderLayout.SOUTH);

		top.add(label);
		top.add(input);
		top.add(button);

		mid.add(from);
		mid.add(comboBox);
		mid.add(to);
		mid.add(comboBox2);

		bot.add(label2);
		bot.add(output);
		for (WeightUnit unit : WeightUnit.values()) {
			comboBox.addItem(unit.value());
			comboBox2.addItem(unit.value());
		}

		KeyListener keyListener = new KeyListener() {			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					convert();
				}
			}
		};

		input.addKeyListener(keyListener);
		button.addKeyListener(keyListener);
		comboBox.addKeyListener(keyListener);
		comboBox2.addKeyListener(keyListener);
		
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				convert();
			}
		});

	}

	/**
	 * Sent value to weight unit converter.
	 */
	public void convert(){
		if(worker != null){
			worker.cancel(true);
		}		

		double value = Double.parseDouble(input.getText());
		String from = comboBox.getSelectedItem().toString();
		String to = comboBox2.getSelectedItem().toString();

		worker = new WeightUnitConverterController(service, this);
		worker.setValue(from, to, value);
		worker.execute();
	}

	public void showResult(String result) {
		output.setText(result);
	}
}
