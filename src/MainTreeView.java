import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainTreeView extends Application {

    Stage window;
    TreeView<String> tree;
    private Juego game;

    public static void main(String[] args) {
        new MainTreeView();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("JavaFX - thenewboston");

        TreeItem<String> root, megan;

        //Root
        root = new TreeItem<>();
        root.setExpanded(true);

        for(Album album:game.getListaAlbum()){
            megan = makeBranch(album.getTitle(), root);
            for (Song song : album.getListaSong()){
                makeBranch(song.getNombre(), megan);
            }
        }
        //Create the tree and hide the main Root
        tree = new TreeView<>(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
                    if (newValue != null)
                        System.out.println(newValue.getValue());
                });

        //Layout
        StackPane layout = new StackPane();
        layout.getChildren().add(tree);
        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();
    }

    //Create branches
    public TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }

    public MainTreeView(){
        game = new Juego();
        Main.setText("http://downloads.khinsider.com/uncharted");
        Main.call(game);
    }


}