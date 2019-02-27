/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.persistence;

import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.persistence.OrdenPedidoPersistence;
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
    
    private List<OrdenPedidoEntity> data = new ArrayList<OrdenPedidoEntity>();
    
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
         }
    
    @Test
    public void createTest()
    {
        PodamFactory podam = new PodamFactoryImpl();
        OrdenPedidoEntity newEntity= podam.manufacturePojo(OrdenPedidoEntity.class);
        OrdenPedidoEntity result = ordenPedido.create(newEntity);
        
        Assert.assertNotNull("no deberia ser nulo",result);
        
        OrdenPedidoEntity entity = em.find(OrdenPedidoEntity.class, result.getId());
        
         Assert.assertNotNull("deberia encontrarlo si lo creo",entity);
       
         }
    
 
    
       @Test
    public void getOrdenPedidoTest() {
        OrdenPedidoEntity entity = data.get(0);
        OrdenPedidoEntity newEntity = ordenPedido.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getEstado(), newEntity.getEstado());
        Assert.assertEquals(entity.getTarjetaCredito(), newEntity.getTarjetaCredito());
        Assert.assertEquals(entity.getComprador().getAlias(), newEntity.getComprador().getAlias());
       
    }
      @Test
    public void updateOrdenPedidoTest() {
        OrdenPedidoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
       OrdenPedidoEntity newEntity = factory.manufacturePojo(OrdenPedidoEntity.class);

        newEntity.setEstado(entity.getEstado()+1);

     //   Assert.assertSame(newEntity.getEstado(),entity.getEstado()+1);
    }
       @Test
    public void deleteOrdenPedidTest() {
        OrdenPedidoEntity entity = data.get(0);
        ordenPedido.delete(entity.getId());
        OrdenPedidoEntity deleted = em.find(OrdenPedidoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
