package zx.soft.crf.segment;

public class LabelMap {

	/**
	 * Decrements the parameter by one.
	 * @param l Value to be decremented.
	 * @return l - 1
	 */
	public int map(int l) {
		return l - 1;
	}

	/**
	 * Increments the parameter by one.
	 * @param l Value to be incremented.
	 * @return l + 1
	 */
	public int revMap(int l) {
		return l + 1;
	}
}

class BinaryLabelMap extends LabelMap {

	int posClass;

	BinaryLabelMap(int sel) {
		posClass = sel;
	}

	@Override
	public int map(int el) {
		return (posClass == el) ? 1 : 0;
	}

	@Override
	public int revMap(int label) {
		return (label == 1) ? posClass : 0;
	}
}
