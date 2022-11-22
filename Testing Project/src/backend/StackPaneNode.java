package backend;

public class StackPaneNode<T> {
	private String paneId;
	private T fxmlController;
	
	public StackPaneNode(String paneId, T fxmlController) {
		this.paneId = paneId;
		this.fxmlController = fxmlController;
	}

	public T getFxmlController() {
		return fxmlController;
	}

	public void setFxmlController(T fxmlController) {
		this.fxmlController = fxmlController;
	}

	public String getPaneId() {
		return paneId;
	}

	public void setPaneId(String paneId) {
		this.paneId = paneId;
	}
	
	
	
}
