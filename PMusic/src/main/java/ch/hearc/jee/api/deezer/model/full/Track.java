
package ch.hearc.jee.api.deezer.model.full;

import java.util.List;

import ch.hearc.jee.api.deezer.model.minimal.MinimalAlbum;
import ch.hearc.jee.api.deezer.model.minimal.MinimalArtist;

public class Track
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Track(long id, Boolean readable, String title, String titleShort, String titleVersion, String isrc, String link, String share, int duration, int trackPosition, int diskNumber, int rank, String releaseDate, Boolean explicitLyrics, int explicitContentLyrics, int explicitContentCover,
			String preview, int bpm, int gain, List<String> availableCountries, List<Contributor> contributors, String md5Image, MinimalArtist artist, MinimalAlbum album)
		{
		// Inputs
			{
			this.id = id;
			this.readable = readable;
			this.title = title;
			this.titleShort = titleShort;
			this.titleVersion = titleVersion;
			this.isrc = isrc;
			this.link = link;
			this.share = share;
			this.duration = duration;
			this.trackPosition = trackPosition;
			this.diskNumber = diskNumber;
			this.rank = rank;
			this.releaseDate = releaseDate;
			this.explicitLyrics = explicitLyrics;
			this.explicitContentLyrics = explicitContentLyrics;
			this.explicitContentCover = explicitContentCover;
			this.preview = preview;
			this.bpm = bpm;
			this.gain = gain;
			this.availableCountries = availableCountries;
			this.contributors = contributors;
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

	public int getTrackPosition()
		{
		return this.trackPosition;
		}

	public int getDiskNumber()
		{
		return this.diskNumber;
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

	public int getBpm()
		{
		return this.bpm;
		}

	public int getGain()
		{
		return this.gain;
		}

	public List<String> getAvailableCountries()
		{
		return this.availableCountries;
		}

	public List<Contributor> getContributors()
		{
		return this.contributors;
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

	public String getIsrc()
		{
		return this.isrc;
		}

	public String getLink()
		{
		return this.link;
		}

	public String getShare()
		{
		return this.share;
		}

	public String getReleaseDate()
		{
		return this.releaseDate;
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
	private int trackPosition;
	private int diskNumber;
	private int rank;
	private int explicitContentLyrics;
	private int explicitContentCover;
	private int bpm;
	private int gain;

	private List<String> availableCountries;
	private List<Contributor> contributors;

	private String title;
	private String titleShort;
	private String titleVersion;
	private String isrc;
	private String link;
	private String share;
	private String releaseDate;
	private String preview;
	private String md5Image;
	}
