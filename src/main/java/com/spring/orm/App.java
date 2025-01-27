package com.spring.orm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

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
                    	//Add New Student
                    	
                    	System.out.println("Enter user id : ");
                    	int uId = Integer.parseInt(br.readLine());
                    	
                    	System.out.println("Enter user name : ");
                    	String uName = br.readLine();
                    	
                    	System.out.println("Enter user city : ");
                    	String uCity = br.readLine();
                    	
                    	Student student = new Student();
                    	student.setStudentId(uId);
                    	student.setStudentName(uName);
                    	student.setStudentCity(uCity);
                    	
                    	int r = studentDao.insert(student);
                    	System.out.println(r+" student added. ");
                    	
                    	System.out.println("*****************************************************************");
                    	System.out.println();
                    	
                        break;

                    case 2:
                    	//Display All Students
                    	
                    	System.out.println("*****************************************************************");
                    	List<Student> allStudents = studentDao.getAllStudents();
                    	
                    	for (Student st : allStudents) {
							System.out.println("Id : "+st.getStudentId());
							System.out.println("Name : "+st.getStudentName());
							System.out.println("City : "+st.getStudentCity());
							System.out.println("-------------------------------------------------------------");
						}
                    	
                    	System.out.println("*****************************************************************");
                        break;

                    case 3:
                    	//Get Single Student Details

                    	System.out.println("Enter user id : ");
                    	int userId = Integer.parseInt(br.readLine());
                    	
                    	Student stud = studentDao.getStudent(userId);
						System.out.println("Id : "+stud.getStudentId());
						System.out.println("Name : "+stud.getStudentName());
						System.out.println("City : "+stud.getStudentCity());
						System.out.println("-------------------------------------------------------------");
                    	
                        
                        break;

                    case 4:
                    	//Delete Student

                    	System.out.println("Enter user id : ");
                        int userID = Integer.parseInt(br.readLine());
                    	studentDao.deleteStudent(userID);
                    	System.out.println("Student deleted...");
                    	
                        break;

                    case 5:
                    	//Update Student
                    	
                    	System.out.println("Enter user id to update: ");
                    	int updateId = Integer.parseInt(br.readLine());
                    	
                    	
                    	Student st = studentDao.getStudent(updateId);
                    	
                    	System.out.println("Enter user name to update: ");
                    	String usName = br.readLine();
                    	st.setStudentName(usName);
                    	
                    	System.out.println("Enter user city to update: ");
                    	String usCity = br.readLine();
                    	st.setStudentCity(usCity);
                    	
                    	studentDao.updateStudent(st);
                        System.out.println("Student updated successfully!");
                    	System.out.println("*****************************************************************");
                    	System.out.println();
                    	
                        break;

                    case 6:
                    	//Exit
                        go = false;
                        break;
                        
                    default:
                    	System.out.println("Invalid Input!!");
                }
                
            } catch (Exception e) {
            	System.out.println("Invalid input try with another one !!");
            	System.out.println(e.getMessage());
            }
        }
        System.out.println("Thank You for using my application!");
        System.out.println("See You Soon!!!");
    }
}
