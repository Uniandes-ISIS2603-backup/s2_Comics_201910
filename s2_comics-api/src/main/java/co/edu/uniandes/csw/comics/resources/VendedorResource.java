/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import co.edu.uniandes.csw.comics.dtos.*;
import co.edu.uniandes.csw.comics.ejb.CalificacionLogic;
import co.edu.uniandes.csw.comics.ejb.VendedorLogic;
import co.edu.uniandes.csw.comics.entities.VendedorEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import java.util.*;
import java.util.logging.Level;
import javax.inject.Inject;
/**
 *
 * @author estudiante
 */

@Path("/vendedores")
@Consumes("application/json")
@Produces("application/json")
@RequestScoped
public class VendedorResource
{
private final static Logger LOGGER = Logger.getLogger(VendedorDTO.class.getName());
    
   // private HashMap<String, VendedorDTO> vendedores;
    
    @Inject
    private VendedorLogic vendedorLogic;
    
   
    public VendedorResource()
    {
      //  vendedores = new HashMap<String, VendedorDTO>();
    }
      /**
     * Crea un nuevo vendedor con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param vendedor {@link VendedorDTO} - EL vendedor que se desea guardar.
     * @return JSON {@link VendedorDTO} - El vendedor guardado con el atributo id
     * autogenerado.
     */
    @POST
    public VendedorDTO crearVendedor(VendedorDTO vendedor) throws BusinessLogicException
    {
        
        LOGGER.log(Level.INFO, "VendedorResource createVendedor: input: {0}", vendedor);
        VendedorDTO vendedorDTO = new VendedorDTO(vendedorLogic.createVendedor(vendedor.toEntity()));
        LOGGER.log(Level.INFO, "VendedorResource createVendedor: output: {0}", vendedorDTO);
        return vendedorDTO;
    }
     /**
     * Busca y devuelve todos los vendedores que existen en la aplicacion.
     *
     * @return JSONArray {@link VendedorDetailDTO} - Los vendedores encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<VendedorDetailDTO> getVendedores()
    {
          LOGGER.info("AuthorResource getAuthors: input: void");
        List<VendedorDetailDTO> listaVendedores = listEntity2DTO(vendedorLogic.getVendedores());
        LOGGER.log(Level.INFO, "AuthorResource getAuthors: output: {0}", listaVendedores);
        return listaVendedores;
    }
     /**
     * Busca el autor con el id asociado recibido en la URL y lo devuelve.
     *
     * @param vendedoresId Identificador del vendedor que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link VendedorDetailDTO} - El vendedor buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el vendedor.
     */
     @GET
    @Path("{vendedoresId: \\d+}")
    public VendedorDetailDTO getVendedor(@PathParam("vendedoresId") Long vendedoresId) {
        LOGGER.log(Level.INFO, "AuthorResource getAuthor: input: {0}", vendedoresId);
        VendedorEntity vendedorEntity = vendedorLogic.getVendedor(vendedoresId);
        if (vendedorEntity == null) {
            throw new WebApplicationException("El recurso /vendedores/" + vendedoresId + " no existe.", 404);
        }
        VendedorDetailDTO detailDTO = new VendedorDetailDTO(vendedorEntity);
        LOGGER.log(Level.INFO, "AuthorResource getAuthor: output: {0}", detailDTO);
        return detailDTO;
    }
        @Path("{vendedoresId: \\d+}/calificaciones")
    public Class<CalificacionResource> getCalificacionResource(@PathParam("vendedoresId") Long vendedoresId) {
        if (vendedorLogic.getVendedor(vendedoresId) == null) {
            throw new WebApplicationException("El recurso /vendedores/" + vendedoresId + "/calificaciones no existe.", 404);
        }
        return CalificacionResource.class;
    }
      @Path("{vendedoresId: \\d+}/ordenesPedido")
    public Class<VendedorOrdenPedidoResource> getVendedorOrdenPedidoResource(@PathParam("vendedoresId") Long vendedoresId) {
        if (vendedorLogic.getVendedor(vendedoresId) == null) {
            throw new WebApplicationException("El recurso /vendedores/" + vendedoresId + " no existe.", 404);
        }
        return VendedorOrdenPedidoResource.class;
    }
       /**
     * Actualiza el vendedor con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param vendedoresId Identificador del vendedor que se desea actualizar. Este
     * debe ser una cadena de dígitos.
     * @param vendedores {@link VendedorDetailDTO} El vendedor que se desea guardar.
     * @return JSON {@link VendedorDetailDTO} - El vendedor guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el vendedor a
     * actualizar.
     */
      @PUT
    @Path("{vendedoresId: \\d+}")
    public VendedorDetailDTO updateVendedor(@PathParam("vendedoresId") Long vendedoresId, VendedorDetailDTO vendedor) {
        LOGGER.log(Level.INFO, "AuthorResource updateAuthor: input: authorsId: {0} , author: {1}", new Object[]{vendedoresId, vendedor});
        vendedor.setId(vendedoresId);
        if (vendedorLogic.getVendedor(vendedoresId) == null) {
            throw new WebApplicationException("El recurso /authors/" + vendedoresId + " no existe.", 404);
        }
        VendedorDetailDTO detailDTO = new VendedorDetailDTO(vendedorLogic.updateVendedor(vendedoresId, vendedor.toEntity()));
        LOGGER.log(Level.INFO, "AuthorResource updateAuthor: output: {0}", detailDTO);
        return detailDTO;
    }
     /**
     * Borra el vendedor con el id asociado recibido en la URL.
     *
     * @param vendedoresId Identificador del autor que se desea borrar. Este debe
     * ser una cadena de dígitos.

     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el vendedor a borrar.
     */
    @DELETE
    @Path("{vendedoresId: \\d+}")
    public void deleteVendedor(@PathParam("vendedoresId") Long vendedoresId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AuthorResource deleteAuthor: input: {0}", vendedoresId);
        if (vendedorLogic.getVendedor(vendedoresId) == null) {
            throw new WebApplicationException("El recurso /authors/" + vendedoresId + " no existe.", 404);
        }
        vendedorLogic.deleteVendedor(vendedoresId);
        LOGGER.info("AuthorResource deleteAuthor: output: void");
    }
        private List<VendedorDetailDTO> listEntity2DTO(List<VendedorEntity> entityList) {
        List<VendedorDetailDTO> list = new ArrayList<>();
        for (VendedorEntity entity : entityList) {
            list.add(new VendedorDetailDTO(entity));
        }
        return list;
    }
}
