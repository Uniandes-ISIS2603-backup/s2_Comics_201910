/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.persistence;

import co.edu.uniandes.csw.comics.entities.CalificacionEntity;
import co.edu.uniandes.csw.comics.entities.ColeccionistaEntity;
import co.edu.uniandes.csw.comics.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.comics.persistence.ColeccionistaPersistence;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class ColeccionistaPersistenceTest {
    @Inject
    private ColeccionistaPersistence coleccionista;
    
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
    public void createColeccionistaTest(){
        PodamFactory factory=new PodamFactoryImpl();
        ColeccionistaEntity newColeccionistaEntity=factory.manufacturePojo(ColeccionistaEntity.class);
        ColeccionistaEntity ce=coleccionista.create(newColeccionistaEntity);
        
        Assert.assertNotNull(ce);
        
        ColeccionistaEntity entity=em.find(ColeccionistaEntity.class,ce.getId());
        Assert.assertEquals(newColeccionistaEntity.getNombre(),entity.getNombre());
    }
}
