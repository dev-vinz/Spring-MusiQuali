
package ch.hearc.jee.api.deezer.model.minimal;

public class MinimalTrack
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public MinimalTrack(long id, Boolean readable, String title, String titleShort, String titleVersion, String link, int duration, int rank)
		{
		// Inputs
			{
			this.id = id;
			this.readable = readable;
			this.title = title;
			this.titleShort = titleShort;
			this.titleVersion = titleVersion;
			this.link = link;
			this.duration = duration;
			this.rank = rank;
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public Boolean getReadable()
		{
		return this.readable;
		}

	public long getId()
		{
		return this.id;
		}

	public int getDuration()
		{
		return this.duration;
		}

	public int getRank()
		{
		return this.rank;
		}

	public String getTitle()
		{
		return this.title;
		}

	public String getTitleShort()
		{
		return this.titleShort;
		}

	public String getTitleVersion()
		{
		return this.titleVersion;
		}

	public String getLink()
		{
		return this.link;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private Boolean readable;

	private long id;
	private int duration;
	private int rank;

	private String title;
	private String titleShort;
	private String titleVersion;
	private String link;
	}
