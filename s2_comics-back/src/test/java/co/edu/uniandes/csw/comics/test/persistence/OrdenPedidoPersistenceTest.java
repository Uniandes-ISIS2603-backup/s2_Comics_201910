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
 * @author jp.rodriguezv
 */
@RunWith(Arquillian.class)

public class OrdenPedidoPersistenceTest {
  @Inject
    private OrdenPedidoPersistence ordenPedido; //variable para tener acceso a la persistencia de la apliacacion
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    /**
     * lsiat de entidades ordenes Pedido utilizadas en las pruebas
     */
    private List<OrdenPedidoEntity> data = new ArrayList<>();
  
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
 @Deployment
    public static JavaArchive createDeployment()
    {
   return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OrdenPedidoEntity.class.getPackage())
                .addPackage(OrdenPedidoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
   
     /**
     * Configuración inicial de la prueba.
     */
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
    
     /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData()
    {
        em.createQuery("delete from OrdenPedidoEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
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
   
    /**
     * prueba del correcto funcinamiento del metodo find  de la base de datos  
     */
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
    /**
     * prueba del correcto funcinamiento del metodo create de la base de datos  
     */
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
    
 /**
     * prueba del correcto funcinamiento del metodo getOrdenPedido  de la base de datos  
     */
    
       @Test
    public void getOrdenPedidoTest() {
        OrdenPedidoEntity entity = data.get(0);
        OrdenPedidoEntity newEntity = ordenPedido.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getEstado(), newEntity.getEstado());
        Assert.assertEquals(entity.getTarjetaCredito(), newEntity.getTarjetaCredito());
       
    }
    
    /**
     * prueba del correcto funcinamiento del metodo getOrdensPedido  de la base de datos  
     */
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
    
    /**
     * prueba del correcto funcinamiento del metodo deleteOrdenPedido  de la base de datos  
     */
       @Test
    public void deleteOrdenPedidTest() {
      
        OrdenPedidoEntity entity = data.get(0);
        ordenPedido.delete(entity.getId());
        OrdenPedidoEntity eliminado = ordenPedido.find(entity.getId());
        Assert.assertNull(eliminado);
    }
    
    /**
     * prueba del correcto funcinamiento del metodo update  de la base de datos  
     */
    @Test
    public void setEstado(){
        OrdenPedidoEntity entity = data.get(0);
       entity.setEstado(OrdenPedidoEntity.Estado.ACEPTADO);
       Assert.assertEquals(entity.getEstado(), OrdenPedidoEntity.Estado.ACEPTADO);
       entity.setEstado(OrdenPedidoEntity.Estado.EN_ESPERA);
       Assert.assertEquals(entity.getEstado(), OrdenPedidoEntity.Estado.EN_ESPERA);
       
    }
}
