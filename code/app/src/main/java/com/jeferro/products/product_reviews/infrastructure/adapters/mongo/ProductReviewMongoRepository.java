package com.jeferro.products.product_reviews.infrastructure.adapters.mongo;

import com.jeferro.products.components.mongodb.product_reviews.ProductReviewMongoDao;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.product_reviews.domain.models.ProductReviews;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.products.product_reviews.infrastructure.adapters.mongo.mappers.ProductReviewIdMongoMapper;
import com.jeferro.products.product_reviews.infrastructure.adapters.mongo.mappers.ProductReviewMongoMapper;
import com.jeferro.products.products.domain.models.ProductId;
import com.jeferro.products.products.infrastructure.adapters.mongo.mappers.ProductIdMongoMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductReviewMongoRepository implements ProductReviewsRepository {

    private final ProductReviewIdMongoMapper productReviewIdMongoMapper = ProductReviewIdMongoMapper.INSTANCE;

    private final ProductReviewMongoMapper productReviewMongoMapper = ProductReviewMongoMapper.INSTANCE;

    private final ProductIdMongoMapper productIdMongoMapper = ProductIdMongoMapper.INSTANCE;

    private final ProductReviewMongoDao productReviewMongoDao;

    @Override
    public void save(ProductReview productReview) {
        var productReviewDto = productReviewMongoMapper.toDTO(productReview);

        productReviewMongoDao.save(productReviewDto);
    }

    public ProductReviewMongoRepository(ProductReviewMongoDao productReviewMongoDao) {
        this.productReviewMongoDao = productReviewMongoDao;
    }

    @Override
    public ProductReviews findAllByProductId(ProductId productId) {
        var productIdDto = productIdMongoMapper.toDTO(productId);

        var products = productReviewMongoDao.findAllByProductId(productIdDto).stream()
                .map(productReviewMongoMapper::toDomain)
                .toList();

        return new ProductReviews(products);
    }

    @Override
    public Optional<ProductReview> findById(ProductReviewId productReviewId) {
        var productReviewIdDto = productReviewIdMongoMapper.toDTO(productReviewId);

        return productReviewMongoDao.findById(productReviewIdDto)
                .map(productReviewMongoMapper::toDomain);
    }

    @Override
    public void deleteById(ProductReviewId productReviewId) {
        var productReviewIdDto = productReviewIdMongoMapper.toDTO(productReviewId);

        productReviewMongoDao.deleteById(productReviewIdDto);
    }

    @Override
    public void deleteAllById(List<ProductReviewId> productReviewIds) {
        var productReviewIdDtos = productReviewIdMongoMapper.toDTOList(productReviewIds);

        productReviewMongoDao.deleteAllById(productReviewIdDtos);
    }
}
