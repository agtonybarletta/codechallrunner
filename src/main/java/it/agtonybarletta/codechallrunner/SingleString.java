package it.agtonybarletta.codechallrunner;

import java.util.function.Function;

public class SingleString extends SingleInput<String> {

	public SingleString() {
		super(Input.Mappers.stringMapper);
	}

    public SingleString(String terminator) {
      super(terminator, Input.Mappers.stringMapper);
    }
}
