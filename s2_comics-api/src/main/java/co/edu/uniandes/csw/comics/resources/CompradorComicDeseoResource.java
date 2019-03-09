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
 * Clase que implementa el recurso "comprador/{id}/comicDeseo"
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
    
    /**
     * Asocia un comicDeseo existente con un Comprador existente
     *
     * @param compradorId El ID del Comprador al cual se le va a asociar el comicDeseo
     * @param comicId El ID del comicDeseo que se asocia
     * @return JSON {@link ComicDeseoDTO} - El comicDeseo asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el comicDeseo.
     */
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
    
    /**
     * Busca y devuelve todos los comics deseo que existen en un comprador.
     *
     * @param compradorId El ID del comprador del cual se buscan los comics deseo
     * @return JSONArray {@link ComicDeseoDTO} - Los comics deseo encontrados en el
     * comprador. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ComicDeseoDTO> getComicsDeseo(@PathParam("compradorId") long compradorId)
    {
        List<ComicDeseoDTO> comics = listEntity2DTO(compradorComicDeseo.getListaDeseos(compradorId));
        return comics;
    }
    
    /**
     * Busca y devuelve el comic deseo con el ID recibido en la URL, relativo a un
     * comprador.
     *
     * @param compradorId El ID del comprador del cual se busca el comic deseo
     * @param comicId El ID del comic deseo que se busca
     * @return {@link ComicDeseoDTO} - El comic deseo encontrado en el comprador.
     * @throws co.edu.uniandes.csw.ComicDeseostore.exceptions.BusinessLogicException
     * si el comic deseo no está asociado al comprador
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el comic deseo.
     */
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
    
    /**
     * Actualiza la lista de comics deseo de un comprador con la lista que se recibe en el
     * cuerpo
     *
     * @param compradorId El ID del comprador al cual se le va a asociar el comicDeseo
     * @param comics JSONArray {@link ComicDeseoDTO} - La lista de comics deseo que se
     * desea guardar.
     * @return JSONArray {@link ComicDeseoDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el comicDeseo.
     */
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
    
    /**
     * Elimina la conexión entre el comicDeseo y e comprador recibidos en la URL.
     *
     * @param compradorId El ID del comprador al cual se le va a desasociar el comicDeseo
     * @param ComicId El ID del comicDeseo que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el comicDeseo.
     */
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
    
    /**
     * Convierte una lista de ComicdDeseoEntity a una lista de ComicDeseoDTO.
     *
     * @param list Lista de ComicDeseoEntity a convertir.
     * @return Lista de ComicDeseoDTO convertida.
     */
    private List<ComicDeseoEntity> listDTO2Entity(List<ComicDeseoDTO> list)
    {
        List<ComicDeseoEntity> newList = new ArrayList();
        for(ComicDeseoDTO dto : list)
        {
            newList.add(dto.toEntity());
        }
        return newList;
    }
    
     /**
     * Convierte una lista de ComicDeseoDTO a una lista de ComicDeseoEntity.
     *
     * @param list Lista de ComicDeseoDTO a convertir.
     * @return Lista de ComicDeseoEntity convertida.
     */
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
