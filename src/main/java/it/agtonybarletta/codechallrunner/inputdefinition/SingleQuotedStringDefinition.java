package it.agtonybarletta.codechallrunner.inputdefinition;


import it.agtonybarletta.codechallrunner.inputdefinition.InputDefinition;
import it.agtonybarletta.codechallrunner.inputdefinition.SingleInputDefinition;

public class SingleQuotedStringDefinition extends SingleInputDefinition<String> {

	public SingleQuotedStringDefinition() {
		super(InputDefinition.Mappers.quotedStringMapper);
	}

    public SingleQuotedStringDefinition(String terminator) {
      super(terminator, InputDefinition.Mappers.quotedStringMapper);
    }
}
