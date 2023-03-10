
package ch.hearc.jee.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ch.hearc.jee.model.User;

public interface IUserRepository extends CrudRepository<User, Long>
	{

	Optional<User> findByEmail(String email);
	}
