/**
 * 
 */
package ecotech.tcc.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ecotech.tcc.demo.model.Local;
import ecotech.tcc.demo.model.Usuario;


/**
 * @author rm86722
 *
 */

@Repository
public interface LocalReopository extends JpaRepository<Local, Long>{
	
	@Query(value="SELECT * from EcoPonto c where c.email=?", nativeQuery = true)
	Local findByEmail (String email);
	//void deleteById(Long id);
	
	@Query(value = "SELECT * FROM EcoPonto c WHERE :status IS NULL OR c.statusPonto = :status", nativeQuery = true)
	List<Local> findByStatus(Boolean status);

	
}
 