package zx.soft.crf.bsegment;

import zx.soft.crf.bsegment.crf.BFeature;
import zx.soft.crf.model.FeatureImpl;

public class BFeatureImpl extends FeatureImpl implements BFeature {

	private static final long serialVersionUID = -818475084232481221L;
	int _startB;
	int _endB;
	boolean _startOpen;
	boolean _endOpen;

	/**
	 *
	 */
	public BFeatureImpl() {
		super();
	}

	/**
	 * @param arg0
	 */
	public BFeatureImpl(FeatureImpl arg0) {
		super(arg0);
	}

	/* (non-Javadoc)
	 * @see iitb.BSegmentCRF.BFeature#start()
	 */
	@Override
	public int start() {
		return _startB;
	}

	/* (non-Javadoc)
	 * @see iitb.BSegmentCRF.BFeature#startOpen()
	 */
	@Override
	public boolean startOpen() {
		return _startOpen;
	}

	/* (non-Javadoc)
	 * @see iitb.BSegmentCRF.BFeature#end()
	 */
	@Override
	public int end() {
		return _endB;
	}

	/* (non-Javadoc)
	 * @see iitb.BSegmentCRF.BFeature#endOpen()
	 */
	@Override
	public boolean endOpen() {
		return _endOpen;
	}

	public void copy(BFeatureImpl feature) {
		super.copy(feature);
		copyBoundary(feature);
	}

	@Override
	public String toString() {
		return super.toString() + " S:" + _startB + ":" + _startOpen + " E:" + _endB + ":" + _endOpen;
	}

	/**
	 * @param boundary
	 */
	public void copyBoundary(BFeatureImpl feature) {
		// TODO Auto-generated method stub
		_startB = feature._startB;
		_endB = feature._endB;
		_endOpen = feature._endOpen;
		_startOpen = feature._startOpen;
	}

}
