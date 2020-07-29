package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.model.enums.StatusPagamento;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "pagamento_cartao")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PagamentoCartao {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    private String numero;

}
