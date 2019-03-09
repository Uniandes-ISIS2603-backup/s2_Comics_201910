/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.logic;


import co.edu.uniandes.csw.comics.ejb.OrdenPedidoLogic;
import co.edu.uniandes.csw.comics.entities.ComicEntity;
import co.edu.uniandes.csw.comics.entities.CompradorEntity;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.entities.VendedorEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
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
public class OrdenPedidoLogicTest {
    
     private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private OrdenPedidoLogic ordenPedido;//variable para tener acceso a ala logica de la aplicacion
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
   /**
    * lista de Ordenes pedido utilizada en las pruebas
    */
    private List<OrdenPedidoEntity> data = new ArrayList<>();
    
 /**
    * lista de vendedores utilizada en las pruebas
    */
    private List<VendedorEntity> dataVendedores = new ArrayList<VendedorEntity>();
 
    /**
    * lista de comics utilizada en las pruebas
    */
    private List<ComicEntity> dataComic = new ArrayList<ComicEntity>();
 
    /**
    * lista de comics-trueque utilizada en las pruebas
    */
    private List<ComicEntity> dataTrueque = new ArrayList<ComicEntity>();
   
    /**
    * lista de compradores utilizada en las pruebas
    */
    private List<CompradorEntity> dataCompradores =  new ArrayList<CompradorEntity>() ;
    
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
                .addPackage(OrdenPedidoLogic.class.getPackage())
                .addPackage(OrdenPedidoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
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
    
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
     private void clearData()
    {
   em.createQuery("delete from OrdenPedidoEntity").executeUpdate();
   em.createQuery("delete from VendedorEntity").executeUpdate();
   em.createQuery("delete from CompradorEntity").executeUpdate();
      em.createQuery("delete from ComicEntity").executeUpdate();
    }
    
     /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData()
    {
                  for (int i = 0; i < 3; i++) {
            VendedorEntity entity = factory.manufacturePojo(VendedorEntity.class);
            em.persist(entity);
           
            dataVendedores.add(entity);
        }
                    for (int i = 0; i < 3; i++) {
                        ComicEntity entity = factory.manufacturePojo(ComicEntity.class);
            em.persist(entity);
           
            dataComic.add(entity);
        }
                      for (int i = 0; i < 3; i++) {
                        ComicEntity entity = factory.manufacturePojo(ComicEntity.class);
            em.persist(entity);
           
            dataTrueque.add(entity);
        }
                    
                    
                            for (int i = 0; i < 3; i++) {
            CompradorEntity entity = factory.manufacturePojo(CompradorEntity.class);
            em.persist(entity);
           
            dataCompradores.add(entity);
        }
        for(int i = 0; i < 3; i++)
        {
            OrdenPedidoEntity entity = factory.manufacturePojo(OrdenPedidoEntity.class);
            em.persist(entity);
            data.add(entity);
        }   
        
    }
    
    /**
     * test del metodo crearOrdenPedido 
     * @throws BusinessLogicException 
     */
    @Test
    public void createOrdenPedidoTest() throws BusinessLogicException
    {
        OrdenPedidoEntity entity = factory.manufacturePojo(OrdenPedidoEntity.class);
         entity.setVendedor(dataVendedores.get(1));
         entity.setComprador(dataCompradores.get(1));
         entity.setComic(dataComic.get(1));
         entity.setTrueque(dataTrueque.get(1));
       
            OrdenPedidoEntity result = ordenPedido.createOrdenPedido(entity, entity.getVendedor().getId(),entity.getComprador().getId(), entity.getComic().getId(), entity.getTrueque().getId() );
            
            Assert.assertNotNull(result);
            OrdenPedidoEntity newEntity = em.find(OrdenPedidoEntity.class, result.getId());

            Assert.assertEquals(entity.getId(), newEntity.getId());
            Assert.assertEquals(entity.getEstado(), newEntity.getEstado());
            Assert.assertEquals(entity.getTarjetaCredito(), newEntity.getTarjetaCredito());
       
       
    }
   
    /**
     * test que valida la regla de negocio que no perite la creacion de dos ordenes de pedido con el mismo id
     */
    @Test
    public void crearOrdenPedidoMismoId()
    {
        try
        {
            OrdenPedidoEntity entity = factory.manufacturePojo(OrdenPedidoEntity.class);
         entity.setVendedor(dataVendedores.get(1));
         entity.setComprador(dataCompradores.get(1));
      
            entity.setId(data.get(2).getId());
            Assert.assertEquals(entity.getId(), data.get(2).getId());
            OrdenPedidoEntity a =ordenPedido.createOrdenPedido(entity, entity.getVendedor().getId(),entity.getComprador().getId(), entity.getComic().getId(), entity.getTrueque().getId());
            Assert.assertNull("No debería crear un comprador con un Id existente",a );
        }
        catch(Exception e)
        {
            e.getMessage();//Debería generar Exception
        }
    }
    
    /**
     * test que verifica el funcionamiento del metodo getOrdenesPedido
     */
    @Test
    public void getOrdenesPedidoTest()
    {
        List<OrdenPedidoEntity> lista = ordenPedido.getOrdenesPedido();
        Assert.assertEquals(data.size(), lista.size());
    
    }
    
    /**
     * test que verifica el funcionamiento del metodo getOrdenPedido
     */
    @Test
    public void getOrdenPedidoTest()
    {
        OrdenPedidoEntity entity = data.get(0);
        OrdenPedidoEntity result = ordenPedido.getOrdenPedido(entity.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(entity.getId(), result.getId());
        Assert.assertEquals(entity.getTarjetaCredito(), result.getTarjetaCredito());
    }
    
    /**
     * test que verifica el funcionamiento del metodo UpdateOrdenPeido
     */
    @Test
    public void updateTest()
    {
        OrdenPedidoEntity entity = data.get(0);
        OrdenPedidoEntity pojoEntity = factory.manufacturePojo(OrdenPedidoEntity.class);
        
        pojoEntity.setId(entity.getId());
        ordenPedido.updateOrdenPedido(pojoEntity.getId(), pojoEntity);
        OrdenPedidoEntity result = em.find(OrdenPedidoEntity.class, entity.getId());
        
        Assert.assertEquals(result.getId(), pojoEntity.getId());
        Assert.assertEquals(result.getTarjetaCredito(), pojoEntity.getTarjetaCredito());
        Assert.assertEquals(result.getEstado(), pojoEntity.getEstado());
    }
    
    /**
     * test que verifica el funcionamiento  del metodo deletOrdenPeido 
     * @throws BusinessLogicException 
     */
    @Test
    public void deleteTest() throws BusinessLogicException
    {
        OrdenPedidoEntity entity = data.get(0);
        ordenPedido.deleteOrdenPedido(entity.getId());
        OrdenPedidoEntity result = em.find(OrdenPedidoEntity.class, entity.getId());
        Assert.assertNull(result);
    }
}
