
package ch.hearc.jee.api.deezer.model.full;

import java.util.List;

import ch.hearc.jee.api.deezer.model.minimal.MinimalArtist;
import ch.hearc.jee.api.deezer.model.minimal.MinimalGenre;
import ch.hearc.jee.api.deezer.model.minimal.MinimalTrack;

public class Album
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Album(long id, String title, String upc, String link, String share, String cover, String coverSmall, String coverMedium, String coverBig, String coverXl, String md5Image, List<MinimalGenre> genres, String label, int nbTracks, int duration, int fans, String releaseDate, String recordType,
			Boolean available, String tracklist, Boolean explicitLyrics, int explicitContentLyrics, int explicitContentCover, List<Contributor> contributors, MinimalArtist artist, List<MinimalTrack> tracks)
		{
		// Inputs
			{
			this.id = id;
			this.title = title;
			this.upc = upc;
			this.link = link;
			this.share = share;
			this.cover = cover;
			this.coverSmall = coverSmall;
			this.coverMedium = coverMedium;
			this.coverBig = coverBig;
			this.coverXl = coverXl;
			this.md5Image = md5Image;
			this.genres = genres;
			this.label = label;
			this.nbTracks = nbTracks;
			this.duration = duration;
			this.fans = fans;
			this.releaseDate = releaseDate;
			this.recordType = recordType;
			this.available = available;
			this.tracklist = tracklist;
			this.explicitLyrics = explicitLyrics;
			this.explicitContentLyrics = explicitContentLyrics;
			this.explicitContentCover = explicitContentCover;
			this.contributors = contributors;
			this.artist = artist;
			this.tracks = tracks;
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public MinimalArtist getArtist()
		{
		return this.artist;
		}

	public Boolean getAvailable()
		{
		return this.available;
		}

	public Boolean getExplicitLyrics()
		{
		return this.explicitLyrics;
		}

	public long getId()
		{
		return this.id;
		}

	public int getNbTracks()
		{
		return this.nbTracks;
		}

	public int getDuration()
		{
		return this.duration;
		}

	public int getFans()
		{
		return this.fans;
		}

	public int getExplicitContentLyrics()
		{
		return this.explicitContentLyrics;
		}

	public int getExplicitContentCover()
		{
		return this.explicitContentCover;
		}

	public List<MinimalGenre> getGenres()
		{
		return this.genres;
		}

	public List<Contributor> getContributors()
		{
		return this.contributors;
		}

	public List<MinimalTrack> getTracks()
		{
		return this.tracks;
		}

	public String getTitle()
		{
		return this.title;
		}

	public String getUpc()
		{
		return this.upc;
		}

	public String getLink()
		{
		return this.link;
		}

	public String getShare()
		{
		return this.share;
		}

	public String getCover()
		{
		return this.cover;
		}

	public String getCoverSmall()
		{
		return this.coverSmall;
		}

	public String getCoverMedium()
		{
		return this.coverMedium;
		}

	public String getCoverBig()
		{
		return this.coverBig;
		}

	public String getCoverXl()
		{
		return this.coverXl;
		}

	public String getMd5Image()
		{
		return this.md5Image;
		}

	public String getLabel()
		{
		return this.label;
		}

	public String getReleaseDate()
		{
		return this.releaseDate;
		}

	public String getRecordType()
		{
		return this.recordType;
		}

	public String getTracklist()
		{
		return this.tracklist;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private MinimalArtist artist;

	private Boolean available;
	private Boolean explicitLyrics;

	private long id;
	private int nbTracks;
	private int duration;
	private int fans;
	private int explicitContentLyrics;
	private int explicitContentCover;

	private List<MinimalGenre> genres;
	private List<Contributor> contributors;
	private List<MinimalTrack> tracks;

	private String title;
	private String upc;
	private String link;
	private String share;
	private String cover;
	private String coverSmall;
	private String coverMedium;
	private String coverBig;
	private String coverXl;
	private String md5Image;
	private String label;
	private String releaseDate;
	private String recordType;
	private String tracklist;
	}
