package org.example.studentmanagementsecure.controller;

import jakarta.validation.Valid;
import org.example.studentmanagementsecure.dto.CreateStudentRequestDTO;
import org.example.studentmanagementsecure.dto.CreateStudentResponseDTO;
import org.example.studentmanagementsecure.dto.UpdateStudentRequestDTO;
import org.example.studentmanagementsecure.dto.UpdateStudentResponseDTO;
import org.example.studentmanagementsecure.entity.Student;
import org.example.studentmanagementsecure.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }
    @PostMapping
    public ResponseEntity<CreateStudentResponseDTO> createStudent(@Valid  @RequestBody CreateStudentRequestDTO createStudentRequestDTO){
        CreateStudentResponseDTO createdStudent=studentService.createStudent(createStudentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDTO> getStudentById(@PathVariable Long id){
        CreateStudentResponseDTO returnedStudent=studentService.getStudentById(id);
        return ResponseEntity.ok(returnedStudent);
    }
    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDTO>> getAllStudents(){
        List<CreateStudentResponseDTO> studentsList=studentService.getAllStudents();
        return ResponseEntity.ok(studentsList);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UpdateStudentResponseDTO> updateStudent(@Valid @RequestBody UpdateStudentRequestDTO updateStudentRequestDTO, @PathVariable Long id){
        UpdateStudentResponseDTO updatedStudent=studentService.updateStudentById(updateStudentRequestDTO,id);
        return ResponseEntity.ok(updatedStudent);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<String> softDelete(@PathVariable Long id){
        studentService.softDeleteStudentById(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
    @GetMapping("/department/{department}")
    public ResponseEntity<List<CreateStudentResponseDTO>> getStudentsByDepartment(@PathVariable String department){
        List<CreateStudentResponseDTO> studentsList=studentService.getAllByDepartment(department);
        return ResponseEntity.ok(studentsList);
    }
}
