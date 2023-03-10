
package ch.hearc.jee.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public User()
		{
		// Outputs
			{
			this.musics = new ArrayList<Music>();
			}
		}

	public User(String firstName, String lastName, String email, String password)
		{
		// Inputs
			{
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.password = password;
			}

		// Outputs
			{
			this.musics = new ArrayList<Music>();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public void addMusic(Music music)
		{
		this.musics.add(music);
		}

	/*------------------------------*\
	|*				equals			*|
	\*------------------------------*/

	public boolean isEquals(User user)
		{
		if (this == user)
			{
			return true;
			}
		else
			{
			return this.id.longValue() == user.id.longValue();
			}
		}

	@Override
	public boolean equals(Object object2)
		{
		if (object2.getClass().equals(this.getClass()))
			{
			return isEquals((User)object2);
			}
		else
			{
			return false;
			}
		}

	@Override
	public int hashCode()
		{

		return Long.hashCode(this.id);
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public Long getId()
		{
		return this.id;
		}

	@JsonIgnore
	public String getName()
		{
		return this.firstName + " " + this.lastName;
		}

	public String getFirstName()
		{
		return this.firstName;
		}

	public String getLastName()
		{
		return this.lastName;
		}

	public String getEmail()
		{
		return this.email;
		}

	public String getPassword()
		{
		return this.password;
		}

	public List<Music> getMusics()
		{
		return this.musics;
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setFirstName(String firstName)
		{
		this.firstName = firstName;
		}

	public void setLastName(String lastName)
		{
		this.lastName = lastName;
		}

	public void setEmail(String email)
		{
		this.email = email;
		}

	public void setPassword(String password)
		{
		this.password = password;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;
	private String lastName;
	private String email;

	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JsonIgnoreProperties("user")
	private List<Music> musics;
	}
