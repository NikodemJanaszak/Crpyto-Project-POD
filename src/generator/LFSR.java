package generator;

import java.util.*;

public class LFSR {
    private int registerLength, stepByStep=0, shrinkLength, countZero=0, countOne=0;
    private String register, shrink="", toPrintStep;
    private ArrayList<Integer> registerInt = new ArrayList<>();
    private ArrayList<Integer> xorPosition = new ArrayList<>();

    public LFSR(){}

    public void beginLFSR(int length){
        this.registerLength = length;
        this.generateLFSR();
    }


    private void generateLFSR(){
        StringBuilder stringBuilder = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i<registerLength; i++){
            stringBuilder.append(r.nextInt(2));
        }
        this.register = stringBuilder.toString();

        for (int i=0; i<register.length();i++){
            registerInt.add(Integer.parseInt(String.valueOf(register.charAt(i))));
        }
    }

    public void setShrinkLength(String length){
        this.shrinkLength = Integer.parseInt(length);
    }

    public void setXorPosition(String toXOR){
        List<String> items = new ArrayList<String>(Arrays.asList(toXOR.split(",")));
        for(String s:items){
            xorPosition.add(Integer.parseInt(s)-1);
        }
        Collections.sort(xorPosition);
    }

    public void eraseRegister(){
        this.register = null;
    }


    public void selfShrink(){
        StringBuilder stringBuilder = new StringBuilder();
        while (shrink.length()<shrinkLength){
            int prevLast = registerInt.get(registerInt.size()-2);
            Integer last = registerInt.get(registerInt.size()-1);
            if(prevLast==1){
                stringBuilder.append(last.toString()); // if przedostatni == 1 to ostatni idzie jako element klucza
                if(last==1){
                    countOne++;
                }
                else if(last==0){
                    countZero++;
                }
            }

            if(xorPosition.isEmpty()){
                registerInt.add(0,last);
            }
            else {
                registerInt.add(0, xorMaker(last)); // dodaje na początku ostatni element, który przechodzi xorowanie
            }
            registerInt.remove(registerInt.size()-1); // usuwa ostatni element
            shrink = stringBuilder.toString();
        }

        StringBuilder sb = new StringBuilder();
        for (int i : registerInt){
            sb.append(i);
        }
        register=sb.toString();
        sb.setLength(0);
    }

    public int xorMaker(int item){
        for (int i=xorPosition.size()-1; i>=0; i--){
            int position = xorPosition.get(i);
            if(item==0 && registerInt.get(position)==0){
                item =0;
            }
            else if(item==0 && registerInt.get(position)==1){
                item=1;
            }
            else if(item==1 && registerInt.get(position)==0) {
                item=1;
            }
            else if(item==1 && registerInt.get(position)==1){
                item=0;
            }
        }
        return item;
    }

    /*public void stepShrink(){
        StringBuilder stringBuilder = new StringBuilder(shrink);
        if(stepByStep<register.length() && register.length()%2==0) {
            toPrintStep = String.valueOf(register.charAt(stepByStep)) + String.valueOf(register.charAt(stepByStep + 1));
            if (register.charAt(stepByStep) == '1') {
                stringBuilder.append(toPrintStep.charAt(1));
            }
            shrink = stringBuilder.toString();
            stepByStep += 2;
        }
        else if(stepByStep<register.length()-1 && register.length()%2==1) {
            toPrintStep = String.valueOf(register.charAt(stepByStep)) + String.valueOf(register.charAt(stepByStep + 1));
            if (register.charAt(stepByStep) == '1') {
                stringBuilder.append(toPrintStep.charAt(1));
            }
            shrink = stringBuilder.toString();
            stepByStep += 2;
        }
        else if(shrink.charAt(shrink.length()-1)=='c'){
            clearShrink();
        }
        else {
            stringBuilder.append(" Koniec");
            shrink = stringBuilder.toString();
        }
    }*/

    public void removeRegisterLength(){
        this.registerLength=0;
    }

    public void clearShrink(){
        shrink="";
        stepByStep=0;
    }

    public String getRegister() {
        return register;
    }

    public String getShrink() {
        return shrink;
    }

    public String getToPrintStep(){
        return toPrintStep;
    }

    public int getCountOne() {
        return countOne;
    }

    public int getCountZero() {
        return countZero;
    }

    public void setRegister(String reg){
        this.register = reg;
    }

    public void eraseCounters(){
        this.countZero=0;
        this.countOne=0;
    }
}
