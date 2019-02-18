/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;


import co.edu.uniandes.csw.comics.dtos.ColeccionistaDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
<<<<<<< HEAD
 * @author Sebastian Baquero
=======
 * @author ca.orduz
>>>>>>> 93f909dc5cbf9acf09a8c3a07663e1be63d8055c
 */
@Path("coleccionistas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ColeccionistaResource {
    
    private static final Logger LOGGER=Logger.getLogger(ColeccionistaResource.class.getName());
    
//<<<<<<< HEAD
    public ColeccionistaResource() {
    
    }
    
//=======
   
    //@POST
    //public ColeccionistaDTO crearColeccionista (ColeccionistaDTO pColeccionista){
      //  return pColeccionista;
    //}
    // @GET
    //public ColeccionistaDTO obtenerColeccionistas (ColeccionistaDTO coleccionista){
     //   return coleccionista;
    //}
     // @GET
      //@Path("{coleccionistasId:\\d+}")
    //public ColeccionistaDTO obtenerColeccionistaId (@PathParam("coleccionistasId")long id){
      //  return null;
  // }
     // @PUT
      //@Path("{coleccionistasId:\\d+}")
    //public ColeccionistaDTO actualizarColeccionista (@PathParam("coleccionistasId")long id){
      //  return null;
    //}
    
   // @DELETE
    //Alias del coleccionista
    //@Path("{name: [a-zA-Z][a-zA-Z]*}")
    //public ColeccionistaDTO deleteColeccionista(@PathParam("name") String pAlias){
      //  return null;
    //}
     
}
