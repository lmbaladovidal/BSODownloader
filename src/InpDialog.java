import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.*;

/**
 * ZetCode QtJambi tutorial
 *
 * This program shows an input
 * dialog
 *
 * @author jan bodnar
 * website zetcode.com
 * last modified March 2009
 */

public class InpDialog extends QWidget {

    QLineEdit edit;

    public InpDialog() {

        setWindowTitle("Input Dialog");

        initUI();

        move(400, 300);
        show();
    }


    public String initUI() {

        setGeometry(300, 300, 350, 80);

        QPushButton show = new QPushButton("Dialog", this);

        show.clicked.connect(this, "showDialog()");
        show.setFocusPolicy(Qt.FocusPolicy.NoFocus);

        show.move(20, 20);

        edit = new QLineEdit(this);
        edit.move(130, 22);
        return showDialog();
    }

    public String showDialog() {

        String text = QInputDialog.getText(this, "Input Dialog", "Ingrese el link");
        if (text!=null && !text.trim().isEmpty()) {
            Main main = new Main();
            main.setText(text);
            main.call();
            edit.setText(text);
        }
        return text;
    }

    public static void main(String[] args) {
        QApplication.initialize(args);
        new InpDialog();
        QApplication.execStatic();
    }
}
