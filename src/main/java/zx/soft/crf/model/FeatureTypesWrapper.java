package zx.soft.crf.model;

import zx.soft.crf.core.DataSequence;
import zx.soft.crf.core.SegmentDataSequence;

public class FeatureTypesWrapper extends FeatureTypes {

	private static final long serialVersionUID = -8994427102150729372L;

	protected FeatureTypes single;

	/**
	 * @param m
	 */
	public FeatureTypesWrapper(FeatureTypes ftype) {
		super(ftype);
		this.single = ftype;
	}

	/* (non-Javadoc)
	 * @see iitb.Model.FeatureTypes#startScanFeaturesAt(iitb.CRF.DataSequence, int, int)
	 */
	@Override
	public boolean startScanFeaturesAt(DataSequence data, int prevPos, int pos) {
		return single.startScanFeaturesAt(data, prevPos, pos);
	}

	/* (non-Javadoc)
	 * @see iitb.Model.FeatureTypes#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return single.hasNext();
	}

	/* (non-Javadoc)
	 * @see iitb.Model.FeatureTypes#next(iitb.Model.FeatureImpl)
	 */
	@Override
	public void next(FeatureImpl f) {
		single.next(f);
	}

	/*public void print(FeatureMap strToInt, double[] crfWs) {
		ftype.print(strToInt, crfWs);
	}
	*/
	@Override
	public int labelIndependentId(FeatureImpl f) {
		return single.labelIndependentId(f);
	}

	@Override
	public int maxFeatureId() {
		return single.maxFeatureId();
	}

	@Override
	int offsetLabelIndependentId(FeatureImpl f) {
		return single.offsetLabelIndependentId(f);
	}

	@Override
	public void setFeatureIdentifier(int fId, FeatureImpl f) {
		single.setFeatureIdentifier(fId, f);
	}

	@Override
	public void setFeatureIdentifier(int fId, int stateId, Object name, FeatureImpl f) {
		single.setFeatureIdentifier(fId, stateId, name, f);
	}

	@Override
	public void setFeatureIdentifier(int fId, int stateId, String name, FeatureImpl f) {
		single.setFeatureIdentifier(fId, stateId, name, f);
	}

	@Override
	public boolean requiresTraining() {
		return single.requiresTraining();
	}

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

	@Override
	public String name() {
		return single.name() + "_" + getClass().getName();
	}
}
