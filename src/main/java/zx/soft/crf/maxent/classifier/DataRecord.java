package zx.soft.crf.maxent.classifier;

import java.io.Serializable;

import zx.soft.crf.core.DataSequence;

public class DataRecord implements DataSequence, Serializable {

	private static final long serialVersionUID = 4853002531871806868L;

	int label;
	float vals[];

	public DataRecord(int ncols) {
		vals = new float[ncols];
	}

	public DataRecord(DataRecord dr) {
		vals = new float[dr.vals.length];
		for (int i = 0; i < vals.length; vals[i] = dr.vals[i], i++)
			;
		label = dr.label;
	}

	public DataRecord(float v[], int l) {
		vals = v;
		label = l;
	}

	@Override
	public int length() {
		return 1;
	}

	public int y() {
		return label;
	}

	@Override
	public int y(int i) {
		return label;
	}

	@Override
	public Object x(int i) {
		return vals;
	}

	@Override
	public void set_y(int i, int l) {
		label = l;
	}

	public float getColumn(int col) {
		return vals[col];
	}

	public void setColumn(int col, float val) {
		vals[col] = val;
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < vals.length; i++) {
			str += (vals[i] + " ");
		}
		str += label;
		return str;
	}
};
