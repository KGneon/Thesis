package com.kg.thesis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.kg.thesis.dto.ListDTOGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kg.thesis.dto.PromoterDTO;
import com.kg.thesis.dto.StudentDTO;
import com.kg.thesis.dto.ThesisDTO;
import com.kg.thesis.entity.Promoter;
import com.kg.thesis.entity.Student;
import com.kg.thesis.entity.Thesis;
import com.kg.thesis.exception.ThesisException;
import com.kg.thesis.repository.PromoterRepository;
import com.kg.thesis.repository.StudentRepository;
import com.kg.thesis.repository.ThesisRepository;

@Service
@Transactional
@PropertySource("classpath:application.properties")
public class ThesisServiceImpl implements ThesisService {
    private final StudentRepository studentRepository;
    private final PromoterRepository promoterRepository;
    private final ThesisRepository thesisRepository;

    @Autowired
    public ThesisServiceImpl(StudentRepository studentRepository, PromoterRepository promoterRepository, ThesisRepository thesisRepository) {
        this.studentRepository = studentRepository;
        this.promoterRepository = promoterRepository;
        this.thesisRepository = thesisRepository;
    }
    //DO THE NUMBER AND NUMBER + LIST AND LIST VALIDATOR?

    //GET ALL
    @Override
    public List<StudentDTO> getStudents(){
        List<Student> studentList = studentRepository.findAll();
        return ListDTOGenerator.getVerifiedStudentsDTOList(studentList, "Service.EMPTY_STUDENTS_LIST");
    }

    @Override
    public List<PromoterDTO> getPromoters(){
        List<Promoter> promoterList = promoterRepository.findAll();
        return ListDTOGenerator.getVerifiedPromotersDTOList(promoterList, "Service.Service.NO_PROMOTER_AVAILABLE");
    }

    @Override
    public List<ThesisDTO> getTheses(){
        List<Thesis> thesisList = thesisRepository.findAll();
        return ListDTOGenerator.getVerifiedThesesDTOList(thesisList, "Service.Service.NO_THESIS");
    }

    //POST (ADD)
    @Override
    //UNFINISHED validation of lack of promoter!!!
    public void addStudent(StudentDTO studentDTO){
        if (studentDTO != null) {
            Student student = StudentDTO.createEntity(studentDTO);
            if (studentDTO.getPromoter() != null) {
                Optional<Promoter> optionalPromoter = promoterRepository.findById(student.getPromoter().getPromoterId());
                Promoter promoter = optionalPromoter.orElseThrow(() -> new ThesisException("Service.NO_PROMOTER_BY_ID"));
                promoter.setNumberOfStudentsLead(promoter.getNumberOfStudentsLead() + 1);
                promoterRepository.save(promoter);
            }
            studentRepository.save(student);
        } else {
            throw new ThesisException("Service.NO_DATA");
        }
    }

    @Override
    //UNFINISHED!!!
    public void addPromoter(PromoterDTO promoterDTO){
        if (promoterDTO != null) {
            Promoter promoter = PromoterDTO.createEntity(promoterDTO);
            promoterRepository.save(promoter);
        } else {
            throw new ThesisException("Service.NO_DATA");
        }
    }

    @Override
    public void addThesis(ThesisDTO thesisDTO){
        if (thesisDTO != null) {
            Thesis thesis = ThesisDTO.createEntity(thesisDTO);
            thesisRepository.save(thesis);
        } else {
            throw new ThesisException("Service.NO_DATA");
        }
    }

    //DELETE
    @Override
    public void deleteStudent(Integer studentId){
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Student student = optionalStudent.orElseThrow(() -> new ThesisException("Service.NO_STUDENT_BY_ID"));
        if (student.getPromoter() != null) {
            Optional<Promoter> optionalPromoter = promoterRepository.findById(student.getPromoter().getPromoterId());
            Promoter promoter = optionalPromoter.orElseThrow(() -> new ThesisException("Service.NO_PROMOTER_BY_ID"));
            promoter.setNumberOfStudentsLead(promoter.getNumberOfStudentsLead() - 1);
            promoterRepository.save(promoter);
        }
        studentRepository.delete(student);
    }

    @Override
    public void deletePromoter(Integer promoterId){
        Optional<Promoter> optionalPromoter = promoterRepository.findById(promoterId);
        Promoter promoter = optionalPromoter.orElseThrow(() -> new ThesisException("Service.NO_PROMOTER_BY_ID"));
        List<Student> studentList = studentRepository.findByPromoter(promoter);
        studentList.forEach(s -> {
            s.setPromoter(null);
            studentRepository.save(s);
        });
        promoterRepository.delete(promoter);
    }

    @Override
    public void deleteThesis(Integer thesisId){
        Optional<Thesis> optionalThesis = thesisRepository.findById(thesisId);
        Thesis thesis = optionalThesis.orElseThrow(() -> new ThesisException("Service.NO_THESIS_BY_ID"));
        List<Student> studentList = studentRepository.findByThesis(thesis);
        studentList.forEach(s -> {
            s.setThesis(null);
            studentRepository.save(s);
        });
        thesisRepository.delete(thesis);
    }

