import java.util.ArrayList;

/**
 * Created by Usuario on 3/7/2016.
 */
public class Juego {

    public ArrayList<Album> listaAlbum = new ArrayList<>();
    public String title;
    //SETTERS
    public void setListaAlbum(ArrayList<Album> listaAlbum) {this.listaAlbum = listaAlbum;}
    public void setTitle(String title) {this.title = title;}
    //GETTERS
    public ArrayList<Album> getListaAlbum() {return listaAlbum;}
    public String getTitle() {return title;}

    public Juego(){

    }
}

