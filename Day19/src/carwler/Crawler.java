package carwler;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {

    public static void main(String[] args) throws IOException {
        String url = "http://tieba.baidu.com/photo/p?kw=%E6%A1%8C%E9%9D%A2&flux=1&tid=2256306796&pic_id=5cd8f2d3572c11df1d2ebc45622762d0f603c2de&pn=1&fp=2&see_lz=1&red_tag=q1140227708";
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        ExecutorService service = Executors.newFixedThreadPool(8);
        String html = fetch(conn);
        Matcher matcher = PATTERN.matcher(html);
        while (matcher.find()) {
            String imageURL = matcher.group(1);
            service.submit( ()-> {
                try {
                    download(imageURL);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void download(String imageURL) throws IOException {
        HttpURLConnection c = (HttpURLConnection) new URL(imageURL).openConnection();
        String image = imageURL.substring(imageURL.lastIndexOf("/") + 1);
        try (
                InputStream in = c.getInputStream();
                OutputStream out = new FileOutputStream("e:\\images\\" + image)
        ) {
            byte[] bytes = new byte[1024 * 1024];
            while (true) {
                int len = in.read(bytes);
                if (len == -1) {
                    break;
                }
                out.write(bytes, 0, len);
            }
        }
    }

    static final Pattern PATTERN = Pattern.compile("<img class=\"BDE_Image\" src=\"(.*?)\">");

    private static String fetch(HttpURLConnection conn) {
        StringBuilder sb = new StringBuilder(1024*1024);
        try (BufferedReader reader =
                        new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))
        ) {
            while(true) {
                String line = reader.readLine();
                if(line != null) {
                    break;
                }
                sb.append(line);
            }
        } catch(IOException e) {

        }
        return sb.toString();
    }

}
