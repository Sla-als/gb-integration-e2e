package ru.geekbrains.happy.market.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.happy.market.model.Product;
import ru.geekbrains.happy.market.repositories.ProductRepository;
import ru.geekbrains.happy.market.services.ProductService;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {ProductRepository.class, ProductService.class})
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

    /**
     * 5.2. Тестирование процесса сохранения данных в базу данных:
     *      создать объект, ввести данные, сохранить их в базу данных,
     *      проверить, что сохраненные данные соответствуют ожидаемым.
     * */
    @Test
    public void givenStudentWithTags_whenSave_thenGetByTagOk() {
        Product product = new Product();
        product.setId(10L);
        product.setTitle("ProdustTest1");
        product.setPrice(899);

        productService.saveOrUpdate(product);
        Product p = productService.findProductById(10L).get();

        assertEquals("ProdustTest1", p.getTitle());
        assertEquals(10L, p.getPrice());
    }

}