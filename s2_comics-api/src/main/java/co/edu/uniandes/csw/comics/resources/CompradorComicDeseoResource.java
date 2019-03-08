/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.*;
import co.edu.uniandes.csw.comics.dtos.OrdenPedidoDTO;
import co.edu.uniandes.csw.comics.ejb.*;
import co.edu.uniandes.csw.comics.entities.*;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.*;
import javax.ws.rs.Produces;
import java.util.*;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author juan pablo cano
 */
@Path("/comprador/{compradorId: \\d+}/comicDeseo/{comicDeseoId: \\d+}")
@Produces("application/json")
@Consumes("application/json")
public class CompradorComicDeseoResource {
    private static final Logger LOGGER=Logger.getLogger(CompradorComicDeseoResource.class.getName());
    
    @Inject
    private ComicDeseoLogic comicDeseo;
    
    @Inject
    private CompradorComicDeseoLogic compradorComicDeseo;
    
    @POST
    @Path("{comicId: \\d+}")
    public ComicDeseoDTO addComicDeseo(@PathParam("compradorId") long compradorId, @PathParam("comicId") long comicId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "CompradorComicDeseoResource addComicDeseo: input: idComprador {0} , idComic {1}", new Object[]{compradorId, comicId});
        if(comicDeseo.getComicDeseo(comicId) == null)
        {
            throw new WebApplicationException("El recurso /comicDeseo/" + comicId + " no existe.", 404);
        }
        ComicDeseoDTO comic = new ComicDeseoDTO(compradorComicDeseo.addComicListaDeseo(compradorId, comicId));
        LOGGER.log(Level.INFO, "CompradorComicDeseoResource addComicDeseo: output: {0}", comic);
        return comic;
    }
    
    @GET
    public List<ComicDeseoDTO> getComicsDeseo(@PathParam("compradorId") long compradorId)
    {
        List<ComicDeseoDTO> comics = listEntity2DTO(compradorComicDeseo.getListaDeseos(compradorId));
        return comics;
    }
    
    @GET
    @Path("{comicId: \\d+}")
    public ComicDeseoDTO getComic(@PathParam("compradorId") long compradorId, @PathParam("comicId") long comicId)throws BusinessLogicException
    {
        if(comicDeseo.getComicDeseo(comicId) == null)
        {
            throw new WebApplicationException("El recurso /comicDeseo/" + comicId + " no existe.", 404);
        }
        ComicDeseoDTO dto = new ComicDeseoDTO(compradorComicDeseo.getComicDeseo(compradorId, comicId));
        return dto;
    }
    
    @PUT
    public List<ComicDeseoDTO> replaceComicsDeseo(@PathParam("compradorId") long compradorId, List<ComicDeseoDTO> comics) throws BusinessLogicException
    {
        for(ComicDeseoDTO comic : comics)
        {
            if(comicDeseo.getComicDeseo(comic.getComic().getId()) == null)
            {
                throw new WebApplicationException("El recurso /comicDeseo/" + comic.getComic().getId() + " no existe.", 404);
            }
        }
        
        List<ComicDeseoDTO> dtos = listEntity2DTO(compradorComicDeseo.replaceComicsDeseo(compradorId, listDTO2Entity(comics)));
        return dtos;
    }
    
    @DELETE
    @Path("{comicId: \\d+}")
    public void deleteComicDeseo(@PathParam("compradorId") long compradorId, @PathParam("comicId") long comicId) throws BusinessLogicException
    {
        if(comicDeseo.getComicDeseo(comicId) == null)
        {
            throw new WebApplicationException("El recurso /comicDeseo/" + comicId + " no existe.", 404);
        }
        compradorComicDeseo.removeComic(compradorId, comicId);
    }
    
    private List<ComicDeseoEntity> listDTO2Entity(List<ComicDeseoDTO> list)
    {
        List<ComicDeseoEntity> newList = new ArrayList();
        for(ComicDeseoDTO dto : list)
        {
            newList.add(dto.toEntity());
        }
        return newList;
    }
    
    private List<ComicDeseoDTO> listEntity2DTO(List<ComicDeseoEntity> list)
    {
        List<ComicDeseoDTO> newList = new ArrayList();
        for(ComicDeseoEntity entity: list)
        {
            newList.add(new ComicDeseoDTO(entity));
        }
        
        return newList;
    }
    
}
