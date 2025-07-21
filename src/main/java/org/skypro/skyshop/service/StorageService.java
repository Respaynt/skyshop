package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        initTestData();
    }

    public Collection<Searchable> getAllSearchables() {
        List<Searchable> result = new ArrayList<>();
        result.addAll(products.values());
        result.addAll(articles.values());
        return result;
    }

    private void initTestData() {

        Product p1 = new SimpleProduct(UUID.randomUUID(), "Сыр", 250);
        Product p2 = new DiscountedProduct(UUID.randomUUID(), "Кофе", 365, 0.10);
        Product p3 = new FixPriceProduct(UUID.randomUUID(), "Торт");

        products.put(p1.getId(), p1);
        products.put(p2.getId(), p2);
        products.put(p3.getId(), p3);


        Article a1 = new Article(UUID.randomUUID(), "Полезные продукты", "Сыр и овощи — важная часть рациона.");
        Article a2 = new Article(UUID.randomUUID(), "Кофейные тренды", "Новый сорт кофе покоряет рынок.");
        Article a3 = new Article(UUID.randomUUID(), "Десерты", "Торт — лучшее завершение обеда.");

        articles.put(a1.getId(), a1);
        articles.put(a2.getId(), a2);
        articles.put(a3.getId(), a3);
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public Collection<Article> getAllArticles() {
        return articles.values();
    }
    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

}