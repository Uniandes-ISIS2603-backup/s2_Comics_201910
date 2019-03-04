/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.ejb;

import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import co.edu.uniandes.csw.comics.entities.VendedorEntity;
import co.edu.uniandes.csw.comics.persistence.OrdenPedidoPersistence;
import co.edu.uniandes.csw.comics.persistence.VendedorPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author ca.orduz
 */
public class VendedorOrdenPedidoLogic {
    @Inject
   private VendedorPersistence vendedorPersistence;
    @Inject
   private OrdenPedidoPersistence ordenPersistence;
    
    
    public OrdenPedidoEntity addOrden(Long vendedoresId,Long ordenesId){
        OrdenPedidoEntity orden=ordenPersistence.find(ordenesId);
        VendedorEntity vendedor=vendedorPersistence.find(vendedoresId);
        vendedor.getOrdenes().add(orden);
        return ordenPersistence.find(ordenesId);
    }
    
    public List<OrdenPedidoEntity> getOrdenes(Long vendedoresId){
        return vendedorPersistence.find(vendedoresId).getOrdenes();
    }
    
      public OrdenPedidoEntity getOrden(Long vendedoresId,Long ordenesId){
          List<OrdenPedidoEntity> ordenes=vendedorPersistence.find(vendedoresId).getOrdenes();
          OrdenPedidoEntity orden=ordenPersistence.find(ordenesId);
          int index=ordenes.indexOf(orden);
          if(index>=0){
              return ordenes.get(index);
          }
        return null;
    }
      
      public List<OrdenPedidoEntity> replaceOrdenes(Long vendedoresId,List<OrdenPedidoEntity> ordenes){
          VendedorEntity vendedor=vendedorPersistence.find(vendedoresId);
          vendedor.setOrdenes(ordenes);
          return vendedor.getOrdenes();
      }
      
         public void removeOrden(Long vendedoresId,Long ordenesId){
         VendedorEntity vendedor=vendedorPersistence.find(vendedoresId);
           OrdenPedidoEntity orden=ordenPersistence.find(ordenesId);
          vendedor.getOrdenes().remove(orden);
      }
}
