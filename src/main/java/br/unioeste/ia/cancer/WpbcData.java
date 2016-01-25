package br.unioeste.ia.cancer;

import java.io.FileNotFoundException;

import br.unioeste.ia.neuralnetwork.architecture.Tuple;

public class WpbcData extends InputData {

	private static final String FILE = "wpbc.data.txt";

	public WpbcData() throws FileNotFoundException {
		this.load(WpbcData.FILE);
	}

	@Override
	protected Tuple parseLine(String line) {
		double input[] = new double[29];
		double target[] = new double[1];
		String[] split = line.split(",");

		for (int i = 4; i < 33; i++)
			input[i - 4] = Double.parseDouble(split[i]);

		target[0] = split[1].charAt(0) == 'N' ? 0 : 1;
		return new Tuple(input, target);
	}

	@Override
	public String getName() {
		return "wpbc";
	}

}
