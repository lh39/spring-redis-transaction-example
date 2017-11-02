package de.lh39.springredistransactionexample.repository;

import java.util.Map;

import javax.annotation.PostConstruct;

import de.lh39.springredistransactionexample.domain.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * Implementation of pet repository.

 * @author lh39
 */
@Repository
public class PetRepositoryImpl implements PetRepository {
  private static final String KEY = "Pet";
  private RedisTemplate<String, Pet> redisTemplate;
  private HashOperations hashOperations;

  @Autowired
  public PetRepositoryImpl(RedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @PostConstruct
  private void init() {
    hashOperations = redisTemplate.opsForHash();
  }

  @Override
  public void savePet(Pet pet) {
    hashOperations.put(KEY, pet.getName() + pet.getPetType().name(), pet);
  }

  @Override
  public void updatePet(Pet pet) {
    hashOperations.put(KEY, pet.getName() + pet.getPetType().name(), pet);
  }

  @Override
  public Pet findPet(String nameAndType) {
    return (Pet) hashOperations.get(KEY, nameAndType);
  }

  @Override
  public Map<Object, Object> findAllPets() {
    return hashOperations.entries(KEY);
  }

  @Override
  public void deletePet(String nameAndType) {
    hashOperations.delete(KEY, nameAndType);
  }
}
