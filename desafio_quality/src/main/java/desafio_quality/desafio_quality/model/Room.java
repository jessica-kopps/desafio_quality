package desafio_quality.desafio_quality.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
    private String name;
    private Double length;
    private Double width;
    private Double area;

    public Double calculateTotalArea( Double length, Double width) {
        this.area = length * width;
        return area;
    }
}
