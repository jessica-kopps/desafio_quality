package desafio_quality.desafio_quality.model;

import lombok.*;

import java.util.List;

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


}
