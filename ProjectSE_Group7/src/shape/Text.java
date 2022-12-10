package shape;

import com.sun.javafx.sg.prism.NGShape;
import javafx.geometry.VPos;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 *
 * @author group7
 */
public class Text extends javafx.scene.text.Text{

    /**
     * method that creates an empty Text
     */
    public Text() {
        super();
        this.setTextOrigin(VPos.TOP);
        
    }

    /**
     * method that creates a Text from the given string.
     * @param string string that appears as Text.
     */
    public Text(String string) {
        super(string);
        this.setTextOrigin(VPos.TOP);
    }

    /**
     * method that creates a Text with the given x,y coordinates and string.
     * @param x the x coordinate where the text begins.
     * @param y the y coordinate where the text begins.
     * @param string the string to make into a Text.
     */
    public Text(double x, double y, String string) {
        super(x, y, string);
        this.setTextOrigin(VPos.TOP);
        
    }
    
    /**
     * method that creates a Text with the give x,y coordinates, string and fontSize
     * @param x the x coordinate where the text begins.
     * @param y the y coordinate where the text begins.
     * @param string the string to show as a Text.
     * @param fontSize the fontSize for the Text.
     */
    public Text(double x, double y, String string, int fontSize){
        super(x, y, string);
        this.setTextFontSize(fontSize);
        this.setTextOrigin(VPos.TOP);
    }

    /**
     * method to create a Text with the specified x,y coordinates, string, fontSize, fill and outline color.
     * @param x the x coordinate where the Text begins.
     * @param y the y coordinate where the Text begins.
     * @param string the string to show as a Text.
     * @param fontSize the fontSize for the Text.
     * @param fillColor the fillColor of the Text.
     * @param outlineColor the outlineColor of the Text.
     */
    public Text(double x, double y, String string, int fontSize, Paint outlineColor, Paint fillColor){
        super(x, y, string);
        this.setTextFontSize(fontSize);
        this.setFillColor(fillColor);
        this.setOutlineColor(outlineColor);
        this.setTextOrigin(VPos.TOP);
    }
    
    /**
     * method to set the string to be displayed as Text.
     * @param string the string to be displayed as Text.
     */
    public void setTextString(String string){
        super.setText(string);
    }
    
    /**
     * method to get the string displayed as Text.
     * @return the string that is displayed as Text.
     */
    public String getTextString(){
        return super.getText();
    }
    
    /**
     * method to set the fontSize for the Text.
     * @param fontSize the fontSize for the Text.
     */
    public void setTextFontSize(int fontSize){
        super.setFont(new Font(fontSize));
    }
    
    /**
     * the method to get the fontSize for the Text.
     * @return the fontSize for the Text.
     */
    public int getTextFontSize(){
        return (int) super.getFont().getSize();
    }
    /**
     * method to set the x coordinate for the beginning for the Text.
     * @param x the starting x coordinate.
     */
   public void setTextX(double x){
       
       super.setX(x);
   }
   
   /**
    * method to set the starting y coordinate for the Text.
    * @param y the starting y coordinate.
    */
   public void setTextY(double y){
     
       super.setY(y);
   }
   
   /**
    * method to get the x coordinate where the text starts.
    * @return the x coordinate where the text starts.
    */
   public double getTextX(){
      
       return super.getX();
   }
   
   /**
    * method that returns the y coordinate where the text starts.
    * @return the y coordinate where the text starts.
    */
   public double getTextY(){
      
       return super.getY();
   }
   
   /**
    * method that sets the specified color as the Text fill color.
    * @param fill the color to use as the Text fill color.
    */
   public void setFillColor(Paint fill) {
        this.setFill(fill);
    }
   
   /**
    * method to get the Text fill color.
    * @return the text fill color.
    */
    public Paint getFillColor() {
        return this.getFill();
    }

    /**
     * method to set the specified color as the Text outline color.
     * @param outline the color to use as the Text outline color.
     */
    public void setOutlineColor(Paint outline) {
        this.setStroke(outline);
    }

    /**
     * method to get the Text outline color.
     * @return the Text outline color.
     */
    public Paint getOutlineColor() {
        return this.getStroke();
    }
    
    /**
     * method to get the height of the Text
     * @return the height of the Text
     */
    public Double getTextHeight(){
        return this.getBoundsInLocal().getHeight();
    }
    
    /**
     * method to get the width of the Text.
     * @return the width of the Text.
     */
    public Double getTextWidth(){
        return this.getBoundsInLocal().getWidth();
    }

    /**
     * method to move the Text of the specified x and y offsets.
     * @param x the x offset to add to the Text x coordinate.
     * @param y the y offset to add to the Text y coordinate.
     */
    public void moveOf(double x, double y) {
      
        this.setTextX(this.getTextX() + x);
        this.setTextY(this.getTextY() + y);
       
    }

   
    /**
     * method that returns a string representation for the Text.
     * @return the string representation for the Text.
     */
    @Override
    public String toString() {
        Color outlineColor = (Color) this.getOutlineColor();
        Color fillColor = (Color) this.getFillColor();
        return "text;" + this.getTextX() + ";" + this.getTextY() + ";" + this.getTextString() + ";"+ this.getTextFontSize() + ";" + 
                toHexString(outlineColor) + ";" + toHexString(fillColor) + ";" +
                this.getScaleX() + ";" + this.getScaleY() + ";" + this.getRotate();
    }

    /**
     * Function that returns a hexadecimal representation of a Color object
     *
     * @param color Input Color object.
     * @return
     */
    private static String toHexString(Color color) {
        int r = ((int) Math.round(color.getRed() * 255));
        int g = ((int) Math.round(color.getGreen() * 255));
        int b = ((int) Math.round(color.getBlue() * 255));

        return String.format("#%02x%02x%02x", r, g, b).toLowerCase();
    }

    
}
