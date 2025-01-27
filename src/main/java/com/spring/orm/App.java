package com.spring.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("com/spring/orm/config.xml");
    	StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
    	
//    	Student student = new Student(2,"Shyam Mahajan","Singnur");
//    	int r = studentDao.insert(student);
//    	System.out.println("Data Insert : "+r);
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	boolean go=true;
    	
    	while (go) {
            System.out.println("\n************ Welcome To Spring ORM Project ***************");
            System.out.println("1: Add New Student");
            System.out.println("2: Display All Students");
            System.out.println("3: Get Single Student Details");
            System.out.println("4: Delete Student");
            System.out.println("5: Update Student");
            System.out.println("6: Exit");
            System.out.print("Enter your choice: ");

            try {
                int input = Integer.parseInt(br.readLine());

                switch (input) {
                    case 1:
                        System.out.println("Enter Student Name: ");
                        String name = br.readLine();

                        System.out.println("Enter Student City: ");
                        String city = br.readLine();

                        Student newStudent = new Student();
                        newStudent.setStudentName(name);
                        newStudent.setStudentCity(city);

                        int studentId = studentDao.insert(newStudent);
                        System.out.println("Student added successfully with ID: " + studentId);
                        break;

                    case 2:
                        System.out.println("Fetching all students...");
                        studentDao.getAllStudents().forEach(System.out::println);
                        break;

                    case 3:
                        System.out.println("Enter Student ID: ");
                        int id = Integer.parseInt(br.readLine());

                        Student student = studentDao.getStudent(id);
                        if (student != null) {
                            System.out.println(student);
                        } else {
                            System.out.println("Student not found with ID: " + id);
                        }
                        break;

                    case 4:
                        System.out.println("Enter Student ID to delete: ");
                        int deleteId = Integer.parseInt(br.readLine());

                        studentDao.deleteStudent(deleteId);
                        System.out.println("Student deleted successfully!");
                        break;

                    case 5:
                        System.out.println("Enter Student ID to update: ");
                        int updateId = Integer.parseInt(br.readLine());

                        Student existingStudent = studentDao.getStudent(updateId);
                        if (existingStudent == null) {
                            System.out.println("Student not found!");
                            break;
                        }

                        System.out.println("Enter new Name (Press Enter to keep current): ");
                        String newName = br.readLine();
                        if (!newName.isEmpty()) {
                            existingStudent.setStudentName(newName);
                        }

                        System.out.println("Enter new City (Press Enter to keep current): ");
                        String newCity = br.readLine();
                        if (!newCity.isEmpty()) {
                            existingStudent.setStudentCity(newCity);
                        }

                        studentDao.updateStudent(existingStudent);
                        System.out.println("Student updated successfully!");
                        break;

                    case 6:
                        go = false;
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
        System.out.println("Thank You for using my application!");
        System.out.println("See You Soon!!!");
    }
}
