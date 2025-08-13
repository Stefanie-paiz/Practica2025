package esfe.com.Asistencia.Modelos;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.JoinColumn;
import java.util.*;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer Id;
    @NotBlank(message = "El nombre es requerido")
    private String login;
    @NotBlank(message = "La clave es requerida")
    private String clave;

    private int status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private List<Rol> roles;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        login = login;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        clave = clave;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        status = status;
    }

    public List<Rol> getroles() {
        return roles;
    }

    public void setroles(List<Rol> roles) {
        roles = roles;
    }
    
    
}
