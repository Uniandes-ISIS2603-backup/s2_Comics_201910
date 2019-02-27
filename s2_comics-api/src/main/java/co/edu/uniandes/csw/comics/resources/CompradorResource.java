/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.CompradorDTO;
import co.edu.uniandes.csw.comics.ejb.CompradorLogic;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.*;
import javax.ws.rs.Produces;
import java.util.*;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.GET;

/**
 *
 * @author estudiante
 */
@Path("/comprador")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CompradorResource
{
    private static final Logger LOGGER = Logger.getLogger(CompradorResource.class.getName());
    
    private HashMap<String, CompradorDTO> compradores; 
    
    @Inject
    private CompradorLogic compradorLogic;
    
    @POST
    public CompradorDTO crearComprador(CompradorDTO comprador)
    {
        try
        {
            LOGGER.log(Level.INFO, "CompradorResource createComprador: input:{0}", comprador);
            CompradorDTO newComprador = new CompradorDTO(compradorLogic.createComprador(comprador.toEntity()));
            LOGGER.log(Level.INFO, "CompradorResource createComprador: output:{0}", newComprador);
            return newComprador;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    @GET
    public List getCompradores()
    {
        return null;
    }
    
    @DELETE
    @Path("{name: [a-zA-Z][a-zA-Z]*}")
    public CompradorDTO deleteComprador(@PathParam("name") String alias)throws Exception
    {
        try
        {
            CompradorDTO eliminado = compradores.get(alias);
            compradores.remove(alias);
            return eliminado;
        }
        catch(Exception e)
        {
            throw new Exception("No se puede eliminar el compador porque no se encontró");
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
    
