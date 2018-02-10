import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;

/**
 * Created by Pol on 01/02/2018.
 */
public class plusdedo {

    public  plusdedo(){


    }


    public static class  Pelip {

        public int posicio;
        public int pagina;
        public String id;
        public String coverPhoto;
        public String iconPhoto;
        public String description;
        public String name;
        public List gender;
        public String year;
        public String link;
        List<Linkp> linksp;
        public String imdb;
        public String tmdb;

        public Pelip(String link){

            this.link = link;
            String linksplit [] = link.split("/");
            this.id = linksplit[linksplit.length -1];

            posicio = -1;
            pagina = -1;
        }
        public Pelip(String link, int pag, int pos){
            this.link = link;
            String linksplit [] = link.split("/");
            this.id = linksplit[linksplit.length -1];

            posicio = pos;
            pagina = pag;

        }

        public void copiaPeli(String peliurl){

            this.linksp = new ArrayList<Linkp>();

            Linkp linkptemp = new Linkp();
            String cookie = "__cfduid=dff6640fd8af6444c69e2bb84b9117d571517479581; _ga=GA1.2.47025116.1517484811; plusdede-sess=eyJpdiI6InNlNUlOOWVBTVwvK25KSUVBMTBCYW1RPT0iLCJ2YWx1ZSI6InpQbW1NWVVKb0lKNjFVN09ZdlcyVGdLTGpqeUJlU1F0R055SnFmQ1NCdmR0MDZySm8rWFd0NjA2OERiVHU3RGUiLCJtYWMiOiI3NmI3YWJlZDZhNWFmNmI3MDJiY2ExMDIwZTljZmE5YWU1YWU3YjdmZGUzMmRlY2ZjYzM1NjBiZTg3Nzg2N2IwIn0%3D; _gid=GA1.2.282494337.1517589154; PHPSESSID=vsq9oc4ee53qv6020pnvra4in2; rhid_c=52236553653; XSRF-TOKEN=eyJpdiI6ImFLbnFcLzhlNXBVM01xYTgrd3ltMkxnPT0iLCJ2YWx1ZSI6ImlvXC9VOXhmN1c2WVVOR2lkZFZra2kzcVRRNzhWMXpGVVYxOXlOSTk1dUo3MkFaNHBGWXNYV2Y2RVdIWW0zWjd5UU5zV1pwNlBrc1Y5bFZBM1dJaG8zdz09IiwibWFjIjoiYzBjM2RjOTk0ZjA5NDM4NjRlNWM5ZTM2MmVmZDFjNWI4ZDRlMTY4ZTYzNDkyNzRjNGI0ODY5ZjQ3YTM2ZDY0YiJ9; cakephp_session=eyJpdiI6IjN6YVN2WjJGMU1OQnFYZDNYUmhlaEE9PSIsInZhbHVlIjoibXd2cFpMUnpOWndCV0F4aWhuT2tPVmlmdTlGY1BjeHg2V09KZ2pXelBGaWU5SFNUNGpFdkdXdHpsSXA5NW1xV3RYSVViYjIxOG9cL3FpYXdBT3k2MmlBPT0iLCJtYWMiOiIzNWU5ODdiMjJlNGY3NTRhYjFlY2EwZDQwYTU3MmYxYjA0M2Y2ODU3OWUxZDgwYTIyYzgyYzc2Y2U2MWZhYWU2In0%3D";
            Document doc = null;



            try {

                doc = Jsoup.connect(this.link).header("Cookie", cookie).get();


            } catch (IOException e) {

                doc = Seriep.connectDoc2(cookie,doc,Jsoup.connect(this.link));
            }
            this.ompleElements(doc);

            Elements boto = doc.getElementsByClass("show-close-footer btn btn-primary");
            String linkap = boto.attr("data-href");
            try {
                doc = Jsoup.connect("https://www.plusdede.com/" + linkap).header("Cookie", cookie).get();
            } catch (IOException e) {
                doc = Seriep.connectDoc2(cookie,doc,Jsoup.connect("https://www.plusdede.com/" + linkap));
            }
            Elements enllas = doc.getElementsByAttribute("data-v");

            //2 per general i 10 per bo
            int compOp =0;int compOpbo=0;
            int compSC =0;int compSCbo=0;
            int compUpl =0;int compUplbo =0;
            int compMega =0;int compMegabo=0;
            int compSM =0; int compSMbo=0;
            int compPV =0; int compPVbo =0;
            int altres=0; int altresbo=0;

            for (int i = 0; i < enllas.size(); i++) {

                linkap = enllas.get(i).attr("href");
                try {
                    doc = Jsoup.connect(linkap).header("Cookie", cookie).get();
                } catch (IOException e) {
                    doc = Seriep.connectDoc2(cookie,doc,Jsoup.connect(linkap));

                }
                boto = doc.getElementsByClass("visit-buttons");
                Document botoaux = Jsoup.parse(boto.html());
                Elements enllac = botoaux.getElementsByAttribute("href");
                //System.out.println(enllas.get(i));
                linkap = enllac.attr("href");
                linkptemp = new Linkp();
                linkptemp.agregaElements(enllas.get(i));
                System.out.println(linkptemp.server);

                if (linkptemp.idioma.equals("catalan")){
                    linkptemp.assignLink("https://www.plusdede.com" + linkap);
                    this.linksp.add(linkptemp);
                }
                else if (linkptemp.server.equals("openload")) {
                    if ((compOpbo < 10) && ((linkptemp.idioma.equals("english")) || (linkptemp.qualitatVideo.equals("Hd 1080")))) {
                        linkptemp.assignLink("https://www.plusdede.com" + linkap);
                        this.linksp.add(linkptemp);
                        compOpbo++;
                    } else if (compOp < 2) {
                        linkptemp.assignLink("https://www.plusdede.com" + linkap);
                        this.linksp.add(linkptemp);
                        compOp++;
                    }
                }else if (linkptemp.server.equals("streamcloud")) {
                    if ((compSCbo < 2) && ((linkptemp.idioma.equals("english") ))) {
                        linkptemp.assignLink("https://www.plusdede.com" + linkap);
                        this.linksp.add(linkptemp);
                        compSCbo++;
                    } else if (compSC < 2) {
                        linkptemp.assignLink("https://www.plusdede.com" + linkap);
                        this.linksp.add(linkptemp);
                        compSC++;
                    }
                }else if (linkptemp.server.equals("uploaded")) {
                    if ((compUplbo < 10) && ((linkptemp.idioma.equals("english")) || (linkptemp.qualitatVideo.equals("Hd 1080")))) {
                        linkptemp.assignLink("https://www.plusdede.com" + linkap);
                        this.linksp.add(linkptemp);
                        compUplbo++;
                    } else if (compUpl < 2) {
                        linkptemp.assignLink("https://www.plusdede.com" + linkap);
                        this.linksp.add(linkptemp);
                        compUpl++;
                    }
                }else if (linkptemp.server.equals("mega")) {
                    if ((compMegabo < 10) && ((linkptemp.idioma.equals("english")) || (linkptemp.qualitatVideo.equals("Hd 1080")))) {
                        linkptemp.assignLink("https://www.plusdede.com" + linkap);
                        this.linksp.add(linkptemp);
                        compMegabo++;
                    } else if (compMega < 2) {
                        linkptemp.assignLink("https://www.plusdede.com" + linkap);
                        this.linksp.add(linkptemp);
                        compMega++;
                    }
                }else if (linkptemp.server.equals("streamango")) {
                    if ((compSMbo < 3) && ((linkptemp.idioma.equals("english")) || (linkptemp.qualitatVideo.equals("Hd 1080")))) {
                        linkptemp.assignLink("https://www.plusdede.com" + linkap);
                        this.linksp.add(linkptemp);
                        compSMbo++;
                    } else if (compSM < 2) {
                        linkptemp.assignLink("https://www.plusdede.com" + linkap);
                        this.linksp.add(linkptemp);
                        compSM++;
                    }
                }else if (linkptemp.server.equals("powvideo")) {
                    if ((compPVbo < 10) && ((linkptemp.idioma.equals("english")) || (linkptemp.qualitatVideo.equals("Hd 1080")))) {
                        linkptemp.assignLink("https://www.plusdede.com" + linkap);
                        this.linksp.add(linkptemp);
                        compPVbo++;
                    } else if (compPV < 2) {
                        linkptemp.assignLink("https://www.plusdede.com" + linkap);
                        this.linksp.add(linkptemp);
                        compPV++;
                    }
                } else if(linkptemp.server.isEmpty()){}
                else if ((altresbo < 10) && ((linkptemp.idioma.equals("english")) || (linkptemp.qualitatVideo.equals("Hd 1080")))) {
                    linkptemp.assignLink("https://www.plusdede.com" + linkap);
                    this.linksp.add(linkptemp);
                    altres++;
                }
                else if (altres < 2) {
                    linkptemp.assignLink("https://www.plusdede.com" + linkap);
                    this.linksp.add(linkptemp);
                    altres++;
                }
            }
            //this.linksp.get(i).assignLink("https://www.plusdede.com" + linkap);
            //System.out.println(this.linksp.get(i).link);



            //linkap = boto.attr()

            //System.out.println(doc);

            this.guardaPeli(this.id);

        }

