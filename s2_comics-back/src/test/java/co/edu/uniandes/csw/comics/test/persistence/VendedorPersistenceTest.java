/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.persistence;

import co.edu.uniandes.csw.comics.entities.VendedorEntity;
import co.edu.uniandes.csw.comics.persistence.VendedorPersistence;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class VendedorPersistenceTest 
{
    @Inject
    private VendedorPersistence vendedorPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx; 
    
    public List<VendedorEntity> data = new ArrayList<VendedorEntity>();
   
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VendedorEntity.class.getPackage())
                .addPackage(VendedorPersistence.class.getPackage())
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
        em.createQuery("delete from VendedorEntity").executeUpdate();
    }
    
    private void insertData()
    {
        PodamFactory podam = new PodamFactoryImpl();
        
        for(int i = 0; i < 3; i++)
        {
            VendedorEntity entity = podam.manufacturePojo(VendedorEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createVendedorTest()
    {
        PodamFactory podam = new PodamFactoryImpl();
        VendedorEntity newEntity = podam.manufacturePojo(VendedorEntity.class);
        VendedorEntity result = vendedorPersistence.create(newEntity);
        
        Assert.assertNotNull("No debería ser nulo", result);
        
        VendedorEntity entity = em.find(VendedorEntity.class, result.getId());
        
        Assert.assertEquals("Hay un error", result.getAlias(), entity.getAlias());
   //     Assert.assertEquals("", result.getEmail(), entity.getEmail());
    }
}
