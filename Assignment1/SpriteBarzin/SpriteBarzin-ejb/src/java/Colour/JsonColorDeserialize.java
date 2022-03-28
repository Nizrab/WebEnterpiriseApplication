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
import java.awt.Event;
import java.lang.reflect.Type;
import javax.json.bind.serializer.DeserializationContext;
import javax.json.bind.serializer.JsonbDeserializer;
import javax.json.stream.JsonParser;
import static javax.json.stream.JsonParser.Event.KEY_NAME;
//JsonColorDeserialize class which implements JsonbDeserializer<Color>
public class JsonColorDeserialize implements JsonbDeserializer<Color> {
    @Override
    //Takes the KEY_NAME from the json 
    public Color deserialize(JsonParser parser, DeserializationContext ctx, Type rtType) {
        //Parses through and takes in the rgb values
        String keyname= "";  int value = 0; int red = 0; int green = 0; int blue = 0;
        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();
            //Gives us the name of the json varaiables 
            switch (event) {
                case KEY_NAME: {
                //sets the keyname
                keyname= parser.getString();
                }break;
                case VALUE_NUMBER: {
                    //IF statments to check the values geting passed in
                    value = parser.getInt();
                    if (keyname.equals("red")) red = value;
                    else if (keyname.equals("green")) green = value;
                    else if (keyname.equals("blue")) blue = value;
                }break;
            }
        }
        //Recreates the colour object with the values 
        return new Color(red,green,blue);
    }
}
