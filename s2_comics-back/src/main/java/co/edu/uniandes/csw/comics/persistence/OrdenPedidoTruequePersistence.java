/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author estudiante
 */
@Stateless
public class OrdenPedidoTruequePersistence {
 @PersistenceContext(unitName = "comicsPU")
  protected EntityManager em;


public OrdenPedidoTruequeEntity create(OrdenPedidoTruequeEntity entity)
{
 em.persist(entity);
return entity;
}

}
