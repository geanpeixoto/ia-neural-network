package br.unioeste.ia.neuralnetwork.training;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import br.unioeste.ia.neuralnetwork.architecture.Layer;
import br.unioeste.ia.neuralnetwork.architecture.Tuple;

public class BackpropagationTrainer implements Trainer {

	private TreinableNeuralNetwork network;
	private double learningRate;
	private double momentum;
	private double errorThresh;
	private int maxIterations;

	public BackpropagationTrainer(TreinableNeuralNetwork network) {
		this.network = network;
		this.learningRate = .3;
		this.momentum = .1;
		this.maxIterations = 1000000;
		this.errorThresh = .001;
	}

	public void train(Tuple trainingData[]) {

		List<Tuple> training = Arrays.asList(Arrays.copyOf(trainingData, trainingData.length));
		
		int iteration = 0;
		double error, greaterError;

		do {
			Collections.shuffle(training);
			greaterError = 0;
			for (Tuple tuple : training) {
				error = this.ajust(tuple.getTarget(), this.network.feed(tuple.getInput()));
				if ( greaterError < error )
					greaterError = error;
			}
		} while ( iteration++ < this.maxIterations && greaterError > this.errorThresh );
	}

	private double ajust(double[] target, double[] output) {
		List<Layer<TrainableNeuron>> layers = this.network.getLayers();
		TrainableNeuron[] prev_neurons;
		TrainableNeuron neuron;
		Layer<TrainableNeuron> layer;
		double[] prev_errors;
		int i;

		// LastLayer
		i = layers.size() - 1;
		layer = layers.get(i);
		prev_neurons = layer.getNeurons();
		prev_errors = new double[prev_neurons.length];
		double greaterError = 0, e;
		for (int n = 0, nn = prev_neurons.length; n < nn; n++) {
			neuron = prev_neurons[n];
			e = target[n] - output[n];
			prev_errors[n] = neuron.getActivation().derivative(neuron.getLastAccumulator()) * e;

			if (greaterError < Math.abs(e) )
				greaterError = Math.abs(e);

			neuron.ajust(prev_errors[n], this.learningRate, this.momentum);
		}

		// InnerLayers
		for (i--; i >= 0; i--) {
			layer = layers.get(i);
			TrainableNeuron[] neurons = layer.getNeurons();
			double[] errors = new double[neurons.length];

			for (int n = 0, nn = neurons.length; n < nn; n++) {
				neuron = neurons[n];

				e = 0;
				for (int p = 0, pp = prev_neurons.length; p < pp; p++)
					e += prev_errors[p] * prev_neurons[p].getConnection(n);

				errors[n] = neuron.getActivation().derivative(neuron.getLastAccumulator()) * e;
				neuron.ajust(errors[n], this.learningRate, this.momentum);
			}

			prev_errors = errors;
			prev_neurons = neurons;
		}

		return greaterError;
	}
	
	public BackpropagationTrainer randomize() {
		Random generator = new Random();
		
		for ( Layer<TrainableNeuron> layer: this.network.getLayers() )
			for ( TrainableNeuron neuron: layer.getNeurons() )
				neuron.randomize(generator);
		
		return this;
	}

	public BackpropagationTrainer setLearningRate(double learningRate) {
		this.learningRate = learningRate;
		return this;
	}

	public BackpropagationTrainer setMomentum(double momentum) {
		this.momentum = momentum;
		return this;
	}

	public BackpropagationTrainer setErrorThresh(double maxError) {
		this.errorThresh = maxError;
		return this;
	}
	
	public BackpropagationTrainer setMaxIterations(int maxIterations) {
		this.maxIterations = maxIterations;
		return this;
	}

}
