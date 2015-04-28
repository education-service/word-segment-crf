package zx.soft.crf.kernel;

import java.io.Serializable;

import zx.soft.crf.core.DataSequence;

public interface SequenceKernel extends Serializable {

	public double kernel(DataSequence d1, int p1, DataSequence d2, int p2);

}
