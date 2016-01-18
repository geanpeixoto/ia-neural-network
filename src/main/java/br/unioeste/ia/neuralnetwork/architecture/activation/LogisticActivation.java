package br.unioeste.ia.neuralnetwork.architecture.activation;

public class LogisticActivation implements DerivableActivation {

	@Override
	public double exec(double value) {
		return 1/(1 + Math.exp(-value));
	}

	@Override
	public double derivative(double value) {
		double fn = this.exec(value);
		return fn*(1-fn);
	}

}
