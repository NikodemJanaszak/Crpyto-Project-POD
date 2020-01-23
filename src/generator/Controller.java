package generator;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Controller {

    public TextField registerLength = new TextField();
    public TextArea lfsrField = new TextArea();
    public TextArea shrinkField = new TextArea();
    public TextField stepField = new TextField();
    public TextField keyLengthField = new TextField();
    public TextField xorField = new TextField();
    public TextField countZeroField = new TextField();
    public TextField countOneField = new TextField();

    public TextArea keyCipherField = new TextArea();
    public TextArea userTextField = new TextArea();
    public TextArea cipherField = new TextArea();

    public TextArea testField = new TextArea();
    public TextField singleBitTestField = new TextField();
    public TextField testZeroField = new TextField();
    public TextField testOneField = new TextField();

    public TextField oneSeriesField = new TextField();
    public TextField zeroSeriesField = new TextField();
    public TextField seriesTestField = new TextField();

    public TextField longZeroField = new TextField();
    public TextField longOneField = new TextField();
    public TextField longSeriesTestField = new TextField();

    public TextField pokerResultField = new TextField();
    public TextField pokerTestField = new TextField();

    private LFSR reg;
    private StreamCipher streamCipher;
    private Stage stage;

    public Controller() throws IOException {

    }

    // Generator

    @FXML
    public void generateRegister(ActionEvent event) {
        // Generating register
        reg = new LFSR();
        reg.removeRegisterLength();
        reg.eraseRegister();
        lfsrField.setWrapText(true);
        reg.beginLFSR(Integer.parseInt(registerLength.getText()));
        lfsrField.setText(reg.getRegister());
    }

    @FXML
    public void keyLength(ActionEvent event) {
    }

    @FXML
    public void xorRegister(ActionEvent event) {
        // setting xor positions
        reg.setXorPosition(xorField.getText());
    }

    @FXML
    public void selfShrink(ActionEvent event) {
        // setting key length
        reg.setShrinkLength(keyLengthField.getText());

        // self shrinking
        shrinkField.setWrapText(true);
        reg.selfShrink();
        shrinkField.setText(reg.getShrink());
        lfsrField.setText("");
        lfsrField.setText(reg.getRegister());
        reg.eraseRegister();
        countOneField.setText(String.valueOf(reg.getCountOne()));
        countZeroField.setText(String.valueOf(reg.getCountZero()));
    }

    @FXML
    public void clearShrink(ActionEvent event) {
        reg.clearShrink();
        reg.eraseCounters();
        shrinkField.setText(reg.getShrink());
    }

    // Szyfrator

    public void takeKey(ActionEvent event) {
        keyCipherField.setWrapText(true);
        keyCipherField.setText(shrinkField.getText());
    }


    // INFORMACJA każdy znak zapisywany jest na 8 bitach. Więc znaki takie jak
    // 'ą' które potrzebują 9 bitów nie mogą być tłumaczone i to wysypuje program
    // można to bardzo łatwo zmienić w metodach streamCipher.encrypText i streamCipher.decryptText
    // oraz sprawdzanie długości klucza w textEncode poniżej
    //  |
    //  v

    public void textEncode(ActionEvent event) {
        cipherField.setWrapText(true);
        userTextField.setWrapText(true);

        streamCipher = new StreamCipher();
        streamCipher.setKey(keyCipherField.getText()); //pobierz klucz z pola i zapisz w obiekcie
        streamCipher.setText(userTextField.getText()); // ustaw userText w obiekcie

        //sprawdzanie długości klucza
        if ((streamCipher.getText().length() * 8) > streamCipher.getKey().length()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uwaga!!!!!111");
            alert.setHeaderText(null);
            alert.setContentText("Klucz jest za krótki");

            alert.showAndWait();
        } else {
            streamCipher.encryptText();
            cipherField.setText(streamCipher.getCode()); //wypisz zaszyfrowane
        }
    }

    public void textDecode(ActionEvent event) {
        cipherField.setWrapText(true);
        userTextField.setWrapText(true);
        streamCipher = new StreamCipher();
        streamCipher.setKey(keyCipherField.getText()); //pobierz klucz z pola i zapisz w obiekcie
        streamCipher.setCode(cipherField.getText()); // ustaw szyfr w obiekcie

        streamCipher.decryptText();

        userTextField.setText(streamCipher.getText());
    }

    //Testy

    public void strFromGen(ActionEvent event) {
        testField.setWrapText(true);
        testField.setText(shrinkField.getText());
    }

    public void startTest(ActionEvent event) {
        Tester tester = new Tester();


        if (testField.getText() == null || testField.getText().length() != 20000) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Uwaga!!!!!111");
            alert.setHeaderText(null);
            alert.setContentText("Podaj prawidłową długość ciągu (20000 bitów)");
            alert.showAndWait();
        }
        tester.setBitString(testField.getText()); // ustaw pole ciągu do testowania w polu bitString w klasie tester

        // test pojedynczych bitów
        tester.counter();
        testOneField.setText(Integer.toString(tester.getCountOne()));
        testZeroField.setText(Integer.toString(tester.getCountZero()));
        tester.sBitTest();
        singleBitTestField.setText(tester.getSingleBitTest());

        //test serii
        tester.sTest();
        zeroSeriesField.setText(String.valueOf(tester.getZeroSeries()));
        oneSeriesField.setText(String.valueOf(tester.getOneSeries()));
        seriesTestField.setText(tester.getSeriesTest());

        //test długiej serii
        tester.longSTest();
        longOneField.setText(Integer.toString(tester.getLongOneSeries()));
        longZeroField.setText(Integer.toString(tester.getLongZeroSeries()));
        longSeriesTestField.setText(tester.getLongSeriesTest());

        //test pokerowy
        tester.pTest();
        pokerResultField.setText(Double.toString(tester.getPokerResult()));
        pokerTestField.setText(tester.getPokerTest());
    }

    public void openFileTest(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Wybierz plik .txt");
        File file = fileChooser.showOpenDialog(stage);

        String text;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            text = sb.toString();
        }
        String result = null;
        if ((text != null) && (text.length() > 0)) {
            result = text.substring(0, text.length() - 1);
        }
        testField.setWrapText(true);
        testField.setText(result);
    }
}
