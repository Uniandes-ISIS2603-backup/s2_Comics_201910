/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Sebastian Baquero
 */


public class ComicDeseoDTO implements Serializable {
 
    private Date fechaAgregado;
    
    
    public ComicDeseoDTO (){
    
    }

    /**
     * @return the fechaAgregado
     */
    public Date getFechaAgregado() {
        return fechaAgregado;
    }

    /**
     * @param fechaAgregado the fechaAgregado to set
     */
    public void setFechaAgregado(Date fechaAgregado) {
        this.fechaAgregado = fechaAgregado;
    }
    
}
