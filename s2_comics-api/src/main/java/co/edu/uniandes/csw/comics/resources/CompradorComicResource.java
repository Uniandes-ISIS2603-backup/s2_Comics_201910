/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.ComicDetailDTO;
import co.edu.uniandes.csw.comics.dtos.ComicDetailDTO;
import co.edu.uniandes.csw.comics.dtos.*;
import co.edu.uniandes.csw.comics.dtos.CompradorDetailDTO;
import co.edu.uniandes.csw.comics.ejb.*;
import co.edu.uniandes.csw.comics.entities.ComicEntity;
import co.edu.uniandes.csw.comics.entities.CompradorEntity;
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
 * Clase que implementa el recurso "comprador/{idComprador}/comics/"
 * @author juan pablo cano
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompradorComicResource
{
    private final static Logger LOGGER = Logger.getLogger(CompradorComicResource.class.getName());
    
    @Inject 
    private CompradorComicLogic compradorLogic;
    
    @Inject
    private ComicLogic comicLogic;
    
    /**
     * Asocia un comic existente con un Comprador existente
     *
     * @param compradorId El ID del Comprador al cual se le va a asociar el comic
     * @param comicId El ID del comic que se asocia
     * @return JSON {@link ComicDetailDTO} - El comic asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el comic.
     */
    @POST
    @Path("{comicId: \\d+}")
    public ComicDetailDTO addComic(@PathParam("compradorId") long compradorId, @PathParam("comicId") long comicId)
    {
        LOGGER.log(Level.INFO, "CompradorComicResource addComic: input: compradorId: {0}, comicId: {1}", new Object[]{compradorId, comicId});
        if(comicLogic.getComic(comicId) == null)
        {
            throw new WebApplicationException("El recurso /comic/" + comicId + " no existe.", 404);
        }
        ComicDetailDTO comic = new ComicDetailDTO(compradorLogic.addComicCarrito(compradorId, comicId));
        LOGGER.log(Level.INFO, "CompradorComicResource addComic: output: {0}", comic);
        return comic;
    }
    
    /**
     * Busca y devuelve todos los comics deseo que existen en un comprador.
     *
     * @param compradorId El ID del comprador del cual se buscan los comics
     * @return JSONArray {@link ComicDetailDTO} - Los comics deseo encontrados en el
     * comprador. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ComicDetailDTO> getCarro(@PathParam("compradorId")long compradorId)
    {
        LOGGER.log(Level.INFO, "CompradorComicResource getCarro: input: {0}", compradorId);
        System.out.println("Tamanio: " + compradorLogic.getComics(compradorId).size());
        List<ComicDetailDTO> comics = listEntity2DTO(compradorLogic.getComics(compradorId));
        LOGGER.log(Level.INFO, "CompradorComicResoruce getCarro: output: {0}", comics);
        return comics;
    }
    
    /**
     * Busca y devuelve el comic deseo con el ID recibido en la URL, relativo a un
     * comprador.
     *
     * @param compradorId El ID del comprador del cual se busca el comic deseo
     * @param comicId El ID del comic deseo que se busca
     * @return {@link ComicDetailDTO} - El comic deseo encontrado en el comprador.
     * @throws co.edu.uniandes.csw.comicstore.exceptions.BusinessLogicException
     * si el comic deseo no está asociado al comprador
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el comic deseo.
     */
    @GET
    @Path("{comicId: \\d+}")
    public ComicDetailDTO getComic(@PathParam("compradorId") long compradorId, @PathParam("comicId") long comicId)
    {
        LOGGER.log(Level.INFO, "CompradorOrdenPedidoResource getComic: input: compradorId: {0}, comicId: {1}", new Object[]{compradorId, comicId});
        if(comicLogic.getComic(comicId) == null)
        {
            throw new WebApplicationException("El recurso /carro/" + comicId + " no existe.", 404);
        }
        
        ComicDetailDTO comic = new ComicDetailDTO(compradorLogic.getComic(compradorId, comicId));
        LOGGER.log(Level.INFO, "CompradorOrdenPedidoResource getComic: output: {0}", comic);
        return comic;
    }
    
    /**
     * Actualiza la lista de comics deseo de un comprador con la lista que se recibe en el
     * cuerpo
     *
     * @param compradorId El ID del comprador al cual se le va a asociar el comic
     * @param comics JSONArray {@link ComicDetailDTO} - La lista de comics deseo que se
     * desea guardar.
     * @return JSONArray {@link ComicDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el comic.
     */
    @PUT
    public List<ComicDetailDTO> replaceComics(@PathParam("compradorId") long compradorId, List<ComicDetailDTO> comics)
    {
        LOGGER.log(Level.INFO, "CompradorComicResource replaceComics: input: compradorId: {0}, comics: {1}", new Object[]{compradorId, comics});
        for(ComicDetailDTO comic : comics)
        {
            if(comicLogic.getComic(comic.getId()) == null)
            {
                throw new WebApplicationException("El recurso /ordenes/" + comic.getId() + " no existe.", 404);
            }
        }
        
        List<ComicDetailDTO> lista = listEntity2DTO(compradorLogic.replaceComics(compradorId, listDto2Entity(comics)));
        LOGGER.log(Level.INFO, "CompradorComicResource replaceOrdenes: output: {0}", lista);
        return lista;
    }
    
    /**
     * Elimina la conexión entre el comic y e comprador recibidos en la URL.
     *
     * @param compradorId El ID del comprador al cual se le va a desasociar el comic
     * @param ComicId El ID del comic que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el comic.
     */
    @DELETE
    @Path("{comicId: \\d+}")
    public void deleteComic(@PathParam("compradorId") long compradorId, @PathParam("comicId")long comicId)
    {
        LOGGER.log(Level.INFO, "CompradorComicResource removeComic: input: compradorId: {0}, comicId: {1}", new Object[]{compradorId, comicId});
        if(comicLogic.getComic(comicId) == null)
        {
            throw new WebApplicationException("El recurso /carro/" + comicId + " no existe.", 404);
        }
        
        compradorLogic.deleteComic(compradorId, comicId);
        LOGGER.info("CompradorComicResource removeComic: output: void");
    }
    
    private List<ComicEntity> listDto2Entity(List<ComicDetailDTO> comics)
    {
        List<ComicEntity> entities = new ArrayList();
        for(ComicDetailDTO dto : comics)
        {
            entities.add(dto.toEntity());
        }
        
        return entities;
    }
    
    private List<ComicDetailDTO> listEntity2DTO(List<ComicEntity> comics)
    {
        List<ComicDetailDTO> dtos = new ArrayList();
        for(ComicEntity entity : comics)
        {
            dtos.add(new ComicDetailDTO(entity));
        }
        return dtos;
    }
}
