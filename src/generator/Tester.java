package generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tester {
    private String singleBitTest, seriesTest, longSeriesTest, pokerTest, bitString;
    private int countZero = 0;
    private int countOne = 0;
    private int longOneSeries = 0;
    private int longZeroSeries = 0;
    private double pokerResult = 0.0;
    private HashMap<Integer, Integer> zeroSeries = new HashMap<>() {{
        put(1, 0);
        put(2, 0);
        put(3, 0);
        put(4, 0);
        put(5, 0);
        put(6, 0);
    }};
    private HashMap<Integer, Integer> oneSeries = new HashMap<>() {{
        put(1, 0);
        put(2, 0);
        put(3, 0);
        put(4, 0);
        put(5, 0);
        put(6, 0);
    }};
    private HashMap<String, Integer> pokerSegments = new HashMap<>() {{
        put("0000", 0);
        put("0001", 0);
        put("0010", 0);
        put("0011", 0);
        put("0100", 0);
        put("0101", 0);
        put("0110", 0);
        put("0111", 0);
        put("1000", 0);
        put("1001", 0);
        put("1010", 0);
        put("1011", 0);
        put("1100", 0);
        put("1101", 0);
        put("1110", 0);
        put("1111", 0);
    }};

    public Tester() {
    }

    //poker test
    public void pTest() {
        StringBuilder sb = new StringBuilder();
        String segment;
        for (int i = 0; i < bitString.length(); i += 4) {
            sb.append(bitString.charAt(i));
            sb.append(bitString.charAt(i+1));
            sb.append(bitString.charAt(i+2));
            sb.append(bitString.charAt(i+3));
            segment = sb.toString();
            sb.delete(0,sb.length());
            switch (segment) {
                case "0000":
                    pokerSegments.put(segment, pokerSegments.get(segment) + 1);
                    break;
                case "0001":
                    pokerSegments.put(segment, pokerSegments.get(segment) + 1);
                    break;
                case "0010":
                    pokerSegments.put(segment, pokerSegments.get(segment) + 1);
                    break;
                case "0011":
                    pokerSegments.put(segment, pokerSegments.get(segment) + 1);
                    break;
                case "0100":
                    pokerSegments.put(segment, pokerSegments.get(segment) + 1);
                    break;
                case "0101":
                    pokerSegments.put(segment, pokerSegments.get(segment) + 1);
                    break;
                case "0110":
                    pokerSegments.put(segment, pokerSegments.get(segment) + 1);
                    break;
                case "0111":
                    pokerSegments.put(segment, pokerSegments.get(segment) + 1);
                    break;
                case "1000":
                    pokerSegments.put(segment, pokerSegments.get(segment) + 1);
                    break;
                case "1001":
                    pokerSegments.put(segment, pokerSegments.get(segment) + 1);
                    break;
                case "1010":
                    pokerSegments.put(segment, pokerSegments.get(segment) + 1);
                    break;
                case "1011":
                    pokerSegments.put(segment, pokerSegments.get(segment) + 1);
                    break;
                case "1100":
                    pokerSegments.put(segment, pokerSegments.get(segment) + 1);
                    break;
                case "1101":
                    pokerSegments.put(segment, pokerSegments.get(segment) + 1);
                    break;
                case "1110":
                    pokerSegments.put(segment, pokerSegments.get(segment) + 1);
                    break;
                case "1111":
                    pokerSegments.put(segment, pokerSegments.get(segment) + 1);
                    break;
            }
        }
        double segmentsSquare = 0.0; // suma wszystkich(ilość wystąpień segmentu podniesiona do kwadratu)
        double el;
        for(Map.Entry element : pokerSegments.entrySet()){
            el = (int)element.getValue();
            el = Math.pow(el,2);
            segmentsSquare = segmentsSquare+el;
        }

        pokerResult = (0.0032*segmentsSquare)-5000;
        if(pokerResult>2.16 && pokerResult<46.17){
            pokerTest="powodzenie";
        }
        else {
            pokerTest="niepowodzenie";
        }
    }

    //long series test
    public void longSTest() {
        int countOneSeries = 0;
        int countZeroSeries = 0;

        StringBuilder sb = new StringBuilder();
        sb.append(bitString);
        sb.append("s");
        bitString = sb.toString();

        int i = 0;
        while (i < bitString.length() - 1) {
            if (i < bitString.length() - 1) {
                while (bitString.charAt(i) == '1') {
                    countOneSeries++;
                    i++;
                }
            }
            if (i < bitString.length() - 1) {
                while (bitString.charAt(i) == '0') {
                    countZeroSeries++;
                    i++;
                }
            }
            if (countOneSeries > longOneSeries) {
                longOneSeries = countOneSeries;
            }
            if (countZeroSeries > longZeroSeries) {
                longZeroSeries = countZeroSeries;
            }
            countZeroSeries = 0;
            countOneSeries = 0;
        }
        bitString = bitString.substring(0, bitString.length() - 1);

        if (longOneSeries < 26 && longZeroSeries < 26) {
            longSeriesTest = "powodzenie";
        } else {
            longSeriesTest = "niepowodzenie";
        }
    }

    //series test
    public void sTest() {
        int countOneSeries = 0;
        int countZeroSeries = 0;

        StringBuilder sb = new StringBuilder();
        sb.append(bitString);
        sb.append("e");
        bitString = sb.toString();
        int i = 0;
        while (i < bitString.length() - 1) {
            if (i < bitString.length() - 1) {
                while (bitString.charAt(i) == '1') {
                    countOneSeries++;
                    i++;
                }
            }
            if (i < bitString.length() - 1) {
                while (bitString.charAt(i) == '0') {
                    countZeroSeries++;
                    i++;
                }
            }
            int tmp1;
            switch (countOneSeries) {
                case 0:
                    break;
                case 1:
                    tmp1 = oneSeries.get(1);
                    oneSeries.put(1, tmp1 + 1);
                    break;
                case 2:
                    tmp1 = oneSeries.get(2);
                    oneSeries.put(2, tmp1 + 1);
                    break;
                case 3:
                    tmp1 = oneSeries.get(3);
                    oneSeries.put(3, tmp1 + 1);
                    break;
                case 4:
                    tmp1 = oneSeries.get(4);
                    oneSeries.put(4, tmp1 + 1);
                    break;
                case 5:
                    tmp1 = oneSeries.get(5);
                    oneSeries.put(5, tmp1 + 1);
                    break;
                case 6:
                    tmp1 = oneSeries.get(6);
                    oneSeries.put(6, tmp1 + 1);
                    break;
                default:
                    tmp1 = oneSeries.get(6);
                    oneSeries.put(6, tmp1 + 1);
                    break;
            }

            int tmp2;
            switch (countZeroSeries) {
                case 0:
                    break;
                case 1:
                    tmp2 = zeroSeries.get(1);
                    zeroSeries.put(1, tmp2 + 1);
                    break;
                case 2:
                    tmp2 = zeroSeries.get(2);
                    zeroSeries.put(2, tmp2 + 1);
                    break;
                case 3:
                    tmp2 = zeroSeries.get(3);
                    zeroSeries.put(3, tmp2 + 1);
                    break;
                case 4:
                    tmp2 = zeroSeries.get(4);
                    zeroSeries.put(4, tmp2 + 1);
                    break;
                case 5:
                    tmp2 = zeroSeries.get(5);
                    zeroSeries.put(5, tmp2 + 1);
                    break;
                case 6:
                    tmp2 = zeroSeries.get(6);
                    zeroSeries.put(6, tmp2 + 1);
                    break;
                default:
                    tmp2 = zeroSeries.get(6);
                    zeroSeries.put(6, tmp2 + 1);
                    break;
            }

            countZeroSeries = 0;
            countOneSeries = 0;
        }
        bitString = bitString.substring(0, bitString.length() - 1);

        if (zeroSeries.get(1) < 2685 && zeroSeries.get(1) > 2315 &&
                zeroSeries.get(2) < 1386 && zeroSeries.get(2) > 1114 &&
                zeroSeries.get(3) < 723 && zeroSeries.get(3) > 527 &&
                zeroSeries.get(4) < 384 && zeroSeries.get(4) > 240 &&
                zeroSeries.get(5) < 209 && zeroSeries.get(5) > 103 &&
                zeroSeries.get(6) < 209 && zeroSeries.get(6) > 103 &&
                oneSeries.get(1) < 2685 && oneSeries.get(1) > 2315 &&
                oneSeries.get(2) < 1386 && oneSeries.get(2) > 1114 &&
                oneSeries.get(3) < 723 && oneSeries.get(3) > 527 &&
                oneSeries.get(4) < 384 && oneSeries.get(4) > 240 &&
                oneSeries.get(5) < 209 && oneSeries.get(5) > 103 &&
                oneSeries.get(6) < 209 && oneSeries.get(6) > 103) {
            seriesTest = "powodzenie";
        } else {
            seriesTest = "niepowodzenie";
        }
    }

    //single bit test
    public void sBitTest() {
        if (countOne > 9725 && countOne < 10275) {
            singleBitTest = "powodzenie";
        } else {
            singleBitTest = "niepowodzenie";
        }
    }

    public void counter() {
        for (int i = 0; i < bitString.length(); i++) {
            if (bitString.charAt(i) == '1') {
                countOne++;
            } else {
                countZero++;
            }
        }
    }

    public String getSingleBitTest() {
        return singleBitTest;
    }

    public void setSingleBitTest(String singleBitTest) {
        this.singleBitTest = singleBitTest;
    }

    public String getSeriesTest() {
        return seriesTest;
    }

    public void setSeriesTest(String seriesTest) {
        this.seriesTest = seriesTest;
    }

    public String getLongSeriesTest() {
        return longSeriesTest;
    }

    public void setLongSeriesTest(String longSeriesTest) {
        this.longSeriesTest = longSeriesTest;
    }

    public String getPokerTest() {
        return pokerTest;
    }

    public void setPokerTest(String pokerTest) {
        this.pokerTest = pokerTest;
    }

    public String getBitString() {
        return bitString;
    }

    public void setBitString(String bitString) {
        this.bitString = bitString;
    }

    public int getCountOne() {
        return countOne;
    }

    public int getCountZero() {
        return countZero;
    }

    public HashMap<Integer, Integer> getZeroSeries() {
        return zeroSeries;
    }

    public void setZeroSeries(HashMap<Integer, Integer> zeroSeries) {
        this.zeroSeries = zeroSeries;
    }

    public HashMap<Integer, Integer> getOneSeries() {
        return oneSeries;
    }

    public void setOneSeries(HashMap<Integer, Integer> oneSeries) {
        this.oneSeries = oneSeries;
    }

    public int getLongOneSeries() {
        return longOneSeries;
    }

    public int getLongZeroSeries() {
        return longZeroSeries;
    }

    public double getPokerResult() {
        return pokerResult;
    }
}
