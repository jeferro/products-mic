package com.jeferro.products.product_reviews.infrastructure.adapters.mongo;

import java.util.Optional;

import com.jeferro.products.components.mongodb.product_reviews.ProductReviewMongoDao;
import com.jeferro.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.product_reviews.domain.models.ProductReviewId;
import com.jeferro.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.products.product_reviews.infrastructure.adapters.mongo.mappers.ProductReviewIdMongoMapper;
import com.jeferro.products.product_reviews.infrastructure.adapters.mongo.mappers.ProductReviewMongoMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductReviewMongoRepository implements ProductReviewsRepository {

  private static final ProductReviewIdMongoMapper productReviewIdMongoMapper = ProductReviewIdMongoMapper.INSTANCE;

  private static final ProductReviewMongoMapper productReviewMongoMapper = ProductReviewMongoMapper.INSTANCE;

  private final ProductReviewMongoDao productReviewMongoDao;

  public ProductReviewMongoRepository(ProductReviewMongoDao productReviewMongoDao) {
	this.productReviewMongoDao = productReviewMongoDao;
  }

  @Override
  public Optional<ProductReview> findById(ProductReviewId productReviewId) {
	var productReviewIdDto = productReviewIdMongoMapper.toDTO(productReviewId);

	return productReviewMongoDao.findById(productReviewIdDto)
		.map(productReviewMongoMapper::toDomain);
  }

  @Override
  public void save(ProductReview productReview) {
	var productReviewDto = productReviewMongoMapper.toDTO(productReview);

	productReviewMongoDao.save(productReviewDto);
  }
}
