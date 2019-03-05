/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.ejb;

import co.edu.uniandes.csw.comics.entities.ComicEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.comics.persistence.ComicPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author pietro
 */
@Stateless
public class ComicLogic {
   // private final static Logger LOGGER = Logger.getLogger(VendedorDTO.class.getName());

    
    @Inject
    private ComicPersistence persistence;
    
    public ComicEntity createComic(ComicEntity comic){
       // LOGGER.log(Level.INFO, "VendedorResource createVendedor: input: {0}", vendedor);
        persistence.create(comic);
        return comic;
    }

    public ComicEntity getComic(long id) {
        ComicEntity ans = persistence.find(id);
        if(ans == null)
            throw new WebApplicationException("no existe el comic");
        return ans;
    }

    public List<ComicEntity> getComics() {
        return persistence.findAll();
    }

    public void deleteComic(long id) {
        if(persistence.find(id) == null)
            throw new WebApplicationException("no existe el comic");
        persistence.delete(id);
    }

    public ComicEntity updateComic(ComicEntity comic) throws BusinessLogicException{
        if(comic.getId() == null)
            throw new BusinessLogicException("el comic no tiene id");
        if(persistence.find(comic.getId()) == null)
            throw new WebApplicationException("no existe el comic");
        return persistence.update(comic);
    }
}
