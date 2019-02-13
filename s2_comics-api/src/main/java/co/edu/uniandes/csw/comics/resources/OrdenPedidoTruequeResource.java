/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.OrdenPedidoTruequeDTO;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */

@Path("ordenPedidoTrueque")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class OrdenPedidoTruequeResource {
   private final static Logger LOGGER = Logger.getLogger(OrdenPedidoTruequeDTO.class.getName());
    
    /**
     * Crea un nueva OrdenPedidoTrueque con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param ordenPedidoTrueque 
     * @return JSON {@link BookDTO} - la ordenPedidoTrueque guardada con el atributo id
     * autogenerado.
     */
    @POST
    public OrdenPedidoTruequeDTO createOrdenPedidoTrueque(OrdenPedidoTruequeDTO ordenPedidoTrueque)  {;
        return ordenPedidoTrueque;
    }
}
