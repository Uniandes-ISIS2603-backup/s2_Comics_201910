/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.logic;

import co.edu.uniandes.csw.comics.ejb.CompradorOrdenPedidoLogic;
import co.edu.uniandes.csw.comics.ejb.OrdenPedidoLogic;
import co.edu.uniandes.csw.comics.entities.CompradorEntity;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.entities.VendedorEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.comics.persistence.CompradorPersistence;
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
    private OrdenPedidoLogic ordenLogic;
    
    @Inject
    private UserTransaction utx;
    
    private CompradorEntity comprador = new CompradorEntity();
    private VendedorEntity vendedor = new VendedorEntity();
    private List<OrdenPedidoEntity> data = new ArrayList();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompradorEntity.class.getPackage())
                .addPackage(OrdenPedidoEntity.class.getPackage())
                .addPackage(CompradorOrdenPedidoLogic.class.getPackage())
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
        catch (Exception e) 
        {
            e.printStackTrace();
            try 
            {
                utx.rollback();
            } 
            catch (Exception e1) 
            {
                e1.printStackTrace();
            }
        }
    }
    
    private void clearData()
    {
        em.createQuery("delete from CompradorEntity").executeUpdate();
        em.createQuery("delete from OrdenPedidoEntity").executeUpdate();
    }
    
    private void insertData()
    {
        for(int i = 0; i < 3; i++)
        {
            vendedor = factory.manufacturePojo(VendedorEntity.class);
            em.persist(vendedor);
        }
        comprador = factory.manufacturePojo(CompradorEntity.class);
        comprador.setId(1L);
        comprador.setOrdenPedidoCompra(new ArrayList());
        em.persist(comprador);
        
        for(int i = 0; i < 3; i++)
        {
            OrdenPedidoEntity entity = factory.manufacturePojo(OrdenPedidoEntity.class);
            
            entity.setComprador(comprador);
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
        ordenLogic.createOrdenPedido(newOrden, comprador.getId(), vendedor.getId());
        OrdenPedidoEntity creada = compradorOrdenLogic.addOrdenPedido(comprador.getId(), newOrden.getId());
        Assert.assertNotNull(creada);
    }
    
    @Test
    public void getOrdenesTest()
    {
        
    }
    
    @Test
    public void getOrdenTest()
    {
        
    }
}
