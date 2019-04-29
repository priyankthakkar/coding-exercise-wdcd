package io.priyank.wdcd.controller;

import io.priyank.wdcd.model.Account;
import io.priyank.wdcd.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "/page/{pageNumber}/size/{size}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<Account> getAccounts(@PathVariable(name = "pageNumber") Integer pageNumber,
                                          @PathVariable(name = "size") Integer size) {
        Pageable pageable = PageRequest.of(pageNumber, size);

        Page<Account> accountsPage = this.accountService.getAccounts(pageable);

        Resources<Account> resources = new Resources<>(accountsPage.getContent());
        if (accountsPage.hasNext()) {
            resources.add(linkTo(methodOn(AccountController.class)
                    .getAccounts(accountsPage.nextPageable().getPageNumber(), accountsPage.nextPageable().getPageSize()))
                    .withRel("next"));
        }

        if (accountsPage.hasPrevious()) {
            resources.add(linkTo(methodOn(AccountController.class)
                    .getAccounts(accountsPage.previousPageable().getPageNumber(), accountsPage.previousPageable().getPageSize()))
                    .withRel("previous"));
        }

        if (accountsPage.getPageable() != null) {
            resources.add(linkTo(methodOn(AccountController.class)
                    .getAccounts(accountsPage.getPageable().getPageNumber(), accountsPage.getPageable().getPageSize()))
                    .withSelfRel());
        }

        return resources;
    }
}

