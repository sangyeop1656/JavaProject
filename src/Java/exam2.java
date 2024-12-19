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

     
        System.out.print("�л� ���� �Է��ϼ���: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); 

     
        for (int i = 0; i < numStudents; i++) {
            System.out.print("�й��� �Է��ϼ���: ");
            String studentId = scanner.nextLine();
            System.out.print("�̸��� �Է��ϼ���: ");
            String name = scanner.nextLine();
            System.out.print("������ �Է��ϼ���: ");
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
            writer.write("����,�й�,�̸�,����\n");
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                writer.write((i + 1) + "," + student.studentId + "," + student.name + "," + student.grade + "\n");
            }
            System.out.println("�л� ������ ����ȭ�鿡 ����Ǿ����ϴ�: " + filePath);
        } catch (IOException e) {
            System.out.println("���� ���� �� ������ �߻��߽��ϴ�.");
        }

       
        System.out.print("Ȯ���ϰ� ���� �й��� �Է��ϼ���: ");
        String searchId = scanner.nextLine();
        if (studentMap.containsKey(searchId)) {
            Student student = studentMap.get(searchId);
            System.out.println("�л� ����: �й�=" + student.studentId + ", �̸�=" + student.name + ", ����=" + student.grade);
        } else {
            System.out.println("�ش� �й��� �л��� �������� �ʽ��ϴ�.");
        }

        scanner.close();
    }
}
