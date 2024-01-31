package com.jeanlima.springrestapiapp.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.jeanlima.springrestapiapp.enums.StatusPedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Um cliente pode ter muitos pedidos!
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column
    private LocalDate dataPedido;

    //1000.00
    @Column(name = "total", precision = 20,scale = 2)
    private BigDecimal total;


    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public LocalDate getDataPedido() {
        return dataPedido;
    }
    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }
    public List<ItemPedido> getItens() {
        return itens;
    }
    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
    @Override
    public String toString() {
        return "Pedido [dataPedido=" + dataPedido + ", id=" + id + ", total=" + total + "]";
    }
    public StatusPedido getStatus() {
        return status;
    }
    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    
    

    
    
}
