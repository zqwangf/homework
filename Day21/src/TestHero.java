import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestHero {
    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));
        List<Hero> list = null;
        try (Stream<String> lines = Files.lines(Paths.get("/heroes.txt"))) {
            list = lines.map((s) -> {
                String[] a = s.split("\t");
                return new Hero(
                        Integer.parseInt(a[0]),
                        a[1],
                        a[2],
                        a[3],
                        Integer.parseInt(a[4]),
                        Integer.parseInt(a[5]),
                        Integer.parseInt(a[6])
                );
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-------------------------武力值前三名--------------------------");
        list.stream().sorted((a,b)->b.getPower()-a.getPower()).limit(3).forEach(
                h -> System.out.println(h)
        );
        System.out.println("-------------------------按出生地分组--------------------------");
        System.out.println(list.stream().collect(Collectors.groupingBy(s -> (s.getLoc()))));
        System.out.println("-------------------------找出寿命前三的武将--------------------------");
        list.stream().sorted((a,b) -> ((b.getDeath()-b.getBirth())-(a.getDeath()-a.getBirth()))).limit(3).forEach(
                i -> System.out.println(i)
        );
        System.out.println("-------------------------找出女性寿命最高的的武将--------------------------");
        list.stream().filter(u -> u.getSex().equals("女")).sorted((a, b) -> ((b.getDeath() - b.getBirth()) - (a.getDeath() - a.getBirth()))).limit(1).forEach(
                j -> System.out.println(j)
        );
        System.out.println("-------------------------找出武力排名前三--------------------------");
        Set<Integer> set = list.stream().map(h -> h.getPower()).sorted((a, b) -> b - a).limit(3).collect(Collectors.toSet());
        list.stream().filter(h -> set.contains(h.getPower())).forEach(h -> System.out.println(h));
        System.out.println("-------------------------按各个年龄段分组--------------------------");
        Map<String, List<Hero>> map1 = list.stream().collect(Collectors.groupingBy(h -> ageRange(h.getDeath() - h.getBirth())));
        map1.forEach((a,b) -> {
            System.out.println(a+":"+b.stream().collect(Collectors.toList()));
        });
        System.out.println("-------------------------按各个武力段分组--------------------------");
        Map<String, List<Hero>> map2 = list.stream().collect(Collectors.groupingBy(h -> powerRange(h.getPower())));
        map2.forEach((a,b) -> {
            System.out.println(a+":"+b.stream().collect(Collectors.toList()));
        });
        System.out.println("-------------------------按出生地分组,并统计各组人数--------------------------");
        Map<String, List<Hero>> map3 = list.stream().collect(Collectors.groupingBy(h -> h.getLoc()));
        map3.forEach((a,b) -> {
            System.out.println(b);
            System.out.println(a+":"+b.stream().count());
        });
    }
    public static String ageRange(int age){
        if(age>=0&&age<20){
            return "0~20";
        }
        if(age>=20&&age<40){
            return "20~40";
        }
        if(age>=40&&age<60){
            return "40~60";
        }else {
            return "大于60";
        }
    }
    public static String powerRange(int power){
        if(power>=90){
            return "大于90";
        }
        if(power>=80&&power<90){
            return "80~90";
        }
        if(power>=70&&power<80){
            return "70~80";
        }else {
            return "小于70";
        }
    }
}
