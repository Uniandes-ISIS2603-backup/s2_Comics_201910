/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.CompradorDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.*;
import javax.ws.rs.Produces;
import java.util.*;
import java.util.logging.Level;
import javax.ws.rs.GET;

/**
 *
 * @author estudiante
 */
@Path("comprador")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CompradorResource
{
    private static final Logger LOGGER = Logger.getLogger(CompradorDTO.class.getName());
    
    private HashMap<String, CompradorDTO> compradores; 
    
    public CompradorResource()
    {
        compradores = new HashMap<String, CompradorDTO>();
    }
    
    @POST
    public String crearComprador(CompradorDTO comprador)
    {
        try
        {
            if(compradores.containsKey(comprador.getAlias()))
            {
                return "error";
            }
            LOGGER.log(Level.INFO, "CompradorResource crearComprador: input: {0}", comprador);
            compradores.put(comprador.getAlias(), comprador);
            LOGGER.log(Level.INFO, "CompradorResource createComprador: output: {0}", comprador);
            return "Operación Exitosa";
        }
        catch(Exception e)
        {
            return "Error";
        }
    }
    
    @GET
        @Path("{name: [a-zA-Z][a-zA-Z]*}")
    public CompradorDTO getCompradorByAlias(@PathParam("name") String alias)throws Exception
    {
        try
        {
            return compradores.get(alias);
        }
        catch(Exception e)
        {
            throw new Exception("No se encontró ningún comprador asociado a ese alias");
        }
    }
    
}
    