    //GET BY ID
    @Override
    public StudentDTO getStudentById(Integer studentId){
        //TO VALIDATOR
        if (studentId < 0) throw new ThesisException("Service.NUMBER_NEGATIVE");
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Student student = optionalStudent.orElseThrow(() -> new ThesisException("Service.NO_STUDENT_BY_ID"));
        StudentDTO studentDTO = StudentDTO.createDTO(student);
        return studentDTO;
    }

    @Override
    public PromoterDTO getPromoterById(Integer promoterId){
        //TO VALIDATOR
        if (promoterId < 0) throw new ThesisException("Service.NUMBER_NEGATIVE");
        Optional<Promoter> optionalStudent = promoterRepository.findById(promoterId);
        Promoter promoter = optionalStudent.orElseThrow(() -> new ThesisException("Service.NO_STUDENT_BY_ID"));
        PromoterDTO promoterDTO = PromoterDTO.createDTO(promoter);
        return promoterDTO;
    }

    @Override
    public ThesisDTO getThesisById(Integer thesisId){
        //TO VALIDATOR
        if (thesisId < 0) throw new ThesisException("Service.NUMBER_NEGATIVE");
        Optional<Thesis> optionalStudent = thesisRepository.findById(thesisId);
        Thesis thesis = optionalStudent.orElseThrow(() -> new ThesisException("Service.NO_STUDENT_BY_ID"));
        ThesisDTO thesisDTO = ThesisDTO.createDTO(thesis);
        return thesisDTO;
    }

    //ALLOCATION AND ASSIGNEMENT
    @Override
    public void allocatePromoterToStudent(Integer studentId, Integer promoterId){
        // TODO Auto-generated method stub
    }

    @Override
    public void assignThesisToStudent(Integer studentId, Integer thesisId){
        // TODO Auto-generated method stub
    }

    //SPECIFIC METHODS
    //???FRONTEND OR BACKEND???
    @Override
    public List<StudentDTO> getStudentsWithDoubtfulThesis(Boolean notMatchedOrBlank){
        List<Student> studentList = studentRepository.findAll();
        if (!studentList.isEmpty()) {
            List<StudentDTO> studentDTOList = new ArrayList<>();
            studentList.forEach(s -> {
                if (notMatchedOrBlank == true && s.getThesis() != null && !s.getPromoter().getField().equals(s.getThesis().getThesisField())) {
                    StudentDTO studentDTO = StudentDTO.createDTO(s);
                    studentDTOList.add(studentDTO);
                }
                if (notMatchedOrBlank == false && s.getThesis() == null) {
                    StudentDTO studentDTO = StudentDTO.createDTO(s);
                    studentDTOList.add(studentDTO);
                }
            });
            if (studentDTOList.isEmpty()) throw new ThesisException("Service.NO_DOUBTFUL_THESIS");
            else return studentDTOList;

        } else {
            throw new ThesisException("Service.EMPTY_STUDENTS_LIST");
        }
    }

    @Override
    public List<PromoterDTO> getPromotersWithPossibleStudentAllocation(){
        List<Promoter> promoterList = promoterRepository.findAll();
        List<PromoterDTO> promoterDTOList = new ArrayList<>();
        promoterList.forEach(p -> {
            if (p.getNumberOfStudentsLead() < 5) {
                PromoterDTO promoterDTO = PromoterDTO.createDTO(p);
                promoterDTOList.add(promoterDTO);
            }
        });
        return promoterDTOList;
    }

    @Override
    public List<PromoterDTO> getPromotersByStudentsLead(Integer studentsLead){
        //TO VALIDATOR
        if (studentsLead < 0) throw new ThesisException("Service.NUMBER_NEGATIVE");
        List<Promoter> promoterList = promoterRepository.findByNumberOfStudentsLead(studentsLead);
        if (promoterList.isEmpty()) throw new ThesisException("Service.NO_PROMOTERS_WITH_THAT_LEAD");
        else {
            List<PromoterDTO> promoterDTOList = new ArrayList<>();
            promoterList.forEach(p -> {
                PromoterDTO promoterDTO = PromoterDTO.createDTO(p);
                promoterDTOList.add(promoterDTO);
            });
            return promoterDTOList;
        }
    }

    @Override
    public List<PromoterDTO> getPromotersBySpecialization(String field){
        List<Promoter> promoterList = promoterRepository.findByField(field);
        if (promoterList.isEmpty()) throw new ThesisException("Service.NO_PROMOTERS_WITH_THAT_SPECIALIZATION");
        else {
            List<PromoterDTO> promoterDTOList = new ArrayList<>();
            promoterList.forEach(p -> {
                PromoterDTO promoterDTO = PromoterDTO.createDTO(p);
                promoterDTOList.add(promoterDTO);
            });
            return promoterDTOList;
        }
    }
}
