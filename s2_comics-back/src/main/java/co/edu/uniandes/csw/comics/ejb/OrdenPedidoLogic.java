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
 * @author jp.rodriguezv
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
   
    /**
     * crea una nueva orden de pedido verifica las reglas de negocio
     * @param ordenPedido el ordenPedidoEntity con la informacion de la nueva ordenPedido
     * @param vendedorId el id del vendedor asociado a la nueva ordenPedido
     * @param compradoraiD el id del comprador asociado a la nueva ordenPedido
     * @param comicId el id del comic asociado a la nueva ordenPedido
     * @param truequeId el id del comicTrueque asociado a ala nueva ordenPedido
     * @return la nueva ordenPedido creada
     * @throws BusinessLogicException 
     * Error cuando : ya existe una orden con esta id,
     * Error cuando : no se tiene asociado ningun vendedor, comprador o comic
     * Error cuando : el comic esta para truque y no se asocia ningun comic para trueque
     */
    public OrdenPedidoEntity createOrdenPedido(OrdenPedidoEntity ordenPedido, Long vendedorId, Long compradoraiD, Long comicId, Long truequeId)throws BusinessLogicException
    {
         LOGGER.log(Level.INFO, "creando ordenPedido");
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
       ordenPedido.setEstado(OrdenPedidoEntity.Estado.EN_ESPERA);
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
         
         LOGGER.log(Level.INFO, "creando ordenPedido 3");
    
         return ordenPedido;
    }
         

/**
 * Devuelve la ordenPedido con la id proporcionada
 * @param id  , id de la orden pedido bsucada
 * @return la ordenPedido buscada
 */
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
    
    /**
     * actualiza la ordenPedido con el identificadordado
     * @param id , identificador de la OrdenPedido a actualizar
     * @param ordenPedidoEntity , entidad con la informacion de la ordenPedido
     * @return la ordenPedido actualizada
     */
          public OrdenPedidoEntity updateOrdenPedido(Long id, OrdenPedidoEntity ordenPedidoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la ordenPedido con id = {0}", id);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
       
        OrdenPedidoEntity newEntity = persistence.update(ordenPedidoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la ordenPedido con id = {0}", ordenPedidoEntity.getId());
        return newEntity;
   
    }
          
          public void ActualizarEstado(Long id, OrdenPedidoEntity.Estado nuevoEstado) throws BusinessLogicException{
              OrdenPedidoEntity orden = persistence.find(id);
              if(orden.getEstado()==OrdenPedidoEntity.Estado.FINALIZADO){
                throw new BusinessLogicException("la orden ya esta finalizada");
              }
              if(orden.getEstado()==OrdenPedidoEntity.Estado.ACEPTADO && nuevoEstado==OrdenPedidoEntity.Estado.RECHAZADO){
                  throw new BusinessLogicException("la orden se encuentra aceptada, no se puede rechazar");
              }
               if(orden.getEstado()== nuevoEstado){
                  throw new BusinessLogicException("la orden ya se encuentra en este estado");
              }
                 orden.setEstado(nuevoEstado);
                 updateOrdenPedido(id, orden);
          }
   
          /** 
           * devuelve todas las ordenes pedido que tiene almacenada la aplicacion
           * @return ordenesPedido almacenadas
           */
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
      if(entity.getEstado()==OrdenPedidoEntity.Estado.FINALIZADO || entity.getEstado()==OrdenPedidoEntity.Estado.EN_ESPERA )
        { 
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Se ha terminado el proceso de eliminación del comprador con id={0}", id);
        }
        else{
             throw new BusinessLogicException("La orden Pedido debe estar en estado -finalizado- o -En espera- para poderla eliminar .");
        }
        
    }
    
}