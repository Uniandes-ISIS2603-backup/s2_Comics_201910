/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.logic;

import co.edu.uniandes.csw.comics.ejb.ComicLogic;
import co.edu.uniandes.csw.comics.ejb.CompradorComicLogic;
import co.edu.uniandes.csw.comics.ejb.CompradorLogic;
import co.edu.uniandes.csw.comics.entities.*;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.comics.persistence.CompradorPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import java.util.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author juan pablo cano
 */
@RunWith(Arquillian.class)
public class CompradorComicLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private CompradorComicLogic compradorComic;
    
    @Inject
    private UserTransaction utx;
    
    @Inject
    private ComicLogic comic;
    
    private CompradorEntity compradorEntity = new CompradorEntity();    
    private List<ComicEntity> data = new ArrayList();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComicEntity.class.getPackage())
                .addPackage(CompradorEntity.class.getPackage())
                .addPackage(CompradorComicLogic.class.getPackage())
                .addPackage(CompradorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Before
    public void configureTest()
    {
        try
        {
            utx.begin();
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
    
    private void insertData()
    {
        compradorEntity = factory.manufacturePojo(CompradorEntity.class);
        compradorEntity.setId(1L);
        compradorEntity.setCarro(new ArrayList());
        em.persist(compradorEntity);
        
        for(int i = 0; i < 3; i++)
        {
            ComicEntity entity = factory.manufacturePojo(ComicEntity.class);
            entity.setCompradores(new ArrayList());
            entity.getCompradores().add(compradorEntity);
            em.persist(entity);
            data.add(entity);
            compradorEntity.getCarro().add(entity);
        }
    }
    
    private void clearData()
    {
        em.createQuery("delete from CompradorEntity").executeUpdate();
        em.createQuery("delete from ComicEntity").executeUpdate();
    }
        
    @Test
    public void addComicTest()
    {
        ComicEntity newComic = factory.manufacturePojo(ComicEntity.class);
        comic.createComic(newComic);
        ComicEntity creado = compradorComic.addComicCarrito(compradorEntity.getId(), newComic.getId());
        Assert.assertNotNull(creado);
        
        Assert.assertEquals(creado.getId(), newComic.getId());
        Assert.assertEquals(creado.getAutor(), newComic.getAutor());
        Assert.assertEquals(creado.getNombre(), newComic.getNombre());
        Assert.assertEquals(creado.getInformacion(), newComic.getInformacion());
    }
    
    @Test
    public void getComicsTest()
    {
        List<ComicEntity> comicsEntity = compradorComic.getComics(compradorEntity.getId());
        Assert.assertEquals(comicsEntity.size(), data.size());
        
        for(int i = 0; i < data.size(); i++)
        {
            Assert.assertTrue(comicsEntity.contains(data.get(i)));
        }
    }
    
    @Test
    public void getComicTest()
    {
        try
        {
            ComicEntity entity = data.get(0);
            ComicEntity newEntity = compradorComic.getComic(compradorEntity.getId(), entity.getId());

            Assert.assertNotNull(newEntity);
            Assert.assertEquals(newEntity.getId(), entity.getId());
            Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
            Assert.assertEquals(newEntity.getInformacion(), entity.getInformacion());
            Assert.assertEquals(newEntity.getAutor(), entity.getAutor());
        }
        catch(Exception e)
        {
            Assert.fail("No debería generar exception");
        }            
    }
    
    @Test
    public void replaceComicsTest()
    {
        List<ComicEntity> nuevaLista = new ArrayList();
        for(int i = 0; i < 3; i++)
        {
            ComicEntity entity = factory.manufacturePojo(ComicEntity.class);
            entity.setCompradores(new ArrayList());
            entity.getCompradores().add(compradorEntity);
            comic.createComic(entity);
            nuevaLista.add(entity);
        }
        
        compradorComic.replaceComics(compradorEntity.getId(), nuevaLista);
        List<ComicEntity> comicEntities = compradorComic.getComics(compradorEntity.getId());
        for(ComicEntity aNuevaLista : nuevaLista)
        {
            Assert.assertTrue(comicEntities.contains(aNuevaLista));
        }
    }
    
    @Test
    public void removeComicTest()throws BusinessLogicException
    {
        for(ComicEntity comic : data)
        {
            compradorComic.deleteComic(compradorEntity.getId(), comic.getId());
        }
        Assert.assertTrue(compradorComic.getComics(compradorEntity.getId()).isEmpty());
    }
}
