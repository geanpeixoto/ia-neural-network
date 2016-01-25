package br.unioeste.ia.cancer;

import java.io.FileNotFoundException;

import br.unioeste.ia.neuralnetwork.architecture.Tuple;

public class WbdcData extends InputData {

	private static final String FILE = "wdbc.data.txt";

	public WbdcData() throws FileNotFoundException {
		this.load(WbdcData.FILE);
	}

	@Override
	protected Tuple parseLine(String line) {
		double input[] = new double[29];
		double target[] = new double[1];
		String[] split = line.split(",");

		for (int i = 3; i < 32; i++)
			input[i - 3] = Double.parseDouble(split[i]);

		target[0] = split[1].charAt(0) == 'M' ? 0 : 1;
		return new Tuple(input, target);
	}

	@Override
	public String getName() {
		return "wdbc";
	}

}
