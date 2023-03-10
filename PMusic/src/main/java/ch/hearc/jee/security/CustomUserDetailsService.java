
package ch.hearc.jee.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ch.hearc.jee.model.User;
import ch.hearc.jee.service.impl.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
		{
		User user = this.userService.getByEmail(email);

		if (user != null)
			{
			List<GrantedAuthority> roles = new ArrayList<>();
			roles.add(new SimpleGrantedAuthority("ROLE_USER"));

			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
			}
		else
			{
			throw new UsernameNotFoundException("Invalid email or password.");
			}
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	@Autowired
	private UserService userService;
	}
