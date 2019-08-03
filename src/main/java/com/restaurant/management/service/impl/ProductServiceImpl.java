package com.restaurant.management.service.impl;

import com.restaurant.management.domain.ecommerce.*;
import com.restaurant.management.exception.product.ProductMessages;
import com.restaurant.management.exception.product.ProductNotFoundException;
import com.restaurant.management.exception.user.UserMessages;
import com.restaurant.management.exception.user.UserNotFoundException;
import com.restaurant.management.repository.AccountUserRepository;
import com.restaurant.management.repository.LineItemRepository;
import com.restaurant.management.repository.ProductRepository;
import com.restaurant.management.security.CurrentUser;
import com.restaurant.management.security.UserPrincipal;
import com.restaurant.management.service.ProductService;
import com.restaurant.management.domain.ecommerce.dto.ProductFormDTO;
import com.restaurant.management.web.response.ApiResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private LineItemRepository lineItemRepository;
    private AccountUserRepository accountUserRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              LineItemRepository lineItemRepository,
                              AccountUserRepository accountUserRepository) {
        this.productRepository = productRepository;
        this.lineItemRepository = lineItemRepository;
        this.accountUserRepository = accountUserRepository;
    }

    private AccountUser getUserById(@CurrentUser UserPrincipal currentUser) {
        return accountUserRepository.findById(currentUser.getId())
                .orElseThrow(() -> new UserNotFoundException(UserMessages.ID_NOT_FOUND.getMessage()));
    }

    public Product registerProduct(@CurrentUser UserPrincipal currentUser, ProductFormDTO request) {

        AccountUser accountUser = getUserById(currentUser);

        ProductInventory productInventory = new ProductInventory();
        Stream.of(productInventory)
                .forEach(pi -> {
                    pi.setQuantity(request.getQuantity());
                    pi.setSku(request.getSku());
        });

        ProductShippingDetails productShippingDetails = new ProductShippingDetails();
        Stream.of(productShippingDetails)
                .forEach(psd -> {
                    psd.setWidth(request.getWidth());
                    psd.setHeight(request.getHeight());
                    psd.setDepth(request.getDepth());
                    psd.setWeight(request.getWeight());
                    psd.setExtraShippingFee(request.getExtraShippingFee());
                });

        Product product = new Product();
        Stream.of(product)
                .forEach(p -> {
                    p.setName(request.getName());
//      CHECK IT LATER    p.setPrice((Math.floor(request.getPriceTaxIncl() * 100) / 100));
                    p.setPrice(request.getPriceTaxIncl());
                    p.setDescription(request.getDescription());
                    p.setDeleted(Boolean.FALSE);
                    p.setProductInventory(productInventory);
                    p.setProductShippingDetails(productShippingDetails);
                    p.setCompany(accountUser.getCompany());
                });

        productRepository.save(product);

        return product;
    }

    public Product updateProduct(ProductFormDTO request, @CurrentUser UserPrincipal currentUser) {
        Product product = getRestaurantProductById(request.getId(), currentUser);

        Stream.of(product)
                .forEach(p -> {
                    p.setName(request.getName());
                    p.setPrice(request.getPriceTaxIncl());
                    p.setDescription(request.getDescription());
                    p.getProductInventory().setSku(request.getSku());
                    p.getProductInventory().setQuantity(request.getQuantity());
                    p.getProductShippingDetails().setWidth(request.getWidth());
                    p.getProductShippingDetails().setHeight(request.getHeight());
                    p.getProductShippingDetails().setDepth(request.getDepth());
                    p.getProductShippingDetails().setWeight(request.getWeight());
                    p.getProductShippingDetails().setExtraShippingFee(request.getExtraShippingFee());
                });

        productRepository.save(product);

        return product;
    }

    public Product getRestaurantProductById(Long productId, @CurrentUser UserPrincipal currentUser) {

        AccountUser accountUser = getUserById(currentUser);

        Long restaurantId = accountUser.getCompany().getId();

        return productRepository.findByIdAndCompanyId(productId, restaurantId)
                .orElseThrow(() -> new ProductNotFoundException(ProductMessages.PRODUCT_ID_NOT_FOUND.getMessage() + productId));
    }

    public Page<Product> getAllByRestaurant(Pageable pageable, @CurrentUser UserPrincipal currentUser) {

        AccountUser accountUser = getUserById(currentUser);

        return productRepository.findAllByCompanyAndIsDeletedIsFalse(pageable, accountUser.getCompany());
    }

    public ApiResponse deleteById(Long productId, @CurrentUser UserPrincipal currentUser) {

        Product product = getRestaurantProductById(productId, currentUser);

        List<LineItem> lineItems = lineItemRepository.findAllByProductId(productId);

        lineItems.iterator().forEachRemaining(lineItem -> {
            lineItem.setDeleted(Boolean.TRUE);
            lineItemRepository.save(lineItem);
        });

        product.setDeleted(Boolean.TRUE);
        productRepository.save(product);

        return new ApiResponse(true, ProductMessages.PRODUCT_DELETED.getMessage());
    }

    public ApiResponse deleteAllByIds(Long[] productsIds, @CurrentUser UserPrincipal currentUser) {
        List<Long> ids = new ArrayList<>(Arrays.asList(productsIds));

        List<Product> products = productRepository.findAllByIdIn(ids);
        Stream.of(products)
                .forEach(p -> {
                    p.iterator().forEachRemaining(v -> v.setDeleted(Boolean.TRUE));
                    p.iterator().forEachRemaining(v -> productRepository.save(v));
                });

        return new ApiResponse(true, ProductMessages.PRODUCT_DELETED.getMessage());
    }
}