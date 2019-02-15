/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.persistence;

import javax.persistence.EntityManager;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoTruequeEntity;
import co.edu.uniandes.csw.comics.persistence.OrdenPedidoTruequePersistence;
//import co.edu.uniandes.csw.comics.persistence.VendedorPersistence;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author estudiante
 */
public class OrdenPedidoTruequePersistenceTest {
    @Inject
        OrdenPedidoTruequePersistence op;
   @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(OrdenPedidoTruequeEntity.class.getPackage())
                .addPackage(OrdenPedidoTruequePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
   }
    
   @Test
   public void createTest(){
       
       PodamFactory factory = new PodamFactoryImpl();
        
        OrdenPedidoTruequeEntity newEntity = factory.manufacturePojo(OrdenPedidoTruequeEntity.class);
        
        OrdenPedidoTruequeEntity result = op.create(newEntity);
        
        Assert.assertNotNull(result);
       
   }
}