        public void ompleElements(Document doc){

            Elements titol = doc.getElementsByTag("h1");
            this.name = titol.get(0).text();
            Elements avatar = doc.getElementsByClass("medium-avatar");
            this.iconPhoto = avatar.attr("src");

            Elements external = doc.getElementsByClass("external-links-container");

            Document doc2 = Jsoup.parse(external.html());
            Elements li = doc2.getElementsByTag("li");

            imdb = li.get(0).getElementsByTag("a").get(0).attr("href");
            tmdb = li.get(1).getElementsByTag("a").get(0).attr("href");
            System.out.println(imdb + "" + tmdb);

            System.out.println(name);
            System.out.println(iconPhoto);

        }

        public void guardaPeli(String nom) {
            Gson gson = new Gson();
            String textGson = gson.toJson(this);

            try {
                File fitxer = new File("pelis/" + pagina +"/" +posicio+"-" + nom + ".json");
                System.out.printf(fitxer.getAbsolutePath());
                FileWriter writer = new FileWriter(fitxer);

                writer.write(textGson);
                writer.close();
                System.out.println("Desat en el fitxer!");

                DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
                DbxClientV2 client = new DbxClientV2(config, Main.ACCESS_TOKEN);
                try (InputStream in = new FileInputStream("pelis/"+ pagina +"/" +posicio+"-" + nom + ".json")) {
                    FileMetadata metadata = client.files().uploadBuilder("/pelis/"+ pagina +"/" +posicio+"-" + nom + ".json")
                            .uploadAndFinish(in);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UploadErrorException e) {
                    e.printStackTrace();
                } catch (DbxException e) {
                    e.printStackTrace();
                }




            } catch (IOException e) {
                System.err.println("Hi ha hagut un problema escrivint al fitxer json");

            }catch (MissingFormatArgumentException me){
                File fitxer = new File("pelis/" + pagina +"/" +posicio+"-" + "pelienye" + ".json");
                System.out.printf(fitxer.getAbsolutePath());
                FileWriter writer = null;

                try {
                    writer = new FileWriter(fitxer);
                    writer.write(textGson);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                System.out.println("Desat en el fitxer!");

            }



        }
    }


