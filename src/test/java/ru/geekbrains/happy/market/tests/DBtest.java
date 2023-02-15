package ru.geekbrains.happy.market.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.happy.market.model.Product;
import ru.geekbrains.happy.market.repositories.ProductRepository;
import ru.geekbrains.happy.market.services.ProductService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ProductService.class)
@AutoConfigureMockMvc
public class DBtest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    /**
    * 5.1. Тестирование процесса сохранения данных в базу данных:
    *      создать объект, ввести данные, сохранить их в базу данных,
    *      проверить, что сохраненные данные соответствуют ожидаемым.
    * */
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

        productService.saveOrUpdate(product);
        Product p = productService.findProductById(10L).get();
        //Mockito.verify(productRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(10L));
        assertEquals("TestProduct", p.getTitle());
    }


}
