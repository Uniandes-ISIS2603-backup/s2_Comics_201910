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

@Path("vendedores")
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
    
    @POST
    public VendedorDTO crearVendedor(VendedorDTO vendedor) throws BusinessLogicException
    {
        
        LOGGER.log(Level.INFO, "VendedorResource createVendedor: input: {0}", vendedor);
        VendedorDTO vendedorDTO = new VendedorDTO(vendedorLogic.createVendedor(vendedor.toEntity()));
        LOGGER.log(Level.INFO, "VendedorResource createVendedor: output: {0}", vendedorDTO);
        return vendedorDTO;
    }
    
    @GET
    public List<VendedorDetailDTO> getVendedores()
    {
          LOGGER.info("AuthorResource getAuthors: input: void");
        List<VendedorDetailDTO> listaVendedores = listEntity2DTO(vendedorLogic.getVendedores());
        LOGGER.log(Level.INFO, "AuthorResource getAuthors: output: {0}", listaVendedores);
        return listaVendedores;
    }
    
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
