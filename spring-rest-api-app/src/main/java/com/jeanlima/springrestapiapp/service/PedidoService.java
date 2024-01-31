package com.jeanlima.springrestapiapp.service;

import com.jeanlima.springrestapiapp.model.Pedido;
import com.jeanlima.springrestapiapp.rest.dto.PedidoDTO;



public interface PedidoService {
    Pedido salvar( PedidoDTO dto );
    
}
