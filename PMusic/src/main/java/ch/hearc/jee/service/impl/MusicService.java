
package ch.hearc.jee.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ch.hearc.jee.model.Music;
import ch.hearc.jee.model.User;
import ch.hearc.jee.repository.IMusicRepository;
import ch.hearc.jee.service.IDatabaseService;

@Service
public class MusicService implements IDatabaseService<Music>
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void add(Music item)
		{
		this.musicRepository.save(item);
		}

	@Override
	public void delete(Music item)
		{
		deleteById(item.getId());
		}

	@Override
	public void deleteById(Long id)
		{
		this.musicRepository.deleteById(Long.valueOf(id));
		}

	@Override
	public void update(Music item)
		{
		this.musicRepository.save(item);
		}

	@Override
	public List<Music> getAll()
		{
		List<Music> musics = new ArrayList<Music>();

		this.musicRepository//
				.findAll()//
				.forEach(musics::add);

		return musics;
		}

	public Page<Music> getAllByUser(User user, Pageable pageable)
		{
		return this.musicRepository.findAllByUser(user, pageable);
		}

	@Override
	public Music getById(Long id)
		{
		return this.musicRepository//
				.findById(Long.valueOf(id))//
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
	private IMusicRepository musicRepository;
	}
