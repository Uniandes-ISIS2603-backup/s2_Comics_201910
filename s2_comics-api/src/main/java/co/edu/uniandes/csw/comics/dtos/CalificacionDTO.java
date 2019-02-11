/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.dtos;

import java.io.Serializable;
/**
 *
 * @author estudiante
 */
public class CalificacionDTO implements Serializable {
    
    //ATRUBUTOS
    
 /**
 *comentarios que realiza el comprador al vendedor por
 * la calidad de la transaccion
 */
    private String comentarios;
    
  /**
   * calificacion otorgada al vendedor
   */
    private Double puntuacion;
     
    //CONSTTRUCTORES
    /**
     * cosntructor vacio
     */
    public CalificacionDTO() {
       
        
    }
    /**
     * constructor con valores
     * @param pComentarios
     * @param pPuntuacion
     */
    public CalificacionDTO(String pComentarios,Double pPuntuacion){
        comentarios = pComentarios;
        puntuacion= pPuntuacion;
    }
    //METODOS

    /**
     * @return the comentarios
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios the comentarios to set
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * @return the puntuacion
     */
    public Double getPuntuacion() {
        return puntuacion;
    }

    /**
     * @param puntuacion the puntuacion to set
     */
    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }
   
}