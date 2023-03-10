
package ch.hearc.jee.api.deezer.model.full;

public class Contributor
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Contributor(long id, String name, String link, String share, String picture, String pictureSmall, String pictureMedium, String pictureBig, String pictureXl, Boolean radio, String tracklist, String role)
		{
		// Inputs
			{
			this.id = id;
			this.name = name;
			this.link = link;
			this.share = share;
			this.picture = picture;
			this.pictureSmall = pictureSmall;
			this.pictureMedium = pictureMedium;
			this.pictureBig = pictureBig;
			this.pictureXl = pictureXl;
			this.radio = radio;
			this.tracklist = tracklist;
			this.role = role;
			}
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public Boolean getRadio()
		{
		return this.radio;
		}

	public long getId()
		{
		return this.id;
		}

	public String getName()
		{
		return this.name;
		}

	public String getLink()
		{
		return this.link;
		}

	public String getShare()
		{
		return this.share;
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

	public String getRole()
		{
		return this.role;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private Boolean radio;

	private long id;

	private String name;
	private String link;
	private String share;
	private String picture;
	private String pictureSmall;
	private String pictureMedium;
	private String pictureBig;
	private String pictureXl;
	private String tracklist;
	private String role;
	}
