package zx.soft.crf.core;

/**
 * The basic interface to be implemented by the user of this package for
 * providing training and test data to the learner.
 */
public interface DataIter {

	void startScan();

	boolean hasNext();

	DataSequence next();

}
