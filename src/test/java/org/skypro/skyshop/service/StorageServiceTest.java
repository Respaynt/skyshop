package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.SearchResult;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class StorageServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @ValueSource(strings = {"Сы","сыр","СЫР"})
    @ParameterizedTest
    void search(String arg){
        when(storageService.getAllSearchables()).thenReturn(List.of(
                new SimpleProduct(UUID.randomUUID(), "Сыр", 250),
                new DiscountedProduct(UUID.randomUUID(), "Кофе", 365, 0.10),
                new FixPriceProduct(UUID.randomUUID(), "Торт"),
                new Article(UUID.randomUUID(), "Полезные продукты", "Сыр и овощи — важная часть рациона."),
                new Article(UUID.randomUUID(), "Кофейные тренды", "Новый сорт кофе покоряет рынок."),
                new Article(UUID.randomUUID(), "Десерты", "Торт — лучшее завершение обеда.")
        ));

        Collection<SearchResult> results = searchService.search(arg);
       SearchResult res1 = results.getClass(0);

        assertEquals(2,results.size());
        assertEquals("PRODUCT", res1.getContentType());

    }

}
