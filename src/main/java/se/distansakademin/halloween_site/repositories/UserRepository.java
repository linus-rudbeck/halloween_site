package se.distansakademin.halloween_site.repositories;

import org.springframework.data.repository.CrudRepository;
import se.distansakademin.halloween_site.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    public Optional<User> findUserByUsername(String username);
}
