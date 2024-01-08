package net.sanchit.springboot.controller;

import net.sanchit.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(1, "Sanchit", "Bhagat");
        return ResponseEntity.ok().header("custome-header", "Sanchit").body(student);
    }

    // http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<Student>>  getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Sanchit", "Bhagat"));
        students.add(new Student(2, "Ramesh", "Niraula"));
        students.add(new Student(3, "Anil", "Chaudhary"));
        students.add(new Student(4, "Salina", "Thapa"));

        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    // Spring BOOT REST API with Path Variable
    //{id} = URL template variable
    // http://localhost:8080/students/1/sanchit/bhagat
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName)
    {
        Student student = new Student(studentId, firstName,lastName);
        return ResponseEntity.ok(student) ;
    }

    // Spring BOOT REST API with Request Param
    // http://localhost:8080/students/query?id=1&firstName=Sanchit&lastName=Bhagat
    @GetMapping("query")
    public Student studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        return new Student(id, firstName,lastName);
    }


    // Spring BOOT REST API that handles HTTP POST Request - creating new resources
    // @PostMapping and @RequestBody
    // http://localhost:8080/students/create
    @PostMapping("create")
  //  @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);

    }


    // Spring BOOT REST API that handles HTTP PUT Request - updating existing resources
    // http://localhost:8080/students/1/update

    @PutMapping("{id}/update")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int studentID){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    // Spring BOOT REST API that handles HTTP DELETE Request -  deleting existing resources
    // http://localhost:8080/students/1/delete
    @DeleteMapping("{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return "Student deleted successfully";
    }

}
