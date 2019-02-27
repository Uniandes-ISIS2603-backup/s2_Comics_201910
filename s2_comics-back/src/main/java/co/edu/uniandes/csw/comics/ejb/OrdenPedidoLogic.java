/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.ejb;

import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.comics.persistence.OrdenPedidoPersistence;
import java.util.ArrayList;
import java.util.List;
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
    
    public OrdenPedidoEntity createOrdenPedido(OrdenPedidoEntity ordenPedido)throws BusinessLogicException{
        if(ordenPedido.getComprador()==null || ordenPedido.getVendedor()==null ){
            throw new BusinessLogicException("La orden Pedido debe tener un cliente y un vendedor asociado.");
        }
        if(ordenPedido.getComic()==null  ){
            throw new BusinessLogicException("La orden Pedido debe tener uaunquesea un comic asociado.");
        }
        if( ordenPedido.getComic().getEnVenta()==false && ordenPedido.getTrueque()==null ){
            throw new BusinessLogicException("Si el  comic asociado a la orden esta para truque debe tener asociado el comic con el cual se hara el truque.");
        }
       ordenPedido= persistence.create(ordenPedido);
        return ordenPedido;
    }
    public OrdenPedidoEntity getOrdenPedido(Long id){
        return null;
    }
    
    public List<OrdenPedidoEntity> getOrdenesPedido(){
       List< OrdenPedidoEntity > resp= new  ArrayList<OrdenPedidoEntity >();
       return resp;
    }
    
    public void eliminarOrdenPedido(Long id){
        
    }
    
}
