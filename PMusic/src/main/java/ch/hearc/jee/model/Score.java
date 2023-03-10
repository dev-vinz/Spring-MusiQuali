
package ch.hearc.jee.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "scores")
public class Score
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Score()
		{
		}

	public Score(Integer artistScore, Integer titleScore)
		{
		// Inputs
			{
			this.artistScore = artistScore;
			this.titleScore = titleScore;
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				equals			*|
	\*------------------------------*/

	public boolean isEquals(Score score)
		{
		if (this == score)
			{
			return true;
			}
		else
			{
			return this.id.longValue() == score.id.longValue();
			}
		}

	@Override
	public boolean equals(Object object2)
		{
		if (object2.getClass().equals(this.getClass()))
			{
			return isEquals((Score)object2);
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

	public Integer getArtistScore()
		{
		return this.artistScore;
		}

	public Integer getTitleScore()
		{
		return this.titleScore;
		}

	public Music getMusic()
		{
		return this.music;
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setMusic(Music music)
		{
		this.music = music;
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

	private Integer artistScore;
	private Integer titleScore;

	@ManyToOne
	@JoinColumn(name = "music_id", nullable = false)
	@JsonIgnoreProperties("scores")
	private Music music;
	}
