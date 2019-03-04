/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.resources;

import co.edu.uniandes.csw.comics.dtos.CompradorDTO;
import co.edu.uniandes.csw.comics.dtos.CompradorDetailDTO;
import co.edu.uniandes.csw.comics.ejb.*;
import co.edu.uniandes.csw.comics.entities.CompradorEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.*;
import javax.ws.rs.Produces;
import java.util.*;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author juan pablo cano
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompradorComicsResource 
{
    private final static Logger LOGGER = Logger.getLogger(CompradorComicsResource.class.getName());
    
    @Inject 
    private CompradorComicLogic compradorLogic;
    
}
