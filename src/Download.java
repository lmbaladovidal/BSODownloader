import java.awt.*;
import java.net.*;
import java.io.*;
/**
 * Created by Usuario on 28/6/2016.
 */
public class Download {
    private int acc = 0;
    private int indice = 1;

    public void setAcc (int acc){this.acc = acc;}
    public void setIndice (int indice){this.indice = indice;}

    public int getAcc() {return acc;}

    public int getIndice() {return indice;}

    public void leer(Album album){
        BufferedReader br = null;
        try {
            //br = new BufferedReader(new FileReader(album.getTitle()+".txt"));
            //Directorio destino para las descargas
            indice = 1;
            album.setRuta("descargas/"+album.getTitle());
            File dir = new File(album.getRuta());
            if (!dir.exists()){
                if (!dir.mkdir()){
                    System.out.println("no se pudo crear la carpeta de destino");
                    return; // no se pudo crear la carpeta de destino
                }
            }else{
                System.out.println("existe");
                return;
            }
            for  (Song cancion : album.getListaSong()){
                //Crea el directorio de destino en caso de que no exista
                descargar(album,cancion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void descargar(Album album, Song song) throws IOException {

        //Creamos el archivo destino, en caso de existir lo elimina:
        File file = new File(album.getRuta(),song.getNombre());
        if (file.exists())
            return;
        //Establece la conexion con la url mediante una clase URLConnection:
        URLConnection conn = new URL(song.getLink()).openConnection();
        int size = conn.getContentLength();
        conn.connect();
        System.out.println("\nempezando descarga: \n");
        System.out.println(">> URL: " + song.getLink());
        System.out.println(">> Nombre: " + song.getNombre());
        System.out.println(">> tamaño: " + size + " bytes");
        System.out.println(">> progreso "+ indice++ +"/"+getAcc());
        //Abrimos los Stream:
        InputStream in = conn.getInputStream();
        OutputStream out = new FileOutputStream(file);
        // Vamos leyendo de a un byte por vez y los escribe en un archivo. El -1 significa que se llego al final.
        int b = 0;
        while (b != -1) {
            b = in.read();
            if (b != -1)
                out.write(b);
        }
        //Cerramos los streams:
        out.close();
        in.close();
        System.out.println("termine");
    }
}

