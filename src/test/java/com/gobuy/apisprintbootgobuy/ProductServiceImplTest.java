package com.gobuy.apisprintbootgobuy;

import com.gobuy.apisprintbootgobuy.domain.model.Comment;
import com.gobuy.apisprintbootgobuy.domain.model.Product;
import com.gobuy.apisprintbootgobuy.domain.repository.ProductRepository;
import com.gobuy.apisprintbootgobuy.domain.service.ProductService;
import com.gobuy.apisprintbootgobuy.exception.ResourceNotFoundException;
import com.gobuy.apisprintbootgobuy.service.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ProductServiceImplTest {
    @MockBean
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @TestConfiguration
    static class ProductServiceImplTestConfiguration {
        @Bean
        public ProductService productService() {
            return new ProductServiceImpl();
        }
    }

    @Test
    @DisplayName("when GetProductById With Valid Id Then Returns Product")
    public void whenGetProductByIdWithValidIdThenReturnsProduct() {
        //Arrange
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        when(productRepository.findById(id))
                .thenReturn(Optional.of(product));
        //Act
        Product foundProduct = productService.getProductById(id);
        //Assert
        assertThat(foundProduct.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("when GetProductById With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetProductByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        //Arrange
        Long id = 2L;
        String template = "Resource %s not found for %s with value %s";
        when(productRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Product", "Id", id);

        //Act
        Throwable exception = catchThrowable(() -> {
            Product foundProduct = productService.getProductById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("when UpdateProductById With Valid Id Then Return UpdatedProduct")
    public void whenUpdateProductByIdWithValidIdThenReturnUpdatedProduct() {
        //Arrange
        Long id = 2L;
        Product product = new Product();
        product.setPrice(1000L);
        product.setProductPhoto("img.jpg");
        product.setName("Maquillaje");
        product.setId(id);
        Product newProduct = new Product();
        newProduct.setName("Play 5");
        newProduct.setPrice(2130L);
        product.setProductPhoto("play5.jpg");
        newProduct.setId(id);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productRepository.save(newProduct)).thenReturn(newProduct);
        //Act
        Product foundProduct = productService.updateProduct(id, newProduct);
        //Assert
        assertThat(product).isNotEqualTo(foundProduct);
    }

    @Test
    @DisplayName("when UpdateProductById With Invalid Id Then Return ResourceNotFoundExceptions")
    public void whenUpdateProductByIdWithInvalidIdThenReturnResourceNotFoundExceptions() {
        //Arrange
        Long id = 14L;
        Product product = new Product();
        String template = "Resource %s not found for %s with value %s";
        when(productRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Product", "Id", id);
        //Act
        Throwable exception = catchThrowable(() -> {
            Product updatedProduct = productService.updateProduct(id, product);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }
}
