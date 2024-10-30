package se.distansakademin.halloween_site.repositories;

import org.springframework.data.repository.CrudRepository;
import se.distansakademin.halloween_site.models.Monster;

public interface MonsterRepository extends CrudRepository<Monster, String> {
}
