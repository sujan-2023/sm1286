package com.sm1286.controller.request;

import com.sm1286.exception.RequestValidationException;
import com.sm1286.utils.DateTimeUtils;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutRequest {
    private static final String DATE_FORMATTER = "MM/dd/yy";

    private String toolCode;
    private Integer rentalDayCount;
    private Integer discountPercent;

    private String checkoutDate;

    public LocalDate getCheckoutLocalDate() {
        return DateTimeUtils.toLocalDate(checkoutDate, DATE_FORMATTER);
    }

    public void doValidation() throws RequestValidationException {
        final List<String> validationErrors = new ArrayList<>();
        if (Objects.isNull(rentalDayCount) || rentalDayCount < 1) {
            validationErrors.add("Rental day count is not 1 or greater");
        }
        if (Objects.isNull(discountPercent) || discountPercent > 100 || discountPercent < 0) {
            validationErrors.add("Discount percent is not in the range 0-100");
        }
        if (Objects.isNull(checkoutDate)) {
            validationErrors.add("Checkout date is required");
        }
        if (Objects.nonNull(checkoutDate) && !DateTimeUtils.isDateInFormat(checkoutDate, DATE_FORMATTER)) {
            validationErrors.add("Checkout date need to be in MM/dd/yy format");
        }
        if (!validationErrors.isEmpty()) {
            throw new RequestValidationException(validationErrors.stream().collect(Collectors.joining("|")));
        }
    }
}
