package calc.calc;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class CalcController extends Calc {

    @FXML
    private Label resultLabel;

    @FXML
    private Button b0Button;

    @FXML
    private Button b1Button;

    @FXML
    private Button b2Button;

    @FXML
    private Button b3Button;

    @FXML
    private Button b4Button;

    @FXML
    private Button b5Button;

    @FXML
    private Button b6Button;

    @FXML
    private Button b7Button;

    @FXML
    private Button b8Button;

    @FXML
    private Button b9Button;

    @FXML
    private Button percentButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button divideButton;

    @FXML
    private Button dotButton;

    @FXML
    private Button equalButton;

    @FXML
    private Button minusButton;

    @FXML
    private Button multiplyButton;

    @FXML
    private Button plusButton;

    @FXML
    private Button returnButton;

    @FXML
    private TextField textField;

    @FXML
    void initialize() {
        textField.setOnKeyPressed(ke -> {
            switch (ke.getCode()){
                case DIGIT0:
                case NUMPAD0:
                    buttonEvent(b0Button);
                    break;
                case DIGIT1:
                case NUMPAD1:
                    buttonEvent(b1Button);
                    break;
                case DIGIT2:
                case NUMPAD2:
                    buttonEvent(b2Button);
                    break;
                case DIGIT3:
                case NUMPAD3:
                    buttonEvent(b3Button);
                    break;
                case DIGIT4:
                case NUMPAD4:
                    buttonEvent(b4Button);
                    break;
                case DIGIT6:
                case NUMPAD6:
                    buttonEvent(b6Button);
                    break;
                case DIGIT7:
                case NUMPAD7:
                    buttonEvent(b7Button);
                    break;
                case DIGIT9:
                case NUMPAD9:
                    buttonEvent(b9Button);
                    break;
                case ENTER:
                    buttonEvent(equalButton);
                    break;
                case PERIOD:
                case DECIMAL:
                case COMMA:
                    buttonEvent(dotButton);
                    break;
                case SUBTRACT:
                case MINUS:
                    buttonEvent(minusButton);
                    break;
                case DELETE:
                    buttonEvent(clearButton);
                    break;
                case BACK_SPACE:
                    buttonEvent(returnButton);
                    break;
                case SLASH:
                case DIVIDE:
                    buttonEvent(divideButton);
                    break;

            }

        });
        textField.setOnKeyTyped(ke -> {
            switch (ke.getCharacter()){
                case "5":
                    buttonEvent(b5Button);
                    break;
                case "8":
                    buttonEvent(b8Button);
                    break;
                case "=":
                    buttonEvent(equalButton);
                    break;
                case "+":
                    buttonEvent(plusButton);
                    break;
                case "*":
                    buttonEvent(multiplyButton);
                    break;
                case "%":
                    buttonEvent(percentButton);
                    break;
            }
        });
        b0Button.setOnAction(event -> buttonEvent(b0Button));
        b1Button.setOnAction(event -> buttonEvent(b1Button));
        b2Button.setOnAction(event -> buttonEvent(b2Button));
        b3Button.setOnAction(event -> buttonEvent(b3Button));
        b4Button.setOnAction(event -> buttonEvent(b4Button));
        b5Button.setOnAction(event -> buttonEvent(b5Button));
        b6Button.setOnAction(event -> buttonEvent(b6Button));
        b7Button.setOnAction(event -> buttonEvent(b7Button));
        b8Button.setOnAction(event -> buttonEvent(b8Button));
        b9Button.setOnAction(event -> buttonEvent(b9Button));
        dotButton.setOnAction(event -> buttonEvent(dotButton));
        returnButton.setOnAction(event -> buttonEvent(returnButton));
        clearButton.setOnAction(event -> buttonEvent(clearButton));
        plusButton.setOnAction(event -> buttonEvent(plusButton));
        minusButton.setOnAction(event -> buttonEvent(minusButton));
        multiplyButton.setOnAction(event -> buttonEvent(multiplyButton));
        divideButton.setOnAction(event -> buttonEvent(divideButton));
        equalButton.setOnAction(event -> buttonEvent(equalButton));
        percentButton.setOnAction(event -> buttonEvent(percentButton));

    }

    public boolean isAction= false;
    public int counter = 1;
    public String calculations = "";
    public boolean isDot = false;
    public boolean isEquals = false;
    public boolean isPercent = false;

    public void buttonEvent(Button b) {
        if (isEquals){
            isEquals=false;
            textField.setText("");
            resultLabel.setVisible(false);
        }
        switch (b.getText()) {
            case "%":
                if(!isPercent) {
                    try {
                    isPercent = true;
                    double percent = Double.parseDouble(textField.getText())/100;
                    StringBuilder s1 = new StringBuilder(calculations);
                    char charAction = s1.charAt(s1.length()-1);
                    switch (charAction) {
                        case '+':
                            percent = 1 + percent;
                            changeAction("*");
                            break;
                        case '-':
                            percent = 1 - percent;
                            changeAction("*");
                            break;
                    }
                    calculations+= percent;
                    textField.setText("");
                    }catch (Exception ignore){}
                }
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                if (!("".equals(String.valueOf(textField))||isAction)) {
                    calculations+=textField.getText()+b.getText();
                    textField.setText("");
                    counter++;
                    isDot=false;
                    isAction=true;
                } else if (isAction) {
                    changeAction(b.getText());
                }
                break;
            case "=":
                isEquals=true;
                try{
                    calculations+=textField.getText();
                    char[] chars = calculations.toCharArray();
                    String[] words= new String[counter];
                    for (int i=0;i<counter;i++)
                        words[i]="";
                    double result=0.0f;
                    char[] functions = new char[counter-1];
                    int n=0, actions=0;
                    for (int i=0;i<chars.length;i++){
                        if (chars[i]=='+'||chars[i]=='-'||chars[i]=='*'||chars[i]=='/') {
                            functions[actions]=chars[i];
                            actions++;
                            if (i<(chars.length-1))
                                n++;
                        } else{
                            words[n]+=chars[i];

                        }
                    }
                    result+=Double.parseDouble(words[0]);
                    for (int i=0;i<n;i++){
                        switch (functions[i]) {
                            case ('+'):
                                result += Double.parseDouble(words[i + 1]);
                                break;
                            case ('-'):
                                result -= Double.parseDouble(words[i + 1]);
                                break;
                            case ('/'):
                                if(words[i+1].equals("0")){
                                    calculations= "";
                                    textField.setText("Деление на 0 невозможно");
                                }else{
                                    result /= Double.parseDouble(words[i + 1]);
                                }
                                break;
                            case ('*'):
                                result *= Double.parseDouble(words[i + 1]);
                                break;

                        }
                    }
                    if (calculations.equals(""))
                        textField.setText("");
                    else {
                        resultLabel.setVisible(true);
                        resultLabel.setText(calculations + " = " );
                        textField.setText(""+result);
                    }
                    counter=1;
                    isDot=false;
                }catch(Exception exception){
                    counter=1;
                    isDot=false;
                    isAction=true;
                    textField.setText("");
                }
                calculations="";
                break;
            case "CLEAR":
                textField.setText("");
                calculations="";
                isDot=false;
                isAction=false;
                isPercent=false;
                break;
            case "←":
                try{
                    StringBuilder s1 = new StringBuilder(textField.getText());
                    char charisDot = s1.charAt(s1.length()-1);
                    if (charisDot=='.') {
                        isDot = false;
                    }
                    s1.delete(s1.length()-1, s1.length());
                    if (!(s1.length()==0))
                        textField.setText(s1.toString());
                    else
                        textField.setText("0");
                }catch(Exception ignored){}
                break;
            case ".":
                if (!isDot){
                    if (textField.getText().equals(""))
                        textField.setText(textField.getText()+"0");
                    isAction = false;
                    textField.setText(textField.getText() + b.getText());
                    isDot=true;
                }
                break;
            default:
                String s0=textField.getText();
                if (s0.equals("0"))
                    textField.setText(b.getText());
                else{
                    isAction = false;
                    isPercent = false;
                    textField.setText(textField.getText() + b.getText());
                }
            break;
        }
    }
    public void changeAction(String action){
        try {
            StringBuilder s1 = new StringBuilder(calculations);
            s1.delete(s1.length()-1, s1.length());
            if (!(s1.length()==0)) {
                calculations = String.valueOf(s1);
                calculations+=action;
            }
            else
                calculations="";
        } catch (Exception ignored){}
    }
}
