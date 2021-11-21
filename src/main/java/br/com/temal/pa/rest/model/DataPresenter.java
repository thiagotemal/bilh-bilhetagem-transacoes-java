package br.com.temal.pa.rest.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@ApiModel(description = "Retorno da bilhetagem")
@Builder
public class DataPresenter implements Serializable {

    private static final long serialVersionUID = 1L;

    private Bill data;

}

