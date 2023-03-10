
package ch.hearc.jee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import ch.hearc.jee.model.Music;
import ch.hearc.jee.model.User;

public interface IMusicRepository extends CrudRepository<Music, Long> ,PagingAndSortingRepository<Music, Long>
	{

	public Page<Music> findAllByUser(User user, Pageable pageable);
	}
