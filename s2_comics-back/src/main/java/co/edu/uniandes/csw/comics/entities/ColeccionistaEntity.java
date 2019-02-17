/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.entities;

/**
 *
 * @author estudiante
 */
public class ColeccionistaEntity extends BaseEntity implements java.io.Serializable
{
    private String alias;
    private String correoElectronico;
    private String intereses;
    private String nombre;
    private String foto;
    
    public String getAlias()
    {
        return alias;
    }
    
    public String getAliasCapital()
    {
        return alias.toUpperCase();
    }
    
    public void setAlias(String alias)
    {
        this.alias = alias;
    }

    /**
     * @return the correoElectronico
     */
    public String getCorreoElectronico() 
    {
        return correoElectronico;
    }

    /**
     * @param correoElectronico the correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) 
    {
        this.correoElectronico = correoElectronico;
    }

    /**
     * @return the intereses
     */
    public String getIntereses()
    {
        return intereses;
    }

    /**
     * @param intereses the intereses to set
     */
    public void setIntereses(String intereses)
    {
        this.intereses = intereses;
    }

    /**
     * @return the nombre
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    /**
     * @return the foto
     */
    public String getFoto()
    {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(String foto)
    {
        this.foto = foto;
    }
}
