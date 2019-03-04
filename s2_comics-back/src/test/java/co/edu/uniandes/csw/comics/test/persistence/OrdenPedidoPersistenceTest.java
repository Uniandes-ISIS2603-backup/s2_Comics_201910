/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.persistence;

import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.persistence.OrdenPedidoPersistence;
import java.util.List;
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

public class OrdenPedidoPersistenceTest {
  @Inject
    private OrdenPedidoPersistence ordenPedido;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    private List<OrdenPedidoEntity> data = new ArrayList<>();
    
 @Deployment
    public static JavaArchive createDeployment()
    {
   return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OrdenPedidoEntity.class.getPackage())
                .addPackage(OrdenPedidoPersistence.class.getPackage())
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
        em.createQuery("delete from OrdenPedidoEntity").executeUpdate();
    }
    
    private void insertData()
    {
        PodamFactory podam = new PodamFactoryImpl();
        for(int i = 0; i < 3; i++)
        {
            OrdenPedidoEntity comp = podam.manufacturePojo(OrdenPedidoEntity.class);
            em.persist(comp);
            data.add(comp);
        }
    }
    
    @Test
    public void findTest()
    {
         OrdenPedidoEntity entity = data.get(0);
        OrdenPedidoEntity newEntity = ordenPedido.find(entity.getId());
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getEstado(), newEntity.getEstado());
        Assert.assertEquals(entity.getTarjetaCredito(), newEntity.getTarjetaCredito());
     }
    
    @Test
    public void createTest()
    {
      PodamFactory podam = new PodamFactoryImpl();
        OrdenPedidoEntity newEntity= podam.manufacturePojo(OrdenPedidoEntity.class);
        OrdenPedidoEntity result = ordenPedido.create(newEntity);
        
        Assert.assertNotNull(result);
        
        OrdenPedidoEntity entity = em.find(OrdenPedidoEntity.class, result.getId());
        
        Assert.assertEquals(result.getNumeroComprasComprador(), entity.getNumeroComprasComprador());
        Assert.assertEquals(result.getTarjetaCredito(), entity.getTarjetaCredito());
     }
    
 
    
       @Test
    public void getOrdenPedidoTest() {
        OrdenPedidoEntity entity = data.get(0);
        OrdenPedidoEntity newEntity = ordenPedido.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getEstado(), newEntity.getEstado());
        Assert.assertEquals(entity.getTarjetaCredito(), newEntity.getTarjetaCredito());
       
    }
      @Test
    public void updateOrdenesPedidoTest() {
            OrdenPedidoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
       OrdenPedidoEntity newEntity = factory.manufacturePojo(OrdenPedidoEntity.class);

        newEntity.setId(entity.getId());

        ordenPedido.update(newEntity);

        OrdenPedidoEntity resp = em.find(OrdenPedidoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getTarjetaCredito(), resp.getTarjetaCredito());
    
    }
       @Test
    public void deleteOrdenPedidTest() {
      
        OrdenPedidoEntity entity = data.get(0);
        ordenPedido.delete(entity.getId());
        OrdenPedidoEntity eliminado = ordenPedido.find(entity.getId());
        Assert.assertNull(eliminado);
    }
}
