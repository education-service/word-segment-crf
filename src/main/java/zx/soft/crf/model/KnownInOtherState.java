package zx.soft.crf.model;

import zx.soft.crf.core.DataSequence;

public class KnownInOtherState extends FeatureTypes {

	private static final long serialVersionUID = -9116122948866020445L;

	int stateId;
	WordsInTrain dict;
	float wordFreq;
	int wordPos;

	public KnownInOtherState(FeatureGenImpl m, WordsInTrain d) {
		super(m);
		dict = d;
	}

	void nextStateId() {
		for (stateId++; (stateId < model.numStates()); stateId++)
			if (dict.count(wordPos, stateId) == 0)
				return;
	}

	@Override
	public boolean startScanFeaturesAt(DataSequence data, int prevPos, int pos) {
		if (dict.count(data.x(pos)) <= WordFeatures.RARE_THRESHOLD + 1) {
			stateId = model.numStates();
			return false;
		} else {
			wordPos = dict.getIndex(data.x(pos));
			stateId = -1;
			nextStateId();
			wordFreq = (float) Math.log((double) dict.count(data.x(pos)) / dict.totalCount());
			return true;
		}
	}

	@Override
	public boolean hasNext() {
		return (stateId < model.numStates());
	}

	@Override
	public void next(FeatureImpl f) {
		setFeatureIdentifier(stateId, stateId, "K", f);
		f.yend = stateId;
		f.ystart = -1;
		f.val = wordFreq;
		nextStateId();
	}
};
