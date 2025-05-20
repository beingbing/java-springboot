package be.springboot.pp.ecommerce.dto;

import be.springboot.pp.ecommerce.filter.Rating;
import lombok.Getter;

import java.util.Optional;

@Getter
public class FilterDetails {
    private final Optional<Double> priceFilter;
    private final Optional<Rating> ratingFilter;
    private final Optional<Boolean> payOnDeliveryFilter;

    public FilterDetails(Optional<Double> priceFilter,
                         Optional<Rating> ratingFilter,
                         Optional<Boolean> payOnDeliveryFilter) {
        this.priceFilter = priceFilter;
        this.ratingFilter = ratingFilter;
        this.payOnDeliveryFilter = payOnDeliveryFilter;
    }
}
