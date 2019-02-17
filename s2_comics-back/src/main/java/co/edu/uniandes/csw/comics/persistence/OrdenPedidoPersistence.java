/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.persistence;

import co.edu.uniandes.csw.comics.entities.ComicEntity;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


/**
 *
 * @author estudiante
 */
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
        
    TypedQuery<OrdenPedidoEntity> q = em.createQuery("select u from ComicEntity u", OrdenPedidoEntity.class);
    return q.getResultList();
    
    }
    
}
