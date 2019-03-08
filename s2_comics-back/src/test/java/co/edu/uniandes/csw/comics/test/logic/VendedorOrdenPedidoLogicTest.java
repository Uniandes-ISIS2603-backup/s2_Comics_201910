/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.logic;

import co.edu.uniandes.csw.comics.ejb.OrdenPedidoLogic;
import co.edu.uniandes.csw.comics.ejb.VendedorLogic;
import co.edu.uniandes.csw.comics.ejb.VendedorOrdenPedidoLogic;
import co.edu.uniandes.csw.comics.entities.CompradorEntity;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.entities.VendedorEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.comics.persistence.VendedorPersistence;
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
 * @author ca.orduz
 */
@RunWith(Arquillian.class)
public class VendedorOrdenPedidoLogicTest {
      private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private VendedorOrdenPedidoLogic vendedorOrdenLogic;

    @Inject
    private OrdenPedidoLogic ordenPedidoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private VendedorEntity vendedor = new VendedorEntity();
   private CompradorEntity comprador = new CompradorEntity();
    private List<OrdenPedidoEntity> data = new ArrayList<>();
    
        @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VendedorEntity.class.getPackage())
                .addPackage(OrdenPedidoEntity.class.getPackage())
                .addPackage(VendedorOrdenPedidoLogic.class.getPackage())
                .addPackage(VendedorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
     @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
     private void clearData() {
        em.createQuery("delete from VendedorEntity").executeUpdate();
        em.createQuery("delete from OrdenPedidoEntity").executeUpdate();
    }
        private void insertData() {
        for (int i = 0; i < 3; i++) {
            comprador = factory.manufacturePojo(CompradorEntity.class);
            em.persist(comprador);
        }

        vendedor = factory.manufacturePojo(VendedorEntity.class);
        vendedor.setId(1L);
        vendedor.setOrdenes(new ArrayList<>());
        em.persist(vendedor);

        for (int i = 0; i < 3; i++) {
            OrdenPedidoEntity entity = factory.manufacturePojo(OrdenPedidoEntity.class);
          
            entity.setVendedor(vendedor);
           
            em.persist(entity);
            data.add(entity);
            vendedor.getOrdenes().add(entity);
        }
    }
       @Test
    public void addOrdenTest() throws BusinessLogicException {
        OrdenPedidoEntity newOrden = factory.manufacturePojo(OrdenPedidoEntity.class);
        newOrden.setComprador(comprador);
       newOrden.setVendedor(vendedor);
        ordenPedidoLogic.createOrdenPedido(newOrden, vendedor.getId(),  comprador.getId());
        OrdenPedidoEntity ordenPedidoEntity = vendedorOrdenLogic.addOrden(vendedor.getId(), newOrden.getId());
        Assert.assertNotNull(ordenPedidoEntity);

        
        Assert.assertEquals(ordenPedidoEntity.getComic(), newOrden.getComic());
        Assert.assertEquals(ordenPedidoEntity.getVendedor(), newOrden.getVendedor());
        Assert.assertEquals(ordenPedidoEntity.getEstado(), newOrden.getEstado());
        Assert.assertEquals(ordenPedidoEntity.getTarjetaCredito(), newOrden.getTarjetaCredito());

        
   
    }
}
