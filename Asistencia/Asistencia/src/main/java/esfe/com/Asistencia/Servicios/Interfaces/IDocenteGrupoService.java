package esfe.com.Asistencia.Servicios.Interfaces;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import esfe.com.Asistencia.Modelos.DocenteGrupo;

public interface IDocenteGrupoService {

    List<DocenteGrupo> obtenerTodos();

    Page<DocenteGrupo> buscarTodosPaginados(Pageable pageable);

    DocenteGrupo buscarPorId(Integer id);

    DocenteGrupo crearOEditar(DocenteGrupo docenteGrupo);

    void eliminarPorId(Integer id);
}
