package com.jeferro.products.products.product_reviews.application;

import com.jeferro.products.products.product_reviews.application.params.DeleteProductReviewParams;
import com.jeferro.products.products.product_reviews.domain.events.ProductReviewDeleted;
import com.jeferro.products.products.product_reviews.domain.exceptions.ProductReviewDoesNotBelongUser;
import com.jeferro.products.products.product_reviews.domain.exceptions.ProductReviewNotFoundException;
import com.jeferro.products.products.product_reviews.domain.models.ProductReview;
import com.jeferro.products.products.product_reviews.domain.models.ProductReviewMother;
import com.jeferro.products.products.product_reviews.domain.repositories.ProductReviewsInMemoryRepository;
import com.jeferro.products.shared.application.ContextMother;
import com.jeferro.products.shared.domain.events.EventInMemoryBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteProductReviewUseCaseTest {

    private ProductReviewsInMemoryRepository productReviewsInMemoryRepository;

    private EventInMemoryBus eventInMemoryBus;

    private DeleteProductReviewUseCase deleteProductReviewUseCase;

    @BeforeEach
    public void beforeEach() {
        productReviewsInMemoryRepository = new ProductReviewsInMemoryRepository();

        eventInMemoryBus = new EventInMemoryBus();

        deleteProductReviewUseCase = new DeleteProductReviewUseCase(productReviewsInMemoryRepository, eventInMemoryBus);
    }

    @Test
    void givenUserCommentsOnProduct_whenDeleteProductReview_thenReturnsDeletedProductReview() {
        var userReviewOfApple = givenAnUserProductReviewOfAppleInDatabase();

        var userContext = ContextMother.user();
        var params = new DeleteProductReviewParams(
                userReviewOfApple.getId()
        );

        var result = deleteProductReviewUseCase.execute(userContext, params);

        assertEquals(userReviewOfApple, result);

        assertTrue(productReviewsInMemoryRepository.isEmpty());

        assertProductReviewDeletedWasPublished(result);
    }

    @Test
    void givenUserDoesNotCommentOnProduct_whenDeleteProductReview_throwsException() {
        var userContext = ContextMother.user();
        var userReviewOfApple = ProductReviewMother.userReviewOfApple();
        var params = new DeleteProductReviewParams(
                userReviewOfApple.getId()
        );

        assertThrows(ProductReviewNotFoundException.class,
                () -> deleteProductReviewUseCase.execute(userContext, params));
    }

    @Test
    void givenOtherUserCommentsOnProduct_whenDeleteProductReviewOfOtherUser_throwsException() {
        var adminContext = ContextMother.admin();
        var userReviewOfApple = givenAnUserProductReviewOfAppleInDatabase();

        var params = new DeleteProductReviewParams(
                userReviewOfApple.getId()
        );

        assertThrows(ProductReviewDoesNotBelongUser.class,
                () -> deleteProductReviewUseCase.execute(adminContext, params));
    }

    private void assertProductReviewDeletedWasPublished(ProductReview result) {
        assertEquals(1, eventInMemoryBus.size());

        var event = (ProductReviewDeleted) eventInMemoryBus.getFirstOrError();

        assertEquals(result.getId(), event.getProductReviewId());
    }

    private ProductReview givenAnUserProductReviewOfAppleInDatabase() {
        var userReviewOfApple = ProductReviewMother.userReviewOfApple();
        productReviewsInMemoryRepository.init(userReviewOfApple);
        return userReviewOfApple;
    }
}
