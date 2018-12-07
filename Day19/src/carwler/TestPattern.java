package carwler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPattern {
    public static void main(String[] args) {
        String html = "图片1 <img class=\"样式\" src=\"3.png\"> 图片2 <img class=\"样式\" src=\"4.jpg\">";
        Pattern pattern = Pattern.compile("<img class=\"BDE_Image\" src=\"(.*?)\">");
        Matcher matcher = pattern.matcher(html);
        while(matcher.find()) {
            System.out.println(matcher.group(1));
        }


    }
}
