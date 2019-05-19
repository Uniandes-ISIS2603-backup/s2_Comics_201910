    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.OrdenPedidoDTO;
import co.edu.uniandes.csw.comics.ejb.OrdenPedidoLogic;
import co.edu.uniandes.csw.comics.ejb.VendedorLogic;
import co.edu.uniandes.csw.comics.ejb.VendedorOrdenPedidoLogic;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
 * @author ca.orduz
 */
@Path("/vendedores/{vendedoresId: \\d+}/pedido")
@Consumes("application/json")
@Produces("application/json")
public class VendedorOrdenPedidoResource {
    @Inject
   private VendedorOrdenPedidoLogic vendedorOrdenLogic ;
    @Inject
     private OrdenPedidoLogic ordenPedidoLogic ;
    
    
    @POST
    @Path("{OrdenesPedidoId: \\d+}")
    public OrdenPedidoDTO addOrdenPedido(@PathParam("vendedoresId")Long vendedorId,@PathParam("OrdenesPedidoId")Long ordenPedidoId){
         if (ordenPedidoLogic.getOrdenPedido(ordenPedidoId) == null) {
            throw new WebApplicationException("El recurso /ordenesPedido/" + ordenPedidoId + " no existe.", 404);
        }
        OrdenPedidoDTO orden=new OrdenPedidoDTO(vendedorOrdenLogic.addOrden(vendedorId, ordenPedidoId));
        return orden;
    }
    @GET
    public List<OrdenPedidoDTO> getOrdenes(@PathParam("vendedoresId")Long vendedoresId){
      
       List<OrdenPedidoDTO> ordenes= ordenesListEntity2DTO(vendedorOrdenLogic.getOrdenes(vendedoresId));
    return ordenes;
    }
    
    @GET
    @Path("{Estado}")
    public List<OrdenPedidoDTO> getOrdenesPedidoEstado ( @PathParam("vendedoresId") long idComprador, @PathParam("Estado")OrdenPedidoEntity.Estado estado) throws WebApplicationException
    {
         List<OrdenPedidoDTO> dtos= ordenesListEntity2DTO(vendedorOrdenLogic.getOrdenes(idComprador));
    List<OrdenPedidoDTO> lista2= new ArrayList<>();
     for(int i =0; i < dtos.size(); i++){
         if( dtos.get(i).getEstado().equals(estado)){
             lista2.add(dtos.get(i));
         }
     }
        return lista2;
      
    }
      @GET
      @Path("{OrdenesPedidoId: \\d+}")
    public OrdenPedidoDTO getOrden(@PathParam("vendedoresId")Long vendedoresId,@PathParam("OrdenesPedidoId")Long ordenesId){
      
     if (ordenPedidoLogic.getOrdenPedido(ordenesId) == null) {
            throw new WebApplicationException("El recurso /ordenesPedido/" + ordenesId + " no existe.", 404);
        }
     OrdenPedidoDTO orden=new OrdenPedidoDTO(vendedorOrdenLogic.getOrden(vendedoresId, ordenesId));
     return orden;
    }
    
   
    @DELETE
    @Path("{OrdenesPedidoId: \\d+}")
    public void removeOrden(@PathParam("vendedoresId") Long vendedoresId, @PathParam("OrdenesPedidoId") Long ordenesId) {
        
        if (ordenPedidoLogic.getOrdenPedido(ordenesId) == null) {
            throw new WebApplicationException("El recurso /ordenesPedido/" + ordenesId + " no existe.", 404);
        }
        vendedorOrdenLogic.removeOrden(vendedoresId, ordenesId);
      
    }
    
        private List<OrdenPedidoEntity> ordenesListDTO2Entity(List<OrdenPedidoDTO> dtos) {
        List<OrdenPedidoEntity> list = new ArrayList<>();
        for (OrdenPedidoDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
            private List<OrdenPedidoDTO> ordenesListEntity2DTO(List<OrdenPedidoEntity> entityList) {
        List<OrdenPedidoDTO> list = new ArrayList<>();
        for (OrdenPedidoEntity entity : entityList) {
            list.add(new OrdenPedidoDTO(entity));
        }
        return list;
    }
}
