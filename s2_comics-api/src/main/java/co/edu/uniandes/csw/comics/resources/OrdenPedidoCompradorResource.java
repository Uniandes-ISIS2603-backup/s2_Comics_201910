/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.OrdenPedidoDTO;
import co.edu.uniandes.csw.comics.dtos.CompradorDTO;
import co.edu.uniandes.csw.comics.ejb.OrdenPedidoLogic;
import co.edu.uniandes.csw.comics.ejb.OrdenPedidoCompradorLogic;
import co.edu.uniandes.csw.comics.ejb.CompradorLogic;
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
 *
 * @author jp.rodriguezv
 */
@Path("ordenesPedido/{ordenPedidoId: \\d+}/comprador")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrdenPedidoCompradorResource {
    
      private static final Logger LOGGER = Logger.getLogger(OrdenPedidoCompradorResource.class.getName());

      @Inject
    private OrdenPedidoLogic ordenPedidoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CompradorLogic compradorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private OrdenPedidoCompradorLogic ordenPedidoCompradorLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

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
    public OrdenPedidoDTO replaceComprador(@PathParam("ordenPedidoId") Long ordenPedidoId, CompradorDTO comprador) {
       
        if (ordenPedidoLogic.getOrdenPedido(ordenPedidoId) == null) {
            throw new WebApplicationException("El recurso /ordenesPedido/" + ordenPedidoId + " no existe.", 404);
        }
        if (compradorLogic.getCompradorByAlias(comprador.getAlias()) == null) {
            throw new WebApplicationException("El recurso /compradores/" + comprador.getId() + " no existe.", 404);
        }
        OrdenPedidoDTO ordenPedidoDTO = new OrdenPedidoDTO(ordenPedidoCompradorLogic.replaceComprador(ordenPedidoId, comprador.getId()));
        return ordenPedidoDTO;
    }
    
    
}
