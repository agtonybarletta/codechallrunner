package it.agtonybarletta.codechallrunner;


public class SingleQuotedString extends SingleInput<String> {

	public SingleQuotedString() {
		super(Input.Mappers.quotedStringMapper);
	}

    public SingleQuotedString(String terminator) {
      super(terminator, Input.Mappers.quotedStringMapper);
    }
}
