package desafio_quality.desafio_quality.util;

import java.math.BigDecimal;

public class CalculationArea {

    /**
     * Function to calculate the total value, based on the base value and dimensions of a property.
     * @param valueDistrictM2
     * @param weight
     * @param length
     * @return BigDecimal
     */
    public static BigDecimal calculateTotalValueDistrictM2(BigDecimal valueDistrictM2,  Double length, Double weight) {
        return valueDistrictM2.multiply(BigDecimal.valueOf(weight)).multiply(BigDecimal.valueOf(length));
    }
}
