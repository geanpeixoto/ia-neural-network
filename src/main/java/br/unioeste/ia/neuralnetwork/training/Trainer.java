package br.unioeste.ia.neuralnetwork.training;

import br.unioeste.ia.neuralnetwork.architecture.Tuple;

public interface Trainer {
	void train(Tuple[] data);
	Trainer randomize();
}
