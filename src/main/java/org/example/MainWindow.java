package org.example;

import io.qt.core.QFile;
import io.qt.core.QResource;
import io.qt.core.Qt;
import io.qt.widgets.QApplication;
import io.qt.widgets.QMainWindow;
import io.qt.widgets.QPushButton;
import io.qt.widgets.QWidget;
import io.qt.widgets.tools.QUiLoader;

import java.math.BigInteger;
import java.nio.Buffer;
import java.util.List;

public class MainWindow extends QMainWindow {
    private List<BigInteger> message;
    private QPushButton firstTypeButton;
    private QPushButton secondTypeButton;
    public MainWindow() {
        QFile file = new QFile("./UIs/untitled.ui");
        file.open(QFile.OpenModeFlag.ReadOnly);
        QUiLoader loader = new QUiLoader();
        QWidget ui = loader.load(file);
        file.close();
        this.setCentralWidget(ui);
        firstTypeButton = ui.findChild(QPushButton.class, "firstTypeButton");
        secondTypeButton = ui.findChild(QPushButton.class, "secondTypeButton");
        firstTypeButton.clicked.connect(this, "firstTypeButton()");
        secondTypeButton.clicked.connect(this, "secondTypeButton()");
    }
    private void firstTypeButton() {
        FirstTypochek firstTypochek = new FirstTypochek();
        firstTypochek.isMessageSend.connect(this,"POHUIMNE(List)");
        firstTypochek.exec();
    }

    private void secondTypeButton() {
        SecondTypochek secondTypochek = new SecondTypochek(message);
        secondTypochek.exec();
    }

    public void POHUIMNE(List<BigInteger> message) {
        this.message = message;
    }

    public static void main(String[] args) {
        QResource.addClassPath("classpath:/UIs");
        QApplication.setAttribute(Qt.ApplicationAttribute.AA_ShareOpenGLContexts);
        QApplication.initialize(args);

        MainWindow mainWindow = new MainWindow();
        mainWindow.show();

        QApplication.exec();
        QApplication.shutdown();
    }
}
