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
   
    /**
     * constructor vacio
     */
    public OrdenPedidoPersistence(){
        
    }
    
    /**
     * crea una ordenPedido nueva en la base de datos
     * @param nuevo , entidad con la informacion de la  nueva ordenPedido
     * @return la ordenPedidocreada
     */
    public OrdenPedidoEntity create(OrdenPedidoEntity nuevo)
    {
        LOGGER.log(Level.INFO, "Creando una OrdenPedido nueva");
        em.persist(nuevo);
         LOGGER.log(Level.INFO, "OrdenPedido creada");
       
        return nuevo;
    }
    
    /**
     * busca una ordenPedido a partir del id
     * @param OrdenPedidoId, id de la ordenPedido buscada
     * @return la ordenPedido buscada
     */
    public OrdenPedidoEntity find (Long OrdenPedidoId){
    
       return em.find(OrdenPedidoEntity.class, OrdenPedidoId);
    }
    
    /**
     * devuelve todas las ordenes Pedido que hay en la base de datos
     * @return todas las ordenesPedido
     */
    public List<OrdenPedidoEntity> findAll (){
        
    LOGGER.log(Level.INFO, "Consultar todos las ordenesPedido");
        Query q = em.createQuery("select u from OrdenPedidoEntity u");
        return q.getResultList();
   
    }
    
    /**
     * elimina una ordenPedido a partir de si identificador
     * @param ordenPedidoId , identificador de la ordenPedido que se dea borrar
     */
     public void delete(Long ordenPedidoId) {
  LOGGER.log(Level.INFO, "Borrando el vendedor con id={0}", ordenPedidoId);
        // Se hace uso de mismo método que esta explicado en public AuthorEntity find(Long id) para obtener la author a borrar.
      OrdenPedidoEntity ordenPedidoEntity = em.find(OrdenPedidoEntity.class, ordenPedidoId);
       
        em.remove(ordenPedidoEntity);
     }
     
    /**
     * actualiza una ordenPedido
     * @param ordenPedidoEntity , entidad con la informacion nueva de la ordenPedido
     * @return  la ordenPedidoActualizada
     */
      public OrdenPedidoEntity update(OrdenPedidoEntity ordenPedidoEntity){
        LOGGER.log(Level.INFO, "Actualizando la orden pedido con id={0}", ordenPedidoEntity.getId() );
      
        return em.merge(ordenPedidoEntity);
          }
      
   
}
