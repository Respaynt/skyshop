package org.skypro.skyshop.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.NoSuchProductException;
import org.skypro.skyshop.model.model.basket.ProductBasket;
import org.skypro.skyshop.model.product.Product;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    @Test
    void addProductWhenProductNotExistThrowException() {
        UUID testUUID = UUID.randomUUID();
        when(storageService.getProductById(testUUID))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class,() -> basketService.addProduct(testUUID));
    }

    @Test
    void addProductWhenProductExist() {
        UUID testUUID = UUID.randomUUID();
        Product product = mock(Product.class);
        when(storageService.getProductById(testUUID)).thenReturn(Optional.of(product));

        basketService.addProduct(testUUID);

        verify(productBasket, times(1)).addProduct(testUUID);
    }
    @Test
    void addProductWhenProductNotExist(){
        UUID id = UUID.randomUUID();
        when(productBasket.getProductsInBasket()).thenReturn(Map.of(id,1));

        assertThrows(NoSuchElementException.class, () -> basketService.getUserBasket());
    }

}
