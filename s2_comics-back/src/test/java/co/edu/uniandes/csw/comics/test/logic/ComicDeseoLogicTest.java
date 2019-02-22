/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.logic;

import co.edu.uniandes.csw.comics.ejb.ComicDeseoLogic;
import co.edu.uniandes.csw.comics.entities.ComicDeseoEntity;
import co.edu.uniandes.csw.comics.entities.CompradorEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
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
public class ComicDeseoLogicTest {
    
  private PodamFactory factory = new PodamFactoryImpl();
  
  @Inject
  private ComicDeseoLogic comicDLogic;
  
  @PersistenceContext
  private EntityManager em;
  
  @Inject
  private UserTransaction utx;
  
  private List<ComicDeseoEntity> data = new ArrayList<ComicDeseoEntity>();
  
  private List<CompradorEntity> dataComprador = new ArrayList<CompradorEntity>();
  
      /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComicDeseoEntity.class.getPackage())
                .addPackage(ComicDeseoLogic.class.getPackage())
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from ComicDeseoEntity").executeUpdate();
        em.createQuery("delete from CompradorEntity").executeUpdate();
       
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
      
        for (int i = 0; i < 3; i++) {
            CompradorEntity entity = factory.manufacturePojo(CompradorEntity.class);
            em.persist(entity);
            dataComprador.add(entity);
        }

        for (int i = 0; i < 3; i++) {
            ComicDeseoEntity entity = factory.manufacturePojo(ComicDeseoEntity.class);
            entity.setComprador(dataComprador.get(1));
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un ComicDeseo
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createComicDeseoTest() throws BusinessLogicException, Exception {
        ComicDeseoEntity newEntity = factory.manufacturePojo(ComicDeseoEntity.class);
        newEntity.setComprador(dataComprador.get(1));
        ComicDeseoEntity result = comicDLogic.createComicDeseo(dataComprador.get(1).getAlias(), newEntity);
        Assert.assertNotNull(result);
        ComicDeseoEntity entity = em.find(ComicDeseoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getFechaAgregado(), entity.getFechaAgregado());
        Assert.assertEquals(newEntity.getComprador(), entity.getComprador());
        
    }
    
    /**
     * Prueba para consultar la lista de ComicsDeseo
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     
    
    @Test
    public void getComicsDeseoTest() throws BusinessLogicException {
        List<ComicDeseoEntity> list = comicDLogic.getComicsDeseo(dataComprador.get(1).getAlias());
        Assert.assertEquals(data.size(), list.size());
        for (ComicDeseoEntity entity : list) {
            boolean found = false;
            for (ComicDeseoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    * */
    
    /**
     * Prueba para consultar un ComicDeseo
     */
    @Test
    public void getComicDeseoTest() {
        ComicDeseoEntity entity = data.get(0);
        ComicDeseoEntity resultEntity = comicDLogic.getComicDeseo(dataComprador.get(1).getAlias(), entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getFechaAgregado(), resultEntity.getFechaAgregado());
        Assert.assertEquals(entity.getComprador(), resultEntity.getComprador());
        
    }
    
    /**
     * Prueba para actualizar un Review.
     */
    @Test
    public void updateComicDeseoTest() {
        ComicDeseoEntity entity = data.get(0);
        ComicDeseoEntity pojoEntity = factory.manufacturePojo(ComicDeseoEntity.class);

        pojoEntity.setId(entity.getId());

        comicDLogic.updateComicDeseo(dataComprador.get(1).getAlias(), pojoEntity);

        ComicDeseoEntity resp = em.find(ComicDeseoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getFechaAgregado(), resp.getFechaAgregado());
        Assert.assertEquals(pojoEntity.getComprador(), resp.getComprador());
        
    }
    
    
     /**
     * Prueba para eliminar un ComicDeseo.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteComicDeseoTest() throws BusinessLogicException {
        ComicDeseoEntity entity = data.get(0);
        comicDLogic.deleteComicDeseo(dataComprador.get(1).getAlias(), entity.getId());
        ComicDeseoEntity deleted = em.find(ComicDeseoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para eliminarle un comic deseo a un comprador del cual no pertenece.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteComicDeseoConCompradorNoAsociadoTest() throws BusinessLogicException {
        ComicDeseoEntity entity = data.get(0);
        comicDLogic.deleteComicDeseo(dataComprador.get(0).getAlias(), entity.getId());
    }


  
  
    
}
