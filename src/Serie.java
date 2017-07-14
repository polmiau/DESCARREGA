import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pol on 05/02/2017.
 */
public class Serie {

    public String nom;

    public List<Episodi> episodis;

    public Serie(){}

    public Serie(String nom){
        this.nom=nom;
    }


    public static Serie carregaSerie(String nom){

        Serie serie = new Serie(nom);
        try{

            Gson gson = new Gson();

            BufferedReader br = new BufferedReader(new FileReader(nom +".json"));
            serie =gson.fromJson(br, Serie.class);



        } catch (IOException e) {
            System.err.println("Hi ha hagut un problema al llegir el json");
        }

        return serie;

    }

    public void guardaSerie(String nom){
        Gson gson = new Gson();
        String textGson = gson.toJson(this);

        try {

            FileWriter writer = new FileWriter(nom+"1.json");
            writer.write(textGson);
            writer.close();

        } catch (IOException e) {
            System.err.println("Hi ha hagut un problema escrivint al fitxer json");

        }

        System.out.println("Desat en el fitxer!");

    }



}