    public static class Seriep{

        public int pagina;
        public int posicio;
        public String id;
        public String coverPhoto;
        public String iconPhoto;
        public String description;
        public String name;
        public List gender;
        public String year;
        public String link;
        public  List<Temporadesp> temporades;

        public Seriep(String link){

            this.link = link;
            String linksplit [] = link.split("/");
            this.id = linksplit[linksplit.length -1];

            posicio = -1;
            pagina = -1;
        }
        public Seriep(String link, int pag, int pos){
            this.link = link;
            String linksplit [] = link.split("/");
            this.id = linksplit[linksplit.length -1];

            posicio = pos;
            pagina = pag;

        }

        public void creaTemporades(){
            temporades = new ArrayList<Temporadesp>();
            Temporadesp temp = new Temporadesp();
            String cookie = "__cfduid=dff6640fd8af6444c69e2bb84b9117d571517479581; _ga=GA1.2.47025116.1517484811; plusdede-sess=eyJpdiI6InNlNUlOOWVBTVwvK25KSUVBMTBCYW1RPT0iLCJ2YWx1ZSI6InpQbW1NWVVKb0lKNjFVN09ZdlcyVGdLTGpqeUJlU1F0R055SnFmQ1NCdmR0MDZySm8rWFd0NjA2OERiVHU3RGUiLCJtYWMiOiI3NmI3YWJlZDZhNWFmNmI3MDJiY2ExMDIwZTljZmE5YWU1YWU3YjdmZGUzMmRlY2ZjYzM1NjBiZTg3Nzg2N2IwIn0%3D; _gid=GA1.2.282494337.1517589154; PHPSESSID=vsq9oc4ee53qv6020pnvra4in2; rhid_c=52236553653; XSRF-TOKEN=eyJpdiI6ImFLbnFcLzhlNXBVM01xYTgrd3ltMkxnPT0iLCJ2YWx1ZSI6ImlvXC9VOXhmN1c2WVVOR2lkZFZra2kzcVRRNzhWMXpGVVYxOXlOSTk1dUo3MkFaNHBGWXNYV2Y2RVdIWW0zWjd5UU5zV1pwNlBrc1Y5bFZBM1dJaG8zdz09IiwibWFjIjoiYzBjM2RjOTk0ZjA5NDM4NjRlNWM5ZTM2MmVmZDFjNWI4ZDRlMTY4ZTYzNDkyNzRjNGI0ODY5ZjQ3YTM2ZDY0YiJ9; cakephp_session=eyJpdiI6IjN6YVN2WjJGMU1OQnFYZDNYUmhlaEE9PSIsInZhbHVlIjoibXd2cFpMUnpOWndCV0F4aWhuT2tPVmlmdTlGY1BjeHg2V09KZ2pXelBGaWU5SFNUNGpFdkdXdHpsSXA5NW1xV3RYSVViYjIxOG9cL3FpYXdBT3k2MmlBPT0iLCJtYWMiOiIzNWU5ODdiMjJlNGY3NTRhYjFlY2EwZDQwYTU3MmYxYjA0M2Y2ODU3OWUxZDgwYTIyYzgyYzc2Y2U2MWZhYWU2In0%3D";
            Document doc = null;

            try {
                doc = Jsoup.connect(this.link).header("Cookie", cookie).get();
               // Elements tempo = doc.getElementsByTag("ul");

            } catch (IOException e) {
                doc = connectDoc2(cookie,doc,Jsoup.connect(this.link));
            }
            Elements dataseason =doc.select("ul[data-season]");
            Elements datee = Jsoup.parse(dataseason.html()).getElementsByTag("ul");
            //System.out.println(dataseason);
            for (int i=0; i<dataseason.size(); i++){

                temp = new Temporadesp();
                temp.num = dataseason.get(i).attr("data-season");
                System.out.println(temp.num);
                temp.ompleTemporada(dataseason.get(i));
                this.temporades.add(temp);
            }

        }

