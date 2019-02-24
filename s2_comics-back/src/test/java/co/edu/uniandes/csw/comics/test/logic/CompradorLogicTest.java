/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.logic;

import co.edu.uniandes.csw.comics.ejb.CompradorLogic;
import co.edu.uniandes.csw.comics.entities.*;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
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
 * @author jp.cano
 */
@RunWith(Arquillian.class)
public class CompradorLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private CompradorLogic comprador;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<CompradorEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompradorEntity.class.getPackage())
                .addPackage(CompradorLogic.class.getPackage())
                .addPackage(CompradorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Before
    public void configTest()
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
        em.createQuery("delete from OrdenPedidoEntity").executeUpdate();
        em.createQuery("delete from ComicEntity").executeUpdate();
        em.createQuery("delete from ComicDeseoEntity").executeUpdate();
        em.createQuery("delete from CompradorEntity").executeUpdate();
    }
    
    private void insertData()
    {
        for(int i = 0; i < 3; i++)
        {
            CompradorEntity entity = factory.manufacturePojo(CompradorEntity.class);
            em.persist(entity);
            //entity.setCarro(new ArrayList<>());
            //entity.setListaDeseos(new ArrayList<>());
            //entity.setOrdenPedidoCompra(new ArrayList());
            data.add(entity);
        }        
    }
    
    @Test
    public void createCompradorTest()
    {
        CompradorEntity entity = factory.manufacturePojo(CompradorEntity.class);
        try
        {
            CompradorEntity result = comprador.createComprador(entity);
            
            Assert.assertNotNull(result);
            CompradorEntity newEntity = em.find(CompradorEntity.class, result.getId());

            Assert.assertEquals(entity.getId(), newEntity.getId());
            Assert.assertEquals(entity.getAlias(), newEntity.getAlias());
            Assert.assertEquals(entity.getEmail(), newEntity.getEmail());
        }
        catch(Exception e)
        {
            Assert.fail("No debería generar exception");
        }
    }
    
    @Test
    public void crearCompradorConMismoAlias()
    {
        try
        {
            CompradorEntity entity = factory.manufacturePojo(CompradorEntity.class);
            entity.setAlias(data.get(0).getAlias());
            Assert.assertEquals(entity.getAlias(), data.get(0).getAlias());
            comprador.createComprador(entity);
            Assert.fail("No genera exception cuando se crea un comprador que ya existe.");
        }
        catch(Exception e)
        {
            //debería generar exception
        }
    }
    
    @Test
    public void crearCompradorMismoMail()
    {
        try
        {
            CompradorEntity entity = factory.manufacturePojo(CompradorEntity.class);
            entity.setEmail(data.get(1).getEmail());
            Assert.assertEquals(entity.getEmail(), data.get(1).getEmail());
            comprador.createComprador(entity);
            Assert.fail("No genera exception cuando se crea un comprador que ya existe.");
        }
        catch(Exception e)
        {
            //Debería generar exception.
        }        
    }
    
    @Test
    public void crearCompradorMismoId()
    {
        try
        {
            CompradorEntity entity = factory.manufacturePojo(CompradorEntity.class);
            entity.setId(data.get(2).getId());
            Assert.assertEquals(entity.getId(), data.get(2).getId());
            comprador.createComprador(entity);
            Assert.fail("No debería crear un comprador con un Id existente");
        }
        catch(Exception e)
        {
            //Debería generar Exception
        }
    }
    
    @Test
    public void getCompradoresTest()
    {
        List<CompradorEntity> lista = comprador.getCompradores();
        Assert.assertEquals(data.size(), lista.size());
        for(CompradorEntity entity : lista)
        {
            boolean found = false;
            for(CompradorEntity stored : data)
            {
                if(entity.getId() == stored.getId())
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getCompradorTest()
    {
        CompradorEntity entity = data.get(0);
        CompradorEntity result = comprador.getCompradorByAlias(entity.getAlias());
        Assert.assertNotNull(result);
        Assert.assertEquals(entity.getAlias(), result.getAlias());
        Assert.assertEquals(entity.getEmail(), result.getEmail());
    }
    
    @Test
    public void updateTest()
    {
        CompradorEntity entity = data.get(0);
        CompradorEntity pojoEntity = factory.manufacturePojo(CompradorEntity.class);
        
        pojoEntity.setId(entity.getId());
        comprador.updateComprador(pojoEntity.getId(), pojoEntity);
        CompradorEntity result = em.find(CompradorEntity.class, entity.getId());
        
        Assert.assertEquals(result.getId(), pojoEntity.getId());
        Assert.assertEquals(result.getAlias(), pojoEntity.getAlias());
        Assert.assertEquals(result.getEmail(), pojoEntity.getEmail());
    }
    
    @Test
    public void deleteTest()
    {
        CompradorEntity entity = data.get(0);
        Assert.assertTrue(entity.getOrdenPedidoCompra().isEmpty());
        try
        {
            comprador.deleteComprador(entity.getId());
        }
        catch(Exception e)
        {
            System.out.println("Error en: " + e.getMessage());
            Assert.fail("No debería generar Exception");
        }
        CompradorEntity result = em.find(CompradorEntity.class, entity.getId());
        Assert.assertNull(result);
    }
}
