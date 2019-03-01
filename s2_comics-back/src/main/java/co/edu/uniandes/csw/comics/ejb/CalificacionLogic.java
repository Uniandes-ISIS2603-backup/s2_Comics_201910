/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.ejb;

import co.edu.uniandes.csw.comics.entities.CalificacionEntity;
import co.edu.uniandes.csw.comics.entities.VendedorEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.comics.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.comics.persistence.VendedorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ca.orduz
 */
@Stateless
public class CalificacionLogic {
     private static final Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());
    @Inject
    private CalificacionPersistence persistence;
    @Inject
    private VendedorPersistence vendedorPersistence;
    public CalificacionEntity createCalificacion(Long vendedorId,CalificacionEntity ce)throws BusinessLogicException{
        if(ce.getPuntuacion()==null){
            throw new BusinessLogicException("La calificación debe tener una puntuacion.");
        }
        VendedorEntity vendedor=vendedorPersistence.find(vendedorId);
        ce.setVendedor(vendedor);
        
        return persistence.create(ce);
        
    }
     public List<CalificacionEntity> getCalificaciones(Long vendedorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores");
       VendedorEntity vendedorEntity= vendedorPersistence.find(vendedorId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los autores");
        return vendedorEntity.getCalificaciones();
    }
        public CalificacionEntity getCalificacion(Long vendedoresId,Long calificacionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la calificacion con id = {0}", calificacionId);
        CalificacionEntity calificacionEntity = persistence.find(vendedoresId, calificacionId);
        if (calificacionEntity == null) {
            LOGGER.log(Level.SEVERE, "La calificacion con el id = {0} no existe", calificacionId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el autor con id = {0}", calificacionId);
        return calificacionEntity;
    }
           public CalificacionEntity updateCalificacion(Long vendedoresId, CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el autor con id = {0}", vendedoresId);
        VendedorEntity vendedorEntity = vendedorPersistence.find(vendedoresId);
        calificacionEntity.setVendedor(vendedorEntity);
        persistence.update(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el autor con id = {0}", vendedoresId);
        return calificacionEntity;
    }
           public void deleteCalificacion(Long vendedorId, Long calificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la calificacion con id = {0} del vendedor con id ="+vendedorId, calificacionId);
        CalificacionEntity old=getCalificacion(vendedorId, calificacionId);
        if(old==null){
             throw new BusinessLogicException("La calificacion con id = " + calificacionId + " no esta asociado a el vendedor con id = " + vendedorId);
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el autor con id = {0}", calificacionId);
    }
}
