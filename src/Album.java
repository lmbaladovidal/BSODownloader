import java.util.ArrayList;

/**
 * Created by Usuario on 3/7/2016.
 */
public class Album {

    public ArrayList<Song> listaSong = new ArrayList<>();
    public String link;
    public String title;
    public String ruta;
    //SETTERS
    public  void setListaSong(ArrayList<Song> listaSong) {this.listaSong = listaSong;}
    public  void setLink(String link) {this.link = link;}
    public  void setTitle(String title) {this.title = title;}
    public void setRuta(String ruta) {this.ruta = ruta;}

    //GETTERS
    public String getLink() {return link;}
    public ArrayList<Song> getListaSong() {return listaSong;}
    public String getTitle() {return title;}
    public String getRuta() {return ruta;}

    public Album(){

    }

    @Override
    public String toString() {
        return "Album{" +
                "listaSong=" + listaSong +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
