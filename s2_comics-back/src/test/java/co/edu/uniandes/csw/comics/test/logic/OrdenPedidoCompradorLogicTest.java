/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.test.logic;

import co.edu.uniandes.csw.comics.ejb.OrdenPedidoLogic;
import co.edu.uniandes.csw.comics.ejb.OrdenPedidoCompradorLogic;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.entities.CompradorEntity;
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
public class OrdenPedidoCompradorLogicTest {
        
     private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private OrdenPedidoLogic ordenPedidoLogic;
    @Inject
    private OrdenPedidoCompradorLogic ordenPedidoCompradorLogic;

    @PersistenceContext
    private EntityManager em;

     @Inject
    private UserTransaction utx;

    private List<CompradorEntity> compradorData = new ArrayList<CompradorEntity>();

    private List<OrdenPedidoEntity> ordenPedidoData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompradorEntity.class.getPackage())
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
        em.createQuery("delete from CompradorEntity").executeUpdate();
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
           CompradorEntity entity = factory.manufacturePojo(CompradorEntity.class);
            em.persist(entity);
            compradorData.add(entity);
            if (i == 0) {
                ordenPedidoData.get(i).setComprador(entity);
            }
        }
    }
    
     /**
     * Prueba para remplazar las instancias de Books asociadas a una instancia
     * de Editorial.
     */
    @Test
    public void replaceVendedorTest() {
        OrdenPedidoEntity entity = ordenPedidoData.get(0);
        ordenPedidoCompradorLogic.replaceComprador(entity.getId(), compradorData.get(1).getId());
        entity = ordenPedidoLogic.getOrdenPedido(entity.getId());
        Assert.assertEquals(entity.getComprador(), compradorData.get(1));
    }

}
