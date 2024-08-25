package com.sm1286.controller.response;

import com.sm1286.utils.CommonUtils;
import com.sm1286.utils.DateTimeUtils;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutResponse {

    private static final String DATE_FORMATTER = "MM/dd/yy";
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private Integer rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private Double dailyRentalCharge;
    private Integer chargeDays;
    private Double preDiscountCharge;
    private Integer discountPercentage;
    private Double discountAmount;
    private Double finalCharge;

    public String printAgreement() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n***********************************************\n");
        stringBuilder.append("***************RENTAL AGREEMENT****************\n");
        stringBuilder.append("***********************************************\n");
        stringBuilder.append("Tool code: " + toolCode + "\n");
        stringBuilder.append("Tool type: " + toolType + "\n");
        stringBuilder.append("Tool brand: " + toolBrand + "\n");
        stringBuilder.append("Rental days: " + rentalDays + "\n");
        stringBuilder.append("Check out date: " + DateTimeUtils.toDateString(checkoutDate, DATE_FORMATTER) + "\n");
        stringBuilder.append("Due date: " + DateTimeUtils.toDateString(dueDate, DATE_FORMATTER) + "\n");
        stringBuilder.append("Daily rental charge: " + CommonUtils.currencyFormatter(dailyRentalCharge) + "\n");
        stringBuilder.append("Charge days: " + chargeDays + "\n");
        stringBuilder.append("Pre-discount charge: " + CommonUtils.currencyFormatter(preDiscountCharge) + "\n");
        stringBuilder.append("Discount percentage: " + discountPercentage + "%\n");
        stringBuilder.append("Discount amount: " + CommonUtils.currencyFormatter(discountAmount) + "\n");
        stringBuilder.append("Final charge: " + CommonUtils.currencyFormatter(finalCharge) + "\n");
        stringBuilder.append("***********************************************\n");
        stringBuilder.append("**********************END**********************\n");
        stringBuilder.append("***********************************************\n");
        return stringBuilder.toString();
    }
}
