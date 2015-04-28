package zx.soft.crf.bsegment;

import zx.soft.crf.core.DataSequence;

public interface BoundaryFeatureFunctions {

	// public void assignBoundary(BFeatureImpl feature, int pos);
	public int maxBoundaryGap();

	public void next(BFeatureImpl feature);

	public boolean startScanFeaturesAt(DataSequence data, int pos);

}
