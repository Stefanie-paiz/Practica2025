package esfe.com.Asistencia.Servicios.Implementacion;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import esfe.com.Asistencia.Modelos.Grupo;
import esfe.com.Asistencia.Repositorios.IGrupoRepository;
import esfe.com.Asistencia.Servicios.Interfaces.IGrupoService;

@Service
public class GrupoService implements IGrupoService {

    @Autowired
    private IGrupoRepository grupoRepository;
    
    @Override
    public Page<Grupo> buscarTodos(Pageable pageable){
        return grupoRepository.findAll(pageable);
    }

    @Override
    public   List<Grupo> obtenerTodos(){
       return  grupoRepository.findAll();
    }

    @Override
    public Optional<Grupo>  buscarPorId(Integer id){
        return grupoRepository.findById(id);
    }

    @Override
    public  Grupo crearOeditar(Grupo grupo){
        return grupoRepository.save(grupo);
    }
    
     @Override
    public  void eliminarPorId(Integer id){
        grupoRepository.deleteById(id);
    }

}
