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
    
    public CompradorEntity createComprador(CompradorEntity entity)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del comprador");
        CompradorEntity exists = getCompradorByAlias(entity.getAlias());
        CompradorEntity existsMail = getCompradorByEmail(entity.getEmail());
        if(exists != null)
        {
            throw new BusinessLogicException("Ya existe un comprador con este alias");
        }
        if(existsMail != null)
        {
            throw new BusinessLogicException("Ya existe un comprador con este email");
        }
        CompradorEntity newEntity = persistencia.create(entity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del comprador");
        return newEntity;
    }
    
    public List<CompradorEntity> getCompradores()
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consulta de todos los compradores");
        List<CompradorEntity> lista = persistencia.findAll();
        LOGGER.log(Level.INFO, "Termina proces de consulta de todos los compradores");
        return lista;
    }
    
    public CompradorEntity findComprador(long id)
    {
        LOGGER.log(Level.INFO, "Se inicia la busqueda del comprador con id={0}", id);
        CompradorEntity entity = persistencia.find(id);
        LOGGER.log(Level.INFO, "Se finaliza la busqueda del comprador con id={0}", id);
        return entity;
    }
    
    public CompradorEntity getCompradorByAlias(String alias)
    {
        LOGGER.log(Level.INFO, "Se crea un proceso de busqueda de comprador");
        CompradorEntity comprador = persistencia.findByAlias(alias);
        LOGGER.log(Level.INFO, "Se termina el proceso de busqueda de comprador");
        return comprador;
    }
    
    public CompradorEntity getCompradorByEmail(String email)
    {
        LOGGER.log(Level.INFO, "Se inicia la consulta por email");
        CompradorEntity entity = persistencia.findByEmail(email);
        LOGGER.log(Level.INFO, "Se inicia la consulta por email");
        return entity;
    }
    
    public CompradorEntity updateComprador(long id, CompradorEntity entity)
    {
        LOGGER.log(Level.INFO, "Se inicia la actualización del comprador con id={0}", id);
        CompradorEntity newEntity = persistencia.update(entity);
        LOGGER.log(Level.INFO, "Se finaliza la actualización del comprador con id={0}", id);
        return newEntity;
    }
    
    public void deleteComprador(long id)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de eliminar al comrpador con id={0}", id);
        CompradorEntity entity = findComprador(id);
        
        List<OrdenPedidoEntity> lista1 = entity.getOrdenPedidoCompra();
        List<ComicEntity> lista2 = entity.getCarro();
        List<ComicDeseoEntity> lista3 = entity.getListaDeseos();
        if(lista1 != null || !lista1.isEmpty())
        {
            throw new BusinessLogicException("No se puede elminar el comprador porque hay comics en su orden de pedido");
        }
        
        if(lista2 != null || !lista2.isEmpty())
        {
            throw new BusinessLogicException("No se puede elminar el comprador porque hay comics en su carro");
        }
        
        if(lista3 != null || !lista3.isEmpty())
        {
            throw new BusinessLogicException("No se puede elminar el comprador porque hay comics en su lista de deseos");
        }
        else
        {
            persistencia.delete(id);
            LOGGER.log(Level.INFO, "Se ha terminado el proceso de eliminación del comprador con id={0}", id);
        }
    }
}
