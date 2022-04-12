package vn.techmaster.demo.service;

import java.util.Random;

import org.springframework.stereotype.Service;
import vn.techmaster.demo.exception.BadRequestException;

@Service
public class ColorService {

    public String randomColor(int type) {
        String color = "";
        switch (type) {
            case 1:
                color = randomColorName();
                break;
            case 2:
                color = randomHexColor();
                break;
            case 3:
                color = randomRGBColor();
                break;
            default:
                throw new BadRequestException("type không hợp lệ");
        }

        return color;
    }

    public String randomColorName() {
        String []colors = {"red","blue","green","black","pink","yellow"};
        Random rd = new Random();
        return colors[rd.nextInt(colors.length)];
    }

    public String randomHexColor() {
        String []colors = {"a","b","c","d","e","f","0","1","2","3","4","5","6","7","8","9"};
        Random rd = new Random();

        // Cách 1 : Cộng chuỗi
        // String color = "#" ;       
        // for(int i = 0 ; i < 6 ; i++){       
        //     color += colors[rd.nextInt(colors.length)];
        // }
        
        //return color;

        // Cách 2 : dùng StringBuilder
        StringBuilder sb = new StringBuilder("#");
        for(int i = 0 ; i < 6 ; i++){       
            String color = colors[rd.nextInt(colors.length)];
            sb.append(color);
        }

        return sb.toString();
    }

    public String randomRGBColor() {
        Random rd = new Random();
        int r = rd.nextInt(256);
        int g = rd.nextInt(256);
        int b = rd.nextInt(256);
        return "rgb(" + r + "," + g + "," + b +")";
    }

}
