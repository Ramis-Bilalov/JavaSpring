package com.bilalov.javaspringbootlessonfour.repositories.specifications;

import com.bilalov.javaspringbootlessonfour.entities.Basket;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class BasketSpecification {
    public static Specification<Basket> trueLiteral() {
        return (root, query, builder) -> builder.isTrue(builder.literal(true));
    }

    public static Specification<Basket> titleLike(String titleFilter) {
        return (root, query, builder) -> builder.like(root.get("title"), "%" + titleFilter + "%");
    }

    public static Specification<Basket> getMin(BigDecimal min) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("price"), min);
    }

    public static Specification<Basket> getBetweenPrice(BigDecimal min, BigDecimal max) {
        return (root, query, builder) -> builder.between(root.get("price"), min, max);
    }

    public static Specification<Basket> getMax(BigDecimal max) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("price"), max);
    }
}
