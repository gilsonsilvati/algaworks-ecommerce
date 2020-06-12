package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.model.enums.StatusPagamento;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pagamentoCartao")
public class PagamentoCartao {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    private Integer pedidoId;
    private StatusPagamento status;
    private String numero;

}
