/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.ejb;

import co.edu.uniandes.csw.comics.entities.VendedorEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.comics.persistence.VendedorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author ca.orduz
 */
public class VendedorLogic {
     private static final Logger LOGGER = Logger.getLogger(VendedorLogic.class.getName());

    @Inject
    private VendedorPersistence persistence;
    
     public VendedorEntity createVendedor(VendedorEntity vendedorEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del vendedor");
       
        if (persistence.findByAlias(vendedorEntity.getAlias()) != null) {
            throw new BusinessLogicException("Ya existe un vendedor con el alias " + vendedorEntity.getAlias() + "\"");
        }
        // Invoca la persistencia para crear la editorial
        persistence.create(vendedorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del vendedor");
        return vendedorEntity;
     }
         public List<VendedorEntity> getVendedores() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los vendedores");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<VendedorEntity> vendedores = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los vendedores");
        return vendedores;
    }
         public VendedorEntity getVendedor(String vendedorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el vendedor con id = {0}", vendedorId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        VendedorEntity vendedorEntity = persistence.findByAlias(vendedorId);
        if (vendedorEntity.equals(null)) {
            LOGGER.log(Level.SEVERE, "El vendedor con el id = {0} no existe", vendedorId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el vendedor con id = {0}", vendedorId);
        return vendedorEntity;
    }
          public VendedorEntity updateVendedor(String vendedorId, VendedorEntity vendedorEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el vendedor con id = {0}", vendedorId);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        VendedorEntity newEntity = persistence.update(vendedorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la editorial con id = {0}", vendedorEntity.getId());
        return newEntity;
    }
             public void deleteVendedor(Long vendedorId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el vendedor con id = {0}", vendedorId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
       
        persistence.delete(vendedorId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la editorial con id = {0}", vendedorId);
    }
}