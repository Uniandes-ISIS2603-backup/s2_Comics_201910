/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;


import co.edu.uniandes.csw.comics.dtos.CalificacionDTO;
import co.edu.uniandes.csw.comics.ejb.CalificacionLogic;
import co.edu.uniandes.csw.comics.entities.CalificacionEntity;
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
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ca.orduz
 */


@Produces("application/json")
@Consumes("application/json")

public class CalificacionResource {
    private static final Logger LOGGER=Logger.getLogger(CalificacionResource.class.getName());
     @Inject
     private CalificacionLogic calificacionLogic;
      /**
     * Crea una nueva calificacion con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param calificacion {@link CalificacionDTO} - La calificacion que se desea guardar.
     * @return JSON {@link CalificacionDTO} - La calificacion guardado con el atributo id
     * autogenerado.
     */
      @POST
    public CalificacionDTO crearCalificacion (@PathParam("vendedoresId")Long vendedorId,CalificacionDTO calificacion)throws BusinessLogicException{
       LOGGER.log(Level.INFO, "CalificacionResource createReview: input: {0}", calificacion);
        CalificacionDTO nuevaCalificacion=new CalificacionDTO(calificacionLogic.createCalificacion(vendedorId,calificacion.toEntity()));
         LOGGER.log(Level.INFO, "CalificacionResource createReview: output: {0}", nuevaCalificacion);
        return nuevaCalificacion;
    }
     /**
     * Busca y devuelve todas las calificaciones que existen en la aplicacion.
     *
     * @return JSONArray {@link CalificacionDTO} - Las calificaciones encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
        @GET
    public List<CalificacionDTO> getCalificaciones (@PathParam("vendedoresId")Long vendedorId){
        LOGGER.log(Level.INFO, "CalificacionResource getReviews: input: {0}", vendedorId);
        List<CalificacionDTO> listaDTOs = listEntity2DTO(calificacionLogic.getCalificaciones(vendedorId));
        LOGGER.log(Level.INFO, "CalificacionResource getBooks: output: {0}", listaDTOs);
        return listaDTOs;
    }
     /**
     * Busca la calificacion con el id asociado recibido en la URL y lo devuelve.
     *
     * @param calificacionesId Identificador la calificaion que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link CalificacionDTO} - La calificacion buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificacion.
     */
      @GET
      @Path("{calificacionesId: \\d+}")
    public CalificacionDTO getCalificacion (@PathParam("vendedoresId")Long vendedoresId,@PathParam("calificacionesId")Long calificacionesId)throws BusinessLogicException{
         LOGGER.log(Level.INFO, "ReviewResource getReview: input: {0}", vendedoresId);
         CalificacionEntity entity = calificacionLogic.getCalificacion(vendedoresId, calificacionesId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /vendedores/" + vendedoresId + "/calificaciones/" + calificacionesId + " no existe.", 404);
        }
        CalificacionDTO calificacionDTO = new CalificacionDTO(entity);
        LOGGER.log(Level.INFO, "ReviewResource getReview: output: {0}", calificacionDTO);
        return calificacionDTO;
    }
      private List<CalificacionDTO> listEntity2DTO(List<CalificacionEntity> entityList) {
        List<CalificacionDTO> list = new ArrayList<CalificacionDTO>();
        for (CalificacionEntity entity : entityList) {
            list.add(new CalificacionDTO(entity));
        }
        return list;
    }
         /**
     * Actualiza la calificacion con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param calificacionesId Identificador de la calificacion que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param calificacion {@link CalificacionDTO} La calificacion que se desea guardar.
     * @return JSON {@link CalificacionDTO} - La calificacion guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificacion a
     * actualizar.
     */
        @PUT
        @Path("{calificacionesId: \\d+}")
    public CalificacionDTO updateCalificacion(@PathParam("vendedoresId") Long vendedoresId, @PathParam("calificacionesId") Long calificacionesId, CalificacionDTO calificacion) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "CalificacionResource updateCalificacion: input: vendedoresId: {0} , calificacionesId: {1} , calificacion:{2}", new Object[]{vendedoresId, calificacionesId, calificacion});
        if (!calificacionesId.equals(calificacion.getId())) {
            throw new BusinessLogicException("Los ids de la calificacion no coinciden.");
        }
        CalificacionEntity entity = calificacionLogic.getCalificacion(vendedoresId, calificacionesId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /vendedores/" + vendedoresId + "/calificaciones/" + calificacionesId + " no existe.", 404);

        }
        CalificacionDTO calificacionDTO = new CalificacionDTO(calificacionLogic.updateCalificacion(vendedoresId, calificacion.toEntity()));
        LOGGER.log(Level.INFO, "CalificacionResource updateCalificacion: output:{0}", calificacionDTO);
        return calificacionDTO;

    }
    /**
     * Borra la calificacion con el id asociado recibido en la URL.
     *
     * @param calificacionesId Identificador de la calificacion que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra la calificacion a borrar.
     */
    @DELETE
        @Path("{calificacionesId: \\d+}")
    public void deleteCalificacion(@PathParam("vendedoresId") Long vendedoresId, @PathParam("calificacionesId") Long calificacionesId) throws BusinessLogicException {
        CalificacionEntity entity = calificacionLogic.getCalificacion(vendedoresId, calificacionesId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /vendedores/" + vendedoresId + "/calificaciones/" + calificacionesId + " no existe.", 404);
        }
        calificacionLogic.deleteCalificacion(vendedoresId, calificacionesId);
    }
}


