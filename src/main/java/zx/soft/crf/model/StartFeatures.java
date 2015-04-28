package zx.soft.crf.model;

import zx.soft.crf.core.DataSequence;

public class StartFeatures extends FeatureTypes {

	private static final long serialVersionUID = 4603152016253011064L;

	int stateId;
	int startStateNum;
	Object fname;

	public StartFeatures(FeatureGenImpl m) {
		super(m);
		fname = "S.";
	}

	public StartFeatures(FeatureGenImpl m, Object name) {
		super(m);
		fname = name;
	}

	@Override
	public boolean startScanFeaturesAt(DataSequence data, int prevPos, int pos) {
		if (prevPos >= 0) {
			stateId = -1;
			return false;
		} else {
			startStateNum = 0;
			stateId = model.startState(startStateNum);
			return true;
		}
	}

	@Override
	public boolean hasNext() {
		return (stateId >= 0);
	}

	@Override
	public void next(FeatureImpl f) {
		setFeatureIdentifier(stateId, stateId, fname, f);
		f.yend = stateId;
		f.ystart = -1;
		f.val = 1;
		startStateNum++;
		stateId = model.startState(startStateNum);
	}
};
