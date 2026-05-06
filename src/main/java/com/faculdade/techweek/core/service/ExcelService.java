package com.faculdade.techweek.core.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faculdade.techweek.core.model.Participante;
import com.faculdade.techweek.core.repository.ParticipanteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final ParticipanteRepository participanteRepository;

    @Transactional(readOnly = true)
    public byte[] gerarPlanilhaParticipantes() throws IOException {

        List<Participante> participantes = participanteRepository.findAll();

        try (XSSFWorkbook workbook = new XSSFWorkbook();
                ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Participantes");

            Row cabecalho = sheet.createRow(0);
            cabecalho.createCell(0).setCellValue("Nome");
            cabecalho.createCell(1).setCellValue("RA");
            cabecalho.createCell(2).setCellValue("Curso");
            cabecalho.createCell(3).setCellValue("Série");
            cabecalho.createCell(4).setCellValue("Coffee Break");
            cabecalho.createCell(5).setCellValue("Presença na 1º palestra");

            for (int i = 0; i < participantes.size(); i++) {
                Participante p = participantes.get(i);
                Row row = sheet.createRow(i + 1);

                row.createCell(0).setCellValue(p.getNome());
                row.createCell(1).setCellValue(p.getRa());
                row.createCell(2).setCellValue(p.getCurso().getDescricao());
                row.createCell(3).setCellValue(p.getSerie() + "º Semestre");
                row.createCell(4).setCellValue(p.isCoffeeBreak() ? "Sim" : "Não");
                row.createCell(5).setCellValue(p.isPresenca() ? "Sim" : "Não");
            }

            workbook.write(out);
            return out.toByteArray();
        }
    }
}