        public void creaTemporadesEmbed(){
            System.out.println("he entrat");
            temporades = new ArrayList<Temporadesp>();
            Temporadesp temp = new Temporadesp();
            String cookie = "__cfduid=dff6640fd8af6444c69e2bb84b9117d571517479581; _ga=GA1.2.47025116.1517484811; plusdede-sess=eyJpdiI6InNlNUlOOWVBTVwvK25KSUVBMTBCYW1RPT0iLCJ2YWx1ZSI6InpQbW1NWVVKb0lKNjFVN09ZdlcyVGdLTGpqeUJlU1F0R055SnFmQ1NCdmR0MDZySm8rWFd0NjA2OERiVHU3RGUiLCJtYWMiOiI3NmI3YWJlZDZhNWFmNmI3MDJiY2ExMDIwZTljZmE5YWU1YWU3YjdmZGUzMmRlY2ZjYzM1NjBiZTg3Nzg2N2IwIn0%3D; _gid=GA1.2.282494337.1517589154; PHPSESSID=vsq9oc4ee53qv6020pnvra4in2; rhid_c=52236553653; XSRF-TOKEN=eyJpdiI6ImFLbnFcLzhlNXBVM01xYTgrd3ltMkxnPT0iLCJ2YWx1ZSI6ImlvXC9VOXhmN1c2WVVOR2lkZFZra2kzcVRRNzhWMXpGVVYxOXlOSTk1dUo3MkFaNHBGWXNYV2Y2RVdIWW0zWjd5UU5zV1pwNlBrc1Y5bFZBM1dJaG8zdz09IiwibWFjIjoiYzBjM2RjOTk0ZjA5NDM4NjRlNWM5ZTM2MmVmZDFjNWI4ZDRlMTY4ZTYzNDkyNzRjNGI0ODY5ZjQ3YTM2ZDY0YiJ9; cakephp_session=eyJpdiI6IjN6YVN2WjJGMU1OQnFYZDNYUmhlaEE9PSIsInZhbHVlIjoibXd2cFpMUnpOWndCV0F4aWhuT2tPVmlmdTlGY1BjeHg2V09KZ2pXelBGaWU5SFNUNGpFdkdXdHpsSXA5NW1xV3RYSVViYjIxOG9cL3FpYXdBT3k2MmlBPT0iLCJtYWMiOiIzNWU5ODdiMjJlNGY3NTRhYjFlY2EwZDQwYTU3MmYxYjA0M2Y2ODU3OWUxZDgwYTIyYzgyYzc2Y2U2MWZhYWU2In0%3D";


            Document doc = null;
            try {
                doc = Jsoup.connect(this.link).header("Cookie", cookie).get();
                // Elements tempo = doc.getElementsByTag("ul");

            } catch (IOException e) {
                doc = connectDoc2(cookie, doc, Jsoup.connect(link));


            }
            Elements dataseason =doc.select("ul[data-season]");
            Elements datee = Jsoup.parse(dataseason.html()).getElementsByTag("ul");
            //System.out.println(dataseason);
            for (int i=0; i<dataseason.size(); i++){

                temp = new Temporadesp();
                temp.num = dataseason.get(i).attr("data-season");
                System.out.println(temp.num);
                temp.buscaTempEmbed(dataseason.get(i));
                this.temporades.add(temp);



            }

        }

