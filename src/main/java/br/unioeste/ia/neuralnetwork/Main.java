package br.unioeste.ia.neuralnetwork;

import br.unioeste.ia.neuralnetwork.architecture.activation.PositiveSigmoidActivation;
import br.unioeste.ia.neuralnetwork.factory.NeuralNetworkFactory;
import br.unioeste.ia.neuralnetwork.training.BackpropagationTrainer;
import br.unioeste.ia.neuralnetwork.training.TreinableNeuralNetwork;

public class Main {

	public static void main(String[] args) {

		NeuralNetworkFactory factory = new NeuralNetworkFactory();
		TreinableNeuralNetwork nn = factory.multiLayerPerceptron(new int[] {2, 2, 1}, new PositiveSigmoidActivation());
		
		BackpropagationTrainer trainer = new BackpropagationTrainer(nn);
	}

}
