package ku.wuconverter;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.xml.ws.WebServiceException;

import ku.wuconverter.view.GUI;
import net.webservicex.ConvertWeights;
import net.webservicex.ConvertWeightsSoap;

/**
 * Main of this program.
 * @author Rungroj Maipradit 5510546654
 */
public class Main {

	/**
	 * Create GUI and put service in it.
	 * @param args
	 */
	public static void main(String[] args) {
		createUI();
	}

	public static void createUI(){
		try{
			ConvertWeights factory = new ConvertWeights();
			ConvertWeightsSoap service = factory.getConvertWeightsSoap();
			GUI gui = new GUI(service);
			gui.setVisible(true);
			gui.pack();
			gui.setSize(new Dimension(400,200));
		}
		catch(WebServiceException e){			
			JDialog.setDefaultLookAndFeelDecorated(true);
			Object[] option = new Object[]{"RETRY","EXIT"};
			int response = JOptionPane.showOptionDialog(null, "Retry ?","Connection error!",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE,null,option, option[1]);
			if(response == JOptionPane.NO_OPTION || response == JOptionPane.CLOSED_OPTION){
				System.exit(0);
			}
			createUI();
		}

	}
}
