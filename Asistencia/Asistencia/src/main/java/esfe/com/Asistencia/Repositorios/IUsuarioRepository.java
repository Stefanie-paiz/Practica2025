package esfe.com.Asistencia.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import esfe.com.Asistencia.Modelos.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
}
