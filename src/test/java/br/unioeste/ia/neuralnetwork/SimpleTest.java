package br.unioeste.ia.neuralnetwork;

import static org.junit.Assert.*;

import org.junit.Test;

import br.unioeste.ia.neuralnetwork.architecture.Tuple;
import br.unioeste.ia.neuralnetwork.architecture.activation.LogisticActivation;
import br.unioeste.ia.neuralnetwork.factory.NeuralNetworkFactory;
import br.unioeste.ia.neuralnetwork.testing.Tester;
import br.unioeste.ia.neuralnetwork.training.BackpropagationTrainer;
import br.unioeste.ia.neuralnetwork.training.TreinableNeuralNetwork;

public class SimpleTest {
	
	@Test
	public void and() {
		this.test(new Tuple[] {
				new Tuple(new double[] {1, 1}, new double[] {1}),
				new Tuple(new double[] {1, 0}, new double[] {0}),
				new Tuple(new double[] {0, 1}, new double[] {0}),
				new Tuple(new double[] {0, 0}, new double[] {0}),
		});
	}

	@Test
	public void or() {
		this.test(new Tuple[] {
				new Tuple(new double[] {1, 1}, new double[] {1}),
				new Tuple(new double[] {1, 0}, new double[] {1}),
				new Tuple(new double[] {0, 1}, new double[] {1}),
				new Tuple(new double[] {0, 0}, new double[] {0}),
		});
	}
	
	@Test
	public void nand() {
		this.test(new Tuple[] {
				new Tuple(new double[] {1, 1}, new double[] {0}),
				new Tuple(new double[] {1, 0}, new double[] {0}),
				new Tuple(new double[] {0, 1}, new double[] {0}),
				new Tuple(new double[] {0, 0}, new double[] {1}),
		});
	}
	
	@Test
	public void xor() {
		this.test(new Tuple[] {
				new Tuple(new double[] {1, 1}, new double[] {0}),
				new Tuple(new double[] {1, 0}, new double[] {1}),
				new Tuple(new double[] {0, 1}, new double[] {1}),
				new Tuple(new double[] {0, 0}, new double[] {0}),
		});
	}
	
	public void test(Tuple[] data) {
		
		NeuralNetworkFactory factory = new NeuralNetworkFactory();
		TreinableNeuralNetwork network = factory.multiLayerPerceptron(new int[] {2, 2, 1}, new LogisticActivation());
		
		BackpropagationTrainer trainer = new BackpropagationTrainer(network);
		
		Tester tester = new Tester(network) {
			@Override
			public boolean accept(double[] target, double[] output) {
				assertEquals(target[0], output[0], .1);
				return Math.abs(target[0] - output[0]) < .1;
			}
		};
		
		trainer.randomize().train(data);
		tester.test(data);
	}
}
