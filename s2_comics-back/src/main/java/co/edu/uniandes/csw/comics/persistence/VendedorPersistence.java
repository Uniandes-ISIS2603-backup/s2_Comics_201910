/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.persistence;

import co.edu.uniandes.csw.comics.entities.VendedorEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Pablo Cano
 */
@Stateless
public class VendedorPersistence
{
    private final static Logger LOGGER = Logger.getLogger(VendedorPersistence.class.getName());
    
    @PersistenceContext(unitName = "comicsPU")
    private EntityManager em;
    
    public VendedorEntity create(VendedorEntity vendedor)
    {
        LOGGER.log(Level.INFO, "Creando un vendedor nuevo");
        em.persist(vendedor);
        LOGGER.log(Level.INFO, "Vendedor creado");
        return vendedor;
    }
    
    public List<VendedorEntity> findAll()
    {
        LOGGER.log(Level.INFO, "Se buscarán todos los vendedores");
        Query q = em.createQuery("select u from VendedorEntity u");
        return q.getResultList();
    }
    
    public VendedorEntity findByAlias(String alias)
    {
        LOGGER.log(Level.INFO, "Se buscará un vendedor", alias);
        TypedQuery query = em.createQuery("Select u From VendedorEntity u where u.alias = :alias", VendedorEntity.class);
        query = query.setParameter("alias", alias);
        
        List<VendedorEntity> sameAlias = query.getResultList();
        VendedorEntity result;
        
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
