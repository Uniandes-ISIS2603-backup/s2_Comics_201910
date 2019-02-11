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
        comentarios="";
        puntuacion= 0.0;
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
   * @return  los comentarios
   */
    public String getComentarios(){
        return comentarios;
    }
    
   /**
   * @set actualiza los comentarios
   * @param pComentarios los nuevos comentarios
   */
    public void setComentarios(String pComentarios){
        comentarios=pComentarios;
    }
    
    /**
   * @return  la puntuacion
   */
    public Double getPuntuacion(){
        return puntuacion;
    }
    
   /**
   * @set actualiza l puntuacion
   * @param pPuntuacion los nuevos comentarios
   */
    public void setPuntuacion(Double pPuntuacion){
        puntuacion=pPuntuacion;
    }
    
}
