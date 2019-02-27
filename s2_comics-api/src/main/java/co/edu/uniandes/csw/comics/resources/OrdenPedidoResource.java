/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;


import co.edu.uniandes.csw.comics.dtos.OrdenPedidoDTO;
import co.edu.uniandes.csw.comics.ejb.OrdenPedidoLogic;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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
 * @author estudiante
 */
@Path("ordenesPedido")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class OrdenPedidoResource {
   
    @Inject
    OrdenPedidoLogic ordenPedidoLogic;
    
    private static final Logger LOGGER=Logger.getLogger(OrdenPedidoResource.class.getName());
    
    /**
     * Crea una nueva editorial con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param ordenPedido {@link OrdenPedidoDTO} - La ordenPedido que se desea
     * guardar.
     * @return JSON {@link OrdenPedidoDTO} - La editorial guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la ordenPedido.
     */
    @POST
    public OrdenPedidoDTO crearOrdenPedido (OrdenPedidoDTO ordenPedido) throws BusinessLogicException{
      //convierte el DTO (json) en un objeto entity para ser manejado por la logica
      LOGGER.info("OrdenpedidoResourse createOrdenPedido: input:" + ordenPedido.toString());
        OrdenPedidoEntity ordenPedidoEntity= ordenPedido.toEntity();
        //invoca la logica para crear la nueva orden de pedido
        OrdenPedidoEntity nuevaOrdenPedidoEntity= ordenPedidoLogic.createOrdenPedido(ordenPedidoEntity);
        //como debe retornar un DTO (json) se invoca el contructor de DTO con argumento el entity nuevo
        OrdenPedidoDTO nuevaOrdenPedidoDTO= new OrdenPedidoDTO(nuevaOrdenPedidoEntity);
        LOGGER.info("OrdenpedidoResourse createOrdenPedido: output:" + nuevaOrdenPedidoDTO.toString());
        
      return nuevaOrdenPedidoDTO;
              
    }
    
    /**
     * Busca la ordenPedido con el id asociado recibido en la URL y la devuelve.
     *
     * @param OrdenesPedidoId Identificador de la ordenPedido que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link OrdenPedidoDTO} - La editorial buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la ordenPedido.
     */
   @GET
    @Path("{OrdenesPedidoId:\\d+}")
    public OrdenPedidoDTO getOrdenPedido (@PathParam("OrdenesPedidoId")Long OrdenesPedidoId) throws WebApplicationException
    {
        OrdenPedidoEntity ordenPedidoEntity = ordenPedidoLogic.getOrdenPedido(OrdenesPedidoId);
        if(ordenPedidoEntity == null){
            throw new WebApplicationException("El recurso/ordenesPedido/" + OrdenesPedidoId + " no existe. ", 404 );
        }  
              
        OrdenPedidoDTO detailDTO = new OrdenPedidoDTO(ordenPedidoEntity);
        return detailDTO;
    }
    
    /**
     * Busca y devuelve todas las ordenPedido que existen en la aplicacion.
     *
     * @return JSONArray {@link OrdenPedidoDTO} - Las ordenesPedido encontradas en
     * la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<OrdenPedidoDTO> getOrdenesPedido ()
    {
        List<OrdenPedidoDTO> listaordenesPedido = listEntity2DetailDTO(ordenPedidoLogic.getOrdenesPedido());
 
        return listaordenesPedido;
    }
    
     /**
     * Actualiza la OrdenPedido con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param OdenesPedidoId Identificador de la editorial que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param ordenPedido {@link OrdenPedidoDTO} La ordenPedido que se desea guardar.
     * @return JSON {@link OrdenPedidoDTO} - La ordenPedido guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la ordenPedido a
     * actualizar.
     */
    @PUT
    @Path("{OdenesPedidoId:\\d+}")
    public OrdenPedidoDTO actualizarOrdenPedido (@PathParam("OdenesPedidoId")long OdenesPedidoId, OrdenPedidoDTO ordenPedido) throws WebApplicationException
    {
        OrdenPedidoEntity ordenPedidoEntity = ordenPedidoLogic.getOrdenPedido(OdenesPedidoId);
        if(ordenPedidoEntity == null){
            throw new WebApplicationException("El recurso/ordenesPedido/" + OdenesPedidoId + " no existe. ", 404 );
            
        }
    return null;
    }
    
    /**
     * Borra la ordenPedido con el id asociado recibido en la URL.
     *
     * @param id Identificador de la ordenPedido que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la ordenPedido.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la ordenPedido.
     */
     @DELETE
      @Path("{id:\\d+}")
    public void eliminarOrdenPedido (@PathParam("id")long id) throws BusinessLogicException{
      
        if (ordenPedidoLogic.getOrdenPedido(id)==null) {
            throw new WebApplicationException("El recurso /ordenesPedido/" + id + " no existe.", 404);
        }
        ordenPedidoLogic.eliminarOrdenPedido(id);
       
    }
    
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos OrdenPedidoEntity a una lista de
     * objetos OrdenPedioDTO (json)
     *
     * @param entityList corresponde a la lista de ordenPedio de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de ordenPedido en forma DTO (json)
     */
    private List<OrdenPedidoDTO> listEntity2DetailDTO(List<OrdenPedidoEntity> entityList) {
        List<OrdenPedidoDTO> list = new ArrayList<>();
        for (OrdenPedidoEntity entity : entityList) {
            list.add(new OrdenPedidoDTO(entity));
        }
        return list;
    }
}
