/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.persistence;

import co.edu.uniandes.csw.comics.entities.VendedorEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author estudiante
 */
public class VendedorPersistence
{
    private final static Logger LOGGER = Logger.getLogger(VendedorPersistence.class.getName());
    
    @PersistenceContext(unitName="comicsPU")
    protected EntityManager em;
    
    
    public VendedorPersistence()
    {
        
    }
    
    public VendedorEntity create(VendedorEntity nuevo)
    {
        LOGGER.log(Level.INFO, "Creando una clase nueva");
        
        em.persist(nuevo);
        
        return nuevo;
    }
    
    
}
