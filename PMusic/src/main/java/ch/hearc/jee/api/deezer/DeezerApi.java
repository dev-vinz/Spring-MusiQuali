
package ch.hearc.jee.api.deezer;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import ch.hearc.jee.api.deezer.model.full.Album;
import ch.hearc.jee.api.deezer.model.full.Contributor;
import ch.hearc.jee.api.deezer.model.full.SearchResult;
import ch.hearc.jee.api.deezer.model.full.Track;
import ch.hearc.jee.api.deezer.model.minimal.MinimalAlbum;
import ch.hearc.jee.api.deezer.model.minimal.MinimalArtist;
import ch.hearc.jee.api.deezer.model.minimal.MinimalGenre;
import ch.hearc.jee.api.deezer.model.minimal.MinimalTrack;

public class DeezerApi
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public static Album albumById(long id)
		{
		Album album = null;

		JSONObject response;
		try
			{
			response = Unirest//
					.get(API_ENDPOINT + "/album/" + id)//
					.asJson()//
					.getBody()//
					.getObject();

			album = parseAlbum(response);
			}
		catch (UnirestException | JSONException e)
			{
			// Do nothing
			}

		return album;
		}

	public static List<SearchResult> search(String query)
		{
		List<SearchResult> results = new ArrayList<SearchResult>();

		try
			{
			JSONArray response = Unirest//
					.get(API_ENDPOINT + "/search?q=" + URLEncoder.encode(query, "UTF-8"))//
					.asJson()//
					.getBody()//
					.getObject()//
					.getJSONArray("data");

			int resultsLength = response.length();

			IntStream//
					.range(0, resultsLength)//
					.mapToObj(i -> response.getJSONObject(i))//
					.map(DeezerApi::parseSearchResult)//
					.forEach(results::add);
			}
		catch (UnirestException | JSONException | UnsupportedEncodingException e)
			{
			// Do nothing
			}

		return results;
		}

	public static Track trackById(long id)
		{
		Track track = null;

		try
			{
			JSONObject response = Unirest//
					.get(API_ENDPOINT + "/track/" + id)//
					.asJson()//
					.getBody()//
					.getObject();

			track = parseTrack(response);
			}
		catch (JSONException | UnirestException e)
			{
			// Do nothing
			}

		return track;
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private static MinimalAlbum parseMinimalAlbum(JSONObject object)
		{
		long id = object.getLong("id");

		String title = object.getString("title");
		String cover = object.getString("cover");
		String coverSmall = object.getString("cover_small");
		String coverMedium = object.getString("cover_medium");
		String coverBig = object.getString("cover_big");
		String coverXl = object.getString("cover_xl");
		String md5Image = object.getString("md5_image");
		String tracklist = object.getString("tracklist");

		return new MinimalAlbum(id, title, cover, coverSmall, coverMedium, coverBig, coverXl, md5Image, tracklist);
		}

	private static MinimalArtist parseMinimalArtist(JSONObject object)
		{
		long id = object.getLong("id");

		String name = object.getString("name");
		String picture = object.getString("picture");
		String pictureSmall = object.getString("picture_small");
		String pictureMedium = object.getString("picture_medium");
		String pictureBig = object.getString("picture_big");
		String pictureXl = object.getString("picture_xl");
		String tracklist = object.getString("tracklist");

		return new MinimalArtist(id, name, picture, pictureSmall, pictureMedium, pictureBig, pictureXl, tracklist);
		}

	private static MinimalGenre parseMinimalGenre(JSONObject object)
		{
		long id = object.getLong("id");

		String name = object.getString("name");
		String picture = object.getString("picture");

		return new MinimalGenre(id, name, picture);
		}

	private static MinimalTrack parseMinimalTrack(JSONObject object)
		{
		Boolean readable = object.getBoolean("readable");

		long id = object.getLong("id");
		int duration = object.getInt("duration");
		int rank = object.getInt("rank");

		String title = object.getString("title");
		String titleShort = object.getString("title_short");
		String titleVersion = object.getString("title_version");
		String link = object.getString("link");

		return new MinimalTrack(id, readable, title, titleShort, titleVersion, link, duration, rank);
		}

	private static Album parseAlbum(JSONObject object)
		{
		MinimalArtist artist = parseMinimalArtist(object.getJSONObject("artist"));

		Boolean available = object.getBoolean("available");
		Boolean explicitLyrics = object.getBoolean("explicit_lyrics");

		long id = object.getLong("id");
		int nbTracks = object.getInt("nb_tracks");
		int duration = object.getInt("duration");
		int fans = object.getInt("fans");
		int explicitContentLyrics = object.getInt("explicit_content_lyrics");
		int explicitContentCover = object.getInt("explicit_content_cover");

		List<MinimalGenre> genres = new ArrayList<MinimalGenre>();

		IntStream//
				.range(0, object.getJSONObject("genres").getJSONArray("data").length())//
				.mapToObj(i -> object.getJSONObject("genres").getJSONArray("data").getJSONObject(i))//
				.map(DeezerApi::parseMinimalGenre)//
				.forEach(genres::add);

		List<Contributor> contributors = new ArrayList<Contributor>();

		IntStream//
				.range(0, object.getJSONArray("contributors").length())//
				.mapToObj(i -> object.getJSONArray("contributors").getJSONObject(i))//
				.map(DeezerApi::parseContributor)//
				.forEach(contributors::add);

		List<MinimalTrack> tracks = new ArrayList<MinimalTrack>();

		IntStream//
				.range(0, object.getJSONObject("tracks").getJSONArray("data").length())//
				.mapToObj(i -> object.getJSONObject("tracks").getJSONArray("data").getJSONObject(i))//
				.map(DeezerApi::parseMinimalTrack)//
				.forEach(tracks::add);

		String title = object.getString("title");
		String upc = object.getString("upc");
		String link = object.getString("link");
		String share = object.getString("share");
		String cover = object.getString("cover");
		String coverSmall = object.getString("cover_small");
		String coverMedium = object.getString("cover_medium");
		String coverBig = object.getString("cover_big");
		String coverXl = object.getString("cover_xl");
		String md5Image = object.getString("md5_image");
		String label = object.getString("label");
		String releaseDate = object.getString("release_date");
		String recordType = object.getString("record_type");
		String tracklist = object.getString("tracklist");

		return new Album(id, title, upc, link, share, cover, coverSmall, coverMedium, coverBig, coverXl, md5Image, genres, label, nbTracks, duration, fans, releaseDate, recordType, available, tracklist, explicitLyrics, explicitContentLyrics, explicitContentCover, contributors, artist, tracks);
		}

	private static Contributor parseContributor(JSONObject object)
		{
		Boolean radio = object.getBoolean("radio");

		long id = object.getLong("id");

		String name = object.getString("name");
		String link = object.getString("link");
		String share = object.getString("share");
		String picture = object.getString("picture");
		String pictureSmall = object.getString("picture_small");
		String pictureMedium = object.getString("picture_medium");
		String pictureBig = object.getString("picture_big");
		String pictureXl = object.getString("picture_xl");
		String tracklist = object.getString("tracklist");
		String role = object.getString("role");

		return new Contributor(id, name, link, share, picture, pictureSmall, pictureMedium, pictureBig, pictureXl, radio, tracklist, role);
		}

	private static SearchResult parseSearchResult(JSONObject object)
		{
		MinimalAlbum album = parseMinimalAlbum(object.getJSONObject("album"));

		MinimalArtist artist = parseMinimalArtist(object.getJSONObject("artist"));

		Boolean readable = object.getBoolean("readable");
		Boolean explicitLyrics = object.getBoolean("explicit_lyrics");

		long id = object.getLong("id");
		int duration = object.getInt("duration");
		int rank = object.getInt("rank");
		int explicitContentLyrics = object.getInt("explicit_content_lyrics");
		int explicitContentCover = object.getInt("explicit_content_cover");

		String title = object.getString("title");
		String titleShort = object.getString("title_short");
		String titleVersion = object.getString("title_version");
		String link = object.getString("link");
		String preview = object.getString("preview");
		String md5Image = object.getString("md5_image");

		return new SearchResult(id, readable, title, titleShort, titleVersion, link, duration, rank, explicitLyrics, explicitContentLyrics, explicitContentCover, preview, md5Image, artist, album);
		}

	private static Track parseTrack(JSONObject object)
		{
		MinimalAlbum album = parseMinimalAlbum(object.getJSONObject("album"));

		MinimalArtist artist = parseMinimalArtist(object.getJSONObject("artist"));

		Boolean readable = object.getBoolean("readable");
		Boolean explicitLyrics = object.getBoolean("explicit_lyrics");

		long id = object.getLong("id");
		int duration = object.getInt("duration");
		int trackPosition = object.getInt("track_position");
		int diskNumber = object.getInt("disk_number");
		int rank = object.getInt("rank");
		int explicitContentLyrics = object.getInt("explicit_content_lyrics");
		int explicitContentCover = object.getInt("explicit_content_cover");
		int bpm = object.getInt("bpm");
		int gain = object.getInt("gain");

		List<String> availableCountries = new ArrayList<String>();

		IntStream//
				.range(0, object.getJSONArray("available_countries").length())//
				.mapToObj(i -> object.getJSONArray("available_countries").getString(i))//
				.forEach(availableCountries::add);

		List<Contributor> contributors = new ArrayList<Contributor>();

		IntStream//
				.range(0, object.getJSONArray("contributors").length())//
				.mapToObj(i -> object.getJSONArray("contributors").getJSONObject(i))//
				.map(DeezerApi::parseContributor)//
				.forEach(contributors::add);

		String title = object.getString("title");
		String titleShort = object.getString("title_short");
		String titleVersion = object.getString("title_version");
		String isrc = object.getString("isrc");
		String link = object.getString("link");
		String share = object.getString("share");
		String releaseDate = object.getString("release_date");
		String preview = object.getString("preview");
		String md5Image = object.getString("md5_image");

		return new Track(id, readable, title, titleShort, titleVersion, isrc, link, share, duration, trackPosition, diskNumber, rank, releaseDate, explicitLyrics, explicitContentLyrics, explicitContentCover, preview, bpm, gain, availableCountries, contributors, md5Image, artist, album);
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static final String API_ENDPOINT = "https://api.deezer.com";
	}
