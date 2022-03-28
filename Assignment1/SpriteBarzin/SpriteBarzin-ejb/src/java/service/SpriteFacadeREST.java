/**
 * @File: SprtieFacade
 * @Author: Barzin Farahani
 * @Assignment: Assignment 1
 * @Date: 23-077-2021
 * @Professor: Yemen
 */
package service;
//Imports
import cst8218.alir0003.entity.Sprite;
import java.util.List;
import java.util.Locale;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
//SpriteFacadeREST class
@Stateless
//Sets the path
@Path("cst8218.alir0003.entity.sprite")
public class SpriteFacadeREST extends AbstractFacade<Sprite> {
    @PersistenceContext(unitName = "SpriteBarzin-ejbPU")
    private EntityManager em;
    public SpriteFacadeREST() {
        super(Sprite.class);
    }
    //post method which checks if the id is null or not 
    //Depending on the Id it will ither auto fill or send a error 
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response creates(Sprite entity) {
        //checks if id null
        if(entity.getId() == null){
            super.create(entity);
        }
        //checks if id not null
        if(entity.getId() != null){
            super.find(entity.getId()).ovveride(entity);  
            ResponseBuilder build = Response.ok();
            build.language(Locale.CANADA).header("Sucessful","200");
            return build.build();
        }
        //If any errors 
        else{
            ResponseBuilder build = Response.ok();
            build.language(Locale.CANADA).header("Incorrect ID","404");
            return build.build();
        }   
    }
    //Post method using the ID
    @POST
    //Uses the value of the id in the path
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response poster(@PathParam("id") Long id, Sprite entity) {
        //checks if the id is not null 
        if(id.equals(entity.getId()) != true){
            //if not null creates obj based on the id number 
            //If not given values default values are in place
            super.find(entity.getId()).updates(entity);  
            ResponseBuilder build = Response.ok();
            build.language(Locale.CANADA).header("Sucessful","200");
            return build.build();
        }
        //Else sends user to error page 
        else{
            ResponseBuilder build = Response.ok();
            build.language(Locale.CANADA).header("Incorrect ID","404");
            return build.build();
        }   
    } 
    //Put method 
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Long id, Sprite entity) {
            ResponseBuilder build = Response.ok();
            build.language(Locale.CANADA).header("Incorrect ID","404");
            return build.build();
    }
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response puter(@PathParam("id") Long id, Sprite entity) {
        //Checks if id corratetes to given id 
        if(id == entity.getId()){
            //If they are the same updates the items given 
            super.find(entity.getId()).updates(entity);  
            ResponseBuilder build = Response.ok();
            build.language(Locale.CANADA).header("Sucessful","501");
            return build.build();
        }
        //Else sends user to error page 
        else{
            ResponseBuilder build = Response.ok();
            build.language(Locale.CANADA).header("Incorrect ID","404");
            return build.build();
        }   
    }
    //Delete method 
    @DELETE
    @Path("{id}")
    //Deletes entity based on id
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }
    //Get method 
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    //returns the entity based on id 
    public Sprite find(@PathParam("id") Long id) {
        return super.find(id);
    }
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprite> findAll() {
        return super.findAll();
    }
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprite> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
