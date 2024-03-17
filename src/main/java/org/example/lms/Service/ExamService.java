package org.example.lms.Service;


import org.example.lms.Model.Exam;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService {

    private final List<Exam> exams = new ArrayList<>();
    private Integer nextId = 1;
    public List<Exam> getAllExams() {
        return exams;
    }

    public Exam addExam(Exam exam) {
        exam.setId(nextId++);
        exams.add(exam);
        return exam;
    }
    public Exam getExamById(Integer id) {
        for (Exam exam : exams) {
            if (exam.getId()==(id)) {
                return exam;
            }
        }
        return null;
    }

    public List<Exam> getExamsBySubject(String subject) {
        List<Exam> examsBySubject = new ArrayList<>();
        for (Exam exam : exams) {
            if (exam.getSubject().equals(subject)) {
                examsBySubject.add(exam);
            }
        }
        return examsBySubject;
    }
    public boolean updateExam(Integer id, Exam exam) {
        for (int i = 0; i < exams.size(); i++) {
            if (exam.get(i).getId() == id) {
                exam.setId(id);
                exams.set(i, exam);
                return true;
            }
        }
        return false;
    }

    public boolean deleteExam(Integer id) {
        for (Exam exam : exams) {
            if (exam.getId() == id) {
                exams.remove(exam);
                return true;
            }
        }
        return false;
    }

    public Exam searchExam(String title) {
        for (Exam exam : exams) {
            if (exam.getTitle().equalsIgnoreCase(title)) {
                return exam;
            }
        }
        return null;
    }
 public Exam updateExamScore(Integer Id, Double score) {
        Exam examToUpdate = getExamById(Id);
        if (examToUpdate == null) {
            return null;
        }
        examToUpdate.setScore(score);
        return examToUpdate;
    }

}







