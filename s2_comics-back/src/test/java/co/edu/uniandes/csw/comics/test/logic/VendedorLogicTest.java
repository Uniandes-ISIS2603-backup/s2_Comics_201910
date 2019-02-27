/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.logic;

import co.edu.uniandes.csw.comics.ejb.CalificacionLogic;
import co.edu.uniandes.csw.comics.ejb.VendedorLogic;
import co.edu.uniandes.csw.comics.entities.CalificacionEntity;
import co.edu.uniandes.csw.comics.entities.VendedorEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.comics.persistence.CalificacionPersistence;
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
public class VendedorLogicTest {
  

    private PodamFactory factory = new PodamFactoryImpl();
    @Inject
    private VendedorLogic vendedorLogic;
      @PersistenceContext
    private EntityManager em;
     @Inject
    private UserTransaction utx;

    private List<VendedorEntity> data = new ArrayList<>();
    
     @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VendedorEntity.class.getPackage())
                .addPackage(VendedorLogic.class.getPackage())
                .addPackage(VendedorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
        
        
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
    }
         private void insertData() {
        for (int i = 0; i < 3; i++) {
           VendedorEntity entity = factory.manufacturePojo(VendedorEntity.class);
            em.persist(entity);
           
            data.add(entity);
        }
     
    }
            @Test
    public void createVendedorTest() throws BusinessLogicException {
        VendedorEntity newEntity = factory.manufacturePojo(VendedorEntity.class);
        VendedorEntity result = vendedorLogic.createVendedor(newEntity);
        Assert.assertNotNull(result);
        VendedorEntity entity = em.find(VendedorEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getAlias(), entity.getAlias());
    }
     @Test(expected = BusinessLogicException.class)
    public void createEditorialConMismoNombreTest() throws BusinessLogicException {
        VendedorEntity newEntity = factory.manufacturePojo(VendedorEntity.class);
        newEntity.setAlias(data.get(0).getAlias());
        vendedorLogic.createVendedor(newEntity);
    }
    @Test
    public void getVendedoresTest() {
        List<VendedorEntity> list = vendedorLogic.getVendedores();
        Assert.assertEquals(data.size(), list.size());
        for (VendedorEntity entity : list) {
            boolean found = false;
            for (VendedorEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
      @Test
    public void getVendedorTest() {
        VendedorEntity entity = data.get(0);
        VendedorEntity resultEntity = vendedorLogic.getVendedor(entity.getAlias());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
    }
     @Test
    public void updateVendedorTest() {
        VendedorEntity entity = data.get(0);
        VendedorEntity pojoEntity = factory.manufacturePojo(VendedorEntity.class);
        pojoEntity.setId(entity.getId());
        vendedorLogic.updateVendedor(pojoEntity.getAlias(), pojoEntity);
        VendedorEntity resp = em.find(VendedorEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
    }
       @Test
    public void deleteVendedorTest() throws BusinessLogicException {
        VendedorEntity entity = data.get(1);
        vendedorLogic.deleteVendedor(entity.getId());
        VendedorEntity deleted = em.find(VendedorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
