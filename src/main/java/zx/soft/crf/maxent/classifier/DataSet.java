package zx.soft.crf.maxent.classifier;

import java.util.Vector;

import zx.soft.crf.core.DataIter;
import zx.soft.crf.core.DataSequence;

public class DataSet implements DataIter {

	Vector allRecords;
	int currPos = 0;

	public DataSet(Vector recs) {
		allRecords = recs;
	}

	@Override
	public void startScan() {
		currPos = 0;
	}

	@Override
	public boolean hasNext() {
		return (currPos < allRecords.size());
	}

	@Override
	public DataSequence next() {
		currPos++;
		return (DataRecord) allRecords.elementAt(currPos - 1);
	}

}
