/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.dtos;

import co.edu.uniandes.csw.comics.entities.ComicEntity;
import java.io.Serializable;

public class ComicDTO implements Serializable {
    
    private Long id;
    private String nombre;
    private String autor;
    private Integer anioSalida;
    private Boolean perteneceColeccion;
    private Boolean perteneceSerie;
    private Double precio;
    private ComicEntity.TemaGlobal tema;
    private Boolean enVenta;
    private VendedorDTO vendedor;
    private String informacion;

    public ComicDTO(){
        
    }
    
    public ComicDTO(ComicEntity ent){
        if(ent != null){
            id = ent.getId();
            nombre = ent.getNombre();
            autor = ent.getAutor();
            anioSalida = ent.getAnioSalida();
            perteneceColeccion = ent.getPerteneceColeccion();
            perteneceSerie = ent.getPerteneceSerie();
            precio = ent.getPrecio();
            tema = ent.getTema();
            enVenta = ent.getEnVenta();
            informacion = ent.getInformacion();
            vendedor = new VendedorDTO(ent.getVendedor());
        }
    }
    
    public ComicEntity toEntity(){
        ComicEntity ent = new ComicEntity();
        ent.setId(getId());
        ent.setNombre(nombre);
        ent.setAutor(autor);
        ent.setAnioSalida(anioSalida);
        ent.setPerteneceColeccion(perteneceColeccion);
        ent.setPerteneceSerie(perteneceSerie);
        ent.setPrecio(precio);
        ent.setTema(tema);
        ent.setEnVenta(enVenta);
        ent.setInformacion(informacion);
        if(vendedor != null)
            ent.setVendedor(vendedor.toEntity());
        return ent;
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
    public ComicEntity.TemaGlobal getTema() {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(ComicEntity.TemaGlobal tema) {
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
    public VendedorDTO getVendedor() {
        return vendedor;
    }

    /**
     * @param vendedor the vendedor to set
     */
    public void setVendedor(VendedorDTO vendedor) {
        this.vendedor = vendedor;
    }

    /**
     * @return the informacion
     */
    public String getInformacion() {
        return informacion;
    }

    /**
     * @param informacion the informacion to set
     */
    public void setInformacion(String informacion) {
        this.informacion = informacion;
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
