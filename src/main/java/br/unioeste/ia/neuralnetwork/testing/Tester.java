package br.unioeste.ia.neuralnetwork.testing;

import br.unioeste.ia.neuralnetwork.architecture.NeuralNetwork;
import br.unioeste.ia.neuralnetwork.architecture.Tuple;

public abstract class Tester {

	private final NeuralNetwork<?> network;

	public abstract boolean accept(double[] target, double[] output);

	public Tester(NeuralNetwork<?> network) {
		this.network = network;
	}

	public double test(Tuple[] data) {

		int hit = 0;

		for (Tuple tuple : data)
			if (this.accept(tuple.getTarget(), this.network.feed(tuple.getInput())))
				hit++;

		return hit/data.length;
	}
}
