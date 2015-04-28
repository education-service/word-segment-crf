package zx.soft.crf.bsegment.crf;

import gnu.trove.map.hash.TIntDoubleHashMap;

import java.util.Properties;

import zx.soft.crf.core.DataSequence;
import zx.soft.crf.core.SegmentCRF;
import zx.soft.crf.core.Trainer;
import zx.soft.crf.core.Viterbi;

/**
 * BSegmentCRF (A significantly faster version of Semi-CRFs that employs a compact feature representation)
 * for fast training and inference of semi-Markov models.
 *
 * @author wanggang
 *
 */
public class BSegmentCRF extends SegmentCRF {

	private static final long serialVersionUID = 655531342585250258L;

	BFeatureGenerator bfgen;

	/**
	 * @param numLabels
	 * @param fgen
	 * @param arg
	 */
	public BSegmentCRF(int numLabels, BFeatureGenerator fgen, String arg) {
		super(numLabels, fgen, arg);
		bfgen = fgen;
	}

	/**
	 * @param numLabels
	 * @param fgen
	 * @param configOptions
	 */
	public BSegmentCRF(int numLabels, BFeatureGenerator fgen, Properties configOptions) {
		super(numLabels, fgen, configOptions);
		bfgen = fgen;
	}

	@Override
	protected Trainer getTrainer() {
		Trainer thisTrainer = dynamicallyLoadedTrainer();
		if (thisTrainer != null)
			return thisTrainer;
		return new BSegmentTrainer(params);
	}

	@Override
	public Viterbi getViterbi(int beamsize) {
		return params.miscOptions.getProperty("segmentViterbi", "false").equals("true") ? super.getViterbi(beamsize)
				: new BSegmentViterbi(this, numY, beamsize);
	}

	@Override
	public double segmentMarginalProbabilities(DataSequence dataSequence, TIntDoubleHashMap segmentMarginals[][],
			TIntDoubleHashMap edgeMarginals[][][]) {
		System.err.println("Not yet implemented for this CRF--use SegmentCRF");
		System.exit(-1);
		return 0;
	}

}
