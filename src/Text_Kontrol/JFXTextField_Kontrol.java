package Text_Kontrol;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.paint.Color;

public class JFXTextField_Kontrol
{
    private JFXTextField_Kontrol(){}
    private static JFXTextField_Kontrol instance=new JFXTextField_Kontrol();
    public static JFXTextField_Kontrol getInstance(){return instance;}

    public boolean TextKontrol(JFXTextField check){
        if (!check.getText().isEmpty()){
            if (!(check.getLength()<50)) {
                check.setUnFocusColor(Color.RED);
                check.setFocusColor(Color.RED);
                return false;
            }else {
                check.setUnFocusColor(Color.rgb(77, 77, 77));
                check.setFocusColor(Color.rgb(77, 77, 77));
                return true;
            }
        }else{
            check.setUnFocusColor(Color.RED);
            check.setFocusColor(Color.RED);
            return false;
        }
    }
    public boolean TextKontrol(JFXPasswordField check){
        if (!check.getText().isEmpty()){
            if (!(check.getLength()<50)) {
                check.setUnFocusColor(Color.RED);
                check.setFocusColor(Color.RED);
                return false;
            }else {
                check.setUnFocusColor(Color.rgb(77, 77, 77));
                check.setFocusColor(Color.rgb(77, 77, 77));
                return true;
            }
        }else{
            check.setUnFocusColor(Color.RED);
            check.setFocusColor(Color.RED);
            return false;
        }
    }
    public boolean MailKontrol(JFXTextField check){
        if (!check.getText().isEmpty()) {
            for (int i = 0; i < check.getLength(); i++) {
                if (check.getText().charAt(i) == '@') {
                    check.setUnFocusColor(Color.rgb(77, 77, 77));
                    check.setFocusColor(Color.rgb(77, 77, 77));
                    return true;
                }
            }
            check.setUnFocusColor(Color.RED);
            check.setFocusColor(Color.RED);
            return false;
        }
        else{
            check.setUnFocusColor(Color.RED);
            check.setFocusColor(Color.RED);
            return false;
        }
    }
    public boolean ComboBoxKontrol(JFXComboBox<?> check){
        if (!check.getSelectionModel().isEmpty()){

            check.setUnFocusColor(Color.rgb(77, 77, 77));
            check.setFocusColor(Color.rgb(77, 77, 77));
            return true;
        }else {
            check.setFocusColor(Color.RED);
            check.setUnFocusColor(Color.RED);
            return false;
        }

    }
}
