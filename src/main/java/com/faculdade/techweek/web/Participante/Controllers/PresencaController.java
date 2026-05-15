package com.faculdade.techweek.web.Participante.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.faculdade.techweek.core.service.PresencaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/presenca")
@RequiredArgsConstructor
public class PresencaController {

    private final PresencaService presencaService;

    @GetMapping
    public ModelAndView exibirFormulario() {
        return new ModelAndView("presenca");
    }

    @PostMapping
    public ModelAndView registrar(@RequestParam("ra") String ra) {
        ModelAndView mav = new ModelAndView("presenca");

        try {
            String mensagem = presencaService.registrarPresenca(ra);
            mav.addObject("sucesso", mensagem);

        } catch (IllegalArgumentException ex) {
            mav.addObject("erro", ex.getMessage());
        }

        return mav;
    }
}