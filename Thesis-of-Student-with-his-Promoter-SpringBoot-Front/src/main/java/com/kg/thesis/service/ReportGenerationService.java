package com.kg.thesis.service;

import com.kg.thesis.dto.ThesisDTO;
import com.kg.thesis.exception.ThesisException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportGenerationService {
    private final ThesisService thesisService;

    public void generateReport() throws ThesisException {
        List<ThesisDTO> theses = thesisService.getTheses();
    }


}
