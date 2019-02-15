/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.persistence;

import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


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
        LOGGER.log(Level.INFO, "Creando una clase nueva");
        
        em.persist(nuevo);
        
        return nuevo;
    }
}