        static Document connectDoc2(String cookie, Document doc, Connection connect) {
            try {
                System.out.println("Dormint 5 minuts");
                Thread.sleep(300000);
                System.out.println("despertat");
                doc = connect.header("Cookie",cookie).get();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            return doc;
        }

        public void guardaSerie() {
            Gson gson = new Gson();
            String textGson = gson.toJson(this);

            try {
                File fitxer = new File("series/" + pagina +"/" +posicio+"-" + this.id + ".json");
                System.out.printf(fitxer.getAbsolutePath());
                FileWriter writer = new FileWriter(fitxer);

                writer.write(textGson);
                writer.close();
                System.out.println("Desat en el fitxer!");


            } catch (IOException e) {
                System.err.println("Hi ha hagut un problema escrivint al fitxer json");

            }



        }


        public class Temporadesp{

            public String title;
            public List<Episodip> episodes;
            public String num;

            public class Episodip{

                String num;
                String name;
                String time;
                String description;
                String imatge;
                String link;
                List<Linkp> linksp;

                public Episodip(String link){this.link = link;}

                public void teEmbed(){
                    String cookie = "__cfduid=dff6640fd8af6444c69e2bb84b9117d571517479581; _ga=GA1.2.47025116.1517484811; plusdede-sess=eyJpdiI6InNlNUlOOWVBTVwvK25KSUVBMTBCYW1RPT0iLCJ2YWx1ZSI6InpQbW1NWVVKb0lKNjFVN09ZdlcyVGdLTGpqeUJlU1F0R055SnFmQ1NCdmR0MDZySm8rWFd0NjA2OERiVHU3RGUiLCJtYWMiOiI3NmI3YWJlZDZhNWFmNmI3MDJiY2ExMDIwZTljZmE5YWU1YWU3YjdmZGUzMmRlY2ZjYzM1NjBiZTg3Nzg2N2IwIn0%3D; _gid=GA1.2.282494337.1517589154; PHPSESSID=vsq9oc4ee53qv6020pnvra4in2; rhid_c=52236553653; XSRF-TOKEN=eyJpdiI6ImFLbnFcLzhlNXBVM01xYTgrd3ltMkxnPT0iLCJ2YWx1ZSI6ImlvXC9VOXhmN1c2WVVOR2lkZFZra2kzcVRRNzhWMXpGVVYxOXlOSTk1dUo3MkFaNHBGWXNYV2Y2RVdIWW0zWjd5UU5zV1pwNlBrc1Y5bFZBM1dJaG8zdz09IiwibWFjIjoiYzBjM2RjOTk0ZjA5NDM4NjRlNWM5ZTM2MmVmZDFjNWI4ZDRlMTY4ZTYzNDkyNzRjNGI0ODY5ZjQ3YTM2ZDY0YiJ9; cakephp_session=eyJpdiI6IjN6YVN2WjJGMU1OQnFYZDNYUmhlaEE9PSIsInZhbHVlIjoibXd2cFpMUnpOWndCV0F4aWhuT2tPVmlmdTlGY1BjeHg2V09KZ2pXelBGaWU5SFNUNGpFdkdXdHpsSXA5NW1xV3RYSVViYjIxOG9cL3FpYXdBT3k2MmlBPT0iLCJtYWMiOiIzNWU5ODdiMjJlNGY3NTRhYjFlY2EwZDQwYTU3MmYxYjA0M2Y2ODU3OWUxZDgwYTIyYzgyYzc2Y2U2MWZhYWU2In0%3D";
                    Document doc = new Document("");
                    doc = Main.connectDoc(link, doc, cookie);

                    Elements prova =doc.select(".embed");
                    Elements enllas = doc.getElementsByAttribute("data-v");
                    //System.out.println(enllas.size());


                    if (prova.isEmpty()&&(enllas.size()>1)){
                        System.out.println(link);
                        Scanner scanner = new Scanner(System.in);
                        String prova2 = scanner.nextLine();
                        }
                }

                public void omple(Element data){

                    String cookie = "__cfduid=dff6640fd8af6444c69e2bb84b9117d571517479581; _ga=GA1.2.47025116.1517484811; plusdede-sess=eyJpdiI6InNlNUlOOWVBTVwvK25KSUVBMTBCYW1RPT0iLCJ2YWx1ZSI6InpQbW1NWVVKb0lKNjFVN09ZdlcyVGdLTGpqeUJlU1F0R055SnFmQ1NCdmR0MDZySm8rWFd0NjA2OERiVHU3RGUiLCJtYWMiOiI3NmI3YWJlZDZhNWFmNmI3MDJiY2ExMDIwZTljZmE5YWU1YWU3YjdmZGUzMmRlY2ZjYzM1NjBiZTg3Nzg2N2IwIn0%3D; _gid=GA1.2.282494337.1517589154; PHPSESSID=vsq9oc4ee53qv6020pnvra4in2; rhid_c=52236553653; XSRF-TOKEN=eyJpdiI6ImFLbnFcLzhlNXBVM01xYTgrd3ltMkxnPT0iLCJ2YWx1ZSI6ImlvXC9VOXhmN1c2WVVOR2lkZFZra2kzcVRRNzhWMXpGVVYxOXlOSTk1dUo3MkFaNHBGWXNYV2Y2RVdIWW0zWjd5UU5zV1pwNlBrc1Y5bFZBM1dJaG8zdz09IiwibWFjIjoiYzBjM2RjOTk0ZjA5NDM4NjRlNWM5ZTM2MmVmZDFjNWI4ZDRlMTY4ZTYzNDkyNzRjNGI0ODY5ZjQ3YTM2ZDY0YiJ9; cakephp_session=eyJpdiI6IjN6YVN2WjJGMU1OQnFYZDNYUmhlaEE9PSIsInZhbHVlIjoibXd2cFpMUnpOWndCV0F4aWhuT2tPVmlmdTlGY1BjeHg2V09KZ2pXelBGaWU5SFNUNGpFdkdXdHpsSXA5NW1xV3RYSVViYjIxOG9cL3FpYXdBT3k2MmlBPT0iLCJtYWMiOiIzNWU5ODdiMjJlNGY3NTRhYjFlY2EwZDQwYTU3MmYxYjA0M2Y2ODU3OWUxZDgwYTIyYzgyYzc2Y2U2MWZhYWU2In0%3D";


                    Linkp linkptemp = new Linkp();


                    this.name = data.select(".name").text();
                    this.num = data.select(".name>.num").text();
                    System.out.println(this.num);

                    Document doc = null;


                    try {
                        doc = Jsoup.connect(link).header("Cookie",cookie).get();

                    } catch (IOException e) {
                        doc = connectDoc2(cookie,doc,Jsoup.connect(link));
                    }

                    Element info = doc.getElementsByClass("col-sm-12 episode-info").first();
                    this.description = info.text();
                    //System.out.println(description);
                    this.imatge = info.select("img").attr("src");
                    //System.out.println(imatge);
                    linksp = new ArrayList<Linkp>();
                    Elements enllas = doc.getElementsByAttribute("data-v");
                    String linkap;
                    Elements boto;
                    int compOp =0;int compOpbo=0;
                    int compSC =0;int compSCbo=0;
                    int compUpl =0;int compUplbo =0;
                    int compMega =0;int compMegabo=0;
                    int compSM =0; int compSMbo=0;
                    int compPV =0; int compPVbo =0;
                    int altres=0; int altresbo=0;

                    for (int i = 0; i < enllas.size(); i++) {

                        linkap = enllas.get(i).attr("href");
                        try {
                            doc = Jsoup.connect(linkap).header("Cookie", cookie).get();
                        } catch (IOException e) {
                            doc = Seriep.connectDoc2(cookie,doc,Jsoup.connect(linkap));

                        }
                        boto = doc.getElementsByClass("visit-buttons");
                        Document botoaux = Jsoup.parse(boto.html());
                        Elements enllac = botoaux.getElementsByAttribute("href");
                        //System.out.println(enllas.get(i));
                        linkap = enllac.attr("href");
                        linkptemp = new Linkp();

                        linkptemp.agregaElements(enllas.get(i));
                        System.out.println(linkptemp.server);



                        if (linkptemp.idioma.equals("catalan")){
                            linkptemp.assignLink("https://www.plusdede.com" + linkap);
                            this.linksp.add(linkptemp);
                        }
                        else if (linkptemp.server.equals("openload")) {
                            if ((compOpbo < 10) && ((linkptemp.idioma.equals("english")) || (linkptemp.qualitatVideo.equals("Hd 1080")))) {
                                linkptemp.assignLink("https://www.plusdede.com" + linkap);
                                this.linksp.add(linkptemp);
                                compOpbo++;
                            } else if (compOp < 2) {
                                linkptemp.assignLink("https://www.plusdede.com" + linkap);
                                this.linksp.add(linkptemp);
                                compOp++;
                            }
                        }else if (linkptemp.server.equals("streamcloud")) {
                            if ((compSCbo < 2) && ((linkptemp.idioma.equals("english") ))) {
                                linkptemp.assignLink("https://www.plusdede.com" + linkap);
                                this.linksp.add(linkptemp);
                                compSCbo++;
                            } else if (compSC < 2) {
                                linkptemp.assignLink("https://www.plusdede.com" + linkap);
                                this.linksp.add(linkptemp);
                                compSC++;
                            }
                        }else if (linkptemp.server.equals("uploaded")) {
                            if ((compUplbo < 10) && ((linkptemp.idioma.equals("english")) || (linkptemp.qualitatVideo.equals("Hd 1080")))) {
                                linkptemp.assignLink("https://www.plusdede.com" + linkap);
                                this.linksp.add(linkptemp);
                                compUplbo++;
                            } else if (compUpl < 2) {
                                linkptemp.assignLink("https://www.plusdede.com" + linkap);
                                this.linksp.add(linkptemp);
                                compUpl++;
                            }
                        }else if (linkptemp.server.equals("mega")) {
                            if ((compMegabo < 10) && ((linkptemp.idioma.equals("english")) || (linkptemp.qualitatVideo.equals("Hd 1080")))) {
                                linkptemp.assignLink("https://www.plusdede.com" + linkap);
                                this.linksp.add(linkptemp);
                                compMegabo++;
                            } else if (compMega < 2) {
                                linkptemp.assignLink("https://www.plusdede.com" + linkap);
                                this.linksp.add(linkptemp);
                                compMega++;
                            }
                        }else if (linkptemp.server.equals("streamango")) {
                            if ((compSMbo < 3) && ((linkptemp.idioma.equals("english")) || (linkptemp.qualitatVideo.equals("Hd 1080")))) {
                                linkptemp.assignLink("https://www.plusdede.com" + linkap);
                                this.linksp.add(linkptemp);
                                compSMbo++;
                            } else if (compSM < 2) {
                                linkptemp.assignLink("https://www.plusdede.com" + linkap);
                                this.linksp.add(linkptemp);
                                compSM++;
                            }
                        }else if (linkptemp.server.equals("powvideo")) {
                            if ((compPVbo < 10) && ((linkptemp.idioma.equals("english")) || (linkptemp.qualitatVideo.equals("Hd 1080")))) {
                                linkptemp.assignLink("https://www.plusdede.com" + linkap);
                                this.linksp.add(linkptemp);
                                compPVbo++;
                            } else if (compPV < 2) {
                                linkptemp.assignLink("https://www.plusdede.com" + linkap);
                                this.linksp.add(linkptemp);
                                compPV++;
                            }
                        } else if(linkptemp.server.isEmpty()){}
                        else if ((altresbo < 10) && ((linkptemp.idioma.equals("english")) || (linkptemp.qualitatVideo.equals("Hd 1080")))) {
                            linkptemp.assignLink("https://www.plusdede.com" + linkap);
                            this.linksp.add(linkptemp);
                            altres++;
                        }
                        else if (altres < 2) {
                            linkptemp.assignLink("https://www.plusdede.com" + linkap);
                            this.linksp.add(linkptemp);
                            altres++;
                        }
                        //linkptemp.agregaElements(enllas.get(i));
                        //this.linksp.add(linkptemp);
                        //this.linksp.get(i).assignLink("https://www.plusdede.com" + linkap);
                        //System.out.println(this.linksp.get(i).link);

                    }



                }

            }

            public void ompleTemporada(Element dades){

                Elements cap = dades.select("ul[data-season]>li:not(.season-header)");
                episodes = new ArrayList<Episodip>();
                Episodip eptemp;
                for (int i = 0; i<cap.size(); i++) {
                    eptemp = new Episodip("https://www.plusdede.com"+cap.select("a").get(i).attr("data-href"));
                    eptemp.omple(cap.get(i));
                    episodes.add(eptemp);
                    //System.out.println(eptemp.link);
                }



            }

            public void buscaTempEmbed(Element dades){
                Elements cap = dades.select("ul[data-season]>li:not(.season-header)");
                episodes = new ArrayList<Episodip>();
                Episodip eptemp;
                for (int i = 0; i<cap.size(); i++) {
                    eptemp = new Episodip("https://www.plusdede.com"+cap.select("a").get(i).attr("data-href"));
                    eptemp.teEmbed();
                    //System.out.println(eptemp.link);
                }
            }

        }

        /*public Serie exportaSerie(String url){

            return ;
        }*/

    }

