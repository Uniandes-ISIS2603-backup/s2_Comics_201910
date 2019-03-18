/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.logic;

import co.edu.uniandes.csw.comics.ejb.OrdenPedidoComicLogic;
import co.edu.uniandes.csw.comics.ejb.OrdenPedidoLogic;
import co.edu.uniandes.csw.comics.entities.ComicEntity;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.entities.VendedorEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.comics.persistence.OrdenPedidoPersistence;
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
 * @author jp.rodriguezv
 */
@RunWith(Arquillian.class)
public class OrdenPedidoComicLogicTest {
           
     private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private OrdenPedidoLogic ordenPedidoLogic;//variable para tener acceso a ala logica de la aplicacion
    @Inject
    private OrdenPedidoComicLogic ordenPedidoComicLogic;//variable para tener acceso a ala logica de la aplicacion

    @PersistenceContext
    private EntityManager em;

     @Inject
    private UserTransaction utx;

     /**
      * lista de comics usado en las pruebas 
      */
    private List<ComicEntity> comicData = new ArrayList<ComicEntity>();

     /**
      * lista de ordenesPedido usado en las pruebas 
      */
    private List<OrdenPedidoEntity> ordenPedidoData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComicEntity.class.getPackage())
                .addPackage(OrdenPedidoLogic.class.getPackage())
                .addPackage(OrdenPedidoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     /**
     * Configuración inicial de la prueba.
     */
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from OrdenPedidoEntity").executeUpdate();
        em.createQuery("delete from ComicEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            OrdenPedidoEntity ordenesPedido = factory.manufacturePojo(OrdenPedidoEntity.class);
            em.persist(ordenesPedido);
            ordenPedidoData.add(ordenesPedido);
        }
        for (int i = 0; i < 3; i++) {
           ComicEntity entity = factory.manufacturePojo(ComicEntity.class);
            em.persist(entity);
            comicData.add(entity);
            if (i == 0) {
                ordenPedidoData.get(i).setComic(entity);
            }
        }
    }
    
     /**
     * Prueba para remplazar las instancias de comic asociadas a una instancia
     * de OrdenPedido.
     */
    @Test
    public void replaceComicTest() {
        OrdenPedidoEntity entity = ordenPedidoData.get(0);
        ordenPedidoComicLogic.replaceComic(entity.getId(), comicData.get(1).getId());
        entity = ordenPedidoLogic.getOrdenPedido(entity.getId());
        Assert.assertEquals(entity.getComic(), comicData.get(1));
    }

    /**
     * prueba para comprobar el correcto funcionamiento del metodo getComic
     * @throws BusinessLogicException 
     */
     @Test 
     public void getComicTest()throws BusinessLogicException{
        OrdenPedidoEntity entity=ordenPedidoData.get(0);
        ComicEntity comicEntity=comicData.get(0);
        ComicEntity response=ordenPedidoComicLogic.getComic(entity.getId());
      Assert.assertEquals(comicEntity.getAutor(), response.getAutor());
   
        
        
     }
}
