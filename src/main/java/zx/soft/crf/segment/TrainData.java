package zx.soft.crf.segment;

import zx.soft.crf.core.DataIter;
import zx.soft.crf.core.DataSequence;

public interface TrainData extends DataIter {

	int size(); // number of training records

	@Override
	void startScan(); // start scanning the training data

	boolean hasMoreRecords();

	public TrainRecord nextRecord();

	@Override
	boolean hasNext();

	@Override
	public DataSequence next();

}
