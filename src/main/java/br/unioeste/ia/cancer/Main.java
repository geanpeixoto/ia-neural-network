package br.unioeste.ia.cancer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import br.unioeste.ia.neuralnetwork.architecture.Tuple;
import br.unioeste.ia.neuralnetwork.architecture.activation.LogisticActivation;
import br.unioeste.ia.neuralnetwork.factory.NeuralNetworkFactory;
import br.unioeste.ia.neuralnetwork.testing.Tester;
import br.unioeste.ia.neuralnetwork.training.BackpropagationTrainer;
import br.unioeste.ia.neuralnetwork.training.TreinableNeuralNetwork;

public class Main {
	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		NeuralNetworkFactory factory = new NeuralNetworkFactory();
		
		InputData data = new WbdcData();
		Tuple[] traningData = data.getTrainingData();
		Tuple[] testingData = data.getTestingData();

		int[][] tests = {
			{29, 6, 1},
			{29, 8, 1},
			{29, 10, 1},
			{29, 12, 1},
			{29, 14, 1},
			{29, 16, 1},
			{29, 18, 1},
			{29, 20, 1},
			{29, 22, 1}
		};
		
		for ( int[] layers: tests ) {
			
			TreinableNeuralNetwork nn = factory.multiLayerPerceptron(layers, new LogisticActivation());
			
			BackpropagationTrainer trainer = new BackpropagationTrainer(nn);
			
			Tester tester = new Tester(nn) {
				@Override
				public boolean accept(double[] target, double[] output) {
					boolean accept = Math.abs(target[0] - output[0]) < .4;
					return accept;
				}
			};
			
			trainer.randomize()
				.setMaxIterations(10000)
				.setErrorThresh(0.2)
				.train(traningData);
			
			String output = 
					"{\n" +
							"  \"network\": " + nn.toString() + ",\n" +
							"  \"traning\": {\n" + 
							"    \"config\": " + trainer.toString() + ",\n" +
							"    \"data\": " + Arrays.toString(traningData) + "\n" +
							"  },\n" +
							"  \"result\": " + tester.test(testingData) + ",\n" +
							"}";
			
			System.out.println(output);
			PrintWriter writer = new PrintWriter(getFilename(data.getName()), "UTF-8");
			writer.print(output);
			writer.close();
		}
		
	}
	
	public static String getFilename(String name) {
		Calendar now = Calendar.getInstance();
		return System.getProperty("user.home") + "/" + name + "." + format.format(now.getTime());
	}

}
