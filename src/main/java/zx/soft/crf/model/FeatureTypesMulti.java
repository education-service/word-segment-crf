package zx.soft.crf.model;

import zx.soft.crf.core.DataSequence;

/*
 * Implements the bag of features model for a given input sequence
 */
public class FeatureTypesMulti extends FeatureTypesWrapper {

	private static final long serialVersionUID = 10L;

	int currPos;
	int segEnd;
	int bagSize;
	transient DataSequence dataSeq;

	public FeatureTypesMulti(FeatureTypes s) {
		super(s);
	}

	void advance() {
		while (true) {
			if (single.hasNext())
				return;
			currPos++;
			if (currPos > segEnd)
				return;
			single.startScanFeaturesAt(dataSeq, currPos - 1, currPos);
		}
	}

	@Override
	public boolean startScanFeaturesAt(DataSequence data, int prevPos, int pos) {
		currPos = prevPos + 1;
		segEnd = pos;
		dataSeq = data;
		bagSize = pos - prevPos;
		single.startScanFeaturesAt(data, prevPos, prevPos + 1);
		advance();
		return single.hasNext();
	}

	@Override
	public boolean hasNext() {
		return (currPos <= segEnd) && single.hasNext();
	}

	@Override
	public void next(FeatureImpl f) {
		single.next(f);
		//f.val /= bagSize;
		advance();
	}
};
