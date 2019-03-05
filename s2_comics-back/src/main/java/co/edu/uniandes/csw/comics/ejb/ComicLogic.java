/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.ejb;

import co.edu.uniandes.csw.comics.entities.ComicEntity;
import co.edu.uniandes.csw.comics.persistence.ComicPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author pietro
 */
@Stateless
public class ComicLogic {
    
    @Inject
    private ComicPersistence persistence;
    
    public ComicEntity createComic(ComicEntity comic)
    {
        
        persistence.create(comic);
        return comic;
    }

    public ComicEntity getComic(long id) {
        return null;
    }
}
