
package ch.hearc.jee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.hearc.jee.model.MusicalGenre;
import ch.hearc.jee.repository.IMusicalGenreRepository;
import ch.hearc.jee.service.IDatabaseService;

@Service
public class MusicalGenreService implements IDatabaseService<MusicalGenre>
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void add(MusicalGenre item)
		{
		this.musicalGenreRepository.save(item);
		}

	@Override
	public void delete(MusicalGenre item)
		{
		deleteById(item.getId());
		}

	@Override
	public void deleteById(Long id)
		{
		this.musicalGenreRepository.deleteById(Long.valueOf(id));
		}

	@Override
	public void update(MusicalGenre item)
		{
		this.musicalGenreRepository.save(item);
		}

	@Override
	public List<MusicalGenre> getAll()
		{
		List<MusicalGenre> genres = new ArrayList<MusicalGenre>();

		this.musicalGenreRepository//
				.findAll()//
				.forEach(genres::add);

		return genres;
		}

	@Override
	public MusicalGenre getById(Long id)
		{
		return this.musicalGenreRepository//
				.findById(Long.valueOf(id))//
				.orElseGet(() -> null);
		}

	public MusicalGenre getByGenreId(Long id)
		{
		Optional<MusicalGenre> musicalGenre = this.musicalGenreRepository//
				.findByGenreId(id);

		if (musicalGenre.isPresent())
			{
			return musicalGenre.get();
			}
		else
			{
			return null;
			}
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
	private IMusicalGenreRepository musicalGenreRepository;
	}
