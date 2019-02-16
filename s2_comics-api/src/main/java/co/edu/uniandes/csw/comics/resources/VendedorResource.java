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
import java.util.*;
/**
 *
 * @author estudiante
 */

@Path("vendedor")
@Consumes("application/json")
@Produces("application/json")
@RequestScoped
public class VendedorResource
{
    private final static Logger LOGGER = Logger.getLogger(VendedorDTO.class.getName());
    
    private HashMap<String, VendedorDTO> vendedores;
    
    public VendedorResource()
    {
        vendedores = new HashMap<String, VendedorDTO>();
    }
    
    @POST
    public VendedorDTO crearVendedor(VendedorDTO vendedor)
    {
        vendedores.put(vendedor.getAlias(), vendedor);
        return vendedor;
    }
    
    @GET
    public HashMap<String, VendedorDTO> getVendedores()
    {
        return vendedores;
    }
    
    @GET
    @Path("{name: [a-zA-Z][a-zA-Z]*}")
    public VendedorDTO getVendedor(String alias) throws Exception
    {/*
        try
        {
            return vendedores.get(alias);
        }
        catch(Exception e)
        {
            throw new Exception("No se encontró el vendedor solicitado");
        }
*/
        return null;
    }
    
    @DELETE
    @Path("{name: [a-zA-Z][a-zA-Z]*}")
    public VendedorDTO deleteVendedor(@PathParam("name") String alias) throws Exception
    {
        try
        {
            VendedorDTO eliminado = vendedores.get(alias);
            vendedores.remove(alias);
            return eliminado;
        }
        catch(Exception e)
        {
            throw new Exception("No se puede eliminar el vendedor porque no existe");
        }
    }
}
