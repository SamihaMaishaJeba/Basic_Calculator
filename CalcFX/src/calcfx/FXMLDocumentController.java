package calcfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private TextField input;
    @FXML
    private TextField output;
    @FXML
    private Label calc;
    @FXML
    private Button enter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

 

     public static double calculation(String s){

        
        double l1 = 0, o1 = 1;
        double l2 = 1, o2 = 1;
     
        if(s.charAt(0)=='-'){
            s='0'+s;
        }

        for (int i = 0; i < s.length(); i++) {
            
            char jeba = s.charAt(i);
            
            if (Character.isDigit(jeba)) {
                int num = jeba - '0';

                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(++i) - '0');
                }

                l2 = (o2 == 1 ? l2 * num : l2 / num);

            } else if (jeba == '(') {
                int j = i;

                for (int cnt = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '(') cnt++;
                    if (s.charAt(i) == ')') cnt--;
                    if (cnt == 0) break;
                }

                double num =(calculation(s.substring(j + 1, i))) ;

                l2 = (o2 == 1 ? l2 * num : l2 / num);

            } else if (jeba == '*' || jeba == '/') {
                o2 = (jeba == '*' ? 1 : -1);

            } else if (jeba == '+' || jeba == '-') {
                l1 = l1 + o1 * l2;
                o1 = (jeba == '+' ? 1 : -1);

                l2 = 1; o2 = 1;
            }
            else{
                return 1e-7;
            }
        }

        return (l1 + o1 * l2);
    }
    
    
    @FXML
    private void calculatebtn(ActionEvent event) throws Exception {
         String s=input.getText();
         
       if(s.equals("id")){
           output.setText("20203017");
           
       }
       else if(calculation(s)==1e-7)output.setText("Enter valid Expression");
       else
       {
          if(s.endsWith("+")|| s.endsWith("-") || s.endsWith("/") || s.endsWith("*")){
               output.setText("Enter valid Expression");}
          else if(s.startsWith("/")|| s.startsWith("*")){
               output.setText("Enter valid Expression");}
           
          else output.setText(String.valueOf(calculation(s)));
           
       }
    }
    
}
