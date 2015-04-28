package zx.soft.crf.model;

import gnu.trove.map.hash.TIntIntHashMap;
import zx.soft.crf.core.DataSequence;
import zx.soft.crf.core.Feature;
import zx.soft.crf.core.FeatureGenCache;

public class FeatureGenSelectiveCache extends FeatureGenCache {

	FeatureGenImpl fgenImpl;

	/**
	 * @param fgen
	 */
	public FeatureGenSelectiveCache(FeatureGenImpl fgen, boolean edgeFeaturesXInd) {
		super(fgen, edgeFeaturesXInd);
		fgenImpl = fgen;
	}

	@Override
	public boolean hasNext() {
		return super.hasNext() ? true : fgenImpl.hasNext();
	}

	/* (non-Javadoc)
	 * @see iitb.CRF.FeatureGenerator#next()
	 */
	@Override
	public Feature next() {
		if (firstScan) {
			boolean needsCaching = fgenImpl.currentFeatureType.needsCaching();
			Feature f = fgenImpl.next();
			if (needsCaching)
				cacheFeature(f);
			return f;
		} else {
			if (super.hasNext())
				return super.next();
			return fgenImpl.next();
		}
	}

	TIntIntHashMap idIndexMap = new TIntIntHashMap();
	int prevId = -1;

	/* (non-Javadoc)
	 * @see iitb.CRF.FeatureGeneratorNested#startScanFeaturesAt(iitb.CRF.DataSequence, int, int)
	 */
	@Override
	protected void startScanFeaturesAt(DataSequence data, int prevPos, int pos, boolean nested) {
		super.startScanFeaturesAt(data, prevPos, pos, nested);
		if (firstScan) {
			;
		} else {
			if (nested)
				fgenImpl.startScanFeaturesAtOnlyNonCached(data, prevPos, pos);
			else
				fgenImpl.startScanFeaturesAtOnlyNonCached(data, pos);
		}
	}
}
