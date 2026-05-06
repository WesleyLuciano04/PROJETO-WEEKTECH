package com.faculdade.techweek.web.Admin.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.faculdade.techweek.core.enums.StatusInscricao;
import com.faculdade.techweek.core.service.AdminService;
import com.faculdade.techweek.core.service.PalestranteService;
import com.faculdade.techweek.core.service.ParticipanteService;
import com.faculdade.techweek.web.Admin.dto.AtualizarStatusDTO;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final ParticipanteService participanteService;
    private final PalestranteService palestranteService;

    @GetMapping({ "/", "/dashboard" })
    public ModelAndView dashboard() {
        ModelAndView mav = new ModelAndView("admin/dashboard");
        mav.addObject("dashboard", adminService.montarDashboard());
        return mav;
    }

    @GetMapping("/participantes")
    public ModelAndView listarParticipantes() {
        var lista = participanteService.listarTodos();
        ModelAndView mav = new ModelAndView("admin/participantes");
        mav.addObject("participantes", lista);
        return mav;
    }

    @GetMapping("/participantes/{id}")
    public ModelAndView detalheParticipante(@PathVariable Long id) {

        ModelAndView mav = new ModelAndView("admin/participante-detalhe");

        mav.addObject("participante", participanteService.buscarPorId(id));
        mav.addObject("statusOpcoes", StatusInscricao.values());
        mav.addObject("atualizarStatusDTO", new AtualizarStatusDTO());

        return mav;
    }

    @PostMapping("/participantes/{id}/status")
    public ModelAndView atualizarStatusParticipante(
            @PathVariable Long id,
            @ModelAttribute AtualizarStatusDTO dto) {

        participanteService.atualizarStatus(id, dto);
        return new ModelAndView("redirect:/admin/participantes/" + id);
    }

    @GetMapping("/palestrantes")
    public ModelAndView listarPalestrantes() {

        var lista = palestranteService.listarTodos();
        ModelAndView mav = new ModelAndView("admin/palestrantes");
        mav.addObject("palestrantes", lista);
        return mav;
    }

    @GetMapping("/palestrantes/{id}")
    public ModelAndView detalhePalestrante(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("admin/palestrante-detalhe");

        mav.addObject("palestrante", palestranteService.buscarPorId(id));
        mav.addObject("statusOpcoes", StatusInscricao.values());
        mav.addObject("atualizarStatusDTO", new AtualizarStatusDTO());

        return mav;
    }

    @PostMapping("/palestrantes/{id}/status")
    public ModelAndView atualizarStatusPalestrante(
            @PathVariable Long id,
            @ModelAttribute AtualizarStatusDTO dto) {

        palestranteService.atualizarStatus(id, dto);
        return new ModelAndView("redirect:/admin/palestrantes/" + id);
    }
}