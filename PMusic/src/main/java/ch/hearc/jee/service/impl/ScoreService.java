
package ch.hearc.jee.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.hearc.jee.model.Score;
import ch.hearc.jee.repository.IScoreRepository;
import ch.hearc.jee.service.IDatabaseService;

@Service
public class ScoreService implements IDatabaseService<Score>
	{
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public void add(Score item)
		{
		this.scoreRepository.save(item);
		}

	@Override
	public void delete(Score item)
		{
		deleteById(item.getId());
		}

	@Override
	public void deleteById(Long id)
		{
		this.scoreRepository.deleteById(Long.valueOf(id));
		}

	@Override
	public void update(Score item)
		{
		this.scoreRepository.save(item);
		}

	@Override
	public List<Score> getAll()
		{
		List<Score> scores = new ArrayList<Score>();

		this.scoreRepository//
				.findAll()//
				.forEach(scores::add);

		return scores;
		}

	@Override
	public Score getById(Long id)
		{
		return this.scoreRepository//
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
	private IScoreRepository scoreRepository;
	}
