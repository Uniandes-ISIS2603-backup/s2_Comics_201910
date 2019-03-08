/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.logic;


import co.edu.uniandes.csw.comics.ejb.*;
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
public class CompradorComicDeseoLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject 
    private CompradorComicDeseoLogic compradorComicDeseoLogic;
    
    @Inject
    private UserTransaction utx;
    
    @Inject
    private ComicDeseoLogic comicDeseoLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    private CompradorEntity comprador;
    private List<ComicDeseoEntity> data;
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompradorEntity.class.getPackage())
                .addPackage(ComicDeseoEntity.class.getPackage())
                .addPackage(CompradorComicDeseoLogic.class.getPackage())
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
    
    private void clearData()
    {
        em.createQuery("delete from CompradorEntity").executeUpdate();
        em.createQuery("delete from ComicDeseoEntity").executeUpdate();
    }
    
    private void insertData()
    {
        comprador = factory.manufacturePojo(CompradorEntity.class);
        comprador.setId(1L);
        comprador.setListaDeseos(new ArrayList());
        em.persist(comprador);
        
        for(int i = 0; i < 3; i++)
        {
            ComicDeseoEntity entity = factory.manufacturePojo(ComicDeseoEntity.class);
            em.persist(entity);
            data.add(entity);
            comprador.getListaDeseos().add(entity);
        }
    }
    
    @Test
    public void addComicDeseoTest()throws BusinessLogicException
    {
        ComicDeseoEntity entity = factory.manufacturePojo(ComicDeseoEntity.class);
        comicDeseoLogic.createComicDeseo(entity.getId(), entity);
        ComicDeseoEntity creado = compradorComicDeseoLogic.addComicListaDeseo(comprador.getId(), entity.getId());
        Assert.assertNotNull(creado);
        
        Assert.assertEquals(entity.getId(), creado.getId());
    }
    
    @Test
    public void getComicsDeseoTest()
    {
        
    }
    
    @Test
    public void getComicDeseoTest()
    {
        
    }
    
    @Test
    public void replaceComicDeseoTest()
    {
        
    }
    
    @Test
    public void deleteComicDeseoTest()
    {
        
    }
}
