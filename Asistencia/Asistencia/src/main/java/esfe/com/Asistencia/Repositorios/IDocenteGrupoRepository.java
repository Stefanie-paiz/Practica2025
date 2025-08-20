package esfe.com.Asistencia.Repositorios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import esfe.com.Asistencia.Modelos.DocenteGrupo;


public interface IDocenteGrupoRepository extends JpaRepository<DocenteGrupo,Integer> {

    Page<DocenteGrupo> findByOrderByDocenteDesc(Pageable pageable);

}
