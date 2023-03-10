
package ch.hearc.jee.repository;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import ch.hearc.jee.model.User;

@DataJpaTest
public class TestUserRepository
	{

	/*------------------------------------------------------------------*\
	 |*							Methodes Public							*|
	 \*-----------------------------------------------------------------*/

	@Test
	public void givenUser_whenPersistUser_thenUserIsPersisted()
		{
		User user = new User("Toto", "Dupond", USER_EMAIL, "toto1234");

		this.entityManager.persist(user);
		this.entityManager.flush();

		Optional<User> userSearch = this.userRepository.findByEmail(user.getEmail());

		Assertions.assertTrue(userSearch.isPresent());
		Assertions.assertEquals(userSearch.get().getId(), user.getId());
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private IUserRepository userRepository;

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static final String USER_EMAIL = "toto@dupond.ch";

	}
