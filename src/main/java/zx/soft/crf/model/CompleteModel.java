package zx.soft.crf.model;

import zx.soft.crf.core.DataSequence;

public class CompleteModel extends Model {

	private static final long serialVersionUID = 9000160197804401741L;

	public CompleteModel(int nlabels) {
		super(nlabels);
		name = "Complete";
	}

	@Override
	public int numStates() {
		return numLabels;
	}

	@Override
	public int label(int stateNum) {
		return stateNum;
	}

	@Override
	public int numEdges() {
		return numLabels * numLabels;
	}

	@Override
	public int numStartStates() {
		return numLabels;
	}

	@Override
	public int numEndStates() {
		return numLabels;
	}

	@Override
	public int startState(int i) {
		if (i < numStartStates())
			return i;
		return -1;
	}

	@Override
	public int endState(int i) {
		if (i < numEndStates())
			return i;
		return -1;
	}

	@Override
	public void stateMappings(DataSequence data) throws Exception {
		;
	}

	@Override
	public void stateMappings(DataSequence data, int len, int start) throws Exception {
		;
	}

	@Override
	public boolean isEndState(int i) {
		return true;
	}

	@Override
	public boolean isStartState(int i) {
		return true;
	}

	public class SingleEdgeIterator implements EdgeIterator {
		CompleteModel model;
		Edge edge;
		Edge edgeToReturn;

		SingleEdgeIterator(CompleteModel m) {
			model = m;
			edge = new Edge();
			edgeToReturn = new Edge();
			start();
		};

		@Override
		public void start() {
			edge.start = 0;
			edge.end = 0;
		}

		@Override
		public boolean hasNext() {
			return (edge.start < model.numStates());
		}

		@Override
		public Edge next() {
			edgeToReturn.start = edge.start;
			edgeToReturn.end = edge.end;
			edge.end++;
			if (edge.end == model.numStates()) {
				edge.end = 0;
				edge.start++;
			}
			return edgeToReturn;
		}

		@Override
		public boolean nextIsOuter() {
			return true;
		}
	};

	@Override
	public EdgeIterator edgeIterator() {
		return new SingleEdgeIterator(this);
	}

	/* (non-Javadoc)
	 * @see iitb.Model.Model#innerEdgeIterator()
	 */
	@Override
	public EdgeIterator innerEdgeIterator() {
		return null;
	}
};
