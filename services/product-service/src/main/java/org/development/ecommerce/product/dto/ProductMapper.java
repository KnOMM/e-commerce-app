package org.development.ecommerce.product.dto;

import org.development.ecommerce.category.Category;
import org.development.ecommerce.product.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest request, Category category) {
        return Product
                .builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .availableQuantity(request.availableQuantity())
                .category(category)
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getName(),
                product.getAvailableQuantity(),
                product.getPrice()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, long quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                quantity
        );
    }
}
