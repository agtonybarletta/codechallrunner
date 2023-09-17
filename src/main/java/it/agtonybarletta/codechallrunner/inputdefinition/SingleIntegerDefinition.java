package it.agtonybarletta.codechallrunner.inputdefinition;

import it.agtonybarletta.codechallrunner.inputdefinition.InputDefinition;
import it.agtonybarletta.codechallrunner.inputdefinition.SingleInputDefinition;

public class SingleIntegerDefinition extends SingleInputDefinition<Integer> {

	public SingleIntegerDefinition() {
      super(InputDefinition.Mappers.intMapper);
	}
	public SingleIntegerDefinition(String terminator) {
      super(terminator, InputDefinition.Mappers.intMapper);
	}
}
