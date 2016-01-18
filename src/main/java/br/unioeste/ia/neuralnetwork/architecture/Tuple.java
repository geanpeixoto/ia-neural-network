package br.unioeste.ia.neuralnetwork.architecture;

public class Tuple {
	private final double input[];
	private final double target[];
	
	public Tuple(double input[], double output[]) {
		this.input = input;
		this.target = output;
	}
	
	public double[] getInput() {
		return this.input;
	}
	
	public double[] getTarget() {
		return this.target;
	}
}
