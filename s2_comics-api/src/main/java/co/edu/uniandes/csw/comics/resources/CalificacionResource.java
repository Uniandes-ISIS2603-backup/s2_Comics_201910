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
      @POST
    public CalificacionDTO crearCalificacion (@PathParam("vendedoresId")Long vendedorId,CalificacionDTO calificacion)throws BusinessLogicException{
       LOGGER.log(Level.INFO, "CalificacionResource createReview: input: {0}", calificacion);
        CalificacionDTO nuevaCalificacion=new CalificacionDTO(calificacionLogic.createCalificacion(vendedorId,calificacion.toEntity()));
         LOGGER.log(Level.INFO, "CalificacionResource createReview: output: {0}", nuevaCalificacion);
        return nuevaCalificacion;
    }
    
        @GET
    public List<CalificacionDTO> getCalificaciones (@PathParam("vendedoresId")Long vendedorId){
        LOGGER.log(Level.INFO, "CalificacionResource getReviews: input: {0}", vendedorId);
        List<CalificacionDTO> listaDTOs = listEntity2DTO(calificacionLogic.getCalificaciones(vendedorId));
        LOGGER.log(Level.INFO, "CalificacionResource getBooks: output: {0}", listaDTOs);
        return listaDTOs;
    }
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


