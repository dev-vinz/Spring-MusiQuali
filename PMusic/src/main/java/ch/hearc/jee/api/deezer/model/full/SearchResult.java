
package ch.hearc.jee.api.deezer.model.full;

import ch.hearc.jee.api.deezer.model.minimal.MinimalAlbum;
import ch.hearc.jee.api.deezer.model.minimal.MinimalArtist;

public class SearchResult
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public SearchResult(long id, Boolean readable, String title, String titleShort, String titleVersion, String link, int duration, int rank, Boolean explicitLyrics, int explicitContentLyrics, int explicitContentCover, String preview, String md5Image, MinimalArtist artist, MinimalAlbum album)
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
			this.explicitLyrics = explicitLyrics;
			this.explicitContentLyrics = explicitContentLyrics;
			this.explicitContentCover = explicitContentCover;
			this.preview = preview;
			this.md5Image = md5Image;
			this.artist = artist;
			this.album = album;
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public MinimalAlbum getAlbum()
		{
		return this.album;
		}

	public MinimalArtist getArtist()
		{
		return this.artist;
		}

	public Boolean getReadable()
		{
		return this.readable;
		}

	public Boolean getExplicitLyrics()
		{
		return this.explicitLyrics;
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

	public int getExplicitContentLyrics()
		{
		return this.explicitContentLyrics;
		}

	public int getExplicitContentCover()
		{
		return this.explicitContentCover;
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

	public String getPreview()
		{
		return this.preview;
		}

	public String getMd5Image()
		{
		return this.md5Image;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private MinimalAlbum album;

	private MinimalArtist artist;

	private Boolean readable;
	private Boolean explicitLyrics;

	private long id;
	private int duration;
	private int rank;
	private int explicitContentLyrics;
	private int explicitContentCover;

	private String title;
	private String titleShort;
	private String titleVersion;
	private String link;
	private String preview;
	private String md5Image;
	}
