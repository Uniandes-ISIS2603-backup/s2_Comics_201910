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
 * @author estudiante
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
    
    public CompradorEntity findByAlias(String alias)
    {
        LOGGER.log(Level.INFO, "", alias);
        
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
}
