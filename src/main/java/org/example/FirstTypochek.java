package org.example;

import io.qt.core.QFile;
import io.qt.widgets.*;
import io.qt.widgets.tools.QUiLoader;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FirstTypochek extends QDialog {
    private QLineEdit key;
    private QLineEdit message;
    private QPushButton sendMessage;
    public final Signal1<List<BigInteger>> isMessageSend = new Signal1<>();
    public FirstTypochek() {
        QFile file = new QFile("./UIs/sendMessage.ui");
        file.open(QFile.OpenModeFlag.ReadOnly);
        QUiLoader loader = new QUiLoader();
        QWidget ui = loader.load(file);
        file.close();
        QVBoxLayout layout = new QVBoxLayout(this);
        layout.addWidget(ui);
        key = ui.findChild(QLineEdit.class, "key");
        message = ui.findChild(QLineEdit.class, "message");
        sendMessage = ui.findChild(QPushButton.class, "sendMessage");
        sendMessage.clicked.connect(this, "sendMessage()");
    }

    private void sendMessage() {
        isMessageSend.emit(RSA.encrypt(message.text(), new BigInteger(key.text())));
    }


}
