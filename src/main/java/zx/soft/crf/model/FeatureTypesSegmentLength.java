package zx.soft.crf.model;

/**
 * This can be used as a wrapper around a FeatureTypes class that wants to
 * generate a feature for each label.
 */
public class FeatureTypesSegmentLength extends FeatureTypes {

	private static final long serialVersionUID = -4576759857994418037L;

	protected int segLen;
	protected int maxLen = Integer.MAX_VALUE;

	public FeatureTypesSegmentLength(FeatureGenImpl m) {
		super(m);
	}

	public FeatureTypesSegmentLength(FeatureGenImpl m, int maxSegLen) {
		super(m);
		maxLen = maxSegLen;
	}

	@Override
	public boolean startScanFeaturesAt(zx.soft.crf.core.DataSequence data, int prevPos, int pos) {
		segLen = Math.min(pos - prevPos, maxLen);
		return true;
	}

	@Override
	public boolean hasNext() {
		return segLen > 0;
	}

	@Override
	public void next(zx.soft.crf.model.FeatureImpl f) {
		f.val = 1;
		f.ystart = -1;
		if (featureCollectMode())
			f.strId.init(segLen, 0, "Length" + ((segLen == maxLen) ? ">=" : "=") + segLen);
		else
			f.strId.init(segLen);
		segLen = 0;
	}
};
