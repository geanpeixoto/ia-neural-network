package br.unioeste.ia.neuralnetwork.architecture;

public class Layer< N extends Neuron > {

	private final N neurons[];
	private final int inputSize;
	
	public Layer(int inputSize, N[] neurons) {
		this.neurons = neurons;		
		this.inputSize = inputSize;
	}
	
	public double[] feed(double input[]) throws IllegalArgumentException {
		if ( this.inputSize != input.length )
			throw  new IllegalArgumentException();
		
		double output[] = new double[this.neurons.length];
		for ( int i=0, ii=this.neurons.length; i<ii; i++ )
			output[i] = this.neurons[i].feed(input);
		return output;
	}

	public int getInputSize() {
		return this.inputSize;
	}
	
	public N[] getNeurons() {
		return neurons;
	}
	
}
