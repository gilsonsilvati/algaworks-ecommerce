package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.model.enums.StatusPedido;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pedido")
public class Pedido {

    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    private LocalDateTime dataPedido;
    private LocalDateTime dataConclusao;
    private Integer notaFiscalId;
    private BigDecimal total;
    private StatusPedido status;

}
