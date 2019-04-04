/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.dtos;

import co.edu.uniandes.csw.comics.entities.VendedorEntity;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author estudiante
 */
public class VendedorDTO extends ColeccionistaDTO implements Serializable
{ /**
     * Constructor vacio
     */
    public VendedorDTO()
    {
        
    }
     /**
     * Crea un objeto VendedorDTO a partir de un objeto VendedorEntity.
     *
     * @param entity Entidad VendedorEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
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
            password=entity.getPassword();
            
        }
    }
     /**
     * Convierte un objeto VendedorDTO a VendedorEntity.
     *
     * @return Nueva objeto VendedorEntity.
     *
     */
    public VendedorEntity toEntity()
    {
        Logger LOGGER=Logger.getLogger(VendedorDTO.class.getName());
        VendedorEntity entity = new VendedorEntity();
        LOGGER.log(Level.INFO, "CreatedVendedorEntity");
        entity.setAlias(alias);
        entity.setNombre(nombre);
        entity.setFoto(foto);
        entity.setIntereses(intereses);
        entity.setEmail(correoElectronico);
        entity.setPassword(password);
        entity.setId(id);
        LOGGER.log(Level.INFO, "Finished CreatedVendedorEntity");
        return entity;
    }
}
