package zx.soft.crf.bsegment.crf;

import zx.soft.crf.core.DataSequence;
import zx.soft.crf.core.FeatureGeneratorNested;

public interface BFeatureGenerator extends FeatureGeneratorNested {

	/**
	 * @return: the maximum gap between start and end boundary of features
	 */
	int maxBoundaryGap();

	void startScanFeaturesAt(DataSequence data);

	BFeature nextFeature();

}
