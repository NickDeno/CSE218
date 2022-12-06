package model;

import java.io.Serializable;

public class FXImage implements Serializable {
	private static final long serialVersionUID = 12L;
	
	private byte[] imageBuffer;

    public FXImage(byte[] imageBuffer) {
        this.imageBuffer = imageBuffer;
    }
    
    public byte[] returnBytes() {
        return imageBuffer;
    }

}
