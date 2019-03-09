/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.logic;

import co.edu.uniandes.csw.comics.ejb.ComicLogic;
import co.edu.uniandes.csw.comics.entities.ComicEntity;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Pietro
 */
@RunWith(Arquillian.class)
public class ComicLogicTest {
    
    @Inject
    private ComicLogic comicLogic;
    
    @PersistenceContext 
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<ComicEntity> data = new ArrayList<>();
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Deployment
    public static JavaArchive createDeployment (){
        return ShrinkWrap.create(JavaArchive.class)
               .addPackage(ComicEntity.class.getPackage())
               .addPackage(ComicLogic.class.getPackage())
               .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
               .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    private void clearData() {   
        em.createQuery("delete from ComicEntity").executeUpdate();
    }
         
    private void insertData() {
        for (int i = 0; i < 3; i++) {
           ComicEntity entity = factory.manufacturePojo(ComicEntity.class);
            em.persist(entity);
           
            data.add(entity);
        }
    }
    
    @Test
    public void createComicTest(){
        ComicEntity newCom = factory.manufacturePojo(ComicEntity.class);
        ComicEntity ans = comicLogic.createComic(newCom);
        
        Assert.assertNotNull(ans);
        Assert.assertEquals(newCom.getId() , ans.getId());
    }
    
    @Test
    public void getComicTest(){
        Long id = data.get(0).getId();
        ComicEntity res = comicLogic.getComic(id);
        
        Assert.assertNotNull(res);
        Assert.assertEquals(id , res.getId());
    }
    
    @Test
    public void getComicsTest(){
        List<ComicEntity> comics = comicLogic.getComics();
        Assert.assertEquals(comics.size() , data.size());
        boolean found = true;
        for(ComicEntity com : comics){
            boolean search = false;
            for(ComicEntity com2 : data)
                search |= (com.getId().equals(com2.getId()));
            found &= search;
        }
        Assert.assertTrue(found);
    }
    
    
    @Test
    public void deleteComicTest(){
        Long id = data.get(0).getId();
        comicLogic.deleteComic(id);
        
        try{
            comicLogic.getComic(id);
            Assert.assertTrue(false);
        }catch(Exception e){}
    }
    
    @Test
    public void updateComicTest(){
        ComicEntity com = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ComicEntity newEntity = factory.manufacturePojo(ComicEntity.class);
        newEntity.setId(com.getId());
        try{
            comicLogic.updateComic(newEntity);
        }catch(Exception e){
            Assert.assertTrue(false);
        }
        
        Assert.assertEquals(newEntity.getNombre(), comicLogic.findComic(com.getId()).getNombre());
    }


}
