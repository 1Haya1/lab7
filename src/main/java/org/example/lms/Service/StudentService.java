package org.example.lms.Service;

import org.example.lms.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final List<Student> students = new ArrayList<>();
    private Integer nextId = 1;

    public List<Student> getAllStudents() {
        return students;
    }

    public Student getStudentById(Integer id) {
        for (Student student : students) {
            if (student.getId()==(id))
            {
                return student;
            }
        }
        return null;
    }

    public Student addStudent(Student student) {
        student.setId(nextId++);
        students.add(student);
        return student;
    }

    public Student updateStudent(Integer id, Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                updatedStudent.setId(id);
                students.set(i, updatedStudent);
                return updatedStudent;
            }
        }
        return null;
    }

    public boolean deleteStudent(Integer id) {
        return students.removeIf(student -> student.getId().equals(id));
    }

    
    public List<Student> getStudentsByAgeRange(int minAge, int maxAge) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getAge() >= minAge && student.getAge() <= maxAge) {
                filteredStudents.add(student);
            }
        }
        return filteredStudents;
    }

    public List<Student> getStudentsWithHighestScore() {
        double maxScore = 0;
        List<Student> highestScoringStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getScore() > maxScore) {
                maxScore = student.getScore();
                highestScoringStudents.clear();
                highestScoringStudents.add(student);
            } else if (student.getScore() == maxScore) {
                highestScoringStudents.add(student);
            }
        }
        return highestScoringStudents;
    }
}


