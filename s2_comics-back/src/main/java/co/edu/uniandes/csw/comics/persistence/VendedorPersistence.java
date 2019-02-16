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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
