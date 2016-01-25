package br.unioeste.ia.cancer;

import java.io.FileNotFoundException;

import br.unioeste.ia.neuralnetwork.architecture.Tuple;

public class BreastCancerData extends InputData {

	private static final String FILE = "breast-cancer-wisconsin.data.txt";
	
	public BreastCancerData() throws FileNotFoundException {
		this.load(BreastCancerData.FILE);
	}

	protected Tuple parseLine(String line) throws NumberFormatException {
		double input[] = new double[9];
		double target[] = new double[1];
		String[] split = line.split(",");
		for ( int i = 1; i < 10; i++ )
			input[i-1] = Double.parseDouble(split[i]);
		target[0] = Double.parseDouble(split[10])/2 - 1;
		return new Tuple(input, target);
	}

	@Override
	public String getName() {
		return "breast-cancer";
	}
}
