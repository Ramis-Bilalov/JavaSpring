package com.bilalov.javaspringbootlessonfour.services;

import com.bilalov.javaspringbootlessonfour.entities.Basket;
import com.bilalov.javaspringbootlessonfour.repositories.BasketRepository;
import com.bilalov.javaspringbootlessonfour.repositories.specifications.BasketSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BasketService {

    private BasketRepository basketRepository;

    @Autowired
    public void setBasketRepository(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Transactional
    public void addOrUpdate(Basket basket) {
        basketRepository.save(basket);
    }

    @Transactional
    public Page<Basket> getByParams(Optional<String> nameFilter,
                                     Optional<BigDecimal> min,
                                     Optional<BigDecimal> max,
                                     Optional<Integer> page,
                                     Optional<Integer> size,
                                     Optional<String> sortField,
                                     Optional<String> sortOrder) {

        Specification<Basket> specification = Specification.where(null);
        if (nameFilter.isPresent()) {
            specification = specification.and(BasketSpecification.titleLike(nameFilter.get()));
        }

        if (min.isPresent()) {
            specification = specification.and(BasketSpecification.getMin(min.get()));
        }

        if (max.isPresent()) {
            specification = specification.and(BasketSpecification.getMax(max.get()));
        }

        if(min.isPresent() && max.isPresent()) {
            specification = specification.and(BasketSpecification.getBetweenPrice(min.get(), max.get()));
        }

        if (sortField.isPresent() && sortOrder.isPresent()) {
            return basketRepository.findAll(specification, PageRequest.of(page.orElse(1) - 1, size.orElse(4),
                    Sort.by(Sort.Direction.fromString(sortOrder.get()), sortField.get())));
        }

        return basketRepository.findAll(specification,
                PageRequest.of(page.orElse(1) - 1, size.orElse(5)));
    }

}
