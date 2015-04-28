package zx.soft.crf.core;

/**
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface Constraint {

	public static int UNION = 1;
	public static int PAIR_DISALLOW = 2;
	public static final int ALLOW_ONLY = 3;

	public int type();

}
