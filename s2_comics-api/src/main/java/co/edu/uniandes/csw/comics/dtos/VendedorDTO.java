/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.dtos;

import co.edu.uniandes.csw.comics.entities.VendedorEntity;
import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class VendedorDTO extends ColeccionistaDTO implements Serializable
{
    public VendedorDTO()
    {
        
    }
    
    public VendedorDTO(VendedorEntity entity)
    {
        if(entity != null)
        {
            nombre=entity.getNombre();
            foto=entity.getFoto();
            intereses=entity.getIntereses();
            alias = entity.getAlias();
            correoElectronico = entity.getEmail();
            id=entity.getId();
            
        }
    }
    
    public VendedorEntity toEntity()
    {
        VendedorEntity entity = new VendedorEntity();
        entity.setAlias(alias);
        entity.setNombre(nombre);
        entity.setFoto(foto);
        entity.setIntereses(intereses);
        entity.setEmail(correoElectronico);
        entity.setId(id);
        return entity;
    }
}
