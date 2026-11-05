package org.example.studentmanagementsecure.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateStudentRequestDTO {
    @NotBlank(message = "Your name column is empty")
    private String name;
    @Min(value = 18)
    @Max(60)
    private int age;
    @NotBlank(message = "Your email id column is empty")
    private String email;
    @NotBlank(message = "Your department column is empty")
    private String department;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
