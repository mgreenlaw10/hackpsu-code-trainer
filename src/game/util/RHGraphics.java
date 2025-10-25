package game.util;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RHGraphics {

	public static void drawCenteredText(GraphicsContext graphics, String text, double centerX, double centerY) {

        Font font = graphics.getFont();
        Text t = new Text(text);
        t.setFont(font);
        
        double textWidth = t.getLayoutBounds().getWidth();
        double textHeight = t.getLayoutBounds().getHeight();
        double drawX = centerX - textWidth / 2;
        double drawY = centerY + textHeight / 4;

        graphics.fillText(text, drawX, drawY);
    }
}