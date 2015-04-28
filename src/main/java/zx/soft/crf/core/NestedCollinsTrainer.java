package zx.soft.crf.core;

/**
 * Implements the CollinsVotedPerceptron training algorithm
 */
class NestedCollinsTrainer extends CollinsTrainer {

	public NestedCollinsTrainer(CrfParams p) {
		super(p);
	}

	@Override
	int getSegmentEnd(DataSequence dataSeq, int ss) {
		return ((SegmentDataSequence) dataSeq).getSegmentEnd(ss);
	}

	@Override
	void startFeatureGenerator(FeatureGenerator _featureGenerator, DataSequence dataSeq, Soln soln) {
		((FeatureGeneratorNested) _featureGenerator).startScanFeaturesAt(dataSeq, soln.prevPos(), soln.pos);
	}

}
