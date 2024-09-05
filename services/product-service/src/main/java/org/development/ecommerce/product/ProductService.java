package org.development.ecommerce.product;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.development.ecommerce.category.Category;
import org.development.ecommerce.category.CategoryRepository;
import org.development.ecommerce.exception.CategoryNotExist;
import org.development.ecommerce.exception.ProductPurchaseException;
import org.development.ecommerce.product.dto.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;

    public Product addProduct(ProductRequest request) {
        Category category = categoryExists(request.categoryId())
                .orElseThrow(() -> new CategoryNotExist("Category does not exist"));
        Product product = mapper.toProduct(request, category);
        return productRepository.save(product);
    }

    private Optional<Category> categoryExists(UUID categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests) {
        var productIds = requests.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = productRepository.findAllById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("Some products are invalid");
        }

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        Map<Product, ProductPurchaseRequest> productToRequest = productToRequest(storedProducts, requests);
        for (Map.Entry<Product, ProductPurchaseRequest> entry : productToRequest.entrySet()) {
            if (entry.getKey().getAvailableQuantity() < entry.getValue().quantity()) {
                throw new ProductPurchaseException("Insufficient quantity for product id " + entry.getKey().getId());
            }

            var newQuantity = entry.getKey().getAvailableQuantity() - entry.getValue().quantity();
            Product product = entry.getKey();
            product.setAvailableQuantity(newQuantity);
            productRepository.save(product);
            purchasedProducts.add(mapper.toProductPurchaseResponse(product, entry.getValue().quantity()));
        }

        return purchasedProducts;
    }

    private Map<Product, ProductPurchaseRequest> productToRequest(List<Product> storedProducts, List<ProductPurchaseRequest> requests) {
        Map<Product, ProductPurchaseRequest> productToRequest = new HashMap<>();
        List<Product> productsSort = storedProducts.stream()
                .sorted(Comparator.comparing(Product::getId))
                .toList();
        List<ProductPurchaseRequest> requestsSort = requests.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        for (int i = 0; i < productsSort.size(); i++) {
            productToRequest.put(productsSort.get(i), requestsSort.get(i));
        }
        return productToRequest;
    }

    public ProductResponse findById(UUID productId) {
        return productRepository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id " + productId));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }

}
