/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.persistence;

import co.edu.uniandes.csw.comics.entities.CalificacionEntity;
import co.edu.uniandes.csw.comics.entities.VendedorEntity;
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
public class CalificacionPersistenceTest {
    @Inject
    private CalificacionPersistence cp;
    
    @PersistenceContext
    private EntityManager em;
     @Inject
    UserTransaction utx;
      private List<CalificacionEntity> data = new ArrayList<>();
       private List<VendedorEntity> dataVendedor = new ArrayList<VendedorEntity>();
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
        
        
    }
     @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
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
        PodamFactory factory = new PodamFactoryImpl();
     
           for (int i = 0; i < 3; i++) {
            VendedorEntity entity = factory.manufacturePojo(VendedorEntity.class);

            em.persist(entity);
            dataVendedor.add(entity);
        }
              for (int i = 0; i < 3; i++) {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            if(i==0){
                entity.setVendedor(dataVendedor.get(0));
            }
            em.persist(entity);
            data.add(entity);
        }
    }
    @Test
    public void createCalificacionTest(){
        PodamFactory factory=new PodamFactoryImpl();
        CalificacionEntity newCalificacionEntity=factory.manufacturePojo(CalificacionEntity.class);
        CalificacionEntity ce=cp.create(newCalificacionEntity);
        
        Assert.assertNotNull(ce);
        
        CalificacionEntity entity=em.find(CalificacionEntity.class,ce.getId());
        Assert.assertEquals(newCalificacionEntity.getComentarios(),entity.getComentarios());
    }

       @Test
    public void getCalificacionTest() {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity newEntity = cp.find(dataVendedor.get(0).getId(),entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getPuntuacion(), newEntity.getPuntuacion());
        Assert.assertEquals(entity.getComentarios(), newEntity.getComentarios());
    }
      @Test
    public void updateCalificacionTest() {
        CalificacionEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
       CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);

        newEntity.setId(entity.getId());

        cp.update(newEntity);

        CalificacionEntity resp = em.find(CalificacionEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getPuntuacion(), resp.getPuntuacion());
    }
       @Test
    public void deleteCalificacionTest() {
        CalificacionEntity entity = data.get(0);
        cp.delete(entity.getId());
        CalificacionEntity deleted = em.find(CalificacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
