/**
 * 
 */
package ecotech.tcc.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ecotech.tcc.demo.model.Local;
import ecotech.tcc.demo.model.Usuario;


/**
 * @author rm86722
 *
 */

@Repository
public interface LocalReopository extends JpaRepository<Local, Long>{
	
	@Query(value="SELECT * from cliente c where c.email=?", nativeQuery = true)
	Usuario findByEmail (String email);
	void deleteById(Long id);
}
 