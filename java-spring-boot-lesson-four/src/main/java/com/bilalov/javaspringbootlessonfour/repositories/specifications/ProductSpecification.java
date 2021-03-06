package com.bilalov.javaspringbootlessonfour.repositories.specifications;

import com.bilalov.javaspringbootlessonfour.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {
    public static Specification<Product> trueLiteral() {
        return (root, query, builder) -> builder.isTrue(builder.literal(true));
    }

    public static Specification<Product> titleLike(String titleFilter) {
        return (root, query, builder) -> builder.like(root.get("title"), "%" + titleFilter + "%");
    }

    public static Specification<Product> getMin(BigDecimal min) {
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("price"), min);
    }

    public static Specification<Product> getBetweenPrice(BigDecimal min, BigDecimal max) {
        return (root, query, builder) -> builder.between(root.get("price"), min, max);
    }

    public static Specification<Product> getMax(BigDecimal max) {
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("price"), max);
    }
}
