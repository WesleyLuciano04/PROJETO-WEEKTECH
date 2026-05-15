package com.faculdade.techweek.web.Palestrante.dtos;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PalestranteDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 120, message = "Nome deve ter entre 3 e 120 caracteres")
    private String nome;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "Use o formato (43) 99999-9999")
    private String telefone;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Size(max = 150)
    private String email;

    @NotBlank(message = "Tema da palestra é obrigatório")
    @Size(min = 3, max = 200)
    private String temaPalestra;

    @NotBlank(message = "Briefing é obrigatório")
    @Size(max = 2000)
    private String briefing;

    @NotBlank(message = "Mini currículo é obrigatório")
    @Size(max = 1500)
    private String curriculoResumo;

    private MultipartFile curriculoArquivo;

    @AssertTrue(message = "Você deve autorizar a divulgação para submeter a proposta")
    private boolean autorizaDivulgacao;
}