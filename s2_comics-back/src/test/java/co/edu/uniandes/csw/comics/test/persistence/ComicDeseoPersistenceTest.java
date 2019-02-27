/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.persistence;

import co.edu.uniandes.csw.comics.entities.ComicDeseoEntity;
import co.edu.uniandes.csw.comics.entities.CompradorEntity;
import co.edu.uniandes.csw.comics.persistence.ComicDeseoPersistence;
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
 * @author Sebastian Baquero
 */
@RunWith(Arquillian.class)
public class ComicDeseoPersistenceTest {
    
    @Inject
    private ComicDeseoPersistence comicPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<ComicDeseoEntity> data = new ArrayList<ComicDeseoEntity>();
    
    private List<CompradorEntity> dataComprador = new ArrayList<CompradorEntity>();
    
    @Deployment 
    public static JavaArchive createDeployment(){
    
       return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComicDeseoEntity.class.getPackage())
                .addPackage(ComicDeseoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
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
        
        em.createQuery("delete from ComicDeseoEntity").executeUpdate();
        em.createQuery("delete from CompradorEntity").executeUpdate();
    }

    private void insertData() {
       PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CompradorEntity entity = factory.manufacturePojo(CompradorEntity.class);
            em.persist(entity);
            dataComprador.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            ComicDeseoEntity entity = factory.manufacturePojo(ComicDeseoEntity.class);
            if (i == 0) {
                entity.setComprador(dataComprador.get(0));
            }
            em.persist(entity);
            data.add(entity);
        }
        
        
    }
    
     /**
     * Prueba para crear un ComicDeseo
     */
    @Test
    public void createComicDeseoTest() {

        PodamFactory factory = new PodamFactoryImpl();
        ComicDeseoEntity newEntity = factory.manufacturePojo(ComicDeseoEntity.class);
        ComicDeseoEntity result = comicPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ComicDeseoEntity entity = em.find(ComicDeseoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
        
        Assert.assertEquals(newEntity.getFechaAgregado(), entity.getFechaAgregado());
        Assert.assertEquals(newEntity.getComprador(), entity.getComprador());
    }
    
    /**
     * Prueba para consultar un ComicDeseo.
     */
    @Test
    public void getComicDeseoTest() {
        ComicDeseoEntity entity = data.get(0);
      
       ComicDeseoEntity newEntity = comicPersistence.find(dataComprador.get(0).getAlias(), entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getFechaAgregado(), newEntity.getFechaAgregado());
        Assert.assertEquals(entity.getComprador(), newEntity.getComprador());
    }
    
    
    /**
     * Prueba para eliminar un ComicDeseo
     */
    @Test
    public void deleteComicDeseoTest() {
        ComicDeseoEntity entity = data.get(0);
        comicPersistence.delete(entity.getId());
        ComicDeseoEntity deleted = em.find(ComicDeseoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
    /**
     * Prueba para actualizar un ComicDeseo
     */
    @Test
    public void updateComicDeseoTest() {
        ComicDeseoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ComicDeseoEntity newEntity = factory.manufacturePojo(ComicDeseoEntity.class);

        newEntity.setId(entity.getId());

         comicPersistence.update(newEntity);

        ComicDeseoEntity resp = em.find(ComicDeseoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
        Assert.assertEquals(newEntity.getFechaAgregado(), resp.getFechaAgregado());
        Assert.assertEquals(newEntity.getComprador(), resp.getComprador());
    }
    
    
    
   
    
}
