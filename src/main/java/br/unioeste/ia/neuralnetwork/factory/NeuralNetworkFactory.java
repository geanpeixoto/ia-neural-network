package br.unioeste.ia.neuralnetwork.factory;

import br.unioeste.ia.neuralnetwork.architecture.Layer;
import br.unioeste.ia.neuralnetwork.architecture.activation.DerivableActivation;
import br.unioeste.ia.neuralnetwork.training.TrainableNeuron;
import br.unioeste.ia.neuralnetwork.training.TreinableNeuralNetwork;

public class NeuralNetworkFactory {

	public TreinableNeuralNetwork multiLayerPerceptron(int[] layers, DerivableActivation activation) {
		if ( layers.length <= 1 )
			throw new IllegalArgumentException("Número de layers deve ser maior que 1");
		
		TreinableNeuralNetwork nn = new TreinableNeuralNetwork();
		
		int outputSize;
		int inputSize;
		for ( int i=1, ii=layers.length; i<ii; i++ ) {
			inputSize = layers[i-1];
			outputSize = layers[i];
			
			TrainableNeuron[] neurons = new TrainableNeuron[outputSize];
			
			for (int j=0; j<outputSize; j++)
				neurons[j] = new TrainableNeuron(inputSize, activation);
			
			nn.addLayer(new Layer<>(inputSize, neurons));
		}
		
		return nn;
	}
}
