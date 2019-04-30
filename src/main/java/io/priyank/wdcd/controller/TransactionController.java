package io.priyank.wdcd.controller;

import io.priyank.wdcd.model.Transaction;
import io.priyank.wdcd.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/accounts/{accountId}/page/{pageNumber}/size/{size}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<Transaction> findAllTransactionsByAccountId(@PathVariable("accountId") Integer accountId,
                                                                 @PathVariable("pageNumber") Integer pageNumber,
                                                                 @PathVariable("size") Integer size) {
        Page<Transaction> transactionPage = this.transactionService.findAllTransactionsByAccountId(accountId, PageRequest.of(pageNumber, size));
        Resources<Transaction> resources = new Resources<>(transactionPage.getContent());

        if(transactionPage.hasNext()) {
            resources.add(linkTo(methodOn(TransactionController.class)
                    .findAllTransactionsByAccountId(accountId,
                            transactionPage.nextPageable().getPageNumber(),
                            transactionPage.nextPageable().getPageSize())).withRel("next"));
        }

        if(transactionPage.hasPrevious()) {
            resources.add(linkTo(methodOn(TransactionController.class)
                    .findAllTransactionsByAccountId(accountId,
                            transactionPage.previousPageable().getPageNumber(),
                            transactionPage.previousPageable().getPageSize())).withRel("previous"));
        }

        if(transactionPage.getPageable() != null) {
            resources.add(linkTo(methodOn(TransactionController.class)
                    .findAllTransactionsByAccountId(accountId,
                            transactionPage.getPageable().getPageNumber(),
                            transactionPage.getPageable().getPageSize())).withSelfRel());
        }

        return  resources;
    }
}

