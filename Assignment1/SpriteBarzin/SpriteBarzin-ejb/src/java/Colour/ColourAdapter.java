/**
 * @File: SprtieFacade
 * @Author: Barzin Farahani
 * @Assignment: Assignment 1
 * @Date: 23-077-2021
 * @Professor: Yemen
 */
package Colour;
//Imports
import java.awt.Color;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
//ColourAdapter class
public class ColourAdapter extends XmlAdapter<ColourAdapter.ColorValueType, Color>{
    @Override
    //Returns the new ColorValueType
    public Color unmarshal(ColorValueType v) throws Exception {
        return new Color(v.red, v.green, v.blue);
    }
    @Override
    //Gets the ColorValueType
    public ColorValueType marshal(Color v) throws Exception {
         return new ColorValueType(v.getRed(), v.getGreen(), v.getBlue());
    }
    @XmlAccessorType(XmlAccessType.FIELD)
    //Sets the ColorValueType
    public static class ColorValueType{
        private int red;
        private int green;
        private int blue;
        public ColorValueType() {
        }
        public ColorValueType(int red, int green, int blue) {
            this.red= red;this.green= green;this.blue= blue;
        }
    } 
}
