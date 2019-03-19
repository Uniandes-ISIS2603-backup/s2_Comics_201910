/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.logic;

import co.edu.uniandes.csw.comics.ejb.ComicDeseoComicLogic;
import co.edu.uniandes.csw.comics.entities.ComicDeseoEntity;
import co.edu.uniandes.csw.comics.entities.ComicEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
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
 * @author Sebastian Baquero
 */
@RunWith(Arquillian.class)
public class ComicDeseoComicLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
     @Inject
    private UserTransaction utx;
     
     @Inject
     private ComicDeseoComicLogic cDCL;
     
     @PersistenceContext
     private EntityManager em;
     
     private List<ComicEntity> data = new ArrayList<ComicEntity>();
     
     private List<ComicDeseoEntity> cDD = new ArrayList<ComicDeseoEntity>();
     
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComicEntity.class.getPackage())
                .addPackage(ComicDeseoComicLogic.class.getPackage())
                .addPackage(ComicPersistence.class.getPackage())
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
       
        em.createQuery("delete from ComicEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    public void insertData(){
    
        
        for (int i = 0; i < 3; i++) {
            
            ComicEntity entity = factory.manufacturePojo(ComicEntity.class);
            em.persist(entity);
            data.add(entity);
        }

        for (int i = 0; i < 3; i++) {
            ComicDeseoEntity entity = factory.manufacturePojo(ComicDeseoEntity.class);
            entity.setComic(data.get(0));
            
            em.persist(entity);
            cDD.add(entity);
        }
    }
    
    /**
     * Prueba para asociar un Comic deseo existente a un Comic.
     */
    @Test
    public void addComicTest() {
        ComicEntity entity = data.get(0);
        ComicDeseoEntity comicDEntity = cDD.get(1);
        ComicEntity resp = cDCL.addComic(entity.getId(), comicDEntity.getId());
               

        Assert.assertNotNull(resp);
        Assert.assertEquals(entity.getId(), resp.getId());
    }
    
      /**
     * Prueba para consultar un Comic
     */
    @Test
    public void getComicTest() {
        ComicDeseoEntity entity = cDD.get(0);
        ComicEntity resultEntity = cDCL.getComic(entity.getId());
               
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getComic().getId(), resultEntity.getId());
    }
    
     /**
     * Prueba para desasociar un Comic Deseo existente de un Comic existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void removePrizeSinAuthorTest() throws BusinessLogicException {
        cDCL.removeComic(cDD.get(1).getId());
               
    }
}
