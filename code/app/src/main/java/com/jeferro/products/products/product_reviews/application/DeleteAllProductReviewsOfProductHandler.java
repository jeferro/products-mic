package com.jeferro.products.products.product_reviews.application;

import com.jeferro.products.products.product_reviews.application.params.DeleteAllProductReviewsOfProductParams;
import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.repositories.ProductReviewsRepository;
import com.jeferro.shared.ddd.application.Handler;
import com.jeferro.shared.ddd.domain.events.EventBus;
import com.jeferro.shared.ddd.domain.models.context.Context;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.jeferro.products.shared.application.Roles.ADMIN;

@Component
@RequiredArgsConstructor
public class DeleteAllProductReviewsOfProductHandler extends Handler<DeleteAllProductReviewsOfProductParams, Void> {

    private final ProductReviewsRepository productReviewsRepository;

    private final EventBus eventBus;

    @Override
    public Set<String> getMandatoryUserRoles() {
        return Set.of(ADMIN);
    }

    @Override
    public Void execute(Context context, DeleteAllProductReviewsOfProductParams params) {
        var productCode = params.getProductCode();

        var productReviews = productReviewsRepository.findAllByProductCode(productCode);

        if (productReviews.isEmpty()) {
            return null;
        }

        productReviews.forEach(ProductReview::deleteBySystem);

        var productReviewIds = productReviews.getIds();

        productReviewsRepository.deleteAllById(productReviewIds);

        eventBus.sendAll(productReviews);

        return null;
    }
}
