package com.bilalov.javaspringbootlessonfour.services;

import com.bilalov.javaspringbootlessonfour.entities.Product;
import com.bilalov.javaspringbootlessonfour.repositories.ProductRepository;
import com.bilalov.javaspringbootlessonfour.repositories.specifications.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;


@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void addOrUpdate(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public Page<Product> getByParams(Optional<String> nameFilter,
                                     Optional<BigDecimal> min,
                                     Optional<BigDecimal> max,
                                     Optional<Integer> page,
                                     Optional<Integer> size) {

        Specification<Product> specification = Specification.where(null);
        if (nameFilter.isPresent()) {
            specification = specification.and(ProductSpecification.titleLike(nameFilter.get()));
        }

        if (min.isPresent()) {
            specification = specification.and(ProductSpecification.getMin(min.get()));
        }

        if (max.isPresent()) {
            specification = specification.and(ProductSpecification.getMax(max.get()));
        }

        if(min.isPresent() && max.isPresent()) {
            specification = specification.and(ProductSpecification.getBetweenPrice(min.get(), max.get()));
        }

        return productRepository.findAll(specification,
                PageRequest.of(page.orElse(1) - 1, size.orElse(5)));
    }
}
