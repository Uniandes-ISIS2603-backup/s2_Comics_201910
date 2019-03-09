/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.ejb;

import co.edu.uniandes.csw.comics.entities.ComicDeseoEntity;
import co.edu.uniandes.csw.comics.entities.ComicEntity;
import co.edu.uniandes.csw.comics.entities.CompradorEntity;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.comics.persistence.CompradorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jp.cano
 */
@Stateless
public class CompradorLogic 
{
    private final static Logger LOGGER = Logger.getLogger(CompradorLogic.class.getName());
    
    @Inject
    private CompradorPersistence persistencia;
    
    /**
     * Se encarga de crear un Comprador en la base de datos.
     *
     * @param CompradorEntity Objeto de CompradorEntity con los datos nuevos
     * @return Objeto de CompradorEntity con los datos nuevos y su ID.
     */
    public CompradorEntity createComprador(CompradorEntity entity)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del comprador");
        CompradorEntity exists = getCompradorByAlias(entity.getAlias());
        CompradorEntity existsMail = getCompradorByEmail(entity.getEmail());
        CompradorEntity existsId = findComprador(entity.getId());
        if(exists != null)
        {
            throw new BusinessLogicException("Ya existe un comprador con este alias");
        }
        if(existsMail != null)
        {
            throw new BusinessLogicException("Ya existe un comprador con este email");
        }
        if(existsId != null)
        {
            throw new BusinessLogicException("Ya existe un comprador con este Id");
        }
        CompradorEntity newEntity = persistencia.create(entity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del comprador");
        return newEntity;
    }
    
    /**
     * Obtiene la lista de los registros de Comprador.
     *
     * @return Colección de objetos de CompradorEntity.
     */
    public List<CompradorEntity> getCompradores()
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consulta de todos los compradores");
        List<CompradorEntity> lista = persistencia.findAll();
        LOGGER.log(Level.INFO, "Termina proces de consulta de todos los compradores");
        return lista;
    }
    
    /**
     * Obtiene los datos de una instancia de Comprador a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de CompradorEntity con los datos del Comprador consultado.
     */
    public CompradorEntity findComprador(long id)
    {
        LOGGER.log(Level.INFO, "Se inicia la busqueda del comprador con id={0}", id);
        CompradorEntity entity = persistencia.find(id);
        LOGGER.log(Level.INFO, "Se finaliza la busqueda del comprador con id={0}", id);
        return entity;
    }
    
    /**
     * Obtiene los datos de una instancia de Comprador a partir de su alias.
     *
     * @param alias Identificador de la instancia a consultar
     * @return Instancia de CompradorEntity con los datos del Comprador consultado.
     */
    public CompradorEntity getCompradorByAlias(String alias)
    {
        LOGGER.log(Level.INFO, "Se crea un proceso de busqueda de comprador");
        CompradorEntity comprador = persistencia.findByAlias(alias);
        LOGGER.log(Level.INFO, "Se termina el proceso de busqueda de comprador");
        return comprador;
    }
    
    /**
     * Obtiene los datos de una instancia de Comprador a partir de su email.
     *
     * @param email Identificador de la instancia a consultar
     * @return Instancia de CompradorEntity con los datos del Comprador consultado.
     */
    public CompradorEntity getCompradorByEmail(String email)
    {
        LOGGER.log(Level.INFO, "Se inicia la consulta por email");
        CompradorEntity entity = persistencia.findByEmail(email);
        LOGGER.log(Level.INFO, "Se inicia la consulta por email");
        return entity;
    }
    
     /**
     * Actualiza la información de una instancia de Comprador.
     *
     * @param id Identificador de la instancia a actualizar
     * @param entity Instancia de CompradorEntity con los nuevos datos.
     * @return Instancia de CompradorEntity con los datos actualizados.
     */
    public CompradorEntity updateComprador(long id, CompradorEntity entity)
    {
        LOGGER.log(Level.INFO, "Se inicia la actualización del comprador con id={0}", id);
        CompradorEntity newEntity = persistencia.update(entity);
        LOGGER.log(Level.INFO, "Se finaliza la actualización del comprador con id={0}", id);
        return newEntity;
    }
    
    /**
     * Elimina una instancia de Comprador de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el autor tiene libros asociados.
     */
    public void deleteComprador(long id)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de eliminar al comrpador con id={0}", id);
        CompradorEntity entity = findComprador(id);
        
        List<OrdenPedidoEntity> lista1 = entity.getOrdenPedidoCompra();
        
        if(!lista1.isEmpty())
        {
            throw new BusinessLogicException("No se puede elminar el comprador porque hay comics en su orden de pedido");
        }
        
        persistencia.delete(id);
        LOGGER.log(Level.INFO, "Se ha terminado el proceso de eliminación del comprador con id={0}", id);
        
    }
}