    public String assignLink(String origen){
        String link = "";


        try {
            HttpURLConnection con = (HttpURLConnection)(new URL( origen ).openConnection());            con.setInstanceFollowRedirects(false);
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
            con.setRequestProperty("cookie", "__cfduid=d9635dc21ffc5c13d6067690e9a3161841512336384; plusdede-sess=eyJpdiI6IndHN2hNTmwwa2FjWWNCSHFaNE9IYWc9PSIsInZhbHVlIjoidnpkT1JVNnVcL3ZXSFBYRDBGSWhLSk9lSGpBRTRGYnBHUUMyTUpWbm9XbmJsU1NCOU5XOFBZNHRrenpucW9XYk0iLCJtYWMiOiI5ZWM1N2U1M2Y0ZmYyNGVhYjAyYTFiZjc1YzY0ZjNmMGNjYjU4YzVjNjE1N2U2MGQ1NzYyNDkyMjgwYzQ0MWFkIn0%3D; _ga=GA1.2.2071938042.1512336388; PHPSESSID=i1i4pamgnvbmm49q10hbude2r6; rhid_c=44917852218; _gid=GA1.2.1826374211.1517514534; popshown=1; _gat=1; XSRF-TOKEN=eyJpdiI6ImgwWDNpblNtd3p6ZTh2SFA5eXZSRWc9PSIsInZhbHVlIjoiangwczZuM0hEV1BTa3AyVmdjWEowSkwrc3hjdzJ5UG1naExKZXpjZ0lQQWJlT0I4c1k1d0I1eEp3Z1R0WlJMa29pQnp1WElJSWZha1dWVkVycE9NaWc9PSIsIm1hYyI6IjU4YjRiYzNlZWM4NjIzNmRjZmNhOTQ5ODNjN2JkMzg3YzYyMDBjODU5ODAzZTQzZTQxNDUyM2M5ZmQwMGUyNWIifQ%3D%3D; cakephp_session=eyJpdiI6Ilk4MlhYUm5BaHlVTzVWNzhPc1FieEE9PSIsInZhbHVlIjoiNDVLN0U2R3NiOHZtOG5rbjB3bm5pajgzMEZINnlOdExnXC9UR3AyXC9LZ0xHVVRIS3hjNUZydEVcL1hwRWhVclNzWklBUE4yZmNMXC93b002djU0Tk9UMWRBPT0iLCJtYWMiOiJkM2EzNDcxYTg3OWQ0MDVlOWY1YTRkZDc3YzRmNWRhZDg1ZWI5YWYyNjY3N2M3OThkMjJhODM0MzQxOGE5MzAyIn0%3D");
            con.connect();
            //con.getInputStream();
            int resCode = con.getResponseCode();
            System.out.println( resCode );
            link = con.getHeaderField( "Location" );
            System.out.println( link );

        } catch (Exception e) {
            try {
                System.out.println("Dormint 5 minuts");
                try {
                    Thread.sleep(300000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                System.out.println("despertat");
                HttpURLConnection con = (HttpURLConnection)(new URL( origen ).openConnection());            con.setInstanceFollowRedirects(false);
                con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
                con.setRequestProperty("cookie", "__cfduid=d9635dc21ffc5c13d6067690e9a3161841512336384; plusdede-sess=eyJpdiI6IndHN2hNTmwwa2FjWWNCSHFaNE9IYWc9PSIsInZhbHVlIjoidnpkT1JVNnVcL3ZXSFBYRDBGSWhLSk9lSGpBRTRGYnBHUUMyTUpWbm9XbmJsU1NCOU5XOFBZNHRrenpucW9XYk0iLCJtYWMiOiI5ZWM1N2U1M2Y0ZmYyNGVhYjAyYTFiZjc1YzY0ZjNmMGNjYjU4YzVjNjE1N2U2MGQ1NzYyNDkyMjgwYzQ0MWFkIn0%3D; _ga=GA1.2.2071938042.1512336388; PHPSESSID=i1i4pamgnvbmm49q10hbude2r6; rhid_c=44917852218; _gid=GA1.2.1826374211.1517514534; popshown=1; _gat=1; XSRF-TOKEN=eyJpdiI6ImgwWDNpblNtd3p6ZTh2SFA5eXZSRWc9PSIsInZhbHVlIjoiangwczZuM0hEV1BTa3AyVmdjWEowSkwrc3hjdzJ5UG1naExKZXpjZ0lQQWJlT0I4c1k1d0I1eEp3Z1R0WlJMa29pQnp1WElJSWZha1dWVkVycE9NaWc9PSIsIm1hYyI6IjU4YjRiYzNlZWM4NjIzNmRjZmNhOTQ5ODNjN2JkMzg3YzYyMDBjODU5ODAzZTQzZTQxNDUyM2M5ZmQwMGUyNWIifQ%3D%3D; cakephp_session=eyJpdiI6Ilk4MlhYUm5BaHlVTzVWNzhPc1FieEE9PSIsInZhbHVlIjoiNDVLN0U2R3NiOHZtOG5rbjB3bm5pajgzMEZINnlOdExnXC9UR3AyXC9LZ0xHVVRIS3hjNUZydEVcL1hwRWhVclNzWklBUE4yZmNMXC93b002djU0Tk9UMWRBPT0iLCJtYWMiOiJkM2EzNDcxYTg3OWQ0MDVlOWY1YTRkZDc3YzRmNWRhZDg1ZWI5YWYyNjY3N2M3OThkMjJhODM0MzQxOGE5MzAyIn0%3D");
                con.connect();
                int resCode = con.getResponseCode();
                System.out.println( resCode );
                link = con.getHeaderField( "Location" );
                System.out.println( link );

            } catch (IOException e1) {
                e1.printStackTrace();
            }
            //con.getInputStream();

        }


        return link;
    }
}
