/**
 * 
 */
package ecotech.tcc.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ecotech.tcc.demo.model.Cliente;


/**
 * @author rm86722
 *
 */

@Repository
public interface ClienteReopository extends JpaRepository<Cliente, Long>{
	
	@Query(value="SELECT * from cliente c where c.email=?", nativeQuery = true)
	Cliente findByEmail (String email);
	void deleteById(Long id);
}
 