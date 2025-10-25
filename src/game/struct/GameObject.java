package game.struct;

import javafx.scene.image.Image;

public class GameObject {

	Image image;

	public GameObject(Image pImage) {
		image = pImage;
	}

	public Image getImage() {
		return image;
	}

	@Override
	public GameObject clone() {
		return new GameObject(image);
	}
}