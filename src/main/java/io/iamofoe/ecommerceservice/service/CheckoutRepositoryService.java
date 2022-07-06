package io.iamofoe.ecommerceservice.service;

import io.iamofoe.ecommerceservice.converter.CheckoutTransactionToCheckoutResponseDto;
import io.iamofoe.ecommerceservice.domain.model.CheckoutProduct;
import io.iamofoe.ecommerceservice.domain.model.CheckoutTransaction;
import io.iamofoe.ecommerceservice.domain.model.Product;
import io.iamofoe.ecommerceservice.domain.model.User;
import io.iamofoe.ecommerceservice.domain.repository.CheckoutProductRepository;
import io.iamofoe.ecommerceservice.domain.repository.CheckoutTransactionRepository;
import io.iamofoe.ecommerceservice.dto.request.CheckoutRequestDto;
import io.iamofoe.ecommerceservice.dto.response.CheckoutResponseDto;
import io.iamofoe.ecommerceservice.dto.response.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckoutRepositoryService implements CheckoutService {
    private final CheckoutProductRepository checkoutProductRepository;
    private final CheckoutTransactionRepository checkoutTransactionRepository;
    private final UserRepositoryService userRepositoryService;
    private final ProductRepositoryService productRepositoryService;
    private final CheckoutTransactionToCheckoutResponseDto toCheckoutResponseDtoConverter;

    @Override
    public CheckoutResponseDto createCheckoutTransaction(CheckoutRequestDto dto) {
        User user = userRepositoryService.getUserWithId(dto.getUserId());
        List<CheckoutProduct> checkoutProducts = dto.getProducts()
                .stream().map(this::saveCheckoutProduct).toList();
        return toCheckoutResponseDtoConverter
                .convert(saveCheckoutTransaction(user, checkoutProducts));
    }

    @Override
    public Page<CheckoutResponseDto> checkoutTransactionHistory(Pageable pageable, long userId) {
        return checkoutTransactionRepository.findCheckoutTransactionsByUser(pageable, userRepositoryService.getUserWithId(userId))
                .map(toCheckoutResponseDtoConverter::convert);
    }

    private CheckoutTransaction saveCheckoutTransaction(User user, List<CheckoutProduct> checkoutProducts) {
        return checkoutTransactionRepository.save(new CheckoutTransaction()
                .setAmount(calculateTotalAmount(checkoutProducts))
                .setUser(user)
                .setProducts(checkoutProducts));
    }

    private CheckoutProduct saveCheckoutProduct(ProductResponseDto product) {
        Product productById = productRepositoryService.getProductById(product.getId());
        return checkoutProductRepository.save(new CheckoutProduct()
                .setProduct(productById)
                .setPrice(product.getPrice())
                .setQuantity(product.getQuantity()));
    }

    private double calculateTotalAmount(List<CheckoutProduct> checkoutProducts) {
        return checkoutProducts.stream()
                .map(checkoutProduct -> checkoutProduct.getPrice() * checkoutProduct.getQuantity())
                .reduce(0.0, Double::sum);
    }
}
