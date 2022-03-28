/**
 * @File: SprtieFacade
 * @Author: Barzin Farahani
 * @Assignment: Assignment 1
 * @Date: 23-077-2021
 * @Professor: Yemen Narshall
 */
package cst8218.alir0003.entity;
//Imports
import Colour.ColourAdapter; 
import Colour.JsonColorDeserialize; 
import Colour.JsonColorSerializer; 
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Random;
import javax.json.bind.annotation.JsonbTypeDeserializer;
import javax.json.bind.annotation.JsonbTypeSerializer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.hibernate.validator.constraints.Range;
@Entity
@XmlRootElement
//Sprite Class
public class Sprite implements Serializable {
    private static final long serialVersionUID = 1L;
    public final static Random random = new Random();
    final static int SIZE = 10;
    final static int MAX_SPEED = 5;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //Gets and Sets the ID
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column
    private Integer panelWidth;
    @Column
    private Integer panelHeight;
    @Column
    @Range(min = 0)
    private Integer x;
    @Column
    @Range(min = 0)
    private Integer y;
    @Column
    @Range(min = 0)
    private Integer dx;
    @Column
    @Range(min = 0)
    private Integer dy;
    @XmlElement
    @XmlJavaTypeAdapter(ColourAdapter.class)
    @JsonbTypeDeserializer(JsonColorDeserialize.class)
    @JsonbTypeSerializer(JsonColorSerializer.class)
    @Column
    private Color color = Color.BLUE;

    public Sprite() {
    }
    public Sprite(Integer height, Integer width) {
        this.panelWidth = width;
        this.panelHeight = height;
        x = random.nextInt(width);
        y = random.nextInt(height);
        dx = random.nextInt(2 * MAX_SPEED) - MAX_SPEED;
        dy = random.nextInt(2 * MAX_SPEED) - MAX_SPEED;
    }
    public Sprite(Integer height, Integer width, Color color) {
        this(height, width);
        this.color = color;
    }
    //Pannel Width Method
    public Integer getPanelWidth() {
        return panelWidth;
    }
    //Pannel Width Set Method
    public void setPanelWidth(Integer panelWidth) {
        this.panelWidth = panelWidth;
    }
    //Pannel Hight Get Method
    public Integer getPanelHeight() {
        return panelHeight;
    }
    //Pannel Hight Set Method
    public void setPanelHeight(Integer panelHeight) {
        this.panelHeight = panelHeight;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getDx() {
        return dx;
    }

    public void setDx(Integer dx) {
        this.dx = dx;
    }

    public Integer getDy() {
        return dy;
    }

    public void setDy(Integer dy) {
        this.dy = dy;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, SIZE, SIZE);
    }

    public void move() {
        // check for bounce and make the ball bounce if necessary
        if (x < 0 && dx < 0) {
            //bounce off the left wall 
            x = 0;
            dx = -dx;
        }
        if (y < 0 && dy < 0) {
            //bounce off the top wall
            y = 0;
            dy = -dy;
        }
        if (x > panelWidth - SIZE && dx > 0) {
            //bounce off the right wall
            x = panelWidth - SIZE;
            dx = -dx;
        }
        if (y > panelHeight - SIZE && dy > 0) {
            //bounce off the bottom wall
            y = panelHeight - SIZE;
            dy = -dy;
        }
        //make the ball move
        x += dx;
        y += dy;
    }
    //Updates obj to new values if not null
    public void updates(Sprite obj){
        if(obj.getColor() != null){
            this.color = obj.getColor();
        }
        if(obj.getPanelHeight() != null){
            this.panelHeight = obj.getPanelHeight(); 
        }
        if(obj.getPanelWidth() != null){
            this.panelWidth = obj.getPanelWidth();
        }
        if(obj.getDx() != null){
            this.dx = obj.getDx();
        }
        if(obj.getDy() != null){
            this.dy = obj.getDy();
        }
        if(obj.getX() != null){
            this.x = obj.getX();
        }
        if(obj.getY() != null){
            this.y = obj.getY();     
        } 
    }
    //Chceks if the values are null
    //If null Sets to default values
    public void ovveride(Sprite obj){
        if(obj.getColor() == null){
            this.color = obj.getColor();
        }
        if(obj.getPanelHeight() == null){
            this.panelHeight = 0; 
        }
        if(obj.getPanelWidth() == null){
            this.panelWidth = 0;
        }
        if(obj.getDx() == null){
            this.dx = 0;
        }
        if(obj.getDy() == null){
            this.dy = 0;
        }
        if(obj.getX() == null){
            this.x = 0;
        }
        if(obj.getY() == null){
            this.y = 0;     
        }  
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sprite)) {
            return false;
        }
        Sprite other = (Sprite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "entities.Sprite[ id=" + id + " ]";
    }
}
