
package ch.hearc.jee.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "genres")
public class MusicalGenre
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public MusicalGenre()
		{
		// Outputs
			{
			this.musics = new HashSet<Music>();
			}
		}

	public MusicalGenre(Long genreId, String name)
		{
		// Inputs
			{
			this.genreId = genreId;
			this.name = name;
			}

		// Outputs
			{
			this.musics = new HashSet<Music>();
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				equals			*|
	\*------------------------------*/

	public boolean isEquals(MusicalGenre musicalGenre)
		{
		if (this == musicalGenre)
			{
			return true;
			}
		else
			{
			return this.id.longValue() == musicalGenre.id.longValue();
			}
		}

	@Override
	public boolean equals(Object object2)
		{
		if (object2.getClass().equals(this.getClass()))
			{
			return isEquals((MusicalGenre)object2);
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

	public Long getGenreId()
		{
		return this.genreId;
		}

	public String getName()
		{
		return this.name;
		}

	public Set<Music> getMusics()
		{
		return this.musics;
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

	private Long genreId;

	private String name;

	@ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("genres")
	private Set<Music> musics;
	}
