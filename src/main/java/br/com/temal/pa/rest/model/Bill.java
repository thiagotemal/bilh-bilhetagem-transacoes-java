package br.com.temal.pa.rest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ApiModel(value = "Parametros de entrada obrigatórios para fazer um billhetagem")
public class Bill implements Serializable {


	private static final long serialVersionUID = 1492453980104260017L;

	@ApiModelProperty( example = "5444", hidden = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

	@ApiModelProperty( example = "ce4d0520-75c0-48e6-8484-674cfb56f79f")
	private String nsuTransaction;

	@ApiModelProperty( example = "65147475")
	private String originAccount;


	@ApiModelProperty( example = "2021-12-31", hidden = true)
	private LocalDateTime creationDate = LocalDateTime.now();

	@ApiModelProperty( example = "2021-12-31")
	private LocalDate transactionDate ;

	@ApiModelProperty( example = "RECEBIDO", hidden = true)
	@Enumerated(EnumType.STRING)
	private SituationInvoiceType status = SituationInvoiceType.RECEBIDO;

	@ApiModelProperty(
			value = "Tipo da transação ",
			example = "TED",
			required = true,
			position = 1)
	@Column(name = "transactiontype")
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
}
