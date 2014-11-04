import java.awt.Dimension;

import javax.xml.ws.WebServiceException;

import net.webservicex.ConvertWeights;
import net.webservicex.ConvertWeightsSoap;
import view.GUI;

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
		try{
			ConvertWeights factory = new ConvertWeights();
			ConvertWeightsSoap service = factory.getConvertWeightsSoap();
			GUI gui = new GUI(service);
			gui.setVisible(true);
			gui.pack();
			gui.setSize(new Dimension(400,200));
		}
		catch(WebServiceException e){			
		}
	}

}
