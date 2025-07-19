package org.skypro.skyshop.model.model.product;


import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final double discount;

    public DiscountedProduct(UUID id, String name, int basePrice, double discount) {
        super(id, name);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше 0. Текущая: " + basePrice);
        }
        if (discount < 0 || discount > 1) {
            throw new IllegalArgumentException("Скидка должна быть от 0 до 1. Текущая: " + discount);
        }
        this.basePrice = basePrice;
        this.discount = discount;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public int getPrice() {
        return (int) Math.round(basePrice * (1 - discount));
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + " (со скидкой " + (int) (discount * 100) + "%) - " +
                getPrice() + " руб. (старая цена: " + basePrice + " руб.)";
    }
}