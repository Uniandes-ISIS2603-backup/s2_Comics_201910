/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.dtos;

import co.edu.uniandes.csw.comics.entities.CalificacionEntity;
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
    
    private Long id;
     
    //CONSTTRUCTORES
    /**
     * cosntructor vacio
     */
    public CalificacionDTO() {
       
        
    }
    public CalificacionDTO(CalificacionEntity entity){
        if(entity!=null){
        this.puntuacion=entity.getPuntuacion();
        this.comentarios=entity.getComentarios();
        this.id=entity.getId();
        }
    }
    /**
     * constructor con valores
     * @param pComentarios
     * @param pPuntuacion
     */
    /**public CalificacionDTO(String pComentarios,Double pPuntuacion){
        comentarios = pComentarios;
        puntuacion= pPuntuacion;
    }*/
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
   public CalificacionEntity toEntity(){
       CalificacionEntity entity=new CalificacionEntity();
       entity.setPuntuacion(puntuacion);
       entity.setComentarios(comentarios);
       entity.setId(id);
       return entity;
   }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}