
package ch.hearc.jee.api.deezer.model.minimal;

public class MinimalArtist
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public MinimalArtist(long id, String name, String picture, String pictureSmall, String pictureMedium, String pictureBig, String pictureXl, String tracklist)
		{
		// Inputs
			{
			this.id = id;
			this.name = name;
			this.picture = picture;
			this.pictureSmall = pictureSmall;
			this.pictureMedium = pictureMedium;
			this.pictureBig = pictureBig;
			this.pictureXl = pictureXl;
			this.tracklist = tracklist;
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public long getId()
		{
		return this.id;
		}

	public String getName()
		{
		return this.name;
		}

	public String getPicture()
		{
		return this.picture;
		}

	public String getPictureSmall()
		{
		return this.pictureSmall;
		}

	public String getPictureMedium()
		{
		return this.pictureMedium;
		}

	public String getPictureBig()
		{
		return this.pictureBig;
		}

	public String getPictureXl()
		{
		return this.pictureXl;
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

	private long id;

	private String name;
	private String picture;
	private String pictureSmall;
	private String pictureMedium;
	private String pictureBig;
	private String pictureXl;
	private String tracklist;
	}
