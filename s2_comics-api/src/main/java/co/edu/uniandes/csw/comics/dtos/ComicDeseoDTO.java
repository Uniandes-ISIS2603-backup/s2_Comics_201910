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
     * @return the fechaAgregado
     */
    public SimpleDateFormat getFechaAgregado() {
        return fechaAgregado;
    }

    /**
     * @param fechaAgregado the fechaAgregado to set
     */
    public void setFechaAgregado(SimpleDateFormat fechaAgregado) {
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
 
   private SimpleDateFormat fechaAgregado;
   private ComicDTO comic;
    
    
    public ComicDeseoDTO (){
    
    }
   public ComicDeseoDTO (ComicDeseoEntity entity){
       
        
        
        
       this.fechaAgregado = entity.getFechaAgregado();
         if(entity.getComic() != null){
     
             this.comic = new ComicDTO(entity.getComic());
             
        }else{
             
             this.comic = null;
         }
       
   }
   
   public ComicDeseoEntity toEntity (){
   
       ComicDeseoEntity comicD = new ComicDeseoEntity();
       
       comicD.setFechaAgregado(this.fechaAgregado);
      
       if(this.comic != null){
       
           comicD.setComic(this.comic.toEntity());
       }
       return comicD;
       
       
   }
   
    
    
  
   
   
}
