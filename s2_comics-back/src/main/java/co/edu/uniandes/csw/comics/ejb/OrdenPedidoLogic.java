/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.ejb;

import co.edu.uniandes.csw.comics.entities.ComicEntity;
import co.edu.uniandes.csw.comics.entities.CompradorEntity;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.entities.VendedorEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.comics.persistence.ComicPersistence;
import co.edu.uniandes.csw.comics.persistence.CompradorPersistence;
import co.edu.uniandes.csw.comics.persistence.OrdenPedidoPersistence;
import co.edu.uniandes.csw.comics.persistence.VendedorPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class OrdenPedidoLogic {
     private final static Logger LOGGER = Logger.getLogger(OrdenPedidoLogic.class.getName());
    
    @Inject
    private OrdenPedidoPersistence persistence;
    
     @Inject
    private ComicPersistence comicPersistence;
    

     @Inject
    private VendedorPersistence vendedorPersistence;
   
      @Inject
    private CompradorPersistence compradorPersistence;
   
    
    public OrdenPedidoEntity createOrdenPedido(OrdenPedidoEntity ordenPedido, Long vendedorId, Long compradoraiD, Long comicId, Long truequeId)throws BusinessLogicException
    {
        if(persistence.find(ordenPedido.getId())!=null ){
        new BusinessLogicException("ya existe una ordenPedido con esta id"); 
        }     
       
        if(ordenPedido.getComprador()==null || ordenPedido.getVendedor()==null ){
            throw new BusinessLogicException("La orden Pedido debe tener un cliente y un vendedor asociado.");
        }
         if(ordenPedido.getComic()==null  ){
            throw new BusinessLogicException("La orden Pedido debe tener uaunquesea un comic asociado.");
        }
        if( ordenPedido.getComic().getEnVenta()==false && ordenPedido.getTrueque()==null ){
            throw new BusinessLogicException("Si el  comic asociado a la orden esta para truque debe tener asociado el comic con el cual se hara el truque.");

        }
        
         VendedorEntity vendedor=vendedorPersistence.find(vendedorId);
         ordenPedido.setVendedor(vendedor);
         CompradorEntity comprador =compradorPersistence.find(compradoraiD);
         ordenPedido.setComprador(comprador);
         ComicEntity comic = comicPersistence.find(comicId);
         ordenPedido.setComic(comic);
       if(truequeId!=null){
         ComicEntity comicTrueque = comicPersistence.find(truequeId);
         ordenPedido.setTrueque(comicTrueque);
       }
         ordenPedido= persistence.create(ordenPedido);
         return ordenPedido;
    }
         

/**    public OrdenPedidoEntity createOrdenPedido(OrdenPedidoEntity ordenPedido)throws BusinessLogicException{

        if(ordenPedido.getComprador()==null || ordenPedido.getVendedor()==null ){
            throw new BusinessLogicException("La orden Pedido debe tener un cliente y un vendedor asociado.");
        }
       /** if(ordenPedido.getComic()==null  ){
            throw new BusinessLogicException("La orden Pedido debe tener uaunquesea un comic asociado.");
        }
        if( ordenPedido.getComic().getEnVenta()==false && ordenPedido.getTrueque()==null ){
            throw new BusinessLogicException("Si el  comic asociado a la orden esta para truque debe tener asociado el comic con el cual se hara el truque.");

        }
       ordenPedido= persistence.create(ordenPedido);

     
        return ordenPedido;
    }*/
    public OrdenPedidoEntity getOrdenPedido(Long id){
       LOGGER.log(Level.INFO, "Inicia proceso de consultar la orden pedido con id = {0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        OrdenPedidoEntity ordenPedidoEntity = persistence.find(id);
        if (ordenPedidoEntity==(null)) {
            LOGGER.log(Level.SEVERE, "El vendedor con el id = {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el vendedor con id = {0}", id);
        return ordenPedidoEntity;
    }
    
          public OrdenPedidoEntity updateOrdenPedido(Long id, OrdenPedidoEntity ordenPedidoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la ordenPedido con id = {0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        OrdenPedidoEntity newEntity = persistence.update(ordenPedidoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la ordenPedido con id = {0}", ordenPedidoEntity.getId());
        return newEntity;
   
    }
    
    public List<OrdenPedidoEntity> getOrdenesPedido()
    {
       LOGGER.log(Level.INFO, "Inicia proceso de consulta de todas las ordenes pedido");
        List<OrdenPedidoEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proces de consulta de todas las ordenes pedido");
        return lista;
    }
    
    public void deleteOrdenPedido(Long id) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia el proceso de eliminar a la ordenPedido con id={0}", id);
        OrdenPedidoEntity entity = getOrdenPedido(id);
   /**     if(entity.getEstado()==4)
        { 
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Se ha terminado el proceso de eliminación del comprador con id={0}", id);
        }
        else{
             throw new BusinessLogicException("La orden Pedido debe estar en estado -finalizado- para poderla eliminar .");
        }*/
   persistence.delete(id);
        
    }
    
}
