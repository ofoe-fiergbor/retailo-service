package io.iamofoe.ecommerceservice.service;

import io.iamofoe.ecommerceservice.converter.CheckoutHistoryToHistoryDtoConverter;
import io.iamofoe.ecommerceservice.converter.CheckoutTransactionToCheckoutResponseDtoConverter;
import io.iamofoe.ecommerceservice.domain.model.CheckoutProduct;
import io.iamofoe.ecommerceservice.domain.model.CheckoutTransaction;
import io.iamofoe.ecommerceservice.domain.model.Product;
import io.iamofoe.ecommerceservice.domain.repository.CheckoutProductRepository;
import io.iamofoe.ecommerceservice.domain.repository.CheckoutTransactionRepository;
import io.iamofoe.ecommerceservice.dto.request.CheckoutProductRequestDto;
import io.iamofoe.ecommerceservice.dto.request.CheckoutRequestDto;
import io.iamofoe.ecommerceservice.dto.response.CheckoutResponseDto;
import io.iamofoe.ecommerceservice.dto.response.CheckoutTransactionHistoryListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionService implements CheckoutTransactionService {
    private final UserRepositoryService userRepositoryService;
    private final ProductRepositoryService productRepositoryService;
    private final CheckoutProductRepository checkoutProductRepository;
    private final CheckoutTransactionRepository checkoutTransactionRepository;
    private final CheckoutHistoryToHistoryDtoConverter toHistoryDtoConverter;
    private final CheckoutTransactionToCheckoutResponseDtoConverter toCheckoutResponseDtoConverter;

    @Override
    public CheckoutResponseDto checkoutFromShoppingCart(CheckoutRequestDto dto) {
        List<CheckoutProduct> checkoutProduct = dto.getProducts()
                .stream()
                .map(product -> checkoutProductRepository.save(getCheckoutProductEntity(product)))
                .toList();
        CheckoutTransaction checkoutTransaction = checkoutTransactionRepository.save(getCheckoutProductEntity(dto, checkoutProduct));
        checkoutProduct.forEach(product -> checkoutProductRepository.save(product.setTransactions(checkoutTransaction)));
        return toCheckoutResponseDtoConverter.convert(checkoutTransaction);
    }

    @Override
    public CheckoutTransactionHistoryListDto getCheckoutTransactionHistoryForUser(long userId) {
        List<CheckoutTransaction> checkoutTransactions = checkoutTransactionRepository
                .findCheckoutTransactionsByUser(userRepositoryService.getUserWithId(userId));
        return CheckoutTransactionHistoryListDto.builder()
                .history(checkoutTransactions.stream()
                        .map(toHistoryDtoConverter::convert).toList())
                .build();
    }

    private CheckoutTransaction getCheckoutProductEntity(CheckoutRequestDto dto, List<CheckoutProduct> checkoutProduct) {
        return new CheckoutTransaction()
                .setProducts(checkoutProduct)
                .setAmount(calculateTotalAmount(dto.getProducts()))
                .setCreated(new Date())
                .setUser(userRepositoryService.getUserWithId(dto.getUserId()));
    }

    private CheckoutProduct getCheckoutProductEntity(CheckoutProductRequestDto dto) {
//        reduceProductQuantity(dto);
        return new CheckoutProduct()
                .setProduct(productRepositoryService.getProductById(dto.getProductId()))
                .setQuantity(dto.getQuantity())
                .setPrice(dto.getPrice());
    }

    private void reduceProductQuantity(CheckoutProductRequestDto dto) {
        Product product = productRepositoryService.getProductById(dto.getProductId());
        double productQuantityLeft = product.getQuantity() - dto.getQuantity();
        product.setPrice(productQuantityLeft);
        productRepositoryService.saveProduct(product);
    }

    private double calculateTotalAmount(List<CheckoutProductRequestDto> products) {
        return products.stream().mapToDouble(product -> product.getPrice() * product.getQuantity()).sum();
    }
}
