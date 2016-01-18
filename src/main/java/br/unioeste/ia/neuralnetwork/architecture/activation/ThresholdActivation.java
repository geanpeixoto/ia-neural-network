package br.unioeste.ia.neuralnetwork.architecture.activation;

public class ThresholdActivation implements Activation {
	
	private final double threshold;
	
	@Override
	public double exec(double value) {
		return value > threshold ? 1 : -1;
	}
	
	public ThresholdActivation(double threshold) {
		this.threshold = threshold;
	}
}
