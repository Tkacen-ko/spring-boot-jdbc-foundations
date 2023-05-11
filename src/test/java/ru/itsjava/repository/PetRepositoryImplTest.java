package ru.itsjava.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DataJpaTest
@Import(PetRepositoryImpl.class)
public class PetRepositoryImplTest
{
    public static final long DEFAULT_PET_ID = 1L;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PetRepository petRepository;
    @Test
    public void shouldHaveCorrectInsert()
    {
        Pet expectedPet = new Pet(3L, "TestPet");
        petRepository.insert(expectedPet);
        var actualPet = petRepository.getById(3L);
        Assertions.assertEquals(expectedPet, actualPet);
    }

    @Test
    public void shouldHaveCorrectUpdate() {
        var genre = petRepository.getById(1L);
        genre.setBreed("VOLKODAV");
        petRepository.update(genre);
        var actualGenre = petRepository.getById(1L);

        Assertions.assertEquals("VOLKODAV", actualGenre.getBreed());
    }

    @Test
    public void shouldHaveCorrectDeleteById() {
        petRepository.deleteBuId(2L);
        var deletedGenre = petRepository.getById(2L);

        Assertions.assertNull(deletedGenre);
    }
}
