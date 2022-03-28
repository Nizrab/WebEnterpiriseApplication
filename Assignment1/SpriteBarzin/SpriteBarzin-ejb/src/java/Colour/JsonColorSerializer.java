/**
 * @File: SprtieFacade
 * @Author: Barzin Farahani
 * @Assignment: Assignment 1
 * @Date: 23-077-2021
 * @Professor: Yemen Narshall
 */
package Colour;
//Imports
import java.awt.Color;
import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;
//Converts colour object to json
public class JsonColorSerializer implements JsonbSerializer<Color> {
    @Override
    public void serialize(Color c, JsonGenerator jg, SerializationContext ctx) {
        //Begins the write for object
        jg.writeStartObject();
        //Writes the values to a json
        jg.write("red", c.getRed());
        jg.write("green", c.getGreen());
        jg.write("blue", c.getBlue());
        jg.writeEnd();
    }
}
