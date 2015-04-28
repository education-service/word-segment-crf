package zx.soft.crf.core;

import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.SparseDoubleMatrix2D;

public class LogSparseDoubleMatrix2D extends SparseDoubleMatrix2D {

	static double map(double val) {
		return LogSparseDoubleMatrix1D.map(val);
	}

	static double reverseMap(double val) {
		return LogSparseDoubleMatrix1D.reverseMap(val);
	}

	public LogSparseDoubleMatrix2D(int numR, int numC) {
		super(numR, numC);
	}

	@Override
	public DoubleMatrix2D assign(double val) {
		return super.assign(map(val));
	}

	@Override
	public void set(int row, int column, double val) {
		super.set(row, column, map(val));
	}

	@Override
	public double get(int row, int column) {
		return reverseMap(super.get(row, column));
	}

	@Override
	public DoubleMatrix1D zMult(DoubleMatrix1D y, DoubleMatrix1D z, double alpha, double beta, boolean transposeA) {
		return RobustMath.logMult(this, y, z, alpha, beta, transposeA);
	}
};
