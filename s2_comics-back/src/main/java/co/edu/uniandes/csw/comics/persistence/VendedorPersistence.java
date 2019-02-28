/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.persistence;

import co.edu.uniandes.csw.comics.entities.CalificacionEntity;
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
    protected EntityManager em;
    
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
        public VendedorEntity find(Long vendedoresId) {
        LOGGER.log(Level.INFO, "Consultando el autor con id={0}", vendedoresId);
      
        return em.find(VendedorEntity.class, vendedoresId);
    }
    public VendedorEntity findByAlias(String alias)
    {
        LOGGER.log(Level.INFO, "Se buscará un vendedor", alias);
        TypedQuery query = em.createQuery("Select u From VendedorEntity u where u.alias = :alias", VendedorEntity.class);
        query = query.setParameter("alias", alias);
        
        List<VendedorEntity> sameAlias = query.getResultList();
        VendedorEntity result;
        
        if(sameAlias== null)
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
    public VendedorEntity findByEmail(String email)
    {
        LOGGER.log(Level.INFO, "Se buscará un vendedor", email);
        TypedQuery query = em.createQuery("Select u From VendedorEntity u where u.email = :email", VendedorEntity.class);
        query = query.setParameter("email", email);
        
        List<VendedorEntity> sameEmail = query.getResultList();
        VendedorEntity result;
        
        if(sameEmail == null)
        {
            result = null;
        }
        else if(sameEmail.isEmpty())
        {
            result = null;
        }
        else
        {
            result = sameEmail.get(0);
        }
        
        return result;
    }
     public VendedorEntity update(VendedorEntity vendedorEntity) {
        LOGGER.log(Level.INFO, "Actualizando el vendedor con id={0}", vendedorEntity.getAlias());
      
        return em.merge(vendedorEntity);
          }
     public void delete(Long vendedorId) {

        LOGGER.log(Level.INFO, "Borrando el vendedor con id={0}", vendedorId);
        // Se hace uso de mismo método que esta explicado en public AuthorEntity find(Long id) para obtener la author a borrar.
      VendedorEntity vendedorEntity = em.find(VendedorEntity.class, vendedorId);
       
        em.remove(vendedorEntity);
    }
}
