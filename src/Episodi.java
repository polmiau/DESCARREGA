import com.dropbox.core.DbxException;
import com.dropbox.core.v1.DbxClientV1;
import com.dropbox.core.v2.DbxClientV2;
import com.google.gson.Gson;
import sun.misc.IOUtils;

import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * Created by Pol on 05/02/2017.
 */
public class Episodi extends Serie {

    public int Episodi;
    public String Titol;
    public String LinkO;
    public String LinkGDolent;
    public String LinkGD;
    public String idOL;
    public String LinkDropbox;
    public String nom;


    public boolean comprovaLinkOP()  {

        String[] id= new String[13];
        try {
            id= LinkO.split("/");
            System.out.println(id[4]);
        } catch (java.lang.ArrayIndexOutOfBoundsException e){
            return false;
        } catch (NullPointerException e){
            return false;
        }

        Openload resposta = new Openload();
        try {
           URL url = new URL("https://api.openload.co/1/file/getsplash?login=ed76f3a39b1bee9d&key=jFtKoWPw&file="+id[4]);
           InputStreamReader reader = new InputStreamReader(url.openStream());
            resposta = new Gson().fromJson(reader, Openload.class);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (resposta.status==(200))
            return true;
        else
            return false;


    }

    public void obreOL(){


        if (comprovaLinkOP()) {

            try {
                Desktop desktop = java.awt.Desktop.getDesktop();
                URI oURL = new URI(LinkO);//el encoder es per posar + en espais
                desktop.browse(oURL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else
            try {
                if  (!LinkGD.equals(null)){

                    copiaOL();

            }


        } catch (NullPointerException e){
                System.out.println("NO GD");
            }


    }


    public void copiaOL(){

        OLRemoteP resposta = new OLRemoteP();
        try {

        URL url = new URL("https://api.openload.co/1/remotedl/add?login=ed76f3a39b1bee9d&key=jFtKoWPw&url="+LinkGD);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            System.out.println(url);
            resposta = new Gson().fromJson(reader, OLRemoteP.class);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        idOL=resposta.result.id;


    }

    public void comprovaEstat(){

        IOUtils utils = new IOUtils();
        String caca="";
        Scanner s =new Scanner(caca);

        try {
            URL url = new URL("https://api.openload.co/1/remotedl/status?login=ed76f3a39b1bee9d&key=jFtKoWPw&id="+idOL);
            InputStreamReader reader = new InputStreamReader(url.openStream());
            s = new Scanner(reader).useDelimiter("\\A");


        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){}

        try {
            String result = s.hasNext() ? s.next() : "";

            String[] link = result.split("\"");
            String linkraro=link[link.length -2];
            String linkBo=linkraro.replaceAll("\\\\", "");
            System.out.println(linkBo);
            if (linkBo.length()>10){
                LinkO=linkBo;
                idOL=null;}

        }catch (IndexOutOfBoundsException e){
            System.out.println("L'episodi "+ Episodi+"encara no s'ha carregat");
        }


    }

    public String passaLinkBo(String link){
        //String [] linkCanviar= link.split("\\");

        return link;

    }

    public void canviaGD(){
        String[] link= new String[2];
        link = LinkGDolent.split("=");

        LinkGD= "https://drive.google.com/file/d/"+link[1]+"/view?usp=sharing";


    }

    public void canviaDropbox(String nom,DbxClientV2 client) throws DbxException{

        client.files().saveUrl("/"+nom+".mp4",LinkDropbox);
        LinkDropbox=client.sharing().createSharedLinkWithSettings("/"+nom+".mp4").getUrl();

    }



}


