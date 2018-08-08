package info.blockchain.controller;

import info.blockchain.handler.ITransactionHandler;
import info.blockchain.helper.RawJsonStringLoaderHelper;
import module.AppModuleTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(classes = AppModuleTest.class)
@WebAppConfiguration
@AutoConfigureMockMvc
class RequestControllerIntegrationTest {

    @Inject
    private MockMvc mockMvc;

    @Mock
    private ITransactionHandler transactionHandler;

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(transactionHandler);
    }

    @Test
    void getUnspentTransaction_AllValidParameters() throws Exception {
        mockMvc.perform(get("/address/{wallet}", "1NNKN1zQnjXnGLJiZmYMmaB74Uy2D6X5Ug"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(RawJsonStringLoaderHelper.getRawJsonStringResponse("unspent-transaction-json")));
    }

    @Test
    void getUnspentTransaction_ValidAddress_NoContent() throws Exception {
        mockMvc.perform(get("/address/{wallet}", "1Aff4FgrtA1dZDwajmknWTwU2WtwUvfiXa"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"));
    }

    @Test
    void getUnspentTransaction_NoSuchAddress() throws Exception {
        mockMvc.perform(get("/address/{wallet}", "1NNKN1zQnjXnGLJiZmYMmaB74Uy2D6X5U1"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"));
    }

    @Test
    void getUnspentTransaction_InvalidAddress_LengthGreaterThan() throws Exception {
        mockMvc.perform(get("/address/{wallet}", "1NNKN1zQnjXnGLJiZmYMmaB74Uy2D6X5U1dfr352"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"));
    }

    @Test
    void getUnspentTransaction_InvalidAddress_LengthLessThan() throws Exception {
        mockMvc.perform(get("/address/{wallet}", "1NNKN1zQnjXnGLJi"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"));
    }

    @Test
    void getUnspentTransaction_NoAddressSupplied() throws Exception {
        mockMvc.perform(get("/address/{wallet}", ""))
                .andExpect(status().is4xxClientError());
    }
}