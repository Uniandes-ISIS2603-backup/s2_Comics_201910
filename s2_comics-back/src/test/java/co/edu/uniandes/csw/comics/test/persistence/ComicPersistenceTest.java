/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.persistence;

import co.edu.uniandes.csw.comics.entities.ComicEntity;
import co.edu.uniandes.csw.comics.persistence.ComicPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Pietro Ehrlich
 */

@RunWith(Arquillian.class)
public class ComicPersistenceTest {
    
    @Inject
    private ComicPersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    public List<ComicEntity> data = new ArrayList<ComicEntity>();
   
    @Deployment
    public static JavaArchive createDeployment (){
        return ShrinkWrap.create(JavaArchive.class)
               .addPackage(ComicEntity.class.getPackage())
               .addPackage(ComicPersistence.class.getPackage())
               .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
               .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Test
    public void createComicTest(){
        PodamFactory factory = new PodamFactoryImpl();
        ComicEntity newComicEntity = factory.manufacturePojo(ComicEntity.class);
        ComicEntity ce = cp.create(newComicEntity);
        
        Assert.assertNotNull(ce);
        
        ComicEntity entity = em.find(ComicEntity.class,ce.getId());
        Assert.assertEquals(newComicEntity.getNombre() , entity.getNombre());
    }
    
    @Before
    public void configTest()
    {
        try
        {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            try
            {
                utx.rollback();
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
            }
        }
    }
    
    private void clearData()
    {
        em.createQuery("delete from ComicEntity").executeUpdate();
    }
    
    private void insertData()
    {
        PodamFactory podam = new PodamFactoryImpl();
        
        for(int i = 0; i < 3; i++)
        {
            ComicEntity entity = podam.manufacturePojo(ComicEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void findTest(){
        ComicEntity comic = data.get(0);
        ComicEntity entity = cp.find(comic.getId());
        
        Assert.assertNotNull(entity);
        Assert.assertEquals(entity.getId(), comic.getId());
        
        entity = cp.find(new Long(-1));
        Assert.assertNull(entity);
    }
    
    @Test
    public void findAllTest(){
        List<ComicEntity> comics = cp.findAll();
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
    public void deleteTest(){
        ComicEntity com = data.get(0);
        cp.delete(com.getId());
        Assert.assertNull(cp.find(com.getId()));
    }
    
    @Test
    public void updateTest(){
        ComicEntity com = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
       ComicEntity newEntity = factory.manufacturePojo(ComicEntity.class);
        newEntity.setId(com.getId());
        cp.update(newEntity);

        Assert.assertEquals(newEntity.getNombre(), cp.find(com.getId()).getNombre());
    }
}