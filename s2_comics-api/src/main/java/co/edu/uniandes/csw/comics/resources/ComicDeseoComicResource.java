/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.ComicDTO;
import co.edu.uniandes.csw.comics.dtos.ComicDetailDTO;
import co.edu.uniandes.csw.comics.ejb.ComicDeseoComicLogic;
import co.edu.uniandes.csw.comics.ejb.ComicLogic;
import co.edu.uniandes.csw.comics.entities.ComicEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Sebastian Baquero
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ComicDeseoComicResource {
    
     private static final Logger LOGGER = Logger.getLogger(ComicDeseoComicResource.class.getName());
     
     @Inject
     private ComicDeseoComicLogic cDCL;
     
     @Inject
     private ComicLogic comicLogic;
     
      /**
     * Guarda un Comic dentro de un Comic deseo con la informacion que recibe el la
     * URL.
     *
     * @param ComicDeseoId Identificador de el comic deseo que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param ComicId Identificador del comic que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link ComicDTO} - El autor guardado en el premio.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @POST
    @Path("{comicId: \\d+}")
    public ComicDTO addComic(@PathParam("comicDeseoId") Long comicDeseoId, @PathParam("comicId") Long comicId) {
       LOGGER.log(Level.INFO, "ComicDeseoComicResource addComic: input: comicDeseoID: {0} , comicId: {1}", new Object[]{comicDeseoId, comicId});
       
       if(comicLogic.getComic(comicId)== null){
       throw new WebApplicationException("El recurso /comics/"+comicId+"no existe.",404);
       }
       
        ComicDTO comicDTO = new ComicDTO(cDCL.addComic(comicId,comicDeseoId));
        LOGGER.log(Level.INFO, "ComicDeseoComicResource addComic: output: {0}", comicDTO);
        return comicDTO;
    }
    
     /**
     * Busca el comic dentro de el comic deseo con id asociado.
     *
     * @param comicDeseoId Identificador de el premio que se esta buscando. Este
     * debe ser una cadena de dígitos.
     * @return JSON {@link ComicDetailDTO} - El comic buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando el premio no tiene autor.
     */
    @GET
    public ComicDetailDTO getComic(@PathParam("comicDeseoId") Long comicDeseoId) {
        LOGGER.log(Level.INFO, "ComicDeseoComicResource getComic: input: {0}", comicDeseoId);
        ComicEntity comicEntity = cDCL.getComic(comicDeseoId);
        if (comicEntity == null) {
            throw new WebApplicationException("El recurso /comicDeseo/" + comicDeseoId + "/comic no existe.", 404);
        }
        ComicDetailDTO comicDetailDTO = new ComicDetailDTO(comicEntity);
        LOGGER.log(Level.INFO, "ComicDeseoComicResource getComic: output: {0}", comicDetailDTO);
        return comicDetailDTO;
    }
    
     /**
     * Elimina la conexión entre el comic y el comic deseo recibido en la URL.
     *
     * @param comicDeseoId El ID del comic deseo al cual se le va a desasociar el comic
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * Error de lógica que se genera cuando el comic deseo no tiene comic.
     */
    @DELETE
    public void removeComic(@PathParam("comicDeseoId") Long comicDeseoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ComicDeseoComicResource removeComic: input: {0}", comicDeseoId);
        cDCL.removeComic(comicDeseoId);
        LOGGER.info("ComicDeseoComicResource removeComic: output: void");
    }
     
    
}
