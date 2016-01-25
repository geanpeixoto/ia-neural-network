package br.unioeste.ia.neuralnetwork.testing;

import java.util.ArrayList;
import java.util.List;

public class ResultSet {
	private List<Result> data = new ArrayList<>();
	
	public void add(Result result) {
		this.data.add(result);
	}
	
	public double getAccuracy() {
		double count = 0;
		for ( Result result: data )
			if ( result.isSuccess() )
				count ++;
		return count/this.data.size();
	}

	@Override
	public String toString() {
		return "{" +
				"\"accuracy\": " + this.getAccuracy() + ", " +
				"\"data\": " + data +
				"}";
	}
	
	
}
