package com.sm1286.service;

import static org.junit.jupiter.api.Assertions.*;

import com.sm1286.controller.request.CheckoutRequest;
import com.sm1286.controller.response.CheckoutResponse;
import com.sm1286.model.Tool;
import com.sm1286.model.ToolType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class CheckoutServiceTest {

    @InjectMocks
    private CheckoutService checkoutService;

    @Mock
    private ToolService toolService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheckoutTest1ToolJAKR() {
        final String toolCode = "JAKR";
        mockTool(toolCode);
        final CheckoutRequest request = CheckoutRequest.builder()
                .toolCode(toolCode)
                .rentalDayCount(5)
                .discountPercent(101)
                .checkoutDate("09/03/15")
                .build();
        final ValidationException thrown = assertThrows(ValidationException.class, () -> {
            checkoutService.checkout(request);
        });
        assertEquals("Discount percent is not in the range 0-100", thrown.getMessage());
    }

    @Test
    void testCheckoutTest2ToolLADW() throws ValidationException {
        final String toolCode = "LADW";
        mockTool(toolCode);
        final CheckoutRequest request = CheckoutRequest.builder()
                .toolCode(toolCode)
                .rentalDayCount(3)
                .discountPercent(10)
                .checkoutDate("07/02/20")
                .build();
        final Optional<CheckoutResponse> response = checkoutService.checkout(request);
        assertTrue(response.isPresent());
        final CheckoutResponse checkoutResponse = response.get();
        assertEquals(toolCode, checkoutResponse.getToolCode());
        assertEquals("Ladder", checkoutResponse.getToolType());
        assertEquals("Werner", checkoutResponse.getToolBrand());
        assertEquals(3, checkoutResponse.getRentalDays());
        assertEquals(LocalDate.of(2020, 7, 2), checkoutResponse.getCheckoutDate());
        assertEquals(LocalDate.of(2020, 7, 4), checkoutResponse.getDueDate());
        assertEquals(1.99, checkoutResponse.getDailyRentalCharge());
        assertEquals(1, checkoutResponse.getChargeDays());
        assertEquals(1.99, checkoutResponse.getPreDiscountCharge());
        assertEquals(10, checkoutResponse.getDiscountPercentage());
        assertEquals(0.2, checkoutResponse.getDiscountAmount());
        assertEquals(1.79, checkoutResponse.getFinalCharge());
    }

    @Test
    void testCheckoutTest3ToolCHNS() throws ValidationException {
        final String toolCode = "CHNS";
        mockTool(toolCode);
        final CheckoutRequest request = CheckoutRequest.builder()
                .toolCode(toolCode)
                .rentalDayCount(5)
                .discountPercent(25)
                .checkoutDate("07/02/15")
                .build();
        final Optional<CheckoutResponse> response = checkoutService.checkout(request);
        assertTrue(response.isPresent());
        final CheckoutResponse checkoutResponse = response.get();
        assertEquals(toolCode, checkoutResponse.getToolCode());
        assertEquals("Chainsaw", checkoutResponse.getToolType());
        assertEquals("Stihl", checkoutResponse.getToolBrand());
        assertEquals(5, checkoutResponse.getRentalDays());
        assertEquals(LocalDate.of(2015, 7, 2), checkoutResponse.getCheckoutDate());
        assertEquals(LocalDate.of(2015, 7, 6), checkoutResponse.getDueDate());
        assertEquals(1.49, checkoutResponse.getDailyRentalCharge());
        assertEquals(3, checkoutResponse.getChargeDays());
        assertEquals(4.47, checkoutResponse.getPreDiscountCharge());
        assertEquals(25, checkoutResponse.getDiscountPercentage());
        assertEquals(1.12, checkoutResponse.getDiscountAmount());
        assertEquals(3.35, checkoutResponse.getFinalCharge());
    }

    @Test
    void testCheckoutTest4ToolJAKD() throws ValidationException {
        final String toolCode = "JAKD";
        mockTool(toolCode);
        final CheckoutRequest request = CheckoutRequest.builder()
                .toolCode(toolCode)
                .rentalDayCount(6)
                .discountPercent(0)
                .checkoutDate("09/03/15")
                .build();
        final Optional<CheckoutResponse> response = checkoutService.checkout(request);
        assertTrue(response.isPresent());
        final CheckoutResponse checkoutResponse = response.get();
        assertEquals(toolCode, checkoutResponse.getToolCode());
        assertEquals("Jackhammer", checkoutResponse.getToolType());
        assertEquals("DeWalt", checkoutResponse.getToolBrand());
        assertEquals(6, checkoutResponse.getRentalDays());
        assertEquals(LocalDate.of(2015, 9, 3), checkoutResponse.getCheckoutDate());
        assertEquals(LocalDate.of(2015, 9, 8), checkoutResponse.getDueDate());
        assertEquals(2.99, checkoutResponse.getDailyRentalCharge());
        assertEquals(3, checkoutResponse.getChargeDays());
        assertEquals(8.97, checkoutResponse.getPreDiscountCharge());
        assertEquals(0, checkoutResponse.getDiscountPercentage());
        assertEquals(0.0, checkoutResponse.getDiscountAmount());
        assertEquals(8.97, checkoutResponse.getFinalCharge());
    }

    @Test
    void testCheckoutTest5ToolJAKR() throws ValidationException {
        final String toolCode = "JAKR";
        mockTool(toolCode);
        final CheckoutRequest request = CheckoutRequest.builder()
                .toolCode(toolCode)
                .rentalDayCount(9)
                .discountPercent(0)
                .checkoutDate("07/02/15")
                .build();
        final Optional<CheckoutResponse> response = checkoutService.checkout(request);
        assertTrue(response.isPresent());
        final CheckoutResponse checkoutResponse = response.get();
        assertEquals(toolCode, checkoutResponse.getToolCode());
        assertEquals("Jackhammer", checkoutResponse.getToolType());
        assertEquals("Ridgid", checkoutResponse.getToolBrand());
        assertEquals(9, checkoutResponse.getRentalDays());
        assertEquals(LocalDate.of(2015, 7, 2), checkoutResponse.getCheckoutDate());
        assertEquals(LocalDate.of(2015, 7, 10), checkoutResponse.getDueDate());
        assertEquals(2.99, checkoutResponse.getDailyRentalCharge());
        assertEquals(6, checkoutResponse.getChargeDays());
        assertEquals(17.94, checkoutResponse.getPreDiscountCharge());
        assertEquals(0, checkoutResponse.getDiscountPercentage());
        assertEquals(0.0, checkoutResponse.getDiscountAmount());
        assertEquals(17.94, checkoutResponse.getFinalCharge());
    }

    @Test
    void testCheckoutTest6ToolJAKR() throws ValidationException {
        final String toolCode = "JAKR";
        mockTool(toolCode);
        final CheckoutRequest request = CheckoutRequest.builder()
                .toolCode(toolCode)
                .rentalDayCount(4)
                .discountPercent(50)
                .checkoutDate("07/02/20")
                .build();
        final Optional<CheckoutResponse> response = checkoutService.checkout(request);
        assertTrue(response.isPresent());
        final CheckoutResponse checkoutResponse = response.get();
        assertEquals(toolCode, checkoutResponse.getToolCode());
        assertEquals("Jackhammer", checkoutResponse.getToolType());
        assertEquals("Ridgid", checkoutResponse.getToolBrand());
        assertEquals(4, checkoutResponse.getRentalDays());
        assertEquals(LocalDate.of(2020, 7, 2), checkoutResponse.getCheckoutDate());
        assertEquals(LocalDate.of(2020, 7, 5), checkoutResponse.getDueDate());
        assertEquals(2.99, checkoutResponse.getDailyRentalCharge());
        assertEquals(1, checkoutResponse.getChargeDays());
        assertEquals(2.99, checkoutResponse.getPreDiscountCharge());
        assertEquals(50, checkoutResponse.getDiscountPercentage());
        assertEquals(1.5, checkoutResponse.getDiscountAmount());
        assertEquals(1.49, checkoutResponse.getFinalCharge());
    }

    @Test
    void testCheckoutToolNotFound() {
        final String toolCode = "TOOL999";
        final CheckoutRequest request = CheckoutRequest.builder()
                .toolCode(toolCode)
                .rentalDayCount(5)
                .discountPercent(10)
                .checkoutDate("08/01/24")
                .build();

        when(toolService.getToolByCode(toolCode)).thenReturn(Optional.empty());
        final ValidationException thrown = assertThrows(ValidationException.class, () -> {
            checkoutService.checkout(request);
        });
        assertEquals("Tool code TOOL999 doesn't exist.", thrown.getMessage());
    }

    private void mockTool(String toolCode) {
        List<Tool> toolData = buildTools();
        when(toolService.getToolByCode(toolCode)).thenReturn(toolData.stream().filter(t -> toolCode.equals(t.getCode())).findFirst());
    }

    private List<Tool> buildTools() {
        final ToolType ladder = ToolType.builder()
                .typeCode("Ladder")
                .dailyCharge(1.99)
                .weekdayCharge(true)
                .weekendCharge(true)
                .holidayCharge(false)
                .build();
        final ToolType chainsaw = ToolType.builder()
                .typeCode("Chainsaw")
                .dailyCharge(1.49)
                .weekdayCharge(true)
                .weekendCharge(false)
                .holidayCharge(true)
                .build();
        final ToolType jackhammer = ToolType.builder()
                .typeCode("Jackhammer")
                .dailyCharge(2.99)
                .weekdayCharge(true)
                .weekendCharge(false)
                .holidayCharge(false)
                .build();

        final Tool chns = Tool.builder()
                .code("CHNS")
                .type(chainsaw)
                .brand("Stihl")
                .active(true)
                .build();
        final Tool ladw = Tool.builder()
                .code("LADW")
                .type(ladder)
                .brand("Werner")
                .active(true)
                .build();
        final Tool jakd = Tool.builder()
                .code("JAKD")
                .type(jackhammer)
                .brand("DeWalt")
                .active(true)
                .build();
        final Tool jakr = Tool.builder()
                .code("JAKR")
                .type(jackhammer)
                .brand("Ridgid")
                .active(true)
                .build();

        return List.of(chns, ladw, jakd, jakr);
    }


}