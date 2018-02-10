import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Pol on 02/02/2018.
 */
public class Linkp{

    String idioma;
    String subtit;
    String qualitatVideo;
    String qualitatAudio;
    String link;
    String server;
    boolean online;
    //boolean funciona;

    //Per teure el nom del png, tambe serveix per saber el server
    public String linkToLang(String link){

        String[] parts = link.split("/");
        String langpng = parts[parts.length -1];
        String[] partspng = langpng.split("\\.");
        return partspng[0];

    }

    public void agregaElements(Element codi){

        //Document codih = Jsoup.parse(codi.html());

        //Busca host

        Elements host = codi.getElementsByClass("host");
        Document codihost = Jsoup.parse(host.html());
        Elements linkhost = codihost.getElementsByTag("img");
        String linklogo = linkhost.get(0).attr("src");
        this.server = linkToLang(linklogo);
        //System.out.println(this.server);


        //Busca idioma
        Elements lang = codi.getElementsByClass("language");
       // System.out.println(lang);
        //Document codilang = Jsoup.parse(lang.html());
        Elements linklang = lang.select("img");
        //System.out.println(linklang);
        if (linklang.size()==2){
            System.out.println("Te subs");
            String linkflag = linklang.get(1).attr("src");
            this.subtit = linkToLang(linkflag);
            //System.out.println("subtitol: " + this.subtit);

        }


        String linkflag = linklang.get(0).attr("src");

        this.idioma = linkToLang(linkflag);
        //System.out.println(this.idioma);

        //Busca qualitat video
        Elements vq = codi.getElementsByClass("videoquality");
        //System.out.println(vq.text());
        qualitatVideo = vq.text();

        //Busca qualitat audio
        Elements va = codi.getElementsByClass("audioquality");
        //System.out.println(va.text());
        qualitatAudio = va.text();









    }

    public void assignLink(String origen){
        String linko = "";


        try {
            String cookie = "__cfduid=dff6640fd8af6444c69e2bb84b9117d571517479581; _ga=GA1.2.47025116.1517484811; plusdede-sess=eyJpdiI6InNlNUlOOWVBTVwvK25KSUVBMTBCYW1RPT0iLCJ2YWx1ZSI6InpQbW1NWVVKb0lKNjFVN09ZdlcyVGdLTGpqeUJlU1F0R055SnFmQ1NCdmR0MDZySm8rWFd0NjA2OERiVHU3RGUiLCJtYWMiOiI3NmI3YWJlZDZhNWFmNmI3MDJiY2ExMDIwZTljZmE5YWU1YWU3YjdmZGUzMmRlY2ZjYzM1NjBiZTg3Nzg2N2IwIn0%3D; _gid=GA1.2.282494337.1517589154; PHPSESSID=vsq9oc4ee53qv6020pnvra4in2; rhid_c=52236553653; XSRF-TOKEN=eyJpdiI6ImFLbnFcLzhlNXBVM01xYTgrd3ltMkxnPT0iLCJ2YWx1ZSI6ImlvXC9VOXhmN1c2WVVOR2lkZFZra2kzcVRRNzhWMXpGVVYxOXlOSTk1dUo3MkFaNHBGWXNYV2Y2RVdIWW0zWjd5UU5zV1pwNlBrc1Y5bFZBM1dJaG8zdz09IiwibWFjIjoiYzBjM2RjOTk0ZjA5NDM4NjRlNWM5ZTM2MmVmZDFjNWI4ZDRlMTY4ZTYzNDkyNzRjNGI0ODY5ZjQ3YTM2ZDY0YiJ9; cakephp_session=eyJpdiI6IjN6YVN2WjJGMU1OQnFYZDNYUmhlaEE9PSIsInZhbHVlIjoibXd2cFpMUnpOWndCV0F4aWhuT2tPVmlmdTlGY1BjeHg2V09KZ2pXelBGaWU5SFNUNGpFdkdXdHpsSXA5NW1xV3RYSVViYjIxOG9cL3FpYXdBT3k2MmlBPT0iLCJtYWMiOiIzNWU5ODdiMjJlNGY3NTRhYjFlY2EwZDQwYTU3MmYxYjA0M2Y2ODU3OWUxZDgwYTIyYzgyYzc2Y2U2MWZhYWU2In0%3D";

            HttpURLConnection con = (HttpURLConnection)(new URL( origen ).openConnection());            con.setInstanceFollowRedirects(false);
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
            con.setRequestProperty("cookie", cookie);
            con.connect();
            //con.getInputStream();
            int resCode = con.getResponseCode();
            System.out.println( resCode );
            if(resCode == 302){
                online=true;
            }
            linko = con.getHeaderField( "Location" );
            System.out.println( linko );
            this.link = linko;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.online = false;
        }


    }
}