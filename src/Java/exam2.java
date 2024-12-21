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

/**
 * 2024-12-19일에 생성
 * 학생 정보를 관리하는 클래스입니다.
 */
class Student {
    String studentId; // 학생 학번
    String name;      // 학생 이름
    double grade;     // 학생 성적

    /**
     * 2024-12-19일에 생성
     * Student 클래스의 생성자입니다.
     * 
     * @param studentId 학생 학번
     * @param name 학생 이름
     * @param grade 학생 성적
     */
    public Student(String studentId, String name, double grade) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
    }
}

/**
 * 2024-12-19일에 생성
 * 학생 정보를 입력받아 정렬하고 파일로 저장하는 메인 클래스입니다.
 */
public class exam2 {
    /**
     * 2024-12-19일에 생성
     * 프로그램의 진입점입니다.
     * 
     * @param args 명령행 인수
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        HashMap<String, Student> studentMap = new HashMap<>();

        // 학생 수 입력
        System.out.print("학생 수를 입력하세요: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); 

        // 학생 정보 입력
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

        // 성적에 따라 학생 목록 정렬
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.grade, s1.grade); 
            }
        });

        // 파일 경로 설정
        String userHome = System.getProperty("user.home");
        String filePath = userHome + "/Desktop/students.csv"; 

        // 학생 정보를 파일에 저장
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

        // 특정 학번 검색
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
