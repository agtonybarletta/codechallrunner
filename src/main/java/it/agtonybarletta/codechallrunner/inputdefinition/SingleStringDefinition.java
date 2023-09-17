package it.agtonybarletta.codechallrunner.inputdefinition;

import it.agtonybarletta.codechallrunner.inputdefinition.InputDefinition;
import it.agtonybarletta.codechallrunner.inputdefinition.SingleInputDefinition;

public class SingleStringDefinition extends SingleInputDefinition<String> {

	public SingleStringDefinition() {
		super(InputDefinition.Mappers.stringMapper);
	}

    public SingleStringDefinition(String terminator) {
      super(terminator, InputDefinition.Mappers.stringMapper);
    }
}
