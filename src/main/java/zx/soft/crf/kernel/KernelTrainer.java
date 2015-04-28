package zx.soft.crf.kernel;

import java.util.Vector;

import zx.soft.crf.core.CrfParams;
import zx.soft.crf.core.Trainer;
import zx.soft.crf.kernel.KernelCRF.SupportVector;

public abstract class KernelTrainer extends Trainer {

	public KernelTrainer(CrfParams p) {
		super(p);
	}

	public abstract Vector<SupportVector> getSupportVectors();

	public abstract SequenceKernel getKernel();

}
