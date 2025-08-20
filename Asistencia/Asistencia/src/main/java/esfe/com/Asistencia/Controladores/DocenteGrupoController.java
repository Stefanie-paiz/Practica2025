package esfe.com.Asistencia.Controladores;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import esfe.com.Asistencia.Modelos.DocenteGrupo;
import esfe.com.Asistencia.Servicios.Interfaces.IDocenteGrupoService;
import esfe.com.Asistencia.Servicios.Interfaces.IDocenteService;
import esfe.com.Asistencia.Servicios.Interfaces.IGrupoService;

@Controller
@RequestMapping("/asignaciones")
public class DocenteGrupoController {

    @Autowired
    private IDocenteGrupoService docenteGrupoService;

    @Autowired
    private IGrupoService grupoService;

    @Autowired
    private IDocenteService docenteService;

    // ----------- LISTADO --------------
    @GetMapping
    public String index(Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<DocenteGrupo> asignaciones = docenteGrupoService.buscarTodosPaginados(pageable);
        model.addAttribute("asignaciones", asignaciones);

        int totalPages = asignaciones.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "asignacion/index";
    }

    // ----------- CREAR --------------
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("docenteGrupo", new DocenteGrupo());
        model.addAttribute("docentes", docenteService.obtenerTodos());
        model.addAttribute("grupos", grupoService.ObtenerTodos());
        model.addAttribute("action", "create");
        return "asignacion/mant";
    }

    // ----------- EDITAR --------------
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        DocenteGrupo docenteGrupo = docenteGrupoService.buscarPorId(id);
        model.addAttribute("docenteGrupo", docenteGrupo);
        model.addAttribute("docentes", docenteService.obtenerTodos());
        model.addAttribute("grupos", grupoService.ObtenerTodos());
        model.addAttribute("action", "edit");
        return "asignacion/mant";
    }

    // ----------- VER (solo lectura) --------------
    @GetMapping("/view/{id}")
    public String view(@PathVariable Integer id, Model model) {
        DocenteGrupo docenteGrupo = docenteGrupoService.buscarPorId(id);
        model.addAttribute("docenteGrupo", docenteGrupo);
        model.addAttribute("docentes", docenteService.obtenerTodos());
        model.addAttribute("grupos", grupoService.ObtenerTodos());
        model.addAttribute("action", "view");
        return "asignacion/mant";
    }

    // ----------- ELIMINAR (confirmación) --------------
    @GetMapping("/delete/{id}")
    public String deleteConfirm(@PathVariable Integer id, Model model) {
        DocenteGrupo docenteGrupo = docenteGrupoService.buscarPorId(id);
        model.addAttribute("docenteGrupo", docenteGrupo);
        model.addAttribute("docentes", docenteService.obtenerTodos());
        model.addAttribute("grupos", grupoService.ObtenerTodos());
        model.addAttribute("action", "delete");
        return "asignacion/mant";
    }

    // ----------- PROCESAR POST según action --------------
    @PostMapping("/create")
    public String saveNuevo(@ModelAttribute DocenteGrupo docenteGrupo, BindingResult result,
                            RedirectAttributes redirect, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("docentes", docenteService.obtenerTodos());
            model.addAttribute("grupos", grupoService.ObtenerTodos());
            model.addAttribute("action", "create");
            return "asignacion/mant";
        }
        docenteGrupoService.crearOEditar(docenteGrupo);
        redirect.addFlashAttribute("msg", "Asignación creada correctamente");
        return "redirect:/asignaciones";
    }

    @PostMapping("/edit")
    public String saveEditado(@ModelAttribute DocenteGrupo docenteGrupo, BindingResult result,
                              RedirectAttributes redirect, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("docentes", docenteService.obtenerTodos());
            model.addAttribute("grupos", grupoService.ObtenerTodos());
            model.addAttribute("action", "edit");
            return "asignacion/mant";
        }
        docenteGrupoService.crearOEditar(docenteGrupo);
        redirect.addFlashAttribute("msg", "Asignación actualizada correctamente");
        return "redirect:/asignaciones";
    }

    @PostMapping("/delete")
    public String deleteDocenteGrupo(@ModelAttribute DocenteGrupo docenteGrupo, RedirectAttributes redirect) {
        docenteGrupoService.eliminarPorId(docenteGrupo.getId());
        redirect.addFlashAttribute("msg", "Asignación eliminada correctamente");
        return "redirect:/asignaciones";
    }
}
