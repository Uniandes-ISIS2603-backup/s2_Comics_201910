/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.CompradorDTO;
import co.edu.uniandes.csw.comics.dtos.CompradorDetailDTO;
import co.edu.uniandes.csw.comics.ejb.CompradorLogic;
import co.edu.uniandes.csw.comics.entities.CompradorEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
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
 * @author juan pablo cano
 */
@Path("/comprador")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CompradorResource
{
    private static final Logger LOGGER = Logger.getLogger(CompradorResource.class.getName());
    
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
    public List<CompradorDetailDTO> getCompradores()
    {
        LOGGER.log(Level.INFO, "CompradorResource getCompradores: input: void");
        List<CompradorDetailDTO> list = listEntity2DTO(compradorLogic.getCompradores());
        LOGGER.log(Level.INFO, "CompradorResource getCompradores: output:{0}", list);
        return list;
    }
    
    @DELETE
    @Path("{compradorId: \\d+}")
    public void deleteComprador(@PathParam("compradorId") long id) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "CompradorResource deleteComprador: input:{0}", id);
        if(compradorLogic.findComprador(id) == null)
        {
            throw new WebApplicationException("El recurso /comprador/" + id + " no existe", 404);
        }
        compradorLogic.deleteComprador(id);
        LOGGER.log(Level.INFO, "CompradorResource deleteComprador:output:void");
    }
    
    @GET
    @Path("{compradorId: \\d+}")
    public CompradorDetailDTO getComprador(@PathParam("compradorId") long id)
    {
        LOGGER.log(Level.INFO, "CompradorResource getComprador: input: {0}", id);
        CompradorEntity entity = compradorLogic.findComprador(id);
        if(entity == null)
        {
            throw new WebApplicationException("El recurso /comprador/" + id + " no existe.", 404);
        }
        CompradorDetailDTO comprador = new CompradorDetailDTO(entity);
        LOGGER.log(Level.INFO, "CompradorResource getComprador: output:{0}", comprador);
        return comprador;
    }
    
    @GET
    @Path("{name: [a-zA-Z][a-zA-Z]*}")
    public CompradorDTO getCompradorByAlias(@PathParam("name") String alias)throws Exception
    {
        LOGGER.log(Level.INFO, "CompradorResource getCompradorByAlias:input:{0}", alias);
        CompradorEntity entity = compradorLogic.getCompradorByAlias(alias);
        if(entity == null)
        {
            throw new WebApplicationException("El recurso /comprador/" + alias + " no existe.", 404);
        }
        CompradorDetailDTO comprador = new CompradorDetailDTO(entity);
        return comprador;
    }
    
    @GET
    @Path("{email: /^([\\w\\-\\.]+)@((\\[([0-9]{1,3}\\.){3}[0-9]{1,3}\\])|(([\\w\\-]+\\.)+)([a-zA-Z]{2,4}))$/}")
    public CompradorDTO getCompradorByEmail(@PathParam("email") String email) throws Exception
    {
        LOGGER.log(Level.INFO, "CompradorResource getCompradorByEmail: input: {0}", email);
        CompradorEntity entity = compradorLogic.getCompradorByEmail(email);
        if(entity == null)
        {
            throw new WebApplicationException("El recurso /comprador/" + email + " no existe.", 404);
        }
        CompradorDetailDTO comprador = new CompradorDetailDTO(entity);
        return comprador;
    }
    
    private List<CompradorDetailDTO> listEntity2DTO(List<CompradorEntity> entity )
    {
        ArrayList<CompradorDetailDTO> list = new ArrayList();
        for(CompradorEntity comprador : entity)
        {
            list.add(new CompradorDetailDTO(comprador));
        }
        return list;
    }
}
    
