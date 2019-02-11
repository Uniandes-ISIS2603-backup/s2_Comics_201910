/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Entity

/**
 *
 * @author Sebastian Baquero
 */
public class ComicEntity extends BaseEntity implements Serializable {
    
    private String nombre;
    private String autor;
    private Date anioSalida;
    private Boolean perteneceColeccion;
    private Boolean perteneceSerie;
    private double precio;
    private Enum temaGlobal;
    private List listaDeComicsTrueque;
    private Boolean enVenta;
    
    
    public ComicEntity (){
    
    }
  @PersistenceContext(unitName = "ComicPU")
    public ComicEntity create (ComicEntity pEntidad){
     
       EntityManager em;
       em.persist(pEntidad);
       
        return pEntidad;
    }
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @return the anioSalida
     */
    public Date getAnioSalida() {
        return anioSalida;
    }

    /**
     * @param anioSalida the anioSalida to set
     */
    public void setAnioSalida(Date anioSalida) {
        this.anioSalida = anioSalida;
    }

    /**
     * @return the perteneceColeccion
     */
    public Boolean getPerteneceColeccion() {
        return perteneceColeccion;
    }

    /**
     * @param perteneceColeccion the perteneceColeccion to set
     */
    public void setPerteneceColeccion(Boolean perteneceColeccion) {
        this.perteneceColeccion = perteneceColeccion;
    }

    /**
     * @return the perteneceSerie
     */
    public Boolean getPerteneceSerie() {
        return perteneceSerie;
    }

    /**
     * @param perteneceSerie the perteneceSerie to set
     */
    public void setPerteneceSerie(Boolean perteneceSerie) {
        this.perteneceSerie = perteneceSerie;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the temaGlobal
     */
    public Enum getTemaGlobal() {
        return temaGlobal;
    }

    /**
     * @param temaGlobal the temaGlobal to set
     */
    public void setTemaGlobal(Enum temaGlobal) {
        this.temaGlobal = temaGlobal;
    }

    /**
     * @return the listaDeComicsTrueque
     */
    public List getListaDeComicsTrueque() {
        return listaDeComicsTrueque;
    }

    /**
     * @param listaDeComicsTrueque the listaDeComicsTrueque to set
     */
    public void setListaDeComicsTrueque(List listaDeComicsTrueque) {
        this.listaDeComicsTrueque = listaDeComicsTrueque;
    }

    /**
     * @return the enVenta
     */
    public Boolean getEnVenta() {
        return enVenta;
    }

    /**
     * @param enVenta the enVenta to set
     */
    public void setEnVenta(Boolean enVenta) {
        this.enVenta = enVenta;
    }
}
