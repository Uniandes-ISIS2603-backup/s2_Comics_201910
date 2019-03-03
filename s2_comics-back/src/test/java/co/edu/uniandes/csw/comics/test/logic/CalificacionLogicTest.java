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
public class CalificacionLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();
    @Inject
    private CalificacionLogic calificacionLogic;
    @Inject
    private VendedorLogic vendedorLogic;
      @PersistenceContext
    private EntityManager em;
     @Inject
    private UserTransaction utx;

    private List<CalificacionEntity> data = new ArrayList<>();
    
    private List<VendedorEntity> dataVendedores = new ArrayList<VendedorEntity>();
     @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionLogic.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
        
        
    }
    
    @Test
    public void createCalificacionTest()throws BusinessLogicException{
         PodamFactory factory=new PodamFactoryImpl();
        CalificacionEntity entity=factory.manufacturePojo(CalificacionEntity.class);
        entity.setVendedor(dataVendedores.get(1));
        CalificacionEntity result=calificacionLogic.createCalificacion(dataVendedores.get(1).getId(),entity);
        Assert.assertNotNull(result);
       
        CalificacionEntity newEntity=em.find(CalificacionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(),entity.getId());
        Assert.assertEquals(newEntity.getComentarios(),entity.getComentarios());
    }
    @Test( expected=BusinessLogicException.class)
    public void calificacionSinPuntuacionTest()throws BusinessLogicException{
        PodamFactory factory=new PodamFactoryImpl();
        CalificacionEntity entity=factory.manufacturePojo(CalificacionEntity.class);
      entity.setVendedor(dataVendedores.get(1));
        entity.setPuntuacion(null);
        calificacionLogic.createCalificacion(dataVendedores.get(1).getId(),entity);
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
       
        em.createQuery("delete from CalificacionEntity").executeUpdate();
         em.createQuery("delete from VendedorEntity").executeUpdate();
    }
         private void insertData() {
                     for (int i = 0; i < 3; i++) {
            VendedorEntity entity = factory.manufacturePojo(VendedorEntity.class);
            em.persist(entity);
           
            dataVendedores.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
           entity.setVendedor(dataVendedores.get(1));
            em.persist(entity);
           
            data.add(entity);
        }
     
     
    }
             @Test
    public void getCalificacionesTest() throws BusinessLogicException{
        List<CalificacionEntity> list = calificacionLogic.getCalificaciones(dataVendedores.get(1).getId());
        Assert.assertEquals(data.size(), list.size());
        for (CalificacionEntity entity : list) {
            boolean found = false;
            for (CalificacionEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
        @Test
    public void getCalificacionTest() {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity resultEntity = calificacionLogic.getCalificacion(dataVendedores.get(1).getId(),entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getComentarios(), resultEntity.getComentarios());
        Assert.assertEquals(entity.getPuntuacion(), resultEntity.getPuntuacion());
    }
        @Test
    public void updateCalificacionTest() {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity pojoEntity = factory.manufacturePojo(CalificacionEntity.class);

        pojoEntity.setId(entity.getId());

        calificacionLogic.updateCalificacion(pojoEntity.getId(), pojoEntity);

        CalificacionEntity resp = em.find(CalificacionEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getComentarios(), resp.getComentarios());
        Assert.assertEquals(pojoEntity.getPuntuacion(), resp.getPuntuacion());
    }
       @Test
    public void deleteCalificacionTest() throws BusinessLogicException {
        CalificacionEntity entity = data.get(0);
        
        calificacionLogic.deleteCalificacion(dataVendedores.get(1).getId(), entity.getId());
        CalificacionEntity deleted = em.find(CalificacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
