package state;

import command.AddShapeCommand;
import command.Invoker;
import gui.DrawingPane;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import shape.Text;

/**
 *
 * @author group7
 */
public class DrawTextState implements DrawState {

    private DrawingPane drawingPane;
    private Invoker invoker;
    private Text creatingText;
    private TextField textTextField;
    ComboBox<Integer> fontDimensionComboBox;

    public DrawTextState(DrawingPane drawingPane) {
        this.drawingPane = drawingPane;
        this.textTextField = drawingPane.getTextTextField();
        this.fontDimensionComboBox = drawingPane.getFontDimensionComboBox();
        invoker = Invoker.getInstance();
    }

    @Override
    public void draw(double x, double y) {

    }

    @Override
    public void startDrawing(double xStartPoint, double yStartPoint, Color outlineColor, Color fillColor) {

        if (textTextField.textProperty().isEmpty().get()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Textfield empty");
            alert.setContentText("In order to insert a text, textfield cannot be empty.");
            alert.showAndWait();
        } else if (fontDimensionComboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Selected font not valid");
            alert.setContentText("In order to insert a text, choose a valid font size.");
            alert.showAndWait();
        } else {

            this.creatingText = new Text(xStartPoint, yStartPoint, textTextField.getText(), fontDimensionComboBox.getValue());

            this.creatingText.setStroke(outlineColor);
            this.creatingText.setFill(fillColor);

            AddShapeCommand addShapeCommand = new AddShapeCommand(this.drawingPane, this.creatingText);
            try {
                invoker.execute(addShapeCommand);
            } catch (Exception ex) {
            }
        }
    }

}
