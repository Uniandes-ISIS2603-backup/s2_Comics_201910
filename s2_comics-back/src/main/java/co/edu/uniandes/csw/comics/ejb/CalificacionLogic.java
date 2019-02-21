/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.ejb;

import co.edu.uniandes.csw.comics.entities.CalificacionEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.comics.persistence.CalificacionPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ca.orduz
 */
@Stateless
public class CalificacionLogic {
    @Inject
    private CalificacionPersistence persistence;
    public CalificacionEntity createCalificacion(CalificacionEntity ce)throws BusinessLogicException{
        if(ce.getPuntuacion()==null){
            throw new BusinessLogicException("La calificación debe tener una puntuacion.");
        }
       ce= persistence.create(ce);
        return ce;
        
    }
}
