package com.jeanlima.springrestapiapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanlima.springrestapiapp.model.ItemPedido;



public interface ItemPedidoRepository extends JpaRepository<ItemPedido,Integer>{
    
}
