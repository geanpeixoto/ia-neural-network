package br.unioeste.ia.cancer;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import br.unioeste.ia.neuralnetwork.architecture.Tuple;

public abstract class InputData {

	private double threshold = 0.5;
	private List<Tuple> tuples;
	
	public InputData setThreshold(double threshold) {
		this.threshold = threshold;
		return this;
	}
	
	public Tuple[] getTrainingData() {
		int max = (int) (this.tuples.size()*this.threshold + .5);
		List<Tuple> training = this.tuples.subList(0, max);
		return training.toArray(new Tuple[training.size()]);
	}
	
	public Tuple[] getTestingData() {
		int min = (int) (this.tuples.size()*this.threshold + .5);
		List<Tuple> testing = this.tuples.subList(min+1, this.tuples.size()-1);
		return testing.toArray(new Tuple[testing.size()]);
	}

	protected void load(String filename) throws FileNotFoundException {
		Scanner reader = new Scanner(this.getClass().getClassLoader().getResourceAsStream(filename));
		tuples = new ArrayList<Tuple>();
		while ( reader.hasNextLine() ) {
			try {
				tuples.add(this.parseLine(reader.nextLine()));
			} catch(NumberFormatException e) {
				continue;
			}
		}
		Collections.shuffle(this.tuples);
		reader.close();
	}

	abstract protected Tuple parseLine(String line);

	abstract public String getName();
}
