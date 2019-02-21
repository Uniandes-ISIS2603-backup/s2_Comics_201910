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
            alias = entity.getAlias();
            correoElectronico = entity.getEmail();
            
        }
    }
    
    public VendedorEntity toEntity()
    {
        VendedorEntity entity = new VendedorEntity();
        entity.setAlias(alias);
        entity.setEmail(correoElectronico);
        return entity;
    }
}
