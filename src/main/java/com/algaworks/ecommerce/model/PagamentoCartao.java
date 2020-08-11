package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.model.base.EntidadeBase;
import com.algaworks.ecommerce.model.enums.StatusPagamento;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "pagamento_cartao")
@Getter @Setter
public class PagamentoCartao extends EntidadeBase {

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    private String numero;

}
