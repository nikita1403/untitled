package org.example;

import io.qt.core.QFile;
import io.qt.widgets.*;
import io.qt.widgets.tools.QUiLoader;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.List;

public class SecondTypochek extends QDialog {
    private QTextEdit message;
    private QPushButton decrypt;
    private QLineEdit key;
    private List<BigInteger> cryptMessage;
    public SecondTypochek(List<BigInteger> message) {
        QFile file = new QFile("./UIs/getMessage.ui");
        cryptMessage = message;
        file.open(QFile.OpenModeFlag.ReadOnly);
        QUiLoader loader = new QUiLoader();
        QWidget ui = loader.load(file);
        file.close();
        QVBoxLayout layout = new QVBoxLayout(this);
        layout.addWidget(ui);
        key = ui.findChild(QLineEdit.class, "key");
        decrypt = ui.findChild(QPushButton.class, "decrypt");
        this.message = ui.findChild(QTextEdit.class, "message");
        decrypt.clicked.connect(this, "showDecryptMessage()");
        showCryptMessage();
    }
    private void showCryptMessage() {
        cryptMessage.forEach(x -> message.append(x.toString()));
    }
    private void showDecryptMessage() {
        message.setText(RSA.decrypt(cryptMessage,new BigInteger(key.text())));
    }



}
