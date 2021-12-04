package br.com.temal.pa.rest.controller;


import br.com.temal.pa.rest.model.*;

import br.com.temal.pa.rest.repository.AccountEntryRepository;
import br.com.temal.pa.rest.repository.PersonRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/transactions")
@Api(value = "/v1/transactions")
public class BillsController {

    @Autowired
    private PersonRepository personService;

    @Autowired
    private AccountEntryRepository accountEntryRepository;



    @ApiOperation("Listar todas as Bilhatagens")
    @ApiResponses(
            @ApiResponse(
                    code = 200,
                    message = "Ok",
                    response = DataBillsPresenter.class))
    @RequestMapping(method = RequestMethod.GET,path = "/accounts/{originAccount}/bills" )
    @ResponseBody
    public ResponseEntity FindAll(@ApiParam(value = "ID da conta",required=true) @PathVariable("originAccount") String accountId) {
   //     logger.info("inciando metodo listagem");
        try {
            List<Bill> bills = this.personService.findByOriginAccount(accountId);
            if (bills != null && !bills.isEmpty() ) {
                return  ResponseEntity.ok().body(DataBillsPresenter.builder().data(bills).build());
            } else {
                return new ResponseEntity<>(new HashMap<>().put("message", "Não encontrado"),
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            //logger.info("Erro ao buscar lista de pessoas.");
            //logger.error(e.getMessage());
            return new ResponseEntity<>(new HashMap<>().put("message", "Ocorreu na requisição"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

 /*   @ApiOperation("Consultar uma pessoa pelo id")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity personById(@PathVariable Long id) throws Exception {
        try {
            Invoice person = this.personService.findById(id);

            if (person != null) {
                return new ResponseEntity<>(new PersonPresenter(person), HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
           // logger.info("Erro ao buscar pessoa registrados.");
            //logger.error(e.getMessage());
            return new ResponseEntity<>(new HashMap<>().put("message", "Ocorreu na requisição"),
                    HttpStatus.BAD_REQUEST);
        }
    }*/


    @RequestMapping(method = RequestMethod.POST,path = "/v1/transactions/bills" )
    @ApiOperation(" Enviar bilhetagem ")
    @ApiResponses(
            @ApiResponse(
                    code = 201,
                    message = "Created",
                    response = DataPresenter.class))
    public ResponseEntity Send(@RequestBody Bill bill) throws Exception {
        try {
            var exists = this.personService.findByNsuTransaction(bill.getNsuTransaction());
            if (exists.isEmpty()) {
                this.accountEntryRepository.save(AccountEntry.builder()
                        .creationDate(bill.getCreationDate())
                        .nsuTransaction(bill.getNsuTransaction())
                        .transactionDate(bill.getTransactionDate())
                        .originAccount(bill.getOriginAccount())
                        .accountEntryType(AccountEntryType.DEBITO).build());

                Bill personSaved = this.personService.save(bill);

                return ResponseEntity.ok(DataPresenter.builder().data(personSaved).build());
            } else {
                return new ResponseEntity<>(exists, HttpStatus.CONFLICT);
            }

        } catch (Exception e) {
            //logger.info("Erro ao alterar pessoa registrados.");
            //logger.error(e.getMessage());
            return new ResponseEntity<>(new HashMap<>().put("message", "Ocorreu na requisição"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

  /*  @ApiOperation(" Alterar uma pessoa pelo id")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiImplicitParams(
            @ApiImplicitParam(name = "x-transaction-id", value = "Transaction ID", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "3bd28702-0fc1-4802-814a-9de747de3de2", type = "string")
    )
    public ResponseEntity<Object> updatePerson(@RequestBody Invoice person, @PathVariable long id) {

        Invoice personUpdated = this.personService.findById(id);

        if (personUpdated != null) {
            return ResponseEntity.notFound().build();
        }
        person.setId(id);

        this.personService.save(person);

        return ResponseEntity.noContent().build();
    }*/
/*

    @ApiOperation(" Deletar uma pessoa pelo id")
    @DeleteMapping("/{id}")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "x-transaction-id", value = "Transaction ID", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class, example = "3bd28702-0fc1-4802-814a-9de747de3de2", type = "string")
    )
    public void deletePerson(@PathVariable long id) {
        this.personService.delete(id);
    }
*/

}
