package zx.soft.crf.core;

/**
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class RestrictConstraint implements Constraint {

	@Override
	public final int type() {
		return ALLOW_ONLY;
	}

	public abstract void startScan();

	public abstract boolean hasNext();

	public abstract void advance();

	public abstract int y();

	public abstract int yprev();

}
