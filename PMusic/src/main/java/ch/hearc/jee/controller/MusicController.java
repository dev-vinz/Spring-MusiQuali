
package ch.hearc.jee.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import ch.hearc.jee.model.User;
import ch.hearc.jee.service.impl.MusicService;
import ch.hearc.jee.service.impl.MusicalGenreService;
import ch.hearc.jee.service.impl.UserService;

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

	@GetMapping(value = { "/", "/index", "/{pageNo}", "/index/{pageNo}" })
	public String showMusicsView(Principal principal, Model model, @PathVariable(required = false) Integer pageNo, @RequestParam(defaultValue = "id") String sortBy)
		{
		// Gets logged in user email - and user
		String email = principal.getName();
		User user = this.userService.getByEmail(email);

		// Gets musics
		Pageable pageable = PageRequest.of(pageNo == null ? 0 : pageNo, 6, Sort.by(sortBy));
		Page<Music> musics = this.musicService.getAllByUser(user, pageable);

		model.addAttribute("musics", musics);
		model.addAttribute("sortBy", sortBy);

		return "music/index";
		}

	@GetMapping(value = { "/show/{musicId}" })
	public String showMusicView(Principal principal, Model model, @PathVariable(required = true) Long musicId)
		{
		// Gets logged in user email - and user
		String email = principal.getName();
		User user = this.userService.getByEmail(email);

		// Gets musics
		List<Music> musics = user.getMusics();

		// Gets music from user
		Optional<Music> music = musics//
				.stream()//
				.filter(m -> m.getId().longValue() == musicId.longValue())//
				.findFirst();

		// Checks music exists. Otherwise, redirect to collection
		if (music.isEmpty())
			{ return "redirect:/musics/"; }

		// Gets track from Deezer
		Track track = DeezerApi.trackById(music.get().getTrackId());

		// Gets album from Deezer
		Album album = DeezerApi.albumById(track.getAlbum().getId());

		model.addAttribute("music", music.get());
		model.addAttribute("track", track);
		model.addAttribute("album", album);

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
	public String addToCollection(Principal principal, @RequestParam Long trackId)
		{
		// Gets logged in user email - and user
		String email = principal.getName();
		User user = this.userService.getByEmail(email);

		// Gets musics
		List<Music> musics = user.getMusics();

		Track track = DeezerApi.trackById(trackId);
		Album album = DeezerApi.albumById(track.getAlbum().getId());

		// Checks if music already exists
		Boolean isMusic = musics//
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
			music.setUser(user);

			// Saves music
			this.musicService.add(music);

			// Saves genres and adds them to music
			genres//
					.stream()//
					.peek(musicalGenreService::add)//
					.forEach(music::addGenre);

			// Updates music & user
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

	@Autowired
	private UserService userService;
	}
