package br.unioeste.ia.neuralnetwork.training;

import java.util.Arrays;
import java.util.Random;

import br.unioeste.ia.neuralnetwork.architecture.Neuron;
import br.unioeste.ia.neuralnetwork.architecture.activation.DerivableActivation;

public class TrainableNeuron implements Neuron {

	private final DerivableActivation activation;
	private final double[] connections;
	private final int inputSize;
	private double[] lastInput;
	private double[] lastChanges;
	
	private double lastAccumulator;
	
	public TrainableNeuron(int inputSize, DerivableActivation activation) {
		this.inputSize = inputSize;
		this.connections = new double[inputSize+1]; // bias
		this.lastChanges = new double[inputSize+1]; // bias
		this.activation = activation;
	}
	
	public double feed(double[] input) {
		double accumulator = 0;
		this.lastInput = input;
		
		accumulator = this.connections[this.inputSize]; // bias
		for ( int i=0, ii=this.inputSize; i<ii; i++ ) 
			accumulator += this.connections[i] * input[i];
		
		this.lastAccumulator = accumulator;
		return this.activation.exec(accumulator);
	}

	protected double getLastAccumulator() {
		return lastAccumulator;
	}
	
	protected DerivableActivation getActivation() {
		return (DerivableActivation) this.activation;
	}

	protected void ajust(double error, double learningRate, double momentum) {
		this.lastChanges[this.inputSize] = momentum * this.lastChanges[this.inputSize] + learningRate * error; // bias
		this.connections[this.inputSize] += this.lastChanges[this.inputSize]; // bias
		
		for ( int i=0, ii=this.inputSize; i<ii; i++ ) {
			this.lastChanges[i] = momentum * this.lastChanges[i] + learningRate * error * this.lastInput[i];
			this.connections[i] += this.lastChanges[i];
		}
	}

	protected double getConnection(int n) {
		return this.connections[n];
	}

	public void randomize(Random generator) {
		for( int i=0, ii=this.connections.length; i<ii; i++ ) {
			this.connections[i] = generator.nextDouble();
			this.lastChanges[i] = 0;
		}
	}

	@Override
	public String toString() {
		return "{\"connections\":" + Arrays.toString(connections) +"}";
	}
	
	
}
