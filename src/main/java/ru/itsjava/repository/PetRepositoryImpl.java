package ru.itsjava.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.Pet;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Transactional
public class PetRepositoryImpl implements PetRepository {
    private final EntityManager entityManager;

    @Override
    public Pet getById(Long id) {
        return entityManager.find(Pet.class, id);
    }

    @Override
    public void insert(Pet pet) {
        if(pet.getId() ==0){
            entityManager.persist(pet);
        }else {
            entityManager.merge(pet);
        }
    }

    @Override
    public void update(Pet pet) {
        entityManager.merge(pet);
    }

    @Override
    public void deleteBuId(long id) {
        Pet pet = entityManager.find(Pet.class, id);
        entityManager.remove(pet);
    }
}
