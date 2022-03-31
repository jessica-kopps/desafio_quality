package desafio_quality.desafio_quality.repository;

import desafio_quality.desafio_quality.model.Neighborhood;
import desafio_quality.desafio_quality.util.JsonFileUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NeighborhoodRepository implements INeighborhoodRepository<Neighborhood> {

    private static final String FILENAME = "neighborhood.json";

    @Override
    public List<Neighborhood> findAll() {
        JsonFileUtil<Neighborhood> jsonFile = new JsonFileUtil<Neighborhood>(FILENAME);
        return jsonFile.read(Neighborhood.class);
    }
}
