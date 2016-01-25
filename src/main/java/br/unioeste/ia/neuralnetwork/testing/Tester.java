package br.unioeste.ia.neuralnetwork.testing;

import br.unioeste.ia.neuralnetwork.architecture.NeuralNetwork;
import br.unioeste.ia.neuralnetwork.architecture.Tuple;

public abstract class Tester {

	private final NeuralNetwork<?> network;

	public abstract boolean accept(double[] target, double[] output);

	public Tester(NeuralNetwork<?> network) {
		this.network = network;
	}

	public ResultSet test(Tuple[] data) {
		ResultSet set = new ResultSet();
		
		for (Tuple tuple : data) {
			double[] output = this.network.feed(tuple.getInput());
			Result r = new Result()
					.setOutput(output)
					.setTuple(tuple)
					.setSuccess(this.accept(tuple.getTarget(), output));
			set.add(r);
		}

		return set;
	}
}
