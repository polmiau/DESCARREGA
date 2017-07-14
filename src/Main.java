import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.sharing.CreateSharedLinkWithSettingsErrorException;
import com.dropbox.core.v2.users.FullAccount;
import org.json.JSONException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
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

    private static final String ACCESS_TOKEN = "5feiNFj3xyAAAAAAAAAFnd-gCxyPGhxJdBWGIpjhMtJxFUxXAth7FOUzO-Q_TUT8";


    public static void main(String args[]) throws DbxException {


        System.out.println("1.Dropbox        2.Gestiona Serie ");

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

               try {
                   mh.login();
               } catch (IOException e) {
                   e.printStackTrace();
               }
               try {
                    //download in the active directory
                   mh.download("https://mega.nz/#!obx1BbgB!vl7Ck3ZjSOcRs2AG-Ej_iTPrya0yKayrF6NBWdUq6DY");

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
       }







    }

}
