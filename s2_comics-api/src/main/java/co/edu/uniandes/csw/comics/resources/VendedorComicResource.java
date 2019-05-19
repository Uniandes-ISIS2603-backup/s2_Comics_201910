/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.ComicDetailDTO;
import co.edu.uniandes.csw.comics.ejb.ComicLogic;
import co.edu.uniandes.csw.comics.ejb.VendedorComicLogic;
import co.edu.uniandes.csw.comics.entities.ComicEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ca.orduz
 */
@Path("/vendedores/{vendedoresId: \\d+}/comics/")
@Consumes("application/json")
@Produces("application/json")
public class VendedorComicResource {
     private static final Logger LOGGER = Logger.getLogger(VendedorComicResource.class.getName());
@Inject 
private VendedorComicLogic vendedorComicLogic;

@Inject 
private ComicLogic comicLogic;


   @POST
    @Path("{id: \\d+}")
    public ComicDetailDTO addComic(@PathParam("vendedoresId") Long vendedoresId, @PathParam("id") Long comicsId) {
        LOGGER.log(Level.INFO, "VendedorComicResource addComic: input: vendedoresId {0} , id {1}", new Object[]{vendedoresId, comicsId});
        if (comicLogic.getComic(comicsId) == null) {
            throw new WebApplicationException("El recurso /comics/" + comicsId + " no existe.", 404);
        }
      ComicDetailDTO detailDTO = new ComicDetailDTO(vendedorComicLogic.addComic(vendedoresId, comicsId));
        LOGGER.log(Level.INFO, "VendedorComicResource addComic: output: {0}", detailDTO);
       return detailDTO;
    }
    @GET
    public List<ComicDetailDTO> getComics(@PathParam("vendedoresId") Long vendedoresId) {
        LOGGER.log(Level.INFO, "VendedorComicResource getComics: input: {0}", vendedoresId);
        List<ComicDetailDTO> lista =comicListEntity2DTO(vendedorComicLogic.getComics(vendedoresId));
        LOGGER.log(Level.INFO, "VendedorComicResource getComics: output: {0}", lista);
        return lista;
    }
      @GET
    @Path("{id: \\d+}")
    public ComicDetailDTO getComic(@PathParam("vendedoresId") Long vendedoresId, @PathParam("id") Long comicsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "VendedorComicResource getComic: input: vendedoresId {0} , comicsId {1}", new Object[]{vendedoresId, comicsId});
        if (comicLogic.getComic(comicsId) == null) {
            throw new WebApplicationException("El recurso /comics/" + comicsId + " no existe.", 404);
        }
        ComicDetailDTO detailDTO = new ComicDetailDTO(vendedorComicLogic.getComic(vendedoresId, comicsId));
        LOGGER.log(Level.INFO, "VendecorComicResource getComic: output: {0}", detailDTO);
        return detailDTO;
  
}
 
       @DELETE
    @Path("{id: \\d+}")
    public void removeComic(@PathParam("vendedoresId") Long vendedoresId, @PathParam("id") Long comicsId) {
        LOGGER.log(Level.INFO, "VendedorComicResource deleteBook: input: vendedoresId {0} , comicsId {1}", new Object[]{vendedoresId, comicsId});
        if (comicLogic.getComic(comicsId) == null) {
           throw new WebApplicationException("El recurso /comics/" + comicsId + " no existe.", 404);
        }
        vendedorComicLogic.removeComic(vendedoresId, comicsId);
        LOGGER.info("VendedorComicResource deleteComic: output: void");
    }


    
       private List<ComicDetailDTO> comicListEntity2DTO(List<ComicEntity> entityList) {
        List<ComicDetailDTO> list = new ArrayList();
        for (ComicEntity entity : entityList) {
            list.add(new ComicDetailDTO(entity));
        }
        return list;
    }
}
