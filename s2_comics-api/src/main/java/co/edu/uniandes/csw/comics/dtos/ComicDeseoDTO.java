/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.dtos;

import co.edu.uniandes.csw.comics.entities.ComicDeseoEntity;
import co.edu.uniandes.csw.comics.entities.ComicEntity;
import java.io.Serializable;
import java.text.SimpleDateFormat;


/**
 *
 * @author Sebastian Baquero
 */


public class ComicDeseoDTO implements Serializable {

   
 
    /**
     * Modela la fecha de agregado del comic al la lista de comic deoseo
     */
   
   private String fechaAgregado;
   
   /**
     * Modela el comic asociado al comic deseo
     */
   private ComicDTO comic;
   
   /**
    * Modela el comprador del comic deseo
    */
   
   private CompradorDTO comprador;
    
    /**
     * Constructor vacio
     */
    public ComicDeseoDTO (){
    
    }
    
    /**
     * Constructor convierte entity to DTO
     * @param entity comic deoseo
     * @return ComicDeseoDTO
     */
   public ComicDeseoDTO (ComicDeseoEntity entity){
       
        
        
        
       this.fechaAgregado = entity.getFechaAgregado();
        this.comprador = new CompradorDTO(entity.getComprador()) ;
         if(entity.getComic() != null){
     
            comic = new ComicDTO(entity.getComic());
            fechaAgregado = entity.getFechaAgregado();
            comprador = new CompradorDTO(entity.getComprador());
            
             
        }
       
   }
   /**
    * Convierte DTO to entity
    * @return 
    */
   
   public ComicDeseoEntity toEntity (){
   
       ComicDeseoEntity comicD = new ComicDeseoEntity();
       
       comicD.setFechaAgregado(this.fechaAgregado);
      if(comprador != null){
      comicD.setComprador(comprador.toEntity());
      }
      
       if(comic !=null){
           comicD.setComic(comic.toEntity());
       }
       
       return comicD;
       
       
   }
   
    
     /**
     * @return the fechaAgregado
     */
    public String getFechaAgregado() {
        return fechaAgregado;
    }

     
    /**
     * @param fechaAgregado the fechaAgregado to set
     */
    public void setFechaAgregado(String fechaAgregado) {
        this.fechaAgregado = fechaAgregado;
    }
    
    

    /**
     * @return the comic
     */
    public ComicDTO getComic() {
        return comic;
    }

    /**
     * @param comic the comic to set
     */
    public void setComic(ComicDTO comic) {
        this.comic = comic;
    }

    /**
     * @return the comprador
     */
    public CompradorDTO getComprador() {
        return comprador;
    }

    /**
     * @param comprador the comprador to set
     */
    public void setComprador(CompradorDTO comprador) {
        this.comprador = comprador;
    }
  
   
   
}
