package zx.soft.crf.model;

public class NoEdgeModel extends CompleteModel {

	private static final long serialVersionUID = 8955183589954114429L;

	class EmptyEdgeIter implements EdgeIterator {
		@Override
		public void start() {
		}

		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public Edge next() {
			return null;
		}

		@Override
		public boolean nextIsOuter() {
			return false;
		}
	};

	EmptyEdgeIter emptyIter;

	public NoEdgeModel(int nlabels) {
		super(nlabels);
		emptyIter = new EmptyEdgeIter();
		name = "NoEdge";
	}

	@Override
	public int numEdges() {
		return 0;
	}

	@Override
	public EdgeIterator edgeIterator() {
		return emptyIter;
	}
};
