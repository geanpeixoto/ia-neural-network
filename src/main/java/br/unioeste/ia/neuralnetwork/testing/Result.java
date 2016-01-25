package br.unioeste.ia.neuralnetwork.testing;

import java.util.Arrays;

import br.unioeste.ia.neuralnetwork.architecture.Tuple;

public class Result {
	private Tuple tuple;
	private double[] output;
	private boolean success;
	
	public Tuple getTuple() {
		return tuple;
	}
	
	public Result setTuple(Tuple tuple) {
		this.tuple = tuple;
		return this;
	}
	
	public Result  setOutput(double[] found) {
		this.output = found;
		return this;
	}
	
	public Result setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	@Override
	public String toString() {
		return "{" +
				"\"success\": " + success + ", " +
				"\"input\":" + Arrays.toString(tuple.getInput()) + ", " +
				"\"target\": " + Arrays.toString(tuple.getTarget()) + ", " +
				"\"output\": " + Arrays.toString(output) + ", " +
				"}";
	}

	public boolean isSuccess() {
		return this.success;
	}
}
