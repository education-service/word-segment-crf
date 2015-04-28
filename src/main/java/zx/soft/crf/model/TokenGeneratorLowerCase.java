package zx.soft.crf.model;

public class TokenGeneratorLowerCase extends TokenGenerator {

	private static final long serialVersionUID = -5104463806229821948L;

	@Override
	public Object getKey(Object xArg) {
		return xArg.toString().toLowerCase();
	}

}
