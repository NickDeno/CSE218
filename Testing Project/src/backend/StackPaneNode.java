package backend;

public class StackPaneNode<T> {
	private T fxmlController;
	private String paneId;
	
	public StackPaneNode(T fxmlController, String paneId) {
		this.fxmlController = fxmlController;
		this.paneId = paneId;
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
