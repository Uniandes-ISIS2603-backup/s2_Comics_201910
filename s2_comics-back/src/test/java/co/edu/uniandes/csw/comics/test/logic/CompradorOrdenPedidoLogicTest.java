/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.logic;

import co.edu.uniandes.csw.comics.ejb.*;
import co.edu.uniandes.csw.comics.entities.*;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity.Estado;
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
public class CompradorOrdenPedidoLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private CompradorOrdenPedidoLogic compradorOrdenLogic;
    
    @Inject
    private OrdenPedidoLogic ordenPedidoLogic;
    
    @Inject
    private UserTransaction utx;
    
    private ComicEntity comic = new ComicEntity();
    private ComicEntity trueque = new ComicEntity();
    private CompradorEntity comprador = new CompradorEntity();
    private VendedorEntity vendedor = new VendedorEntity();
    private ArrayList<OrdenPedidoEntity> data = new ArrayList();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompradorEntity.class.getPackage())
                .addPackage(CompradorOrdenPedidoLogic.class.getPackage())
                .addPackage(OrdenPedidoEntity.class.getPackage())
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
        em.createQuery("delete from CompradorEntity").executeUpdate();
    }
    
    private void insertData()
    {
        vendedor = factory.manufacturePojo(VendedorEntity.class);
        em.persist(vendedor);
        
        comic = factory.manufacturePojo(ComicEntity.class);
        em.persist(comic);
        
        trueque = factory.manufacturePojo(ComicEntity.class);
        em.persist(trueque);
        
        comprador = factory.manufacturePojo(CompradorEntity.class);
        comprador.setId(1L);
        comprador.setOrdenPedidoCompra(new ArrayList());
        em.persist(comprador);
        
        for(int i = 0; i < 3; i++)
        {
            OrdenPedidoEntity entity = factory.manufacturePojo(OrdenPedidoEntity.class);
            entity.setComprador(comprador);
            entity.setVendedor(vendedor);
            em.persist(entity);
            data.add(entity);
            comprador.getOrdenPedidoCompra().add(entity);
        }
    }
    
    @Test
    public void addOrdenTest() throws BusinessLogicException
    {
        OrdenPedidoEntity newOrden = factory.manufacturePojo(OrdenPedidoEntity.class);
        newOrden.setComprador(comprador);
        newOrden.setVendedor(vendedor);
        newOrden.setComic(comic);
        ordenPedidoLogic.createOrdenPedido(newOrden, comprador.getId(),  vendedor.getId(), comic.getId(), null);
        OrdenPedidoEntity ordenPedidoEntity = compradorOrdenLogic.addOrdenPedido(comprador.getId(), newOrden.getId());
        Assert.assertNotNull(ordenPedidoEntity);

        
        Assert.assertEquals(ordenPedidoEntity.getComic(), newOrden.getComic());
        Assert.assertEquals(ordenPedidoEntity.getVendedor(), newOrden.getVendedor());
        Assert.assertEquals(ordenPedidoEntity.getEstado(), newOrden.getEstado());
        Assert.assertEquals(ordenPedidoEntity.getTarjetaCredito(), newOrden.getTarjetaCredito());
    }
    
    @Test
    public void getOrdenesTest()
    {
        List<OrdenPedidoEntity> ordenes = compradorOrdenLogic.getOrdenes(comprador.getId());
        
        Assert.assertEquals(ordenes.size(), data.size());
        
        for(int i = 0; i < data.size(); i++)
        {
            Assert.assertTrue(ordenes.contains(data.get(0)));
        }
    }
    
    @Test
    public void getOrdenTest() throws BusinessLogicException
    {
        OrdenPedidoEntity entity = data.get(0);
        OrdenPedidoEntity newEntity = compradorOrdenLogic.getOrden(comprador.getId(), data.get(0).getId());
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), data.get(0).getId());
        Assert.assertEquals(newEntity.getId(), data.get(0).getId());
        Assert.assertEquals(entity.getComprador().getId(), newEntity.getComprador().getId());
        Assert.assertEquals(entity.getId(), newEntity.getId());
        
    }
    
    @Test
    public void replaceOrdenTest()throws BusinessLogicException
    {
        List<OrdenPedidoEntity> lista = new ArrayList();
        for(int i = 0; i < 3; i++)
        {
            OrdenPedidoEntity entity = factory.manufacturePojo(OrdenPedidoEntity.class);
            entity.setComprador(comprador);
            entity.setVendedor(vendedor);
            entity.setComic(comic);
            ordenPedidoLogic.createOrdenPedido(entity, vendedor.getId(), comprador.getId(), comic.getId(), trueque.getId());
            lista.add(entity);
        }
        
        compradorOrdenLogic.replaceOrden(comprador.getId(), lista);
        List<OrdenPedidoEntity> entities = compradorOrdenLogic.getOrdenes(comprador.getId());
        for(OrdenPedidoEntity entity : lista)
        {
            Assert.assertTrue(entities.contains(entity));
        }
    }
    
    @Test
    public void deleteOrdenTest()throws BusinessLogicException
    {
        for(OrdenPedidoEntity entity : data)
        {
            try
            {
                compradorOrdenLogic.eliminarOrden(comprador.getId(), entity.getId());
                Assert.fail("Debería generar Exception");
            }
            catch(Exception e)
            {
                //Genera Excepcion
            }
        }
        Assert.assertTrue(!compradorOrdenLogic.getOrdenes(comprador.getId()).isEmpty());
        
        for(OrdenPedidoEntity entity: data)
        {
            entity.setEstado(Estado.FINALIZADO);
            compradorOrdenLogic.getOrden(comprador.getId(), entity.getId()).setEstado(Estado.FINALIZADO);
        }
        
        for(OrdenPedidoEntity entity : data)
        {
            try
            {
                compradorOrdenLogic.eliminarOrden(comprador.getId(), entity.getId());
            }
            catch(Exception e)
            {
                for(int i = 0; i < data.size(); i++)
                {
                    
                    System.out.println(data.get(i).getEstado());
                    System.out.println(compradorOrdenLogic.getOrdenes(comprador.getId()).get(i).getEstado());
                }
                System.out.println(e.getMessage());
                Assert.fail("No debería generar exception");
            }
        }
    }
}
