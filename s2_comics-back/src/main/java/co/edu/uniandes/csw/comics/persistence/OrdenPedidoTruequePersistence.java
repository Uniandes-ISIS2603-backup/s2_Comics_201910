/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.persistence;

import co.edu.uniandes.csw.comics.entities.OrdenPedidoTruequeEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author estudiante
 */
@Stateless
public class OrdenPedidoTruequePersistence {
  @PersistenceContext(unitName = "comicsPU")
    protected EntityManager em;
    
  private final static Logger LOGGER = Logger.getLogger(VendedorPersistence.class.getName());
    
    public OrdenPedidoTruequePersistence(){
        
    }
public OrdenPedidoTruequeEntity create(OrdenPedidoTruequeEntity nuevo)
    {
        LOGGER.log(Level.INFO, "Creando una clase nueva");
        
        em.persist(nuevo);
        
        return nuevo;
    }
}
