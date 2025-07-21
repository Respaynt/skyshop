package org.skypro.skyshop.service;

import org.skypro.skyshop.NoSuchProductException;
import org.skypro.skyshop.model.model.basket.BasketItem;
import org.skypro.skyshop.model.model.basket.ProductBasket;
import org.skypro.skyshop.model.model.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket basket;
    private final StorageService storageService;

    public BasketService(ProductBasket basket, StorageService storageService) {
        this.basket = basket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        if (storageService.getProductById(id).isEmpty()) {
            throw new NoSuchProductException("Такого продукта нет!");
        }
        basket.addProduct(id);
    }

    public UserBasket getUserBasket() {
        List<BasketItem> items = basket.getProductsInBasket()
                .entrySet()
                .stream()
                .map(el -> new BasketItem(storageService.getProductById(el.getKey()).orElseThrow(),el.getValue()))
                .toList();
        return new UserBasket(items);
    }
}
