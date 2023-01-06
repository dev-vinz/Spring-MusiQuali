
package ch.hearc.jee.service.impl;

import java.util.ArrayList;
import java.util.List;

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
				.findById(id)//
				.orElseGet(() -> null);
		}

	public MusicalGenre getByGenreId(Long id)
		{
		return this.musicalGenreRepository//
				.findByGenreId(id)//
				.orElseGet(() -> null);
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