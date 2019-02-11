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
public class OrdenPedidoTruequeDTO extends OrdenPedidoDTO implements Serializable

{
    //ATRIBUTOS
    /**
     * comic a intercambiar (comprador)
     */
 //   private ComicDTO comicTrueque;
     /**
      * comic del vendedor 
      */
     //public ComicDTO comic;
     
    //CONSTRUCTORES
   /**
    * constructor 
    * @param pVendedor
     * @param pComprador
     * @param pNumero
     * @param pComic
     * @param pComicTrueque
    */
    public OrdenPedidoTruequeDTO(VendedorDTO pVendedor, CompradorDTO pComprador, Integer pNumero, ComicDTO pComicTrueque, ComicDTO pComic){
        super( pVendedor,pComprador,pNumero);
       // comicTrueque= pComicTrueque;
        //comic =pComic;
    }
    
    public OrdenPedidoTruequeDTO(){
        
    }
    //METODOS
    
    /**
     *@return Comic
     */
  //  public ComicDTO getComic(){
    //    return comic;
    //}
    /**
     * @set actualiza comic
     * @param pComic , nuevo comic
     */
    public void setComic(ComicDTO pComic){
  //      comic=pComic;
    }
    
    /**
     *@return ComicTrueque
     */
   // public ComicDTO getComicTrueque(){
  //      return comicTrueque;
   // }
    /**
     * @set actualiza comicTrueque
     * @param pComicTrueque , nuevo comicTrueque
     */
<<<<<<< HEAD
   public void setComicTrueque(ComicDTO pComicTrueque){
        comicTrueque=pComicTrueque;
=======
    public void setComicTrueque(ComicDTO pComicTrueque){
 //     comicTrueque=pComicTrueque;
>>>>>>> 23c9fd0fc5ce8b61463ea58d9b08f813b44a3d23
    }
   
}
