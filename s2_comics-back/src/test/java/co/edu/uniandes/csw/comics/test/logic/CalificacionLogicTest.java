/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.logic;

import co.edu.uniandes.csw.comics.ejb.CalificacionLogic;
import co.edu.uniandes.csw.comics.entities.CalificacionEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
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
public class CalificacionLogicTest {
    @Inject
    private CalificacionLogic calificacionLogic;
      @PersistenceContext
    private EntityManager em;
    
    
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
        CalificacionEntity result=calificacionLogic.createCalificacion(entity);
        Assert.assertNotNull(result);
       
        CalificacionEntity newEntity=em.find(CalificacionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(),entity.getId());
        Assert.assertEquals(newEntity.getComentarios(),entity.getComentarios());
    }
    @Test( expected=BusinessLogicException.class)
    public void calificacionSinPuntuacionTest()throws BusinessLogicException{
        PodamFactory factory=new PodamFactoryImpl();
        CalificacionEntity entity=factory.manufacturePojo(CalificacionEntity.class);
        entity.setPuntuacion(null);
        calificacionLogic.createCalificacion(entity);
    }
}
