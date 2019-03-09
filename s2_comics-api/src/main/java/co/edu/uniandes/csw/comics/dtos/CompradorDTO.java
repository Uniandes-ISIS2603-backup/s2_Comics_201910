/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.dtos;

import co.edu.uniandes.csw.comics.entities.CompradorEntity;
import java.io.Serializable;

/**
 *  CompradorDTO Objeto de transferencia de datos de Compradores. Los DTO contienen las
 * representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 * @author Juan Pablo
 */

public class CompradorDTO extends ColeccionistaDTO implements Serializable
{
    /**
     * Constructor vacio
     */
    public CompradorDTO()
    {
        
    }    
    
    /**
     * Crea un objeto CompradorDTO a partir de un objeto CompradorEntity.
     *
     * @param CompradorEntity Entidad CompradorEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public CompradorDTO(CompradorEntity entity)
    {
        if(entity != null)
        {
            id = entity.getId();
            alias = entity.getAlias();
            correoElectronico = entity.getEmail();
            intereses = entity.getIntereses();
            nombre = entity.getNombre();
            foto = entity.getFoto();
        }
    }
    
    /**
     * Convierte un objeto CompradorDTO a CompradorEntity.
     *
     * @return Nueva objeto CompradorEntity.
     *
     */
    public CompradorEntity toEntity()
    {
        CompradorEntity entity = new CompradorEntity();
        entity.setId(id);
        entity.setAlias(alias);
        entity.setEmail(correoElectronico);
        entity.setIntereses(intereses);
        entity.setNombre(nombre);
        entity.setFoto(foto);
        return entity;
    }
}
