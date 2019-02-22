/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.ejb;

import co.edu.uniandes.csw.comics.entities.CalificacionEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.comics.persistence.CalificacionPersistence;
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
    public CalificacionEntity createCalificacion(CalificacionEntity ce)throws BusinessLogicException{
        if(ce.getPuntuacion()==null){
            throw new BusinessLogicException("La calificación debe tener una puntuacion.");
        }
       ce= persistence.create(ce);
        return ce;
        
    }
     public List<CalificacionEntity> getCalificaciones() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los autores");
        List<CalificacionEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los autores");
        return lista;
    }
        public CalificacionEntity getCalificacion(Long calificacionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la calificacion con id = {0}", calificacionId);
        CalificacionEntity calificacionEntity = persistence.find(calificacionId);
        if (calificacionEntity == null) {
            LOGGER.log(Level.SEVERE, "La calificacion con el id = {0} no existe", calificacionId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el autor con id = {0}", calificacionId);
        return calificacionEntity;
    }
           public CalificacionEntity updateCalificacion(Long calificacionId, CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el autor con id = {0}", calificacionId);
        CalificacionEntity newCalificacionEntity = persistence.update(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el autor con id = {0}", calificacionId);
        return newCalificacionEntity;
    }
           public void deleteCalificacion(Long calificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el autor con id = {0}", calificacionId);
     
        persistence.delete(calificacionId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el autor con id = {0}", calificacionId);
    }
}
