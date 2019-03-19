/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.ComicDeseoDTO;
import co.edu.uniandes.csw.comics.ejb.ComicDeseoLogic;
import co.edu.uniandes.csw.comics.entities.ComicDeseoEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Sebastian Baquero
 */

@Path("comicDeseo")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped


public class ComicDeseoResource {
 
  private static final Logger LOGGER = Logger.getLogger(ComicDeseoResource.class.getName());
  
  /**
   * Inyecta la logica cunado se hace el llamado del recurso
   */
  @Inject
  private ComicDeseoLogic comicDLogic;
  
  /**
   * Crea un comic deseo cuando se llama a POST del recurso
   */
 
   @POST
  public ComicDeseoDTO createComicDeseo (@PathParam("comicDeseoId") Long comicId,ComicDeseoDTO pComicD)throws BusinessLogicException, Exception{
  
        
        LOGGER.log(Level.INFO, "ComicDeseoResource createComicDeseo: input: {0}", pComicD);
        ComicDeseoDTO nComicDDTO = new ComicDeseoDTO(comicDLogic.createComicDeseo(comicId, pComicD.toEntity()));
        LOGGER.log(Level.INFO, "ComicDeseoResource createComicDeseo: output: {0}",nComicDDTO );
        return nComicDDTO;
        
       
        
      
     
      
      
     
  }
  
  /**
   * Trae los comic deseo
   */
  
  @GET
  public List<ComicDeseoDTO> getComicsDeseos (){
      
       LOGGER.log(Level.INFO, "ComicDeseoResource getComicsDeseo: input: {0}");
       List<ComicDeseoDTO> listaComicsDDTO = listEntity2DTO(comicDLogic.getComicsDeseo());
       LOGGER.log(Level.INFO, "ComicDeseoResource getComicDeseos: output: {0}", listaComicsDDTO);
       return listaComicsDDTO;
  }
  
  /**
   * Trae un comic deseo con id
   * @PARAM comic deseo id
   */
  
  @GET
  @Path("{comicDeseoId:\\d+}")
  public ComicDeseoDTO findComicDeseoXId ( @PathParam("comicDeseoId") Long comicDeseoId)throws BusinessLogicException{
     
      LOGGER.log(Level.INFO, "ComicDeseoResource findComicDeseoXId: input: {0}", comicDeseoId);
      ComicDeseoEntity entity = comicDLogic.getComicDeseo(comicDeseoId);
     if(entity == null){
     
       //  throw new WebApplicationException("El recurso/compradores/"+compradoresAlias + "/comicDeseo/"+comicDeseoId+"no existe.", 404);
         
     }
     ComicDeseoDTO comicDDTO = new ComicDeseoDTO(entity);
     LOGGER.log(Level.INFO, "ComicDeseoResource findComicDeseoXId: output: {0}", comicDDTO);
     return comicDDTO;
      
  }
  
  /*
  * Borrar el comic deseo 
  */
 
  @DELETE
  @Path("{comicDeseoId: \\d+}")
  public void deleteComicDeseo ( @PathParam("comicDeseoId") Long comicsDeseoId) throws BusinessLogicException{
 
      ComicDeseoEntity entity = comicDLogic.getComicDeseo( comicsDeseoId);
      if(entity == null){
      //throw new WebApplicationException("El recurso/compradores/"+compradoresAlias+"/comicsDeseo/"+comicsDeseoId+"no existe.", 404);
      }
      comicDLogic.deleteComicDeseo( comicsDeseoId);
  }
  
  //@PUT
  //@Path("{comicDeseoId: \\d+}")
  //public ComicDeseoDTO updateComicDeseo(@PathParam("compradoresAlias") String compradoresAlias,@PathParam("comicsDeseoId")Long comicsDeseoId, ComicDeseoDTO comicDeseo){
  //LOGGER.log(Level.INFO, "ComicDeseoResource updateComicDeseo: input: compradoresAlias: {0}, comicsDeseoId: {1}, comicDeseo: {2}", new Object[]{compradoresAlias,comicsDeseoId,comicDeseo});
    //  if(comicsDeseoId.equals(comicDeseo.ge)){
      
    //  }
 // }
  
  /*
  *Recibe lista de comic deseo entity y lo convierte en lista de DTO
  */
  
  private List<ComicDeseoDTO> listEntity2DTO(List<ComicDeseoEntity> entityList){
  
     List<ComicDeseoDTO> list = new ArrayList<ComicDeseoDTO>();
      
      for(ComicDeseoEntity entity : entityList){
          list.add(new ComicDeseoDTO(entity));
          
      }
      return list;
  }
  
}
