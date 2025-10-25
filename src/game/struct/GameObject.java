package game.struct;

import javafx.scene.image.Image;

public class GameObject {

	String name;
	Image image;

	public GameObject(String pName, Image pImage) {
		name = pName;
		image = pImage;
	}

	public Image getImage() {
		return image;
	}

	@Override
	public GameObject clone() {
		return new GameObject(name, image);
	}
}