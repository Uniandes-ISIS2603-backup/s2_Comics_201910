/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.logic;

import co.edu.uniandes.csw.comics.ejb.OrdenPedidoLogic;
import co.edu.uniandes.csw.comics.ejb.VendedorLogic;
import co.edu.uniandes.csw.comics.ejb.VendedorOrdenPedidoLogic;
import co.edu.uniandes.csw.comics.entities.ComicEntity;
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

    
    private List<CompradorEntity> dataComprador = new ArrayList<CompradorEntity>();
     private List<VendedorEntity> dataVendedor = new ArrayList<VendedorEntity>();
        private List<ComicEntity> dataComics = new ArrayList<ComicEntity>();
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
         em.createQuery("delete from OrdenPedidoEntity").executeUpdate();
         em.createQuery("delete from ComicEntity").executeUpdate();
         em.createQuery("delete from CompradorEntity").executeUpdate();
         
        em.createQuery("delete from VendedorEntity").executeUpdate();
        
        
    }
        private void insertData() {
        
            for (int i = 0; i < 3; i++) {
            ComicEntity comics = factory.manufacturePojo(ComicEntity.class);
            em.persist(comics);
            dataComics.add(comics);
        }
        for (int i = 0; i < 3; i++) {
            VendedorEntity entity = factory.manufacturePojo(VendedorEntity.class);
            em.persist(entity);
            dataVendedor.add(entity);
           
        }
          for (int i = 0; i < 3; i++) {
           CompradorEntity entity = factory.manufacturePojo(CompradorEntity.class);
            em.persist(entity);
            dataComprador.add(entity);
           
        }
            for (int i = 0; i < 3; i++) {
           OrdenPedidoEntity entity = factory.manufacturePojo(OrdenPedidoEntity.class);
            em.persist(entity);
            data.add(entity);
          
        }
           
           
        
    }
       @Test
    public void addOrdenTest() throws BusinessLogicException {
   OrdenPedidoEntity ordenEntity = data.get(0);
   OrdenPedidoEntity response=vendedorOrdenLogic.addOrden(dataVendedor.get(0).getId(), ordenEntity.getId());
   Assert.assertNotNull(response);
   Assert.assertEquals(ordenEntity.getId(),response.getId());
   Assert.assertEquals(ordenEntity.getEstado(),response.getEstado());
   Assert.assertEquals(ordenEntity.getVendedor(),response.getVendedor());
    }
    
    @Test
    public void getOrdenesTest(){
        
        List<OrdenPedidoEntity> lista=vendedorOrdenLogic.getOrdenes(dataVendedor.get(0).getId());
    
        Assert.assertEquals(0,lista.size());
    }  
}
