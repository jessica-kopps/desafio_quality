package desafio_quality.desafio_quality.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {
    private Long id;
    private String name;
    private List<Room> rooms;
    private Neighborhood neighborhood;


    private BigDecimal calculateTotalValueDistrictM2(BigDecimal valueDistrictM2, Double length, Double width) {
        return valueDistrictM2.multiply(BigDecimal.valueOf(width)).multiply(BigDecimal.valueOf(length));
    }

    public BigDecimal calculateTotalAreaOfRooms() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        getRooms().forEach(room ->
                totalPrice.add(this.calculateTotalValueDistrictM2(
                        this.getNeighborhood().getValueDistrictM2(),
                        room.getLength(), room.getWidth())
                ));
        return  totalPrice;
    }
}
