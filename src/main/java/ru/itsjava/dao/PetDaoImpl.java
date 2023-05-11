package ru.itsjava.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class PetDaoImpl implements PetDao {
    private final NamedParameterJdbcOperations jdbc;

    @Override
    public List<Pet> findAll() {
        return jdbc.query("select id, breed from pet", new PetMapper());
    }

    @Override
    public Pet findByBreed(String breed) {
        return jdbc.queryForObject("select id, breed from pet where breed = :breed",
                new MapSqlParameterSource(Map.of("breed", breed)), new PetMapper());
    }

    @Override
    public long insert(Pet pet) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = Map.of("breed", pet.getBreed(), "id", pet.getId());

        jdbc.update("insert into pet(id, breed) values (:id, :breed)",
                new MapSqlParameterSource(params), keyHolder);

        return keyHolder.getKey().longValue();
    }

    private static class PetMapper implements RowMapper<Pet> {

        @Override
        public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Pet(rs.getLong("id"), rs.getString("breed"));
        }
    }
}
