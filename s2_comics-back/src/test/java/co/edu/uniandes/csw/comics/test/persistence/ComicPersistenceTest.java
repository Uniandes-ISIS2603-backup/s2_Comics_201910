/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.persistence;

import co.edu.uniandes.csw.comics.entities.ComicEntity;
import co.edu.uniandes.csw.comics.persistence.ComicPersistence;
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
 * @author Pietro Ehrlich
 */

@RunWith(Arquillian.class)
public class ComicPersistenceTest {
    
    @Inject
    private ComicPersistence cp;
    
    @PersistenceContext
    private EntityManager em; 
   
    @Deployment
    public static JavaArchive createDeployment (){
        return ShrinkWrap.create(JavaArchive.class)
               .addPackage(ComicEntity.class.getPackage())
               .addPackage(ComicPersistence.class.getPackage())
               .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
               .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Test
    public void createComicTest(){
        PodamFactory factory = new PodamFactoryImpl();
        ComicEntity newComicEntity = factory.manufacturePojo(ComicEntity.class);
        ComicEntity ce = cp.create(newComicEntity);
        
        Assert.assertNotNull(ce);
        
        ComicEntity entity = em.find(ComicEntity.class,ce.getId());
        Assert.assertEquals(newComicEntity.getNombre() , entity.getNombre());
    }
}