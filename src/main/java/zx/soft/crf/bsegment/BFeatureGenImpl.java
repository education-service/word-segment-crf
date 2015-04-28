package zx.soft.crf.bsegment;

import zx.soft.crf.bsegment.crf.BFeature;
import zx.soft.crf.bsegment.crf.BFeatureGenerator;
import zx.soft.crf.core.DataIter;
import zx.soft.crf.core.DataSequence;
import zx.soft.crf.core.Segmentation;
import zx.soft.crf.model.ClassPriorFeature;
import zx.soft.crf.model.ConcatRegexFeatures;
import zx.soft.crf.model.EdgeFeatures;
import zx.soft.crf.model.EndFeatures;
import zx.soft.crf.model.FeatureGenImpl;
import zx.soft.crf.model.FeatureImpl;
import zx.soft.crf.model.FeatureTypes;
import zx.soft.crf.model.Model;
import zx.soft.crf.model.StartFeatures;
import zx.soft.crf.model.WindowFeatures;
import zx.soft.crf.model.WordFeatures;
import zx.soft.crf.model.WordsInTrain;

public class BFeatureGenImpl extends FeatureGenImpl implements BFeatureGenerator {

	private static final long serialVersionUID = 1L;

	boolean bfeatureMode = false;
	BFeatureImpl bfeature = new BFeatureImpl(), bfeatureToReturn = new BFeatureImpl();
	int maxGap = 1;

	/**
	 * @param arg0
	 * @param arg1
	 * @throws java.lang.Exception
	 */
	public BFeatureGenImpl(String arg0, int arg1) throws Exception {
		super(arg0, arg1);
	}

	public BFeatureGenImpl(String arg0, int arg1, java.util.Properties options) throws Exception {
		super(arg0, arg1, false);
		if (options.getProperty("MaxMemory") != null) {
			maxGap = Integer.parseInt(options.getProperty("MaxMemory"));
		}
		addFeatures();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws java.lang.Exception
	 */
	public BFeatureGenImpl(String arg0, int arg1, boolean arg2) throws Exception {
		super(arg0, arg1, arg2);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @throws java.lang.Exception
	 */
	public BFeatureGenImpl(Model arg0, int arg1, boolean arg2) throws Exception {
		super(arg0, arg1, arg2);
	}

	public void addFeature(BFeatureTypes fType, boolean retainThis) {
		super.addFeature(fType, retainThis);
		maxGap = Math.max(maxGap, fType.maxBoundaryGap());
	}

	public void addFeature(BFeatureTypes fType) {
		super.addFeature(fType);
		maxGap = Math.max(maxGap, fType.maxBoundaryGap());
	}

	/* (non-Javadoc)
	 * @see iitb.BSegmentCRF.BFeatureGenerator#maxBoundaryGap()
	 */
	@Override
	public int maxBoundaryGap() {
		return maxGap;
	}

	@Override
	protected void addFeatures() {
		addFeature(new BFeatureEachPosition(new BFeatureEachPosition.TypePosEndOpen(new StartFeatures(this))));
		addFeature(new BFeatureEachPosition(new BFeatureEachPosition.TypePosStartOpen(new EndFeatures(this))));
		addFeature(new BFeatureEachPosition(new BFeatureEachPosition.TypePosEndOpen(new ClassPriorFeature(this))), true);
		addFeature(new BFeatureEachPosition(new BFeatureEachPosition.TypePosEndOpen(new EdgeFeatures(this))), true);
		WindowFeatures.Window windows[] = new WindowFeatures.Window[] {
				new WindowFeatures.Window(0, true, 0, true, "start", 2, Integer.MAX_VALUE),
				new WindowFeatures.Window(0, false, 0, false, "end", 2, Integer.MAX_VALUE),
				new WindowFeatures.Window(1, true, -1, false, "continue", 3, Integer.MAX_VALUE),
				new WindowFeatures.Window(0, true, 0, false, "Unique", 1, 1),
				new WindowFeatures.Window(-1, true, -1, true, "left-1"),
				new WindowFeatures.Window(1, false, 1, false, "right+1"), };

		dict = new WordsInTrain();
		addFeature(new BFeatureEachPosition(new BWindowFeatureMulti(windows, new WordFeatures(this, dict))));

		FeatureTypes features = new ConcatRegexFeatures(this, 0, 0);
		addFeature(new BFeatureEachPosition(
				new BFeatureTypesEachLabel(this, new BWindowFeatureMulti(windows, features))));

		addFeature(new BFeatureEachPosition(new BFeatureTypesEachLabel(this, new BSegmentLength(this, maxGap))));
	}

	/* (non-Javadoc)
	 * @see iitb.BSegmentCRF.BFeatureGenerator#startScanFeaturesAt(iitb.BSegmentCRF.BDataSequence)
	 */
	@Override
	public void startScanFeaturesAt(DataSequence d) {
		bfeatureMode = true;
		for (int i = numFeatureTypes() - 1; i >= 0; i--) {
			((BFeatureTypes) getFeature(i)).startScanFeaturesAt(d);
		}
		super.initScanFeaturesAt(d);
	}

	@Override
	protected void copyNextFeature(FeatureImpl featureToReturn) {
		if (!bfeatureMode) {
			super.copyNextFeature(featureToReturn);
			return;
		}
		((BFeatureTypes) currentFeatureType).next(bfeatureToReturn);
		featureToReturn.copy(bfeatureToReturn);
	}

	@Override
	public boolean featureValid(DataSequence data, int cposStart, int cposEnd, FeatureImpl featureToReturn,
			Model model, boolean cacheEdgeFeatures) {
		return true;
	}

	/* (non-Javadoc)
	 * @see iitb.BSegmentCRF.BFeatureGenerator#nextFeature()
	 */
	@Override
	public BFeature nextFeature() {
		bfeature.copy(bfeatureToReturn);
		bfeature.copy(featureToReturn);
		assert (featureToReturn.identifier().equals(bfeatureToReturn.identifier()));
		advance();
		return bfeature;
	}

	@Override
	public int addTrainRecord(DataSequence seq) {
		int numF = 0;
		for (startScanFeaturesAt(seq); hasNext();) {
			BFeature feature = nextFeature();
			numF++;
		}
		return numF;
	}

	@Override
	public boolean train(DataIter trainData, boolean cachedLabels) throws Exception {
		boolean retVal = super.train(trainData, cachedLabels, false);
		for (trainData.startScan(); trainData.hasNext();) {
			DataSequence seq = trainData.next();
			for (startScanFeaturesAt(seq); hasNext(); nextFeature())
				;
		}
		freezeFeatures();
		return retVal;
	}

	@Override
	protected boolean retainFeature(DataSequence seq, FeatureImpl f) {
		Segmentation data = (Segmentation) seq;
		BFeature feature = bfeatureToReturn;
		if (data.getSegmentId(feature.start()) != data.getSegmentId(feature.end()))
			return false;
		int segNum = data.getSegmentId(feature.start());
		if (data.segmentLabel(segNum) != feature.y())
			return false;
		if (!feature.startOpen() && (data.segmentStart(segNum) != feature.start()))
			return false;
		if (!feature.endOpen() && (data.segmentEnd(segNum) != feature.end()))
			return false;
		if ((segNum == 0) && (feature.yprev() >= 0))
			return false;
		if ((segNum > 0) && (feature.yprev() >= 0) && (data.segmentLabel(segNum - 1) != feature.yprev()))
			return false;
		return true;
	}

	@Override
	public void startScanFeaturesAt(DataSequence d, int prev, int p) {
		bfeatureMode = false;
		super.startScanFeaturesAt(d, prev, p);
	}

}
