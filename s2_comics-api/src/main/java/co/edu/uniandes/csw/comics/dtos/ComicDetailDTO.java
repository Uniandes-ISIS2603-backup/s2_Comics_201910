/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.dtos;

import co.edu.uniandes.csw.comics.entities.ComicEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class ComicDetailDTO extends ComicDTO implements Serializable {
    
    private List<ComicDTO> comicsTrueque;
    
    public ComicDetailDTO (){
        super();
    }
    
    public ComicDetailDTO(ComicEntity comicEntity){
        super(comicEntity);
        if(comicEntity != null && comicEntity.getComicsTrueque() != null){
            comicsTrueque = new ArrayList<>();
            for(ComicEntity en : comicEntity.getComicsTrueque())
                comicsTrueque.add(new ComicDTO(en));
        }
    }
    
    public ComicEntity toEntity(){
        ComicEntity ans = super.toEntity();
        if(comicsTrueque != null){
            List<ComicEntity> cTrueque = new ArrayList<>();
            for(ComicDTO dto : comicsTrueque)
                cTrueque.add(dto.toEntity());
        }
        return ans;
    }

    /**
     * @return the comicsTrueque
     */
    public List<ComicDTO> getComicsTrueque() {
        return comicsTrueque;
    }

    /**
     * @param comicsTrueque the comicsTrueque to set
     */
    public void setComicsTrueque(List<ComicDTO> comicsTrueque) {
        this.comicsTrueque = comicsTrueque;
    }
}
