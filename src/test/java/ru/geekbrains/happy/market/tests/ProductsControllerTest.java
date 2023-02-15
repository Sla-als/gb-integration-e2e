package ru.geekbrains.happy.market.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.geekbrains.happy.market.dto.ProductDto;
import ru.geekbrains.happy.market.model.Product;
import ru.geekbrains.happy.market.services.ProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductsControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductService productService;
    @Test
    public void getAllProductsTest() throws Exception {
        mvc.perform(get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(3)))
                .andExpect(jsonPath("$.content[0].title", is("Bread")))
                .andExpect(jsonPath("$.content[0].price", greaterThan(10)));
    }

    @Test
    public void givenProductWhenSaveThenGetOk() {
        Product product = new Product();
        product.setTitle("TestProduct");
        product.setPrice(50);
        product.setId(10L);
        //        Mockito
        //                .doReturn(Optional.of(demoProduct))
        //                .when(productRepository)
        //                .findById(10L);
        Product p = productService.findProductById(10L).get();
        //Mockito.verify(productRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(10L));
        assertEquals("Snickers", p.getTitle());
    }
}
