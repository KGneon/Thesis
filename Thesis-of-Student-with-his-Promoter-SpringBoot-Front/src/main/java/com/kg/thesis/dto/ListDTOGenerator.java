package com.kg.thesis.dto;

import com.kg.thesis.entity.Promoter;
import com.kg.thesis.entity.Student;
import com.kg.thesis.entity.Thesis;
import com.kg.thesis.exception.ThesisException;

import java.util.List;
import java.util.stream.Collectors;

public class ListDTOGenerator {
    public static List<StudentDTO> getVerifiedStudentsDTOList(List<Student> listOfStudents, String exceptionMessage) {
        if (listOfStudents != null && !listOfStudents.isEmpty()) {
            return listOfStudents.stream().map(StudentDTO::createDTO).collect(Collectors.toList());
        } else {
            throw new ThesisException(exceptionMessage);
        }
    }
    public static List<PromoterDTO> getVerifiedPromotersDTOList(List<Promoter> listOfPromoter, String exceptionMessage) {
        if (listOfPromoter != null && !listOfPromoter.isEmpty()) {
            return listOfPromoter.stream().map(PromoterDTO::createDTO).collect(Collectors.toList());
        } else {
            throw new ThesisException(exceptionMessage);
        }
    }
    public static List<ThesisDTO> getVerifiedThesesDTOList(List<Thesis> listOfThesis, String exceptionMessage) {
        if (listOfThesis != null && !listOfThesis.isEmpty()) {
            return listOfThesis.stream().map(ThesisDTO::createDTO).collect(Collectors.toList());
        } else {
            throw new ThesisException(exceptionMessage);
        }
    }
}
