package com.faculdade.techweek.web.Palestrante.Controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faculdade.techweek.core.service.PalestranteService;
import com.faculdade.techweek.web.Palestrante.dtos.PalestranteDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/inscricao-palestrante")
@RequiredArgsConstructor
@Slf4j
public class PalestranteController {

    private final PalestranteService palestranteService;

    @GetMapping
    public ModelAndView exibirFormulario() {
        ModelAndView mav = new ModelAndView("inscricao-palestrante");
        mav.addObject("palestranteDTO", new PalestranteDTO());
        return mav;
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(
            @Valid @ModelAttribute("palestranteDTO") PalestranteDTO dto,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("inscricao-palestrante");
        }

        try {
            palestranteService.submeterProposta(dto);
            
            redirectAttributes.addFlashAttribute("sucesso",
                    "Proposta enviada! Retornaremos em até 7 dias úteis pelo e-mail informado.");
            return new ModelAndView("redirect:/inscricao-palestrante");

        } catch (IllegalArgumentException ex) {

            ModelAndView mav = new ModelAndView("inscricao-palestrante");
            mav.addObject("erro", ex.getMessage());
            return mav;

        } catch (IOException ex) {

            log.error("Erro ao salvar currículo: {}", ex.getMessage());

            ModelAndView mav = new ModelAndView("inscricao-palestrante");
            mav.addObject("erro", "Erro ao salvar o arquivo. Tente novamente ou envie sem o arquivo.");
            return mav;
        }
    }
}