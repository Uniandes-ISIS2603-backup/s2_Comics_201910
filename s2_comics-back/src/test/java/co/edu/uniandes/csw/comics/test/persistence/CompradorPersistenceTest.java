/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.persistence;

import co.edu.uniandes.csw.comics.entities.CompradorEntity;
import co.edu.uniandes.csw.comics.persistence.CompradorPersistence;
import java.util.*;
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
 * @author Juan Pablo Cano
 */
@RunWith(Arquillian.class)
public class CompradorPersistenceTest 
{
    @Inject
    private CompradorPersistence comprador;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<CompradorEntity> data = new ArrayList<CompradorEntity>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompradorEntity.class.getPackage())
                .addPackage(CompradorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
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
    }
    
    private void insertData()
    {
        PodamFactory podam = new PodamFactoryImpl();
        for(int i = 0; i < 3; i++)
        {
            CompradorEntity comp = podam.manufacturePojo(CompradorEntity.class);
            em.persist(comp);
            data.add(comp);
        }
    }
    
    @Test
    public void createTest()
    {
        PodamFactory podam = new PodamFactoryImpl();
        CompradorEntity newEntity= podam.manufacturePojo(CompradorEntity.class);
        CompradorEntity result = comprador.create(newEntity);
        
        Assert.assertNotNull(result);
        
        CompradorEntity entity = em.find(CompradorEntity.class, result.getId());
        
        Assert.assertEquals(result.getAlias(), entity.getAlias());
        Assert.assertEquals(result.getEmail(), entity.getEmail());
    }
    
    @Test
    public void getCompradoresTest()
    {
        List<CompradorEntity> lista = comprador.findAll();
        Assert.assertTrue(lista.size() == data.size());
        
        for(CompradorEntity ce: lista)
        {
            boolean found = false;
             for(int i = 0; i < data.size() && !found; i++)
             {
                 if(data.get(i).getAlias().equals(ce.getAlias()))
                 {
                     found = true;
                 }
             }
             Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getCompradorByAlias()
    {
        CompradorEntity entity = data.get(0);
        CompradorEntity newEntity = comprador.findByAlias(entity.getAlias());
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getAlias(), entity.getAlias());
        
        newEntity = comprador.findByAlias(null);
        Assert.assertNull(newEntity);
    }
}
