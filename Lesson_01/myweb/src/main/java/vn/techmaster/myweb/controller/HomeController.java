package vn.techmaster.myweb.controller;

import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.techmaster.model.BMI;
import vn.techmaster.model.ListStudent;
import vn.techmaster.model.RandomString;

import java.util.ArrayList;
import java.util.Random;
import org.apache.tomcat.util.http.parser.MediaType;


@Controller
@RequestMapping("/")
public class HomeController<Student> {
   
    //b1
    @GetMapping(value = "/random")
    @ResponseBody
    public String randomString() {
        return RandomString.randomAlphaNumer(8);
      }
    

    //b2
    @GetMapping(value = "/quote")
    @ResponseBody
    public String quote(){
        String[] arr = {"Kiến tha lâu đầy tổ", "Có công mài sắt có ngày nên kim", "Không thầy đố mày làm nên", "Học thầy không tày học bạn"};
        Random r = new Random();
        return arr[r.nextInt(arr.length)];
    }

    //b3
    @GetMapping(value = "/bmi")
    @ResponseBody
    public double getBmi(@RequestBody BMI body) {
        return body.getWeight()/(body.getHeight()*body.getHeight());
    }

    //b4
    ArrayList<Student> student = ListStudent.infor();
  @GetMapping(value = "/student")
  @ResponseBody
  public ArrayList<Student> danhsachSinhvien(){
    return student;
  }

  @PostMapping(value = "/student")
  @ResponseBody
  public Student themmoiSinhvien(@RequestBody Student newStudent){
    student.add(newStudent);
    return newStudent;
  }
}
