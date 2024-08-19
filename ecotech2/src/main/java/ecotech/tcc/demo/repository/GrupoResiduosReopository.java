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
import ecotech.tcc.demo.model.GrupoResiduos;


/**
 * @author rm86722
 *
 */

@Repository
public interface GrupoResiduosReopository extends JpaRepository<GrupoResiduos, Long>{
	

	
	@Query(value = "SELECT * From GrupoResiduo", nativeQuery = true)
	List<GrupoResiduos> findAllResidGroup();
	

}
 