package br.com.temal.pa.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


public enum SituationInvoiceType implements Serializable {

	RECEBIDO,
	RECUSADO,
	CANCELADO,
	PENDENTE,
	CONCLUIDO
}