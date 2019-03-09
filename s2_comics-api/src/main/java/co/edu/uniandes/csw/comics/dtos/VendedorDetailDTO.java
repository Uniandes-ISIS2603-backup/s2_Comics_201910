/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.dtos;

import co.edu.uniandes.csw.comics.entities.CalificacionEntity;
import co.edu.uniandes.csw.comics.entities.ComicEntity;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.entities.VendedorEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class VendedorDetailDTO extends VendedorDTO implements Serializable
{
    private List<ComicDTO> comics;
    private List<CalificacionDTO> calificaciones;
    private List<OrdenPedidoDTO> ordenes;
    
    public VendedorDetailDTO(){
    super();
    
    }
    public VendedorDetailDTO(VendedorEntity vendedorEntity){
        super(vendedorEntity);
        if(vendedorEntity!=null){
            comics=new ArrayList<>();
            for(ComicEntity entityComics:vendedorEntity.getComics()){
                comics.add(new ComicDTO(entityComics));
            }
            calificaciones=new ArrayList<>();
            for(CalificacionEntity entityCalificacion:vendedorEntity.getCalificaciones()){
                calificaciones.add(new CalificacionDTO(entityCalificacion));
            }
             ordenes=new ArrayList<>();
            for(OrdenPedidoEntity entityOrdenes:vendedorEntity.getOrdenes()){
                ordenes.add(new OrdenPedidoDTO(entityOrdenes));
            }
        }
    }
    @Override
    public VendedorEntity toEntity(){
        VendedorEntity vendedorEntity=super.toEntity();
        if(getComics()!=null){
            List<ComicEntity> comicsEntity=new ArrayList<>();
            for(ComicDTO dtoComic:getComics()){
                comicsEntity.add(dtoComic.toEntity());
            }
            vendedorEntity.setComics(comicsEntity);
        }
        if(getCalificaciones()!=null){
            List<CalificacionEntity> calificacionesEntity=new ArrayList<>();
            for(CalificacionDTO dtoCalificacion: getCalificaciones()){
                calificacionesEntity.add(dtoCalificacion.toEntity());
            }
            vendedorEntity.setCalificaciones(calificacionesEntity);
        }
         if(getOrdenes()!=null){
            List<OrdenPedidoEntity> ordenesEntity=new ArrayList<>();
            for(OrdenPedidoDTO dtoOrden: getOrdenes()){
                ordenesEntity.add(dtoOrden.toEntity());
            }
            vendedorEntity.setOrdenes(ordenesEntity);
        }
        return vendedorEntity;
    }

    /**
     * @return the comics
     */
    public List<ComicDTO> getComics() {
        return comics;
    }

    /**
     * @param comics the comics to set
     */
    public void setComics(List<ComicDTO> comics) {
        this.comics = comics;
    }

    /**
     * @return the calificaciones
     */
    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    /**
     * @param calificaciones the calificaciones to set
     */
    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }

    /**
     * @return the ordenes
     */
    public List<OrdenPedidoDTO> getOrdenes() {
        return ordenes;
    }

    /**
     * @param ordenes the ordenes to set
     */
    public void setOrdenes(List<OrdenPedidoDTO> ordenes) {
        this.ordenes = ordenes;
    }
}
