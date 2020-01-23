package generator;

import java.util.ArrayList;
import java.util.BitSet;

public class StreamCipher {
    private String text, code = "", key = "";

    StreamCipher() {
    }

    public void encryptText() {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<Integer> textInt = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            textInt.add((int) text.charAt(i));
            String ascii = Integer.toBinaryString(textInt.get(i));
            while (ascii.length() < 8) {
                ascii = "0" + ascii;
            }
            stringBuilder.append(ascii);
        }
        code = stringBuilder.toString();
        code = xorBits(code);
    }


    public void decryptText() {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder textBuilder = new StringBuilder();
        text = xorBits(code);

        String ascii;
        int asciiDec;


        for (int i = 0; i < text.length(); i += 8) {
            for (int j = 0; j < 8; j++) {
                stringBuilder.append(text.charAt(j + i));
            }
            ascii = stringBuilder.toString();
            asciiDec = Integer.parseInt(ascii, 2);
            textBuilder.append((char) asciiDec);
            stringBuilder.setLength(0);
        }
        text = textBuilder.toString();
    }

    private String xorBits(String bits) {
        int j = 0;
        char b;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < bits.length(); i++) {
            b = xorBit(bits.charAt(i), key.charAt(j));
            stringBuilder.append(b);
            j++;
            if (j > key.length()) {
                j = 0;
            }
        }
        bits = stringBuilder.toString();
        return bits;
    }

    private char xorBit(char item, char key) {
        if (item == '0' && key == '0') {
            item = '0';
        } else if (item == '1' && key == '0') {
            item = '1';
        } else if (item == '0' && key == '1') {
            item = '1';
        } else if (item == '1' && key == '1') {
            item = '0';
        }
        return item;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
