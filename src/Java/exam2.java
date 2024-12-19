package Java;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Student {
    String studentId;
    String name;
    double grade;

    public Student(String studentId, String name, double grade) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
    }
}

public class exam2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        HashMap<String, Student> studentMap = new HashMap<>();

     
        System.out.print("학생 수를 입력하세요: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); 

     
        for (int i = 0; i < numStudents; i++) {
            System.out.print("학번을 입력하세요: ");
            String studentId = scanner.nextLine();
            System.out.print("이름을 입력하세요: ");
            String name = scanner.nextLine();
            System.out.print("성적을 입력하세요: ");
            double grade = scanner.nextDouble();
            scanner.nextLine(); 

            Student student = new Student(studentId, name, grade);
            students.add(student);
            studentMap.put(studentId, student); 
        }

      
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.grade, s1.grade); 
            }
        });

       
        String userHome = System.getProperty("user.home");
        String filePath = userHome + "/Desktop/students.csv"; 
       

      
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("순위,학번,이름,성적\n");
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                writer.write((i + 1) + "," + student.studentId + "," + student.name + "," + student.grade + "\n");
            }
            System.out.println("학생 정보가 바탕화면에 저장되었습니다: " + filePath);
        } catch (IOException e) {
            System.out.println("파일 저장 중 오류가 발생했습니다.");
        }

       
        System.out.print("확인하고 싶은 학번을 입력하세요: ");
        String searchId = scanner.nextLine();
        if (studentMap.containsKey(searchId)) {
            Student student = studentMap.get(searchId);
            System.out.println("학생 정보: 학번=" + student.studentId + ", 이름=" + student.name + ", 성적=" + student.grade);
        } else {
            System.out.println("해당 학번의 학생이 존재하지 않습니다.");
        }

        scanner.close();
    }
}
