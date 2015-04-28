package zx.soft.crf.model;

import zx.soft.crf.core.DataSequence;
import zx.soft.crf.core.SegmentDataSequence;

/**
 * This can be used as a wrapper around a FeatureTypes class that wants to
 * generate a feature for each label.
 */
public class FeatureTypesEachLabel extends FeatureTypes {

	private static final long serialVersionUID = 6540153966191141827L;

	protected FeatureTypes single;

	int numStates;

	int stateId;

	FeatureImpl featureImpl;

	boolean optimize = false;

	public FeatureTypesEachLabel(FeatureGenImpl fgen, FeatureTypes single) {
		super(fgen);
		numStates = model.numStates();
		this.single = single;
		featureImpl = new FeatureImpl();
		thisTypeId = single.thisTypeId;
	}

	protected void nextFeature() {
		single.next(featureImpl);
	}

	boolean advance() {
		stateId++;
		if (stateId < numStates)
			return true;
		if (single.hasNext()) {
			nextFeature();
			stateId = 0;
		}
		return stateId < numStates;
	}

	@Override
	public boolean startScanFeaturesAt(zx.soft.crf.core.DataSequence data, int prevPos, int pos) {
		stateId = numStates;
		single.startScanFeaturesAt(data, prevPos, pos);
		return advance();
	}

	@Override
	public boolean startScanFeaturesAt(zx.soft.crf.core.DataSequence data, int pos) {
		stateId = numStates;
		single.startScanFeaturesAt(data, pos);
		return advance();
	}

	@Override
	public boolean hasNext() {
		return (stateId < numStates);
	}

	@Override
	public void next(zx.soft.crf.model.FeatureImpl f) {
		f.copy(featureImpl);
		nextCopyDone(f);
	}

	protected void nextCopyDone(zx.soft.crf.model.FeatureImpl f) {
		f.yend = stateId;
		single.setFeatureIdentifier(f.strId.id * numStates + stateId, stateId, f.strId.name, f);
		advance();
	}

	/* (non-Javadoc)
	 * @see iitb.Model.FeatureTypes#requiresTraining()
	 */
	@Override
	public boolean requiresTraining() {
		return single.requiresTraining();
	}

	/* (non-Javadoc)
	 * @see iitb.Model.FeatureTypes#train(iitb.CRF.DataSequence, int)
	 */
	@Override
	public void train(DataSequence data, int pos) {
		single.train(data, pos);
	}

	@Override
	public void train(SegmentDataSequence sequence, int segStart, int segEnd) {
		single.train(sequence, segStart, segEnd);
	}

	@Override
	public boolean fixedTransitionFeatures() {
		return single.fixedTransitionFeatures();
	}

	@Override
	public boolean needsCaching() {
		return single.needsCaching();
	}
};
