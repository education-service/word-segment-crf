package zx.soft.crf.model;

import zx.soft.crf.core.DataSequence;

public class ClassPriorFeature extends FeatureTypes {

	private static final long serialVersionUID = 16L;
	transient int thisClassId;

	/**
	 * @param fgen
	 */
	public ClassPriorFeature(FeatureGenImpl fgen) {
		super(fgen);
	}

	/* (non-Javadoc)
	 * @see iitb.Model.FeatureTypes#startScanFeaturesAt(iitb.CRF.DataSequence, int, int)
	 */
	@Override
	public boolean startScanFeaturesAt(DataSequence data, int prevPos, int pos) {
		thisClassId = model.numStates() - 1;
		return hasNext();
	}

	/* (non-Javadoc)
	 * @see iitb.Model.FeatureTypes#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return thisClassId >= 0;
	}

	/* (non-Javadoc)
	 * @see iitb.Model.FeatureTypes#next(iitb.Model.FeatureImpl)
	 */
	@Override
	public void next(FeatureImpl f) {
		f.yend = thisClassId;
		f.ystart = -1;
		f.val = 1;
		String name = "";
		if (featureCollectMode()) {
			name = "Bias_" + thisClassId;
		}
		setFeatureIdentifier(thisClassId, thisClassId, name, f);
		thisClassId--;
	}

}
