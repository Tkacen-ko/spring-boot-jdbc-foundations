package ru.itsjava.repository;

import ru.itsjava.domain.Pet;

public interface PetRepository {
    Pet getById(long id);

    void insert(Pet genre);

    void update(Pet genre);

    void deleteBuId(long id);
}

