/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.persistence;

import co.edu.uniandes.csw.comics.entities.CompradorEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author juan pablo cano
 */
@Stateless
public class CompradorPersistence 
{
    private final static Logger LOGGER = Logger.getLogger(CompradorPersistence.class.getName());
    
    @PersistenceContext(unitName = "comicsPU")
    private EntityManager em;
    
    public CompradorEntity create(CompradorEntity comprador)
    {
        LOGGER.log(Level.INFO, "Creando un comprador nuevo");
        em.persist(comprador);
        LOGGER.log(Level.INFO, "Comprador creado");
        
        return comprador;
    }
    
    public List<CompradorEntity> findAll()
    {
        LOGGER.log(Level.INFO, "Consultar todos los compradores");
        Query q = em.createQuery("select u from CompradorEntity u");
        return q.getResultList();
    }
    
    public CompradorEntity find(long id)
    {
        return em.find(CompradorEntity.class, id);
    }
    
    public CompradorEntity findByAlias(String alias)
    {
        LOGGER.log(Level.INFO, "Se buscará al comprador por el alias", alias);
        
        TypedQuery query = em.createQuery("Select u From CompradorEntity u where u.alias = :alias", CompradorEntity.class);
        
        query = query.setParameter("alias", alias);
        
        List<CompradorEntity> sameAlias = query.getResultList();
        
        CompradorEntity result;
        
        if(sameAlias == null)
        {
            result = null;
        }
        else if(sameAlias.isEmpty())
        {
            result = null;
        }
        else
        {
            result = sameAlias.get(0);
        }
        
        return result;
    }
    
    public CompradorEntity findByEmail(String email)
    {
        LOGGER.log(Level.INFO, "Se buscará un comprador por el mail", email);
        
        TypedQuery query = em.createQuery("Select u From CompradorEntity u where u.email = :email", CompradorEntity.class);
        
        query = query.setParameter("email", email);
        
        List<CompradorEntity> sameMail = query.getResultList();
        
        CompradorEntity result;
        
        if(sameMail == null)
        {
            result = null;
        }
        else if(sameMail.isEmpty())
        {
            result = null;
        }
        else
        {
            result = sameMail.get(0);
        }
        
        return result;
    }
    
    public void deleteByAlias(String alias)
    {
        LOGGER.log(Level.INFO,"Eliminando un comprador con alias={0}", alias);
        CompradorEntity eliminado = findByAlias(alias);
        em.remove(eliminado);
    }
    
    public void delete(long id)
    {
        LOGGER.log(Level.INFO, "Eliminando un comprador con id={0}", id);
        CompradorEntity eliminado = em.find(CompradorEntity.class, id);
        em.remove(eliminado);
    }
    
    public CompradorEntity update(CompradorEntity entity)
    {
        LOGGER.log(Level.INFO,"Actualizando el comprador con id={0}", entity.getId());
        return em.merge(entity);
    }
}
