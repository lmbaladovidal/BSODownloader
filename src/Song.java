/**
 * Created by Usuario on 3/7/2016.
 */
public class Song {

    public String nombre;
    public String ruta;
    public String link;
    //SETTERS
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setRuta(String ruta) {this.ruta = ruta;}

    @Override
    public String toString() {
        return "Song{" +
                "nombre='" + nombre + '\'' +
                ", ruta='" + ruta + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    public void setLink(String link) {this.link = link;}


    //GETTERS
    public String getNombre() {return nombre;}
    public String getRuta() {return ruta;}
    public String getLink() {return link;}


    public static void Song(String[] args){

    }
}
