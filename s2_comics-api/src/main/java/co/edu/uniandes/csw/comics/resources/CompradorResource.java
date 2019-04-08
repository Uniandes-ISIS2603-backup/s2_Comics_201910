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
 * Clase que implementa el recurso "comrpador"
 * 
 * @comprador juan pablo cano
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
    
    /**
     * Crea un nuevo autor con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param comprador {@link CompradorDTO} - EL autor que se desea guardar.
     * @return JSON {@link CompradorDTO} - El autor guardado con el atributo id
     * autogenerado.
     */
    @POST
    public CompradorDTO crearComprador(CompradorDTO comprador) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "CompradorResource createComprador: input:{0}", comprador);
        CompradorDTO newComprador = new CompradorDTO(compradorLogic.createComprador(comprador.toEntity()));
        LOGGER.log(Level.INFO, "CompradorResource createComprador: output:{0}", newComprador);
        return newComprador;
    }
    
    /**
     * Busca y devuelve todos los autores que existen en la aplicacion.
     *
     * @return JSONArray {@link compradorDetailDTO} - Los autores encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<CompradorDetailDTO> getCompradores()
    {
        LOGGER.log(Level.INFO, "CompradorResource getCompradores: input: void");
        List<CompradorDetailDTO> list = listEntity2DTO(compradorLogic.getCompradores());
        LOGGER.log(Level.INFO, "CompradorResource getCompradores: output:{0}", list);
        return list;
    }
    
    /**
     * Borra el autor con el id asociado recibido en la URL.
     *
     * @param compradorsId Identificador del autor que se desea borrar. Este debe
     * ser una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * si el autor tiene libros asociados
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el autor a borrar.
     */
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
    
    /**
     * 
     * @param id
     * @param comprador
     * @return
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("{compradorId: \\d+}")
    public CompradorDetailDTO updateComprador(@PathParam("compradorId") long id, CompradorDetailDTO comprador)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "CompradorResource updateComprador: input: {0}, comprador: {1}", new Object[]{id, comprador});
        comprador.setId(id);
        if(compradorLogic.findComprador(id) == null)
        {
            throw new WebApplicationException("No se encontró el comprador con el id: " + id, 404);
        }
        CompradorDetailDTO detail = new CompradorDetailDTO(compradorLogic.updateComprador(id, comprador.toEntity()));
        LOGGER.log(Level.INFO, "Comprador updated: output: {0}", detail);
        return detail;
    }
    
    /**
     * Busca el autor con el id asociado recibido en la URL y lo devuelve.
     *
     * @param compradorsId Identificador del autor que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link compradorDetailDTO} - El autor buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
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
    
    /**
     * 
     * @param alias
     * @return
     * @throws Exception 
     */
    @GET
    @Path("{name: [a-zA-Z0-9][a-zA-Z0-9]*}")
    public CompradorDetailDTO getCompradorByAlias(@PathParam("name") String alias)throws Exception
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
    
    /*@GET
    @Path("{email}")
    public CompradorDetailDTO getCompradorByEmail(@PathParam("email") String email) throws Exception
    {
        LOGGER.log(Level.INFO, "CompradorResource getCompradorByEmail: input: {0}", email);
        CompradorEntity entity = compradorLogic.getCompradorByEmail(email);
        if(entity == null)
        {
            throw new WebApplicationException("El recurso /comprador/" + email + " no existe.", 404);
        }
        CompradorDetailDTO comprador = new CompradorDetailDTO(entity);
        return comprador;
    }*/
    
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
    
