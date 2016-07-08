/**
 * Created by Usuario on 3/7/2016.
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Teclita on 28/6/2016.
 */
public class Main {

    public static String text;

    public static void setText(String t) {text = t;}

    public String getText() {return text;}

    public static String reemplazar(String cadena){

        cadena = cadena.replace("/","0");
        cadena = cadena.replace(":","1");
        cadena = cadena.replace("*","2");
        cadena = cadena.replace("?","3");
        cadena = cadena.replace("<","4");
        cadena = cadena.replace(">","5");
        cadena = cadena.replace("|","6");
        return cadena;
    }

    public static  void escritura(Song song, Album album){
        try {
            File file = new File(album.getTitle()+".txt");
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(song.getLink()+"\r\n");
            bw.close();
            System.out.println("Listo capo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void call(Juego juego){
        Document meta;
        Document doc;
        Document doc2;
        String s;
        //Scanner enlace = new Scanner(System.in);
        Download descargar = new Download();
        //s = enlace.next();
        s = text;
        try {
            meta = Jsoup.connect(s).get();
            Elements links = meta.select("a[href]");
            juego.setTitle(s.substring(s.lastIndexOf("/")+1,s.length()));
            for (Element link : links) {
                String b = link.toString();
                if (b.contains("/album")){
                    Album album = new Album();
                    album.setLink(b);
                    juego.getListaAlbum().add(album);
                    // need http protocol
                    //System.out.println(album);
                    doc = Jsoup.connect(album.getLink().substring(album.getLink().indexOf('"')+1,album.getLink().lastIndexOf('"'))).get();
                    // get page title
                    //System.out.println(doc.title());
                    album.setTitle(reemplazar(doc.title().substring(0,album.getLink().indexOf('-')-1).trim()));
                    album.setTitle(reemplazar(album.getTitle()));
                    //System.out.println("title : " + album.getTitle());
                    // get all links
                    Elements links2 = doc.select("a[href]");
                    boolean flg = false;
                    for (Element link2 : links2) {
                        flg = !flg;
                        String a = link2.toString();
                        if (a.contains(".mp3") && flg) {
                            Song song = new Song();
                            int i = a.indexOf('"');
                            int f = a.lastIndexOf('"');
                            String url = a.substring(i+1 ,f );
                            // need http protocol
                            doc2 = Jsoup.connect(url).get();
                            Elements audios = doc2.select("audio");
                            String e =  audios.first().toString();
                            int g = e.indexOf("src=") + 5;
                            int h = e.indexOf("mp3") + 3;
                            song.setLink(e.substring(g , h));
                            song.setNombre(song.getLink().substring(song.getLink().lastIndexOf("/")+1,song.getLink().length()));
                            album.getListaSong().add(song);
                            System.out.println(song.getNombre());
                            //escritura(song,album);
                        }
                    }
                    //descargar.setAcc(album.getListaSong().size());
                    //descargar.leer(album);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}

