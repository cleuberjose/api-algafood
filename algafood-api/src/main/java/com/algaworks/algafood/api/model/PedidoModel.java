package com.algaworks.algafood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.algaworks.algafood.domain.model.PedidoStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoModel {
	private String codigo;
	private UsuarioModel usuario;
	private RestauranteResumoModel restaurante;
	private FormaPagamentoModel formaPagamento;
	private PedidoStatus pedidoStatus;
	private EnderecoModel endereco;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataEntrega;
	private List<PedidoItemModel> pedidoItens= new ArrayList<PedidoItemModel>();

}
