/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.ComicDTO;
import co.edu.uniandes.csw.comics.dtos.ComicDetailDTO;
import co.edu.uniandes.csw.comics.entities.ComicEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Pietro
 */
@Path("Comic")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ComicResource {
    private static final Logger LOGGER=Logger.getLogger(ComicResource.class.getName());
    
//    @Inject
//    private ComicLogic comicLogic;
//    
//    @POST
//    public ComicDTO crearComic (ComicDTO comic){
//        LOGGER.log(Level.INFO, "ComicResource createComic: input: {0}", comic);
//        ComicEntity comicEntity = comic.toEntity();
//        ComicEntity newComicEntity = comicLogic.createComic(comicEntity);
//        ComicDTO nuevoComicDTO = new ComicDTO(newComicEntity);
//        LOGGER.log(Level.INFO, "ComicResource createComic: output: {0}", nuevoComicDTO);
//        return nuevoComicDTO;
//    }
//    
//    @GET
//    @Path("(id: \\d+)")
//    public ComicDetailDTO getComic(@PathParam("id") long id){
//        ComicEntity entidad = comicLogic.getComic(id);
//        if(entidad == null)
//            throw new WebApplicationException("El recurso /Comic/" + id + " no existe" , 404);
//        return new ComicDetailDTO(entidad);
//    }
//    
//    private List<ComicDTO> listEntityToDTO(List<ComicEntity> entity){
//        List<ComicDTO> list = new ArrayList<>();
//        for(ComicEntity ent : entity)
//            list.add(new ComicDTO(ent));
//        return list;
//    }
}
