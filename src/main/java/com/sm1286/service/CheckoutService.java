package com.sm1286.service;

import com.sm1286.controller.request.CheckoutRequest;
import com.sm1286.controller.response.CheckoutResponse;
import com.sm1286.model.Tool;
import com.sm1286.utils.CommonUtils;
import com.sm1286.utils.DateTimeUtils;
import com.sm1286.utils.HolidayUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CheckoutService {

    private final ToolService toolService;

    public Optional<CheckoutResponse> checkout(CheckoutRequest checkoutRequest) throws ValidationException {
        checkoutRequest.doValidation();
        final Optional<Tool> tool = toolService.getToolByCode(checkoutRequest.getToolCode());
        if (!tool.isPresent()) {
            throw new ValidationException("Tool code " + checkoutRequest.getToolCode() + " doesn't exist.");
        }

        int chargeDays = getChargeDays(checkoutRequest, tool.get());
        Double preDiscountCharge = CommonUtils.roundToCents(tool.get().getType().getDailyCharge() * chargeDays);
        Double discountAmount = CommonUtils.roundToCents(((double) checkoutRequest.getDiscountPercent() / 100) * preDiscountCharge);
        final CheckoutResponse response = CheckoutResponse.builder()
                .toolCode(checkoutRequest.getToolCode())
                .toolType(tool.get().getType().getTypeCode())
                .toolBrand(tool.get().getBrand())
                .rentalDays(checkoutRequest.getRentalDayCount())
                .checkoutDate(checkoutRequest.getCheckoutLocalDate())
                .dueDate(checkoutRequest.getCheckoutLocalDate().plusDays(checkoutRequest.getRentalDayCount() - 1))
                .dailyRentalCharge(tool.get().getType().getDailyCharge())
                .chargeDays(chargeDays)
                .preDiscountCharge(preDiscountCharge)
                .discountPercentage(checkoutRequest.getDiscountPercent())
                .discountAmount(discountAmount)
                .finalCharge(CommonUtils.roundToCents(preDiscountCharge - discountAmount))
                .build();

        log.info(response.printAgreement());
        return Optional.of(response);
    }

    private int getChargeDays(CheckoutRequest checkoutRequest, Tool tool) {
        final LocalDate startDate = checkoutRequest.getCheckoutLocalDate();
        int chargeDays = 0;
        boolean isWeekdayChargeable = Boolean.TRUE.equals(tool.getType().getWeekdayCharge());
        boolean isWeekendChargeable = Boolean.TRUE.equals(tool.getType().getWeekendCharge());
        boolean isHolidayChargeable = Boolean.TRUE.equals(tool.getType().getHolidayCharge());
        for (int i = 0; i < checkoutRequest.getRentalDayCount(); i++) {
            final LocalDate currentDate = startDate.plusDays(i);
            boolean isWeekday = !DateTimeUtils.isWeekendDate(currentDate);
            boolean isWeekend = DateTimeUtils.isWeekendDate(currentDate);
            boolean isHoliday = HolidayUtils.isHoliday(currentDate);

            if (isWeekday && !isHoliday && isWeekdayChargeable) {
                chargeDays = chargeDays + 1;
            } else if (isWeekday && isHoliday && isWeekdayChargeable && isHolidayChargeable) {
                chargeDays = chargeDays + 1;
            } else if (isWeekend && !isHoliday && isWeekendChargeable) {
                chargeDays = chargeDays + 1;
            } else if (isWeekend && isHoliday && isWeekendChargeable && isHolidayChargeable) {
                chargeDays = chargeDays + 1;
            }
        }

        return chargeDays;
    }
}
