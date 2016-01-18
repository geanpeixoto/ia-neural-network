package br.unioeste.ia.neuralnetwork.architecture.activation;

public class PositiveSigmoidActivation implements DerivableActivation{
	@Override
	public double exec(double value) {
		return 2/(1+Math.exp(-value));
	}

	@Override
	public double derivative(double value) {
		double sigmoid = this.exec(value);
		return sigmoid*(1-sigmoid);
	}
}
