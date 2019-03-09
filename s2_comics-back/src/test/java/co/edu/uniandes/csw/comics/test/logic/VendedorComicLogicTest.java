/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.logic;

import co.edu.uniandes.csw.comics.ejb.VendedorComicLogic;
import co.edu.uniandes.csw.comics.ejb.VendedorLogic;
import co.edu.uniandes.csw.comics.ejb.VendedorOrdenPedidoLogic;
import co.edu.uniandes.csw.comics.entities.ComicEntity;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.entities.VendedorEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.comics.persistence.VendedorPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ca.orduz
 */
@RunWith(Arquillian.class)
public class VendedorComicLogicTest {
     private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private VendedorLogic vendedorLogic;
    @Inject
    private VendedorComicLogic vendedorComicLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    private List<VendedorEntity> data = new ArrayList<>();
     private List<ComicEntity> comicsData = new ArrayList<>();
     
     
     
      @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VendedorEntity.class.getPackage())
                .addPackage(ComicEntity.class.getPackage())
                .addPackage(VendedorComicLogic.class.getPackage())
                .addPackage(VendedorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
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
        em.createQuery("delete from VendedorEntity").executeUpdate();
    }
     private void insertData(){
      
             for(int i=0;i<3;i++){
             ComicEntity comic=factory.manufacturePojo(ComicEntity.class);
             em.persist(comic);
             comicsData.add(comic);
           
             
         }
                for(int i=0;i<3;i++){
             VendedorEntity vendedor=factory.manufacturePojo(VendedorEntity.class);
             em.persist(vendedor);
             data.add(vendedor);
               if(i==0){
                 comicsData.get(i).setVendedor(vendedor);
               }
         }
     }
     @Test
     public void addComicsTest(){
         VendedorEntity entity=data.get(0);
         ComicEntity comic=comicsData.get(1);
         ComicEntity response=vendedorComicLogic.addComic(entity.getId(), comic.getId());
         Assert.assertNotNull(response);
         Assert.assertEquals(comic.getId(), response.getId());
     }
     @Test 
     public void getComicsTest(){
         List<ComicEntity> lista=vendedorComicLogic.getComics(data.get(0).getId());
    Assert.assertEquals(1, lista.size());
    
     }
       @Test 
     public void getComicTest()throws BusinessLogicException{
        VendedorEntity entity=data.get(0);
        ComicEntity comicEntity=comicsData.get(0);
        ComicEntity response=vendedorComicLogic.getComic(entity.getId(), comicEntity.getId());
    
        
        Assert.assertEquals(comicEntity.getId(), response.getId());
         Assert.assertEquals(comicEntity.getAutor(), response.getAutor());
          Assert.assertEquals(comicEntity.getNombre(), response.getNombre());
           Assert.assertEquals(comicEntity.getTema(), response.getTema());
            Assert.assertEquals(comicEntity.getInformacion(), response.getInformacion());
     }
     @Test(expected=BusinessLogicException.class)
     public void removeComicTest()throws BusinessLogicException{
         VendedorEntity entity=data.get(0);
         ComicEntity comicEntity=comicsData.get(0);
         vendedorComicLogic.removeComic(entity.getId(), comicEntity.getId());
        ComicEntity respuesta=vendedorComicLogic.getComic(entity.getId(),comicEntity.getId());
     }
}
