
package ch.hearc.jee.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestUser
	{

	/*------------------------------------------------------------------*\
	 |*							Methodes Public							*|
	 \*-----------------------------------------------------------------*/

	@Test
	public void givenAUser_whenChangeFirstName_thenVerify()
		{
		User user = new User();
		String firstName = "Toto";

		user.setFirstName(firstName);

		Assertions.assertEquals(user.getFirstName(), firstName);
		}

	@Test
	public void givenAUser_whenChangeLastName_thenVerify()
		{
		User user = new User();
		String lastName = "Dupond";

		user.setLastName(lastName);

		Assertions.assertEquals(user.getLastName(), lastName);
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	}
