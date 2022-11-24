package shape;

import javafx.scene.paint.Paint;

/**
 *
 * @author group7
 */
public abstract class Shape extends javafx.scene.shape.Shape {

    public Shape() {
        super();
    }

    public Paint getFillColor() {
        return super.getFill();
    }

    public Paint getOutlineColor() {
        return super.getStroke();
    }

    public void setFillColor(Paint color) {
        super.setFill(color);
    }

    public void setOutlineColor(Paint color) {
        super.setStroke(color);
    }

}
