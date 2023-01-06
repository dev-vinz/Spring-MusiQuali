
package ch.hearc.jee.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.hearc.jee.api.deezer.DeezerApi;
import ch.hearc.jee.api.deezer.model.full.Album;
import ch.hearc.jee.api.deezer.model.full.SearchResult;
import ch.hearc.jee.api.deezer.model.full.Track;
import ch.hearc.jee.api.deezer.model.minimal.MinimalGenre;
import ch.hearc.jee.model.Music;
import ch.hearc.jee.model.MusicalGenre;
import ch.hearc.jee.service.impl.MusicService;
import ch.hearc.jee.service.impl.MusicalGenreService;

@Controller
@RequestMapping("/musics")
public class MusicController
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@GetMapping(value = { "/", "/index" })
	public String showMusicsView(Model model)
		{
		model.addAttribute("musics", this.musicService.getAll());

		return "music/index";
		}

	@GetMapping(value = { "/{musicId}" })
	public String showMusicView(Model model, @PathVariable(required = true) Long musicId)
		{
		// Gets music from database
		Music music = this.musicService.getById(musicId);

		// Checks music exists. Otherwise, redirect to collection
		if (music == null)
			{ return "redirect:/musics/"; }

		// Gets track from Deezer
		Track track = DeezerApi.trackById(music.getTrackId());

		model.addAttribute("music", music);
		model.addAttribute("track", track);

		return "music/show";
		}

	@GetMapping(value = { "/search" })
	public String showSearchView()
		{
		return "music/search";
		}

	@PostMapping(value = { "/search/results" })
	public String showSearchResults(Model model, @RequestParam String query)
		{
		List<SearchResult> results = DeezerApi//
				.search(query);

		model.addAttribute("query", query);
		model.addAttribute("results", results);

		return "music/search_results";
		}

	@PostMapping(value = { "/add" })
	public String addToCollection(@RequestParam Long trackId)
		{
		Track track = DeezerApi.trackById(trackId);
		Album album = DeezerApi.albumById(track.getAlbum().getId());

		// Checks if music already exists
		Boolean isMusic = this.musicService//
				.getAll()//
				.stream()//
				.anyMatch(m -> m.getTrackId().longValue() == trackId.longValue());

		if (!isMusic)
			{
			// Creates all genres
			List<MusicalGenre> genres = album//
					.getGenres()//
					.stream()//
					.map(this::parseGenreToMusicalGenre)//
					.collect(Collectors.toList());

			// Creates music
			Music music = new Music(Long.valueOf(trackId), track.getArtist().getName(), track.getArtist().getPictureSmall(), track.getTitle(), track.getPreview());

			// Save music
			this.musicService.add(music);

			// Save genres and adds them to music
			genres//
					.stream()//
					.peek(musicalGenreService::add)//
					.forEach(music::addGenre);

			// Update music
			this.musicService.update(music);
			}

		return "redirect:/musics/";
		}

	@PostMapping(value = { "remove" })
	public String removeFromCollection(@RequestParam Long musicId)
		{
		// Simply removes
		this.musicService.deleteById(musicId);

		return "redirect:/musics/";
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	private MusicalGenre parseGenreToMusicalGenre(MinimalGenre genre)
		{
		// Checks if music already exists
		MusicalGenre mGenre = this.musicalGenreService.getByGenreId(genre.getId());

		if (mGenre == null)
			{
			mGenre = new MusicalGenre(Long.valueOf(genre.getId()), genre.getName());
			}

		return mGenre;
		}

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	@Autowired
	private MusicService musicService;

	@Autowired
	private MusicalGenreService musicalGenreService;
	}