package models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StudentStore {
    private Map<Integer, Student> students = new HashMap<>();

    private static StudentStore single_instance = null;

    public Student addStudent(Student student) {
        int id = students.size();
        student.setId(id);
        students.put(id, student);
        return student;
    }

    public Student getStudent(int id) {
        return students.get(id);
    }

    public Set<Student> getAllStudents() {
        return new HashSet<>(students.values());
    }

    public Student updateStudent(Student student) {
        int id = student.getId();
        if (students.containsKey(id)) {
            students.put(id, student);
            return student;
        }
        return null;
    }

    public boolean deleteStudent(int id) {
        return students.remove(id) != null;
    }

    // static method to create instance of Singleton class
    public static StudentStore getInstance()
    {
        if (single_instance == null)
            single_instance = new StudentStore();

        return single_instance;
    }
}
