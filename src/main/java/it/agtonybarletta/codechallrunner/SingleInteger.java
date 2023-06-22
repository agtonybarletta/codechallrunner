package it.agtonybarletta.codechallrunner;

public class SingleInteger extends SingleInput<Integer> {

	public SingleInteger() {
      super(Input.Mappers.intMapper);
	}
	public SingleInteger(String terminator) {
      super(terminator, Input.Mappers.intMapper);
	}
}
