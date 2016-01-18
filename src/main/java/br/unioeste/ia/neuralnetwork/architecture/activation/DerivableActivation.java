package br.unioeste.ia.neuralnetwork.architecture.activation;

public interface DerivableActivation extends Activation {
	double derivative(double value);
}
