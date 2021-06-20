package com.gobuy.apisprintbootgobuy;

import com.gobuy.apisprintbootgobuy.domain.model.Comment;
import com.gobuy.apisprintbootgobuy.domain.model.Purchase;
import com.gobuy.apisprintbootgobuy.domain.model.Role;
import com.gobuy.apisprintbootgobuy.domain.repository.PurchaseRepository;
import com.gobuy.apisprintbootgobuy.domain.service.PurchaseService;
import com.gobuy.apisprintbootgobuy.exception.ResourceNotFoundException;
import com.gobuy.apisprintbootgobuy.service.PurchaseServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PurchaseServiceImplTest {
    @MockBean
    private PurchaseRepository purchaseRepository;
    @Autowired
    private PurchaseService purchaseService;

    @TestConfiguration
    static class PurchaseServiceImplTestConfiguration{
        @Bean
        public PurchaseService purchaseService(){return new PurchaseServiceImpl();
        }
    }

    @Test
    @DisplayName("when GetPurchaseById With Valid Id Then Returns Purchase")
    public void whenGetPurchaseByIdWithValidIdThenReturnsPurchase() {
        //Arrange
        Long id = 1L;
        Purchase purchase = new Purchase();
        purchase.setId(id);
        when(purchaseRepository.findById(id))
                .thenReturn(Optional.of(purchase));
        //Act
        Purchase foundPurchase = purchaseService.getPurchaseById(id);
        //Assert
        assertThat(foundPurchase.getId()).isEqualTo(id);
    }

    @Test
    @DisplayName("when GetPurchaseById With Invalid Id Then Returns ResourceNotFoundException")
    public void whenGetPurchaseByIdWithInvalidIdThenReturnsResourceNotFoundException() {
        //Arrange
        Long id = 2L;
        String template = "Resource %s not found for %s with value %s";
        when(purchaseRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Purchase", "Id", id);

        //Act
        Throwable exception = catchThrowable(() -> {
            Purchase foundPurchase = purchaseService.getPurchaseById(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("when DeletePurchaseById With Invalid Id Then ReturnResourceNotFoundExceptions")
    public void whenDeletedPurchaseByIdWithInvalidIdThenReturnResourceNotFoundExceptions() {
        //Arrange
        Long id = 10L;
        String template = "Resource %s not found for %s with value %s";
        when(purchaseRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Purchase", "Id", id);
        //Act
        Throwable exception = catchThrowable(() -> {
            ResponseEntity<?> deletedPurchase = purchaseService.deletePurchase(id);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("when UpdatePurchaseById With Valid Id Then Return UpdatedPurchase")
    public void whenUpdateCommentByIdWithValidIdThenReturnUpdatedComment() {
        //Arrange
        Long id = 2L;
        Purchase purchase = new Purchase();
        purchase.setId(id);
        Purchase newPurchase = new Purchase();
        newPurchase.setId(id);

        when(purchaseRepository.findById(id)).thenReturn(Optional.of(purchase));
        when(purchaseRepository.save(newPurchase)).thenReturn(newPurchase);
        //Act
        Purchase foundPurchase = purchaseService.updatePurchase(id, newPurchase);
        //Assert
        assertThat(purchase).isNotEqualTo(foundPurchase);
    }

    @Test
    @DisplayName("when UpdatePurchaseById With Invalid Id Then Return ResourceNotFoundExceptions")
    public void whenUpdatePurchaseByIdWithInvalidIdThenReturnResourceNotFoundExceptions() {
        //Arrange
        Long id = 14L;
        Purchase purchase = new Purchase();
        String template = "Resource %s not found for %s with value %s";
        when(purchaseRepository.findById(id))
                .thenReturn(Optional.empty());
        String expectedMessage = String.format(template, "Purchase", "Id", id);
        //Act
        Throwable exception = catchThrowable(() -> {
            Purchase updatedPurchase = purchaseService.updatePurchase(id, purchase);
        });
        //Assert
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage(expectedMessage);
    }

}
