package be.springboot.pp.ecommerce.filter;

import be.springboot.pp.ecommerce.FilterDetails;
import be.springboot.pp.ecommerce.filter.impl.IdleFilter;
import be.springboot.pp.ecommerce.filter.impl.PayOnDeliveryFilter;
import be.springboot.pp.ecommerce.filter.impl.PriceFilter;
import be.springboot.pp.ecommerce.filter.impl.RatingFilter;

public class FilterFactory {
    private FilterFactory() {}

    public static ProductFilter createFilter(FilterDetails filterDetails) {
        ProductFilter filter = new IdleFilter();
        if (filterDetails.getRatingFilter().isPresent()) {
            filter = new RatingFilter(filterDetails.getRatingFilter().get(), filter);
        }
        if (filterDetails.getPayOnDeliveryFilter().isPresent()) {
            filter = new PayOnDeliveryFilter(filterDetails.getPayOnDeliveryFilter().get(), filter);
        }
        if (filterDetails.getPriceFilter().isPresent()) {
            filter = new PriceFilter(filterDetails.getPriceFilter().get(), filter);
        }
        return filter;
    }
}
