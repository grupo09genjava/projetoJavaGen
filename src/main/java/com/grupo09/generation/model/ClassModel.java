package com.grupo09.generation.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_classes")
public class ClassModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String instructor;

    @OneToMany(mappedBy = "tbClass", cascade = CascadeType.ALL)
    private List<StudentModel> students = new ArrayList<>();

    public void addStudent(StudentModel student) {
        students.add(student);
        student.setTbClass(this);
    }

    public ClassModel(String name, String instructor) {
        this.name = name;
        this.instructor = instructor;
    }

    public static ClassModel toEntity(String name,String instructor,List<StudentModel> students) {
        return new ClassModel(name, instructor);
    }
}
