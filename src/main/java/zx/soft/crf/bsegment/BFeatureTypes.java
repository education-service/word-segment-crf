package zx.soft.crf.bsegment;

import zx.soft.crf.core.DataSequence;
import zx.soft.crf.model.FeatureGenImpl;
import zx.soft.crf.model.FeatureImpl;
import zx.soft.crf.model.FeatureTypes;

public abstract class BFeatureTypes extends FeatureTypes implements BoundaryFeatureFunctions {

	private static final long serialVersionUID = -7882587085868697212L;

	public BFeatureTypes(FeatureGenImpl fgen) {
		super(fgen);
	}

	/**
	 * @param single
	 */
	public BFeatureTypes(FeatureTypes single) {
		super(single);
	}

	public abstract boolean startScanFeaturesAt(DataSequence arg);

	@Override
	public abstract void next(BFeatureImpl arg0);

	/* (non-Javadoc)
	 * @see iitb.Model.FeatureTypes#startScanFeaturesAt(iitb.CRF.DataSequence, int, int)
	 */
	//public boolean startScanFeaturesAt(DataSequence data, int prevPos, int pos) {
	//    return false;
	//}
	/* (non-Javadoc)
	 * @see iitb.Model.FeatureTypes#next(iitb.Model.FeatureImpl)
	 */
	@Override
	public void next(FeatureImpl f) {
		System.err.println("WARNING: Semi-CRF feature not implemented ");
	}

}
