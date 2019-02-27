/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.comics.dtos;

import co.edu.uniandes.csw.comics.entities.CompradorEntity;
import co.edu.uniandes.csw.comics.entities.OrdenPedidoEntity;
import java.util.ArrayList;

/**
 *
 * @author estudiante
 */
public class CompradorDetailDTO extends CompradorDTO
{
    private ArrayList<OrdenPedidoDTO> pedidos;
    
    private ArrayList<ComicDeseoDTO> listaDeseos;
    
    private ArrayList<ComicDTO> carro;
    
    public CompradorDetailDTO()
    {
        super();
    }
    
    public CompradorDetailDTO(CompradorEntity entity)
    {
        super(entity);
        if(entity != null)
        {
            
        }
    }
}
