package zx.soft.crf.core;

public class NestedCRF extends CRF {

	FeatureGeneratorNested featureGenNested;
	transient NestedViterbi nestedViterbi;

	public NestedCRF(int numLabels, FeatureGeneratorNested fgen, String arg) {
		super(numLabels, fgen, arg);
		featureGenNested = fgen;
		nestedViterbi = new NestedViterbi(this, 1);
	}

	public NestedCRF(int numLabels, FeatureGeneratorNested fgen, java.util.Properties configOptions) {
		super(numLabels, fgen, configOptions);
		featureGenNested = fgen;
		nestedViterbi = new NestedViterbi(this, 1);
	}

	@Override
	protected Trainer getTrainer() {
		if (params.trainerType.startsWith("SegmentCollins"))
			return new NestedCollinsTrainer(params);
		return new NestedTrainer(params);
	}

	@Override
	public Viterbi getViterbi(int beamsize) {
		return new NestedViterbi(this, beamsize);
	}

	@Override
	public double apply(DataSequence dataSeq) {
		return apply((SegmentDataSequence) dataSeq);
	}

	public double apply(SegmentDataSequence dataSeq) {
		if (nestedViterbi == null)
			nestedViterbi = new NestedViterbi(this, 1);
		if (params.debugLvl > 2)
			Util.printDbg("NestedCRF: Applying on " + dataSeq);
		return nestedViterbi.bestLabelSequence(dataSeq, lambda);
	}
};
