package esfe.com.Asistencia.Servicios.Implementacion;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import esfe.com.Asistencia.Modelos.Rol;
import esfe.com.Asistencia.Repositorios.IRolRepository;
import esfe.com.Asistencia.Servicios.Interfaces.IRolService;

@Service
public class RolService implements IRolService {
    @Autowired
    private IRolRepository rolRepository;

    @Override
    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }
}

