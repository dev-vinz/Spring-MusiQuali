
package ch.hearc.jee.controller;

import java.security.Principal;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.hearc.jee.model.Music;
import ch.hearc.jee.model.Score;
import ch.hearc.jee.model.User;
import ch.hearc.jee.service.impl.MusicService;
import ch.hearc.jee.service.impl.ScoreService;
import ch.hearc.jee.service.impl.UserService;
import ch.hearc.jee.utilities.Levenshtein;

@Controller
public class HomeController
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@GetMapping(value = { "/", "/index" })
	public String showHomeView()
		{
		return "accueil";
		}

	@GetMapping(value = { "/play" })
	public String showPlayView(Principal principal, Model model)
		{
		// Gets logged in user email - and user
		String email = principal.getName();
		User user = this.userService.getByEmail(email);

		// Gets all musics
		List<Music> musics = user.getMusics();
		int size = musics.size();

		// Gets a random music
		Music music = null;

		if (size > 0)
			{
			int rnd = new Random().nextInt(size);
			music = musics.get(rnd);
			}

		model.addAttribute("music", music);
		model.addAttribute("isEmpty", size < 1);

		return "home/play";
		}

	@PostMapping(value = { "/play" })
	public String validatePlayView(Principal principal, Model model, @RequestParam Long musicId, @RequestParam String artist, @RequestParam String title)
		{
		Music music = this.musicService.getById(musicId);

		// Verifies artist
		double artistScore = Levenshtein.getRatio(artist.toLowerCase(), music.getAuthor().toLowerCase());

		// Verifies title
		double titleScore = Levenshtein.getRatio(title.toLowerCase(), music.getTitle().toLowerCase());

		// Creates score
		Score score = new Score((int)Math.round(artistScore * 100), (int)Math.round(titleScore * 100));
		score.setMusic(music);

		// Saves
		this.scoreService.add(score);

		model.addAttribute("isPost", true);
		model.addAttribute("solution", music);
		model.addAttribute("score", score);

		return showPlayView(principal, model);
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	@Autowired
	private MusicService musicService;

	@Autowired
	private ScoreService scoreService;

	@Autowired
	private UserService userService;
	}
