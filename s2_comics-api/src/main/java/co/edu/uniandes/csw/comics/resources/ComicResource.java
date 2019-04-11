/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.ComicDTO;
import co.edu.uniandes.csw.comics.dtos.ComicDetailDTO;
import co.edu.uniandes.csw.comics.ejb.ComicLogic;
import co.edu.uniandes.csw.comics.entities.ComicEntity;
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
 * @author Pietro
 */
@Path("comic")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ComicResource {
    private static final Logger LOGGER=Logger.getLogger(ComicResource.class.getName());
    
    @Inject
    private ComicLogic comicLogic;
    
    public ComicResource(){}
    
    @POST
    public ComicDTO crearComic (ComicDTO comic){
        LOGGER.log(Level.INFO, "{" + comic.getNombre() + ", " + comic.getAutor() + ", " + comic.getAnioSalida() + ", " + comic.getPerteneceColeccion() + ", " + comic.getPerteneceSerie() + ", " + comic.getPrecio() + ", " + comic.getTema() + ", " + comic.getEnVenta() + ", " + comic.getInformacion());
        ComicEntity comicEntity = comic.toEntity();
        ComicEntity newComicEntity = comicLogic.createComic(comicEntity);
        ComicDTO nuevoComicDTO = new ComicDTO(newComicEntity);
        LOGGER.log(Level.INFO, "{" + nuevoComicDTO.getNombre() + ", "  + nuevoComicDTO.getAutor() + ", " + nuevoComicDTO.getAnioSalida() + ", " + nuevoComicDTO.getPerteneceColeccion() + ", " + nuevoComicDTO.getPerteneceSerie() + ", " + nuevoComicDTO.getPrecio() + ", " + nuevoComicDTO.getTema() + ", " + nuevoComicDTO.getEnVenta() + ", " + nuevoComicDTO.getInformacion());
        return nuevoComicDTO;
    }
    
    @GET
    @Path("{id: \\d+}")
    public ComicDetailDTO getComic(@PathParam("id") long id){
        ComicEntity entidad = comicLogic.getComic(id);
        if(entidad == null)
            throw new WebApplicationException("El recurso /Comic/" + id + " no existe" , 404);
        return new ComicDetailDTO(entidad);
    }
    
    @GET
    public List<ComicDetailDTO> getComics(){
        LOGGER.log(Level.INFO , "ComicResource getComics: Input");
        List<ComicDetailDTO> ans = this.listEntityToDTO(comicLogic.getComics());
        LOGGER.log(Level.INFO , "ComicResource getComics: Output {0}", ans);
        return ans;
    }
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteComic(@PathParam("id") long id){
        LOGGER.log(Level.INFO, "ComicResource deleteComic: input: {0}", id);
        ComicEntity entidad = comicLogic.getComic(id);
        if(entidad == null)
            throw new WebApplicationException("El recurso /Comic/" + id + " no existe" , 404);
        comicLogic.deleteComic(id);
        LOGGER.log(Level.INFO, "ComicResource deleteComic done");
    }
    
    @PUT
    @Path("{id: \\d+}")
    public ComicDetailDTO updateComic(@PathParam("id") long id, ComicDTO comic) throws BusinessLogicException{
        return new ComicDetailDTO(comicLogic.updateComic(id, comic.toEntity()));
    }
    
    private List<ComicDetailDTO> listEntityToDTO(List<ComicEntity> entity){
        List<ComicDetailDTO> list = new ArrayList<>();
        for(ComicEntity ent : entity)
            list.add(new ComicDetailDTO(ent));
        return list;
    }
}
