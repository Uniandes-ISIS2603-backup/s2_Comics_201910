/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.persistence;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
 @RunWith(Arquillian.class)

/**
 *
 * @author estudiante
 */
public class ComicPersistanceTest {
    
   @Deployment
   
   public static JavaArchive createDeployment (){
   
       return ShrinkWrap.create(JavaArchive.class).addPackage(ComicEntity.class.getPackage()).addPackage(ComicPersistence.class.getPackage()).addAsManifestResource("META-INF/")
   }
    
   @Test
   
   public Test (){
   
       private comicPersistance = comicPersistance.create();
   }
}
