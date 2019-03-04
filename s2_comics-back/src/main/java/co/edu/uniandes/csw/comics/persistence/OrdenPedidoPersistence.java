/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.persistence;

import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import static java.awt.Event.DELETE;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class OrdenPedidoPersistence {
    private static final Logger LOGGER = Logger.getLogger(OrdenPedidoPersistence.class.getName());
    
    @PersistenceContext(unitName = "comicsPU")
    protected EntityManager em;
    
    public OrdenPedidoPersistence(){
        
    }
    
    public OrdenPedidoEntity create(OrdenPedidoEntity nuevo)
    {
        LOGGER.log(Level.INFO, "Creando una OrdenPedido nueva");
        em.persist(nuevo);
         LOGGER.log(Level.INFO, "OrdenPedido creada");
       
        return nuevo;
    }
    
    public OrdenPedidoEntity find (Long OrdenPedidoId){
    
       return em.find(OrdenPedidoEntity.class, OrdenPedidoId);
    }
    
    public List<OrdenPedidoEntity> findAll (){
        
     LOGGER.log(Level.INFO, "Se buscarán todos las ordenes Pedido");
        Query q = em.createQuery("select u from OrdenPedidoEntity u");
        return q.getResultList();
    
    }
    
    
     public void delete(Long ordenPedidoId) {
  LOGGER.log(Level.INFO, "Borrando el vendedor con id={0}", ordenPedidoId);
        // Se hace uso de mismo método que esta explicado en public AuthorEntity find(Long id) para obtener la author a borrar.
      OrdenPedidoEntity ordenPedidoEntity = em.find(OrdenPedidoEntity.class, ordenPedidoId);
       
        em.remove(ordenPedidoEntity);
     }
     
    /** public OrdenPedidoEntity findById(Long id)
    {
        LOGGER.log(Level.INFO, "Se buscará la ordenPedido por el id", id);
        
        TypedQuery query = em.createQuery("Select u From OrdenPedidoEntity u where u.id = :id", OrdenPedidoEntity.class);
        
        query = query.setParameter("id", id);
        
        List<OrdenPedidoEntity> sameId = query.getResultList();
        
        OrdenPedidoEntity result;
        
        if(sameId == null)
        {
            result = null;
        }
        else if(sameId.isEmpty())
        {
            result = null;
        }
        else
        {
            result = sameId.get(0);
        }
        
        return result;
    }
     */
     
 
      
      public OrdenPedidoEntity update(OrdenPedidoEntity ordenPedidoEntity){
        LOGGER.log(Level.INFO, "Actualizando la orden pedido con id={0}", ordenPedidoEntity.getId() );
      
        return em.merge(ordenPedidoEntity);
          }
      
   
}
