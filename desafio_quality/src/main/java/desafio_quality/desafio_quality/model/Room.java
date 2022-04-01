package desafio_quality.desafio_quality.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    private String name;
    private Double width;
    private Double length;
    private Double area;

    public Room(String name, Double length, Double width){
        this.name = name;
        this.length = length;
        this.width = width;
        this.area = this.calculateArea();
    }

    public Double calculateArea() {
        this.area = length * width;
        return area;
    }
}
