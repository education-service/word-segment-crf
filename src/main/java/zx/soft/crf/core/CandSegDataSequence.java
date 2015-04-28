package zx.soft.crf.core;

import java.util.Iterator;

public interface CandSegDataSequence extends SegmentDataSequence, CandidateSegments {

	boolean holdsInTrainingData(Feature feature, int prevPos, int pos);

	public Iterator constraints(int prevPos, int pos);

}
