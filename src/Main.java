import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.sharing.CreateSharedLinkWithSettingsErrorException;
import com.dropbox.core.v2.users.FullAccount;
import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**
 * Created by Pol on 05/02/2017.
 */
public class Main {

    public static final String ACCESS_TOKEN = "9VkhRDz68iAAAAAAAAAFmCSUbCE_cGd5qWvWmrEhzCcmPa42GMzSbrfzZ4cmYJBo";


    public static void main(String args[]){
        System.out.println("opcio 6: pelis catala");
        Scanner scmenu = new Scanner(System.in);
        int menu = scmenu.nextInt();

        switch (menu) {


            case 1:
                System.out.println("Escriu link serie ");
                Scanner sclink = new Scanner(System.in);
                String link1 = sclink.nextLine();

                plusdedo pls = new plusdedo();
                plusdedo.Seriep serie = new plusdedo.Seriep(link1);
                serie.creaTemporades();
                break;
            case 2:
                /*System.out.println("Escriu INDEX");
        Scanner sclink= new Scanner(System.in);
        int index= sclink.nextInt();
       // String linkda = "https://www.plusdede.com/pelis/all?quality=1&first_filters=1";


        }

        /**/

                for (int index = 13; index < 100; index++) {

                    String linkda = "https://www.plusdede.com/pelis/all/" + index + "?quality=1&first_filters=1";
                    String cookie = "__cfduid=dff6640fd8af6444c69e2bb84b9117d571517479581; _ga=GA1.2.47025116.1517484811; plusdede-sess=eyJpdiI6InNlNUlOOWVBTVwvK25KSUVBMTBCYW1RPT0iLCJ2YWx1ZSI6InpQbW1NWVVKb0lKNjFVN09ZdlcyVGdLTGpqeUJlU1F0R055SnFmQ1NCdmR0MDZySm8rWFd0NjA2OERiVHU3RGUiLCJtYWMiOiI3NmI3YWJlZDZhNWFmNmI3MDJiY2ExMDIwZTljZmE5YWU1YWU3YjdmZGUzMmRlY2ZjYzM1NjBiZTg3Nzg2N2IwIn0%3D; _gid=GA1.2.282494337.1517589154; PHPSESSID=vsq9oc4ee53qv6020pnvra4in2; rhid_c=52236553653; XSRF-TOKEN=eyJpdiI6ImFLbnFcLzhlNXBVM01xYTgrd3ltMkxnPT0iLCJ2YWx1ZSI6ImlvXC9VOXhmN1c2WVVOR2lkZFZra2kzcVRRNzhWMXpGVVYxOXlOSTk1dUo3MkFaNHBGWXNYV2Y2RVdIWW0zWjd5UU5zV1pwNlBrc1Y5bFZBM1dJaG8zdz09IiwibWFjIjoiYzBjM2RjOTk0ZjA5NDM4NjRlNWM5ZTM2MmVmZDFjNWI4ZDRlMTY4ZTYzNDkyNzRjNGI0ODY5ZjQ3YTM2ZDY0YiJ9; cakephp_session=eyJpdiI6IjN6YVN2WjJGMU1OQnFYZDNYUmhlaEE9PSIsInZhbHVlIjoibXd2cFpMUnpOWndCV0F4aWhuT2tPVmlmdTlGY1BjeHg2V09KZ2pXelBGaWU5SFNUNGpFdkdXdHpsSXA5NW1xV3RYSVViYjIxOG9cL3FpYXdBT3k2MmlBPT0iLCJtYWMiOiIzNWU5ODdiMjJlNGY3NTRhYjFlY2EwZDQwYTU3MmYxYjA0M2Y2ODU3OWUxZDgwYTIyYzgyYzc2Y2U2MWZhYWU2In0%3D";
                    Document doc = null;
                    try {
                        Files.createDirectories(Paths.get("pelis/" + index));//CREA LA CARPETA
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    doc = connectDoc(linkda,doc,cookie);


                    Elements media = doc.getElementsByClass("media-container");
                    for (int q = 0; q < media.size(); q++) {
                        //System.out.println(media.get(q).getElementsByTag("a").get(5));
                        String elink = media.get(q).getElementsByTag("a").get(5).attr("href");
                        plusdedo.Pelip pelip = new plusdedo.Pelip(elink, index, q);

                        pelip.copiaPeli(elink);
                    }
                }
                break;

            case 3:

                /*int index = -1;
                int q =-1;

                String linkda = "https://www.plusdede.com/pelis/all/" + index + "?quality=1&first_filters=1";

                try {
                    Files.createDirectories(Paths.get("pelis/" + index));//CREA LA CARPETA
                    String cookie = "__cfduid=dff6640fd8af6444c69e2bb84b9117d571517479581; _ga=GA1.2.47025116.1517484811; plusdede-sess=eyJpdiI6InNlNUlOOWVBTVwvK25KSUVBMTBCYW1RPT0iLCJ2YWx1ZSI6InpQbW1NWVVKb0lKNjFVN09ZdlcyVGdLTGpqeUJlU1F0R055SnFmQ1NCdmR0MDZySm8rWFd0NjA2OERiVHU3RGUiLCJtYWMiOiI3NmI3YWJlZDZhNWFmNmI3MDJiY2ExMDIwZTljZmE5YWU1YWU3YjdmZGUzMmRlY2ZjYzM1NjBiZTg3Nzg2N2IwIn0%3D; _gid=GA1.2.282494337.1517589154; PHPSESSID=vsq9oc4ee53qv6020pnvra4in2; rhid_c=52236553653; XSRF-TOKEN=eyJpdiI6ImFLbnFcLzhlNXBVM01xYTgrd3ltMkxnPT0iLCJ2YWx1ZSI6ImlvXC9VOXhmN1c2WVVOR2lkZFZra2kzcVRRNzhWMXpGVVYxOXlOSTk1dUo3MkFaNHBGWXNYV2Y2RVdIWW0zWjd5UU5zV1pwNlBrc1Y5bFZBM1dJaG8zdz09IiwibWFjIjoiYzBjM2RjOTk0ZjA5NDM4NjRlNWM5ZTM2MmVmZDFjNWI4ZDRlMTY4ZTYzNDkyNzRjNGI0ODY5ZjQ3YTM2ZDY0YiJ9; cakephp_session=eyJpdiI6IjN6YVN2WjJGMU1OQnFYZDNYUmhlaEE9PSIsInZhbHVlIjoibXd2cFpMUnpOWndCV0F4aWhuT2tPVmlmdTlGY1BjeHg2V09KZ2pXelBGaWU5SFNUNGpFdkdXdHpsSXA5NW1xV3RYSVViYjIxOG9cL3FpYXdBT3k2MmlBPT0iLCJtYWMiOiIzNWU5ODdiMjJlNGY3NTRhYjFlY2EwZDQwYTU3MmYxYjA0M2Y2ODU3OWUxZDgwYTIyYzgyYzc2Y2U2MWZhYWU2In0%3D";
                    Document doc = Jsoup.connect(linkda).header("Cookie", cookie).get();
                    Elements media = doc.getElementsByClass("media-container");
                    for (int q = 12; q < media.size(); q++) {
                        //System.out.println(media.get(q).getElementsByTag("a").get(5));
                        String elink = media.get(q).getElementsByTag("a").get(5).attr("href");
                        plusdedo.Seriep seriep = new plusdedo.Seriep(elink, index, q);

                        pelip.copiaPeli(elink);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                System.out.println("Escriu link serie ");
                Scanner sclink2= new Scanner(System.in);
                String link2= sclink2.nextLine();

                plusdedo.Seriep seriep = new plusdedo.Seriep(link2);

                seriep.creaTemporades();
                seriep.guardaSerie();



                break;

            case 4:

                System.out.println("Escriu link serie ");
                Scanner sclink5= new Scanner(System.in);
                String link5= sclink5.nextLine();

                plusdedo.Seriep seriep2 = new plusdedo.Seriep(link5);
                seriep2.creaTemporadesEmbed();
               // seriep2.guardaSerie();
                break;
            case 5:
                for (int index = 1; index < 100; index++) {

                    String linkda = "https://www.plusdede.com/series/top/" + index + "?quality=1&first_filters=1";
                    Document doc = null;
                    String cookie = "__cfduid=dff6640fd8af6444c69e2bb84b9117d571517479581; _ga=GA1.2.47025116.1517484811; plusdede-sess=eyJpdiI6InNlNUlOOWVBTVwvK25KSUVBMTBCYW1RPT0iLCJ2YWx1ZSI6InpQbW1NWVVKb0lKNjFVN09ZdlcyVGdLTGpqeUJlU1F0R055SnFmQ1NCdmR0MDZySm8rWFd0NjA2OERiVHU3RGUiLCJtYWMiOiI3NmI3YWJlZDZhNWFmNmI3MDJiY2ExMDIwZTljZmE5YWU1YWU3YjdmZGUzMmRlY2ZjYzM1NjBiZTg3Nzg2N2IwIn0%3D; _gid=GA1.2.282494337.1517589154; PHPSESSID=vsq9oc4ee53qv6020pnvra4in2; rhid_c=52236553653; XSRF-TOKEN=eyJpdiI6ImFLbnFcLzhlNXBVM01xYTgrd3ltMkxnPT0iLCJ2YWx1ZSI6ImlvXC9VOXhmN1c2WVVOR2lkZFZra2kzcVRRNzhWMXpGVVYxOXlOSTk1dUo3MkFaNHBGWXNYV2Y2RVdIWW0zWjd5UU5zV1pwNlBrc1Y5bFZBM1dJaG8zdz09IiwibWFjIjoiYzBjM2RjOTk0ZjA5NDM4NjRlNWM5ZTM2MmVmZDFjNWI4ZDRlMTY4ZTYzNDkyNzRjNGI0ODY5ZjQ3YTM2ZDY0YiJ9; cakephp_session=eyJpdiI6IjN6YVN2WjJGMU1OQnFYZDNYUmhlaEE9PSIsInZhbHVlIjoibXd2cFpMUnpOWndCV0F4aWhuT2tPVmlmdTlGY1BjeHg2V09KZ2pXelBGaWU5SFNUNGpFdkdXdHpsSXA5NW1xV3RYSVViYjIxOG9cL3FpYXdBT3k2MmlBPT0iLCJtYWMiOiIzNWU5ODdiMjJlNGY3NTRhYjFlY2EwZDQwYTU3MmYxYjA0M2Y2ODU3OWUxZDgwYTIyYzgyYzc2Y2U2MWZhYWU2In0%3D";


                    doc = connectDoc(linkda, doc, cookie);

                    Elements media = doc.getElementsByClass("media-container");
                    for (int q = 0; q < media.size(); q++) {
                        //System.out.println(media.get(q).getElementsByTag("a").get(6));
                        String elink = media.get(q).getElementsByTag("a").get(6).attr("href");
                        plusdedo.Seriep seriep3 = new plusdedo.Seriep(elink);
                        System.out.println(seriep3.link);
                        seriep3.creaTemporadesEmbed();

                    }
                }

                break;

                case 6:

                    for (int index = 0; index < 20; index++) {

                        String linkda = "https://www.plusdede.com/pelis/all/" + index + "?language=7&first_filters=1";
                        String cookie = "__cfduid=dff6640fd8af6444c69e2bb84b9117d571517479581; _ga=GA1.2.47025116.1517484811; plusdede-sess=eyJpdiI6InNlNUlOOWVBTVwvK25KSUVBMTBCYW1RPT0iLCJ2YWx1ZSI6InpQbW1NWVVKb0lKNjFVN09ZdlcyVGdLTGpqeUJlU1F0R055SnFmQ1NCdmR0MDZySm8rWFd0NjA2OERiVHU3RGUiLCJtYWMiOiI3NmI3YWJlZDZhNWFmNmI3MDJiY2ExMDIwZTljZmE5YWU1YWU3YjdmZGUzMmRlY2ZjYzM1NjBiZTg3Nzg2N2IwIn0%3D; _gid=GA1.2.282494337.1517589154; PHPSESSID=vsq9oc4ee53qv6020pnvra4in2; rhid_c=52236553653; XSRF-TOKEN=eyJpdiI6ImFLbnFcLzhlNXBVM01xYTgrd3ltMkxnPT0iLCJ2YWx1ZSI6ImlvXC9VOXhmN1c2WVVOR2lkZFZra2kzcVRRNzhWMXpGVVYxOXlOSTk1dUo3MkFaNHBGWXNYV2Y2RVdIWW0zWjd5UU5zV1pwNlBrc1Y5bFZBM1dJaG8zdz09IiwibWFjIjoiYzBjM2RjOTk0ZjA5NDM4NjRlNWM5ZTM2MmVmZDFjNWI4ZDRlMTY4ZTYzNDkyNzRjNGI0ODY5ZjQ3YTM2ZDY0YiJ9; cakephp_session=eyJpdiI6IjN6YVN2WjJGMU1OQnFYZDNYUmhlaEE9PSIsInZhbHVlIjoibXd2cFpMUnpOWndCV0F4aWhuT2tPVmlmdTlGY1BjeHg2V09KZ2pXelBGaWU5SFNUNGpFdkdXdHpsSXA5NW1xV3RYSVViYjIxOG9cL3FpYXdBT3k2MmlBPT0iLCJtYWMiOiIzNWU5ODdiMjJlNGY3NTRhYjFlY2EwZDQwYTU3MmYxYjA0M2Y2ODU3OWUxZDgwYTIyYzgyYzc2Y2U2MWZhYWU2In0%3D";
                        Document doc = null;
                        try {
                            Files.createDirectories(Paths.get("pelis/" + index));//CREA LA CARPETA
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        doc = connectDoc(linkda,doc,cookie);


                        Elements media = doc.getElementsByClass("media-container");
                        for (int q = 0; q < media.size(); q++) {
                            //System.out.println(media.get(q).getElementsByTag("a").get(5));
                            String elink = media.get(q).getElementsByTag("a").get(5).attr("href");
                            plusdedo.Pelip pelip = new plusdedo.Pelip(elink, index, q);

                            pelip.copiaPeli(elink);
                        }
                    }
                    break;
            case 7:


                for (int index = 0; index < 20; index++) {

                    String linkda = "https://www.plusdede.com/series/all/" + index + "?first_filters=1";
                    String cookie = "__cfduid=dff6640fd8af6444c69e2bb84b9117d571517479581; _ga=GA1.2.47025116.1517484811; plusdede-sess=eyJpdiI6InNlNUlOOWVBTVwvK25KSUVBMTBCYW1RPT0iLCJ2YWx1ZSI6InpQbW1NWVVKb0lKNjFVN09ZdlcyVGdLTGpqeUJlU1F0R055SnFmQ1NCdmR0MDZySm8rWFd0NjA2OERiVHU3RGUiLCJtYWMiOiI3NmI3YWJlZDZhNWFmNmI3MDJiY2ExMDIwZTljZmE5YWU1YWU3YjdmZGUzMmRlY2ZjYzM1NjBiZTg3Nzg2N2IwIn0%3D; _gid=GA1.2.282494337.1517589154; PHPSESSID=vsq9oc4ee53qv6020pnvra4in2; rhid_c=52236553653; XSRF-TOKEN=eyJpdiI6ImFLbnFcLzhlNXBVM01xYTgrd3ltMkxnPT0iLCJ2YWx1ZSI6ImlvXC9VOXhmN1c2WVVOR2lkZFZra2kzcVRRNzhWMXpGVVYxOXlOSTk1dUo3MkFaNHBGWXNYV2Y2RVdIWW0zWjd5UU5zV1pwNlBrc1Y5bFZBM1dJaG8zdz09IiwibWFjIjoiYzBjM2RjOTk0ZjA5NDM4NjRlNWM5ZTM2MmVmZDFjNWI4ZDRlMTY4ZTYzNDkyNzRjNGI0ODY5ZjQ3YTM2ZDY0YiJ9; cakephp_session=eyJpdiI6IjN6YVN2WjJGMU1OQnFYZDNYUmhlaEE9PSIsInZhbHVlIjoibXd2cFpMUnpOWndCV0F4aWhuT2tPVmlmdTlGY1BjeHg2V09KZ2pXelBGaWU5SFNUNGpFdkdXdHpsSXA5NW1xV3RYSVViYjIxOG9cL3FpYXdBT3k2MmlBPT0iLCJtYWMiOiIzNWU5ODdiMjJlNGY3NTRhYjFlY2EwZDQwYTU3MmYxYjA0M2Y2ODU3OWUxZDgwYTIyYzgyYzc2Y2U2MWZhYWU2In0%3D";
                    Document doc = null;
                    try {
                        Files.createDirectories(Paths.get("series/" + index));//CREA LA CARPETA
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    doc = connectDoc(linkda,doc,cookie);

                    Elements media = doc.getElementsByClass("media-container");
                    for (int q = 0; q < media.size(); q++) {
                        //System.out.println(media.get(q).getElementsByTag("a").get(6));
                        String elink = media.get(q).getElementsByTag("a").get(6).attr("href");
                        plusdedo.Seriep seriep3 = new plusdedo.Seriep(elink,index,q);
                        System.out.println(seriep3.link);
                        seriep3.creaTemporades();
                        seriep3.guardaSerie();


                    }

                }
            case 8:
                try {
                    antic();
                } catch (DbxException e) {
                    e.printStackTrace();
                }
        }






/*
        System.out.println("Escriu link peli ");
        Scanner sclink= new Scanner(System.in);
        String link1= sclink.nextLine();

        plusdedo.Pelip pelip = new plusdedo.Pelip(link1);

        pelip.copiaPeli(link1);*/

    }

    static Document connectDoc(String linkda, Document doc, String cookie) {
        try {
           // Files.createDirectories(Paths.get("pelis/" + index));//CREA LA CARPETA
            doc = Jsoup.connect(linkda).header("Cookie", cookie).get();

        } catch (IOException e) {
            doc = plusdedo.Seriep.connectDoc2(cookie, doc, Jsoup.connect(linkda));
        }
        return doc;
    }

    public static void antic()  throws DbxException {
        System.out.println("1.Dropbox        2.Gestiona Serie   3.Url Remote    4.DL MEGA   5.ULDroppbox");

        Scanner numsc = new Scanner(System.in);
        int numero=numsc.nextInt();

       switch (numero){

           case 1:

               // Create Dropbox client
               DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
               DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

               System.out.println("Introdueix path:");

               Scanner scpath = new Scanner(System.in);
               String pathh= scpath.nextLine();
               // Get files and folder metadata from Dropbox root directory
               ListFolderResult result = client.files().listFolder(pathh);
               Serie dropSerie = new Serie();

               dropSerie.episodis = new ArrayList<Episodi>();

               int nep=0;

               while (true) {
                   for (Metadata metadata : result.getEntries()) {

                       Episodi temp =new Episodi();
                       System.out.println(metadata.getPathLower());
                      //System.out.println(client.sharing().createSharedLinkWithSettings(metadata.getPathDisplay()).getUrl());

                       try {
                           temp.LinkDropbox =client.sharing().createSharedLinkWithSettings(metadata.getPathDisplay()).getUrl();
                       }catch (CreateSharedLinkWithSettingsErrorException e){

                       }



                      temp.nom = metadata.getName();
                      //
                      dropSerie.episodis.add(temp);
                      System.out.println(dropSerie.episodis.get(nep).LinkDropbox);
                       nep++;
                   }

                   if (!result.getHasMore()) {
                       break;
                   }

                   result = client.files().listFolderContinue(result.getCursor());
               }

               System.out.println("Introdueix un nom pel json:");
               Scanner jscan = new Scanner(System.in);
               String nomjson =jscan.nextLine();

               dropSerie.guardaSerie(nomjson);



               /*client.files().saveUrl("/inu.mp4","https://www.dropbox.com/s/wyj3mo0v8ki63rc/inu.avi?dl=0");
               System.out.println(client.sharing().createSharedLinkWithSettings("/inu.mp4").getUrl());*/

               break;

           case 2:


               Scanner sc1= new Scanner(System.in);
               String nom= sc1.nextLine();
               Serie serie= Serie.carregaSerie(nom);

               boolean resposta=false;

               for (int i=0; i<serie.episodis.size(); i++) {

                   try {
                       serie.episodis.get(i).canviaGD();
                   }catch (java.lang.NullPointerException e){
                   }catch (ArrayIndexOutOfBoundsException e){}


                   try {
                       if(!serie.episodis.get(i).idOL.equals(null))
                           serie.episodis.get(i).comprovaEstat();

                   } catch (java.lang.NullPointerException e){
                       System.out.println(i+" idOP no trobat");

                   }






                   try {
                       int num= serie.episodis.get(i).Episodi;
                       if (serie.episodis.get(i).Episodi!=1+i){
                           serie.episodis.get(i).Episodi=1+i;
                       }

                   }catch (java.lang.NullPointerException e){
                       serie.episodis.get(i).Episodi=i+1;
                   }
                   System.out.println(serie.episodis.get(i).LinkO);
                   serie.episodis.get(i).obreOL();



               }

        /*for (int i=0; i<onepiece.episodis.size(); i++) {
            resposta = onepiece.episodis.get(i).comprovaLinkOP();
            System.out.println(resposta);
        }


       /* Scanner sc= new Scanner(System.in);
        int numero=sc.nextInt();

        serie.episodis.get(numero-1).obreOL();
*/

               serie.guardaSerie(nom);
               break;

           case 3:

               System.out.println("Escriu url ");
               Scanner sc21= new Scanner(System.in);
               String url= sc21.nextLine();

               DbxRequestConfig config2 = new DbxRequestConfig("dropbox/", "en_US");
               DbxClientV2 client2 = new DbxClientV2(config2, ACCESS_TOKEN);


               FullAccount account = client2.users().getCurrentAccount();
               System.out.println(account.getName().getDisplayName());

               ListFolderResult result2 = client2.files().listFolder("");
               while (true) {
                   for (Metadata metadata : result2.getEntries()) {
                       System.out.println(metadata.getPathLower());
                   }

                   if (!result2.getHasMore()) {
                       break;
                   }

                   result = client2.files().listFolderContinue(result2.getCursor());
               }

               System.out.println("Introdueix path:");

               Scanner scpath2 = new Scanner(System.in);
               String pathh2= scpath2.nextLine();

               client2.files().saveUrl(pathh2,url);

               System.out.println("Pujat");
               break;

           case 4:
               MegaHandler mh = new MegaHandler("user@mail.com", "password");

               System.out.println("Escriu un Link: ");

               Scanner sclink = new Scanner(System.in);
               String link = new String(sclink.nextLine());
               try {
                   mh.login();
               } catch (IOException e) {
                   e.printStackTrace();
               }
               try {
                    //download in the active directory
                   mh.download(link);

               } catch (InvalidAlgorithmParameterException e) {
                   e.printStackTrace();
               } catch (IOException e) {
                   e.printStackTrace();
               } catch (NoSuchAlgorithmException e) {
                   e.printStackTrace();
               } catch (JSONException e) {
                   e.printStackTrace();
               } catch (InvalidKeyException e) {
                   e.printStackTrace();
               } catch (NoSuchPaddingException e) {
                   e.printStackTrace();
               } catch (BadPaddingException e) {
                   e.printStackTrace();
               } catch (IllegalBlockSizeException e) {
                   e.printStackTrace();
               }
               break;


           case 5:



               System.out.println("Escriu path per pujar ");
               Scanner sc2f1= new Scanner(System.in);
               String pat= sc2f1.nextLine();

               DbxRequestConfig config3 = new DbxRequestConfig("dropbox/", "en_US");
               DbxClientV2 client3 = new DbxClientV2(config3, ACCESS_TOKEN);


              /*FullAccount account = client3.users().getCurrentAccount();
               System.out.println(account.getName().getDisplayName());

               ListFolderResult result2 = client2.files().listFolder("");
               while (true) {
                   for (Metadata metadata : result2.getEntries()) {
                       System.out.println(metadata.getPathLower());
                   }

                   if (!result2.getHasMore()) {
                       break;
                   }

                   result = client2.files().listFolderContinue(result2.getCursor());
               }*/

               System.out.println("Introdueix path:");

               Scanner scpath3 = new Scanner(System.in);
               String pathh3= scpath3.nextLine();


               try (InputStream in = new FileInputStream(pat)) {
                   FileMetadata metadata = client3.files().uploadBuilder(pathh3)
                           .uploadAndFinish(in);
               } catch (FileNotFoundException e) {
                   e.printStackTrace();
               } catch (IOException e) {
                   e.printStackTrace();
               }

               System.out.println("Pujat");
               break;
           case 6:
               config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
               client = new DbxClientV2(config, ACCESS_TOKEN);
               try (InputStream in = new FileInputStream("pelis/0/0-interstellar.json")) {
                   FileMetadata metadata = client.files().uploadBuilder("/pelis/0-interstellar.json")
                           .uploadAndFinish(in);
               } catch (FileNotFoundException e) {
                   e.printStackTrace();
               } catch (IOException e) {
                   e.printStackTrace();
               }
       }







    }

}
