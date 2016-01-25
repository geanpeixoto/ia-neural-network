package br.unioeste.ia.neuralnetwork.architecture;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork<N extends Neuron> {

	private final List<Layer<N>> layers;
	private int inputSize;
	
	public NeuralNetwork() {
		this.layers = new ArrayList<>();
	}

	public List<Layer<N>> getLayers() {
		return layers;
	}

	public void addLayer(Layer<N> layer) {
		this.layers.add(layer);
		
		if ( this.layers.size() == 1 )
			this.inputSize = layer.getInputSize();
	}
	
	public double[] feed(double input[]) {
		if ( this.inputSize != input.length ) 
			throw new IllegalArgumentException();
		
		double current[] = input;
		for( int i=0, ii=this.layers.size(); i<ii; i++)
			current = this.layers.get(i).feed(current);
		return current;
	}

	@Override
	public String toString() {
		return "{\"inputSize\":" + inputSize + ", \"layers\":" + layers + "}";
	}

	
}
