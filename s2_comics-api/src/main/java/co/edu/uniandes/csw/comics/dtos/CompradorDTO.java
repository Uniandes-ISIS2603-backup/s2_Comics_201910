/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.dtos;

import co.edu.uniandes.csw.comics.entities.CompradorEntity;
import java.io.Serializable;

/**
 *
 * @author Juan Pablo
 */

public class CompradorDTO extends ColeccionistaDTO implements Serializable
{
    
    public CompradorDTO()
    {
        
    }    
    
    public CompradorDTO(CompradorEntity entity)
    {
        if(entity != null)
        {
            alias = entity.getAlias();
            correoElectronico = entity.getEmail();
            intereses = entity.getIntereses();
            nombre = entity.getNombre();
            foto = entity.getFoto();
        }
    }
    
    public CompradorEntity toEntity()
    {
        CompradorEntity entity = new CompradorEntity();
        entity.setAlias(alias);
        entity.setEmail(correoElectronico);
        entity.setIntereses(intereses);
        entity.setNombre(nombre);
        entity.setFoto(foto);
        return entity;
    }
}
