package io.priyank.wdcd.it;

import io.priyank.wdcd.WdcdApplication;
import io.priyank.wdcd.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = WdcdApplication.class)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-integrationtest.properties")
public class ControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AccountService accountService;

    @Test
    public void test_AccountController_getAccounts() throws Exception {
        mvc.perform(get("/accounts/page/9/size/10")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$._links.previous.href", is("http://localhost/accounts/page/8/size/10")))
            .andExpect(jsonPath("$._links.self.href", is("http://localhost/accounts/page/9/size/10")));
    }

    @Test
    public void test_controller_ExceptionHandling() throws Exception {
         mvc.perform(get("/accounts/page/9/si")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void test_TransactionController_findAllTransactionsByAccountId() throws Exception {
        mvc.perform(get("/transactions/accounts/204478037/page/1/size/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$._links.previous.href", is("http://localhost/transactions/accounts/204478037/page/0/size/5")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/transactions/accounts/204478037/page/1/size/5")))
                .andExpect(jsonPath("$._links.next.href", is("http://localhost/transactions/accounts/204478037/page/2/size/5")))
                .andExpect(jsonPath("$._embedded.transactionList[0].account.accountNumber", is(204478037)));
    }
}
