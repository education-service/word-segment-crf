package zx.soft.crf.utils;

import gnu.trove.map.hash.TIntObjectHashMap;

public class OptimizedSparseDoubleMatrix2D {

	TIntObjectHashMap<OptimizedSparseDoubleMatrix1D> rows; //row-index --> row (OptimizedSparseDoubleMatrix1D

	public OptimizedSparseDoubleMatrix2D(int capacity) {
		rows = new TIntObjectHashMap<OptimizedSparseDoubleMatrix1D>(capacity);
	}

	public OptimizedSparseDoubleMatrix2D() {
		this(0);
	}

	public OptimizedSparseDoubleMatrix1D getRow(int rowId) {
		return rows.get(rowId);
	}

	public void setRow(int rowId, OptimizedSparseDoubleMatrix1D row) {
		rows.put(rowId, row);
	}

	public void clear() {
		rows.clear();
	}

	public static void main(String[] args) {
	}

}
