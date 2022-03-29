package desafio_quality.desafio_quality.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    private String name;
    private List<Room> roms;
    private Neighborhood neighborhood;
}
