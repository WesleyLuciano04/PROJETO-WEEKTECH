package com.faculdade.techweek.web.Participante.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.faculdade.techweek.core.enums.Curso;
import com.faculdade.techweek.core.service.ParticipanteService;
import com.faculdade.techweek.web.Participante.dtos.ParticipanteDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/inscricao-participante")
@RequiredArgsConstructor
public class ParticipanteController {

    private final ParticipanteService participanteService;

    @GetMapping
    public ModelAndView exibirFormulario() {
        ModelAndView mav = new ModelAndView("inscricao-participante");
        mav.addObject("participanteDTO", new ParticipanteDTO());
        mav.addObject("cursos", Curso.values());


        return mav;
    }

    @PostMapping()
    public ModelAndView salvar(
            @ModelAttribute("participanteDTO") @Valid ParticipanteDTO dto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) { 
            return new ModelAndView("inscricao-participante"); 
        }

        ModelAndView mav = new ModelAndView("inscricao-participante");
        try {
            participanteService.inscrever(dto);
            mav.addObject("participanteDTO", new ParticipanteDTO());
            mav.addObject("sucesso", "Inscrição realizada com sucesso!");
        } catch (IllegalArgumentException | IllegalStateException e) {
            mav.addObject("participanteDTO", dto);
            mav.addObject("erro", e.getMessage());
        }
        return mav;
    }

    
    @GetMapping("/presenca")
    public ModelAndView presentica() {
        return new ModelAndView("presenca");
    }

    @PostMapping("/registrar")
    public ModelAndView registrarPresenca(@RequestParam("ra") String ra) {
        ModelAndView mav =  new ModelAndView("presenca");

        try {
            String mensagem = participanteService.registrarPresenca(ra);
            mav.addObject("sucesso", mensagem);
        } catch (IllegalArgumentException | IllegalStateException e) {
            mav.addObject("erro", e.getMessage());
        }

        return mav;
    }


}