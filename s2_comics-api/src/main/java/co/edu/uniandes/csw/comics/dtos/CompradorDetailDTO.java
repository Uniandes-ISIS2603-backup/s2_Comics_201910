/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.dtos;

import co.edu.uniandes.csw.comics.entities.ComicDeseoEntity;
import co.edu.uniandes.csw.comics.entities.ComicEntity;
import co.edu.uniandes.csw.comics.entities.CompradorEntity;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.io.*;

/**
* Clase que extiende de {@link compradorDTO} para manejar las relaciones entre los
 * compradorDTO y otros DTOs. Para conocer el contenido de la un comprador vaya a la
 * documentacion de {@link compradorDTO}
 * @author juan pablo cano
 */
public class CompradorDetailDTO extends CompradorDTO implements Serializable
{
    private ArrayList<OrdenPedidoDTO> pedidos;
    
    private ArrayList<ComicDeseoDTO> listaDeseos;
    
    private ArrayList<ComicDTO> carro;
    
    public CompradorDetailDTO()
    {
        super();
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param compradorEntity La entidad de la cual se construye el DTO
     */
    public CompradorDetailDTO(CompradorEntity entity)
    {
        super(entity);
        if(entity != null)
        {
            pedidos = new ArrayList();
            for(OrdenPedidoEntity orden : entity.getOrdenPedidoCompra())
            {
                pedidos.add(new OrdenPedidoDTO(orden));
            }
            
            listaDeseos = new ArrayList();
            for(ComicDeseoEntity deseo : entity.getListaDeseos())
            {
                listaDeseos.add(new ComicDeseoDTO(deseo));
            }
            
            carro = new ArrayList();
            for(ComicEntity comic : entity.getCarro())
            {
                carro.add(new ComicDTO(comic));
            }
        }
    }
    
     /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el comprador.
     */
    @Override
    public CompradorEntity toEntity()
    {
        CompradorEntity compradorEntity = super.toEntity();
        if(getPedidos() != null)
        {
            List<OrdenPedidoEntity> pedidosEntity = new ArrayList();
            for(OrdenPedidoDTO pedido : getPedidos())
            {
                pedidosEntity.add(pedido.toEntity());
            }
            compradorEntity.setOrdenPedidoCompra(pedidosEntity);
        }
        if(getListaDeseos() != null)
        {
            List<ComicDeseoEntity> deseos = new ArrayList();
            for(ComicDeseoDTO deseo : getListaDeseos())
            {
                deseos.add(deseo.toEntity());
            }
            
            compradorEntity.setListaDeseos(deseos);
        }
        if(getCarro() != null)
        {
            List<ComicEntity> comics = new ArrayList();
            for(ComicDTO comic : getCarro())
            {
                comics.add(comic.toEntity());
            }
            compradorEntity.setCarro(comics);
        }
        return compradorEntity;
    }

    /**
     * @return the pedidos
     */
    public ArrayList<OrdenPedidoDTO> getPedidos() {
        return pedidos;
    }

    /**
     * @param pedidos the pedidos to set
     */
    public void setPedidos(ArrayList<OrdenPedidoDTO> pedidos) {
        this.pedidos = pedidos;
    }

    /**
     * @return the listaDeseos
     */
    public ArrayList<ComicDeseoDTO> getListaDeseos() {
        return listaDeseos;
    }

    /**
     * @param listaDeseos the listaDeseos to set
     */
    public void setListaDeseos(ArrayList<ComicDeseoDTO> listaDeseos) {
        this.listaDeseos = listaDeseos;
    }

    /**
     * @return the carro
     */
    public ArrayList<ComicDTO> getCarro() {
        return carro;
    }

    /**
     * @param carro the carro to set
     */
    public void setCarro(ArrayList<ComicDTO> carro) {
        this.carro = carro;
    }

    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
