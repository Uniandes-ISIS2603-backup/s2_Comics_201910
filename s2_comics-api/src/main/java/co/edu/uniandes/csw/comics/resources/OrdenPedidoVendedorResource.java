/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.OrdenPedidoDTO;
import co.edu.uniandes.csw.comics.dtos.VendedorDTO;
import co.edu.uniandes.csw.comics.ejb.OrdenPedidoLogic;
import co.edu.uniandes.csw.comics.ejb.VendedorLogic;
import co.edu.uniandes.csw.comics.ejb.OrdenPedidoVendedorLogic;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso "ordenesPedido/{id}/vendedor".
 *
 * @author jp.rodriguezv
 */
@Path("ordenesPedido/{ordenPedidoId: \\d+}/vendedor")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrdenPedidoVendedorResource {
    
     private static final Logger LOGGER = Logger.getLogger(OrdenPedidoVendedorResource.class.getName());

      @Inject
    private OrdenPedidoLogic ordenPedidoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private VendedorLogic vendedorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private OrdenPedidoVendedorLogic ordenPedidoVendedorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Remplaza la instancia de Vendedor asociada a una OrdenPedido.
     *
     * @param ordenPedidoId Identificador de la ordenPedido que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param vendedor el vendedor que se será de la ordenPedido.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el vendedor o la
     * ordenPedido.
     */
    @PUT
    public OrdenPedidoDTO replaceVendedor(@PathParam("ordenPedidoId") Long ordenPedidoId, VendedorDTO vendedor) {
       
        if (ordenPedidoLogic.getOrdenPedido(ordenPedidoId) == null) {
            throw new WebApplicationException("El recurso /ordenesPedido/" + ordenPedidoId + " no existe.", 404);
        }
        if (vendedorLogic.getVendedor(vendedor.getId()) == null) {
            throw new WebApplicationException("El recurso /vendedores/" + vendedor.getId() + " no existe.", 404);
        }
        OrdenPedidoDTO ordenPedidoDTO = new OrdenPedidoDTO(ordenPedidoVendedorLogic.replaceVendedor(ordenPedidoId, vendedor.getId()));
        return ordenPedidoDTO;
    }
    
    
}

