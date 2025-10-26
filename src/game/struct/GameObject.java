package game.struct;

import javafx.scene.image.Image;

public class GameObject {

	String name;
	Image image;

	public GameObject(String pName, Image pImage) {
		name = pName;
		image = pImage;
	}

	public String getName() { 
		return name;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image pImage) {
		image = pImage;
	}

	@Override
	public GameObject clone() {
		return new GameObject(name, image);
	}
}