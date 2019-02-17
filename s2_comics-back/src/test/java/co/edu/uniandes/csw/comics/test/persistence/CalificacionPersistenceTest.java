/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.persistence;

import co.edu.uniandes.csw.comics.entities.CalificacionEntity;
import co.edu.uniandes.csw.comics.persistence.CalificacionPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
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
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
        
        
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
}
