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
 * 2024-12-19�Ͽ� ���� �л� ������ �����ϴ� Ŭ�����Դϴ�.
 */
class Student {
	String studentId; // �л� �й�
	String name; // �л� �̸�
	double grade; // �л� ����
	String gradeLetter; // �л� ����

	/**
	 * 2024-12-19�Ͽ� ���� Student Ŭ������ �������Դϴ�.
	 * 
	 * @param studentId    �л� �й�
	 * @param name         �л� �̸�
	 * @param grade        �л� ����
	 * 
	 * 2024-12-22�Ͽ� ���� Student Ŭ������ �������Դϴ�.
	 * @param gradeLetter; �ʱⰪ ����
	 */
	public Student(String studentId, String name, double grade) {
		this.studentId = studentId;
		this.name = name;
		this.grade = grade;
		this.gradeLetter = ""; // �ʱⰪ ����
	}
}

/**
 * 2024-12-19�Ͽ� ���� �л� ������ �Է¹޾� �����ϰ� ���Ϸ� �����ϴ� ���� Ŭ�����Դϴ�.
 */
public class exam2 {
	/**
	 * 2024-12-19�Ͽ� ���� ���α׷��� �������Դϴ�.
	 * 
	 * @param args ����� �μ�
	 * 
	 * 2024-12-21�Ͽ� ���� �й� �ߺ� üũ�ϸ� ���α׷� ���� �ڵ� ���� ��ü �л� ���� �л����� ��� ���� ���
	 * ��� ����
	 * 
	 * 2024-12-22�Ͽ� ���� �ο� �ڵ� ����.
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Student> students = new ArrayList<>();
		HashMap<String, Student> studentMap = new HashMap<>();

		// �л� �� �Է�
		System.out.print("�л� ���� �Է��ϼ���: ");
		int numStudents = scanner.nextInt();
		scanner.nextLine();

		// �л� ���� �Է�
		for (int i = 0; i < numStudents; i++) {
			System.out.print("�й��� �Է��ϼ���: ");
			String studentId = scanner.nextLine();

			// �й� �ߺ� üũ
			if (studentMap.containsKey(studentId)) {
				System.out.println("�й��� �̹� �����մϴ�: " + studentId);
				System.exit(0); // ���α׷� ����
			}

			System.out.print("�̸��� �Է��ϼ���: ");
			String name = scanner.nextLine();
			System.out.print("������ �Է��ϼ���: ");
			double grade = scanner.nextDouble();
			scanner.nextLine();

			Student student = new Student(studentId, name, grade);
			students.add(student);
			studentMap.put(studentId, student);
		}

		// ������ ���� �л� ��� ����
		Collections.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student s1, Student s2) {
				return Double.compare(s2.grade, s1.grade);
			}
		});
		// ���� 20% �л����� A+ ���� �ο�
		int topCount = (int) Math.ceil(numStudents * 0.2);
		for (int i = 0; i < topCount; i++) {
			students.get(i).gradeLetter = "A+";
		}
		
		// ���� 40% �л����� A ���� �ο�
		int midCount = (int) Math.ceil(numStudents * 0.4);
		for (int i = topCount; i < midCount; i++) {
		    students.get(i).gradeLetter = "A";
		}
		
		// ���� 60% �л����� B+ ���� �ο�
		int highMidCount = (int) Math.ceil(numStudents * 0.6);
		for (int i = midCount; i < highMidCount; i++) {
		    students.get(i).gradeLetter = "B+";
		}
		
		// ���� 80% �л����� B ���� �ο�
		int lowMidCount = (int) Math.ceil(numStudents * 0.8);
		for (int i = highMidCount; i < lowMidCount; i++) {
		    students.get(i).gradeLetter = "B";
		}
		// ������ �л��鿡�� C+ ���� �ο�
		for (int i = lowMidCount; i < numStudents; i++) {
		    students.get(i).gradeLetter = "C+";
		}
		
		// ���� ��� ����
		String userHome = System.getProperty("user.home");
		String filePath = userHome + "/Desktop/students.csv";

		// �л� ������ ���Ͽ� ����
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			writer.write("����,�й�,�̸�,����,����\n");;
			for (int i = 0; i < students.size(); i++) {
				Student student = students.get(i);
				writer.write((i + 1) + "," + student.studentId + "," + student.name + "," + student.grade + "," + student.gradeLetter + "\n");
			}
			System.out.println("�л� ������ ����ȭ�鿡 ����Ǿ����ϴ�: " + filePath);
		} catch (IOException e) {
			System.out.println("���� ���� �� ������ �߻��߽��ϴ�.");
		}

		// �л� ���� ��� ���� ���
		double totalGrades = 0;
		for (Student student : students) {
			totalGrades += student.grade;
		}
		double averageGrade = totalGrades / numStudents;

		// ���Ͽ� �л� ���� ��� ���� �߰�
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
			writer.write("��ü �л� ��: " + numStudents + "\n");
			writer.write("�л����� ��� ����: " + String.format("%.2f", averageGrade) + "\n");
		} catch (IOException e) {
			System.out.println("���� ���� �� ������ �߻��߽��ϴ�.");
		}

		// Ư�� �й� �˻�
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