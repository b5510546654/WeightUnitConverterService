package ku.wuconverter.controller;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.SwingWorker;

import ku.wuconverter.view.GUI;
import net.webservicex.ConvertWeightsSoap;
import net.webservicex.WeightUnit;

/**
 * Use for sent value to service .
 * Extends SwingWorker so UI don't need to wait for service response.
 * @author Rungroj Maipradit 5510546654
 */
public class WeightUnitConverterController extends SwingWorker<Double, String>{
	private WeightUnit fromUnit;
	private WeightUnit toUnit;
	private double value;
	private GUI gui;
	private ConvertWeightsSoap service;

	/**
	 * Set service and GUI.
	 * @param service use for connect with service.
	 */
	public WeightUnitConverterController(ConvertWeightsSoap service,GUI gui) {
		this.service = service;
		this.gui = gui;
	}

	/**
	 * Set value for convert.
	 * @param from unit of value.
	 * @param to unit want to convert.
	 * @param value use for convert.
	 */
	public void setValue(String from,String to,double value) {
		fromUnit = WeightUnit.fromValue(from);
		toUnit = WeightUnit.fromValue(to);
		this.value = value;
	}

	@Override
	protected Double doInBackground() throws Exception {
		gui.setProgressBar(50);
		return service.convertWeight(value, fromUnit, toUnit);
	}

	@Override
	protected void done() {
		try {
			if(!this.isCancelled()){
				gui.showResult(this.get(10, TimeUnit.SECONDS)+"");
				gui.setProgressBar(100);
			}
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			gui.showResult("connection error");
			gui.setProgressBar(0);
			e.printStackTrace();
		}
	}

	@Override
	protected void process(List<String> chunks) {
		super.process(chunks);
	}
}
