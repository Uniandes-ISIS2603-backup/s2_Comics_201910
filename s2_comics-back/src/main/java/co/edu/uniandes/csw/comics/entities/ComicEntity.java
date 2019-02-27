/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Pietro Ehrlich
 */
@Entity
public class ComicEntity extends BaseEntity implements Serializable {
    
    public static enum TemaGlobal{
        AVENTURA_ACCION,
        ARTE_ILUSTRACION,
        COMEDIA,
        ENCICLOPEDIA_DOCUMENTAL,
        DRAMA,
        EROTIQUE,
        FANTASTICO,
        NOVELA_GRAFICA,
        HEROICO_FANTASIA_MAGIA,
        HISTORICO,
        HUMOR,
        AMOR_AMISTAD,
        POLAR_THRILLER,
        CIENCIA_FICCION,
        DEPORTE,
        VIEJO_OESTE
    }
    
    private String nombre;
    private String autor;
    private Integer anioSalida;
    private Boolean perteneceColeccion;
    private Boolean perteneceSerie;
    private Double precio;
    private TemaGlobal tema;
    private Boolean enVenta;
    
    @javax.persistence.ManyToOne(
    )
    private VendedorEntity vendedor;
    
    @javax.persistence.OneToMany(mappedBy = "comic", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ComicEntity> comicsTrueque;

    public ComicEntity(){
        
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
    public Integer getAnioSalida() {
        return anioSalida;
    }

    /**
     * @param anioSalida the anioSalida to set
     */
    public void setAnioSalida(Integer anioSalida) {
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
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * @return the tema
     */
    public TemaGlobal getTema() {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(TemaGlobal tema) {
        this.tema = tema;
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

    /**
     * @return the vendedor
     */
    public VendedorEntity getVendedor() {
        return vendedor;
    }

    /**
     * @param vendedor the vendedor to set
     */
    public void setVendedor(VendedorEntity vendedor) {
        this.vendedor = vendedor;
    }

    /**
     * @return the comicsTrueque
     */
    public List<ComicEntity> getComicsTrueque() {
        return comicsTrueque;
    }

    /**
     * @param comicsTrueque the comicsTrueque to set
     */
    public void setComicsTrueque(List<ComicEntity> comicsTrueque) {
        this.comicsTrueque = comicsTrueque;
    }

    
}
