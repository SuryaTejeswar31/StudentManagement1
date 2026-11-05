package org.example.studentmanagementsecure.service;

import org.example.studentmanagementsecure.dto.CreateStudentRequestDTO;
import org.example.studentmanagementsecure.dto.CreateStudentResponseDTO;
import org.example.studentmanagementsecure.dto.UpdateStudentRequestDTO;
import org.example.studentmanagementsecure.dto.UpdateStudentResponseDTO;
import org.example.studentmanagementsecure.entity.Student;
import org.example.studentmanagementsecure.exceptions.DuplicateEmailException;
import org.example.studentmanagementsecure.exceptions.ResourceNotFoundException;
import org.example.studentmanagementsecure.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StudentService {
    StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO createStudentRequestDTO){
        Student student=mapToCreateEntity(createStudentRequestDTO);
        if(emailExist(student)){
            throw new DuplicateEmailException("Student with email "+student.getEmail()+" already exist");
        }
        studentRepository.save(student);
        return mapToCreateDTO(student);
    }
    public CreateStudentResponseDTO getStudentById(Long id){
        Student student=studentRepository.findByIdAndDeletedFalse(id).orElseThrow(()->new ResourceNotFoundException("Student with id "+id+" not found"));
        return mapToCreateDTO(student);
    }
    public List<CreateStudentResponseDTO> getAllStudents(){
        List<Student> studentsList=studentRepository.findAllByDeletedFalse();
        return mapToCreateListDTO(studentsList);
    }
    public UpdateStudentResponseDTO updateStudentById(UpdateStudentRequestDTO updateStudentRequestDTO,Long id){
        Student existingStudent=studentRepository.findByIdAndDeletedFalse(id).orElseThrow(()->new ResourceNotFoundException("Student with id "+id+" not found"));
        existingStudent.setName(updateStudentRequestDTO.getName());
        existingStudent.setEmail(updateStudentRequestDTO.getEmail());
        existingStudent.setAge(updateStudentRequestDTO.getAge());
        existingStudent.setDepartment(updateStudentRequestDTO.getDepartment());
        if(emailExist(existingStudent)){
            throw new DuplicateEmailException("Student with email "+existingStudent.getEmail()+" already exist");
        }
        studentRepository.save(existingStudent);
        return mapToUpdateDTO(existingStudent);
    }
    public void softDeleteStudentById(Long id){
        Student student=studentRepository.findByIdAndDeletedFalse(id).orElseThrow(()->new ResourceNotFoundException("Student with id "+id+" is already deleted"));
        student.setDeleted(true);
        studentRepository.save(student);
    }
    public List<CreateStudentResponseDTO> getAllByDepartment(String department){
        List<Student> studentList=studentRepository.findAllByDepartmentAndDeletedFalse(department);
        return mapToCreateListDTO(studentList);
    }
    private Student mapToCreateEntity(CreateStudentRequestDTO createStudentRequestDTO){
        Student student=new Student();
        student.setName(createStudentRequestDTO.getName());
        student.setEmail(createStudentRequestDTO.getEmail());
        student.setAge(createStudentRequestDTO.getAge());
        student.setDepartment(createStudentRequestDTO.getDepartment());
        return student;
    }
    private CreateStudentResponseDTO mapToCreateDTO(Student student){
        CreateStudentResponseDTO createStudentResponseDTO=new CreateStudentResponseDTO();
        createStudentResponseDTO.setId(student.getId());
        createStudentResponseDTO.setName(student.getName());
        createStudentResponseDTO.setEmail(student.getEmail());
        createStudentResponseDTO.setAge(student.getAge());
        createStudentResponseDTO.setDepartment(student.getDepartment());
        createStudentResponseDTO.setMessage("New Student created successfully");
        return createStudentResponseDTO;
    }
    private List<CreateStudentResponseDTO> mapToCreateListDTO(List<Student> studentsList){
        List<CreateStudentResponseDTO> responseList=new ArrayList<>();
        for(Student student:studentsList){
            responseList.add(mapToCreateDTO(student));
        }
        return responseList;
    }
    private Student mapToUpdateEntity(UpdateStudentRequestDTO updateStudentRequestDTO){
        Student student=new Student();
        student.setName(updateStudentRequestDTO.getName());
        student.setEmail(updateStudentRequestDTO.getEmail());
        student.setAge(updateStudentRequestDTO.getAge());
        student.setDepartment(updateStudentRequestDTO.getDepartment());
        return student;
    }
    private UpdateStudentResponseDTO mapToUpdateDTO(Student student){
        UpdateStudentResponseDTO updateStudentResponseDTO=new UpdateStudentResponseDTO();
        updateStudentResponseDTO.setId(student.getId());
        updateStudentResponseDTO.setName(student.getName());
        updateStudentResponseDTO.setEmail(student.getEmail());
        updateStudentResponseDTO.setAge(student.getAge());
        updateStudentResponseDTO.setDepartment(student.getDepartment());
        updateStudentResponseDTO.setMessage("Student record updated successfully");
        return updateStudentResponseDTO;
    }
    private boolean emailExist(Student student){
        Student presentStudent=studentRepository.findByEmail(student.getEmail());
        if(presentStudent!=null){
            if(!(presentStudent.getId()).equals(student.getId()))return true;
        }
        return false;
    }
}
