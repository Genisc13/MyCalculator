package edu.upc.dsa.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView solution_textView,result_textView;
    MaterialButton button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9,button_0;
    MaterialButton button_AC,button_plus,button_minus,button_equal,button_C,button_sin,button_cos,button_tan,button_off;
    MaterialButton button_dot,button_open_bracket,button_close_bracket,button_multiply,button_divide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_textView = (TextView)findViewById(R.id.result_textView);
        solution_textView = (TextView)findViewById(R.id.solution_textView);
        assignID(button_AC,R.id.button_AC);
        assignID(button_0,R.id.button_0);
        assignID(button_1,R.id.button_1);
        assignID(button_2,R.id.button_2);
        assignID(button_3,R.id.button_3);
        assignID(button_4,R.id.button_4);
        assignID(button_5,R.id.button_5);
        assignID(button_6,R.id.button_6);
        assignID(button_7,R.id.button_7);
        assignID(button_8,R.id.button_8);
        assignID(button_9,R.id.button_9);
        assignID(button_equal,R.id.button_equal);
        assignID(button_plus,R.id.button_plus);
        assignID(button_minus,R.id.button_minus);
        assignID(button_C,R.id.button_C);
        assignID(button_sin,R.id.button_sin);
        assignID(button_cos,R.id.button_cos);
        assignID(button_tan,R.id.button_tan);
        assignID(button_off,R.id.button_off);
        assignID(button_dot,R.id.button_dot);
        assignID(button_open_bracket,R.id.button_open_bracket);
        assignID(button_close_bracket,R.id.button_close_bracket);
        assignID(button_multiply,R.id.button_multiply);
        assignID(button_divide,R.id.button_divide);


    }

    void assignID(Button btn, int id){
        btn= findViewById(id);
        btn.setOnClickListener(this);
    }

    String calculate_trig(String operation,String calculation){
        String trig="";
        String a="";
        String b="";
        String c="";
        if(calculation.length()<=1)
            a=calculation.substring(0,calculation.length()-1);
        else if (calculation.length()<=2){
            a=calculation.substring(0,calculation.length()-1);
            b=calculation.substring(0,calculation.length()- 2);
        }
        else{
            a=calculation.substring(0,calculation.length()-1);
            b=calculation.substring(0,calculation.length()- 2);
            c=calculation.substring(0,calculation.length()-3);
        }
        if(a.endsWith("+") || a.endsWith("-") || a.endsWith("*")|| a.endsWith("/")|| a.endsWith("(")|| a.endsWith(")") || a.equals("")){
            trig= calculation.substring(calculation.length()-1);
            calculation=a;
            Double Cal=Double.valueOf(trig);
            if(operation.equals("sin")){
                trig= String.valueOf(Math.sin(Math.toRadians(Cal)));
            }
            if(operation.equals("cos")){
                trig= String.valueOf(Math.cos(Math.toRadians(Cal)));
            }
            if(operation.equals("tan")){
                trig= String.valueOf(Math.tan(Math.toRadians(Cal)));
            }
            if (trig.length()>5){
                trig=trig.substring(0,0+4);
            }
            calculation=calculation+trig;
        }
        else if(b.endsWith("+") || b.endsWith("-") || b.endsWith("*")|| b.endsWith("/")|| b.endsWith("(")|| b.endsWith(")")|| b.equals("")){
            trig= calculation.substring(calculation.length()-2);
            calculation=b;
            Double Cal=Double.valueOf(trig);
            if(operation.equals("sin")){
                trig= String.valueOf(Math.sin(Math.toRadians(Cal)));
            }
            if(operation.equals("cos")){
                trig= String.valueOf(Math.cos(Math.toRadians(Cal)));
            }
            if(operation.equals("tan")){
                trig= String.valueOf(Math.tan(Math.toRadians(Cal)));
            }
            if (trig.length()>5){
                trig=trig.substring(0,0+4);
            }
            calculation=calculation+trig;
        }
        else if(c.endsWith("+") || c.endsWith("-") || c.endsWith("*")|| c.endsWith("/")|| c.endsWith("(")|| c.endsWith(")")|| c.equals("")){
            trig= calculation.substring(calculation.length()-3);
            calculation=c;
            Double Cal=Double.valueOf(trig);
            if(operation.equals("sin")){
                trig= String.valueOf(Math.sin(Math.toRadians(Cal)));
            }
            if(operation.equals("cos")){
                trig= String.valueOf(Math.cos(Math.toRadians(Cal)));
            }
            if(operation.equals("tan")){
                trig= String.valueOf(Math.tan(Math.toRadians(Cal)));
            }
            if (trig.length()>5){
                trig=trig.substring(0,0+4);
            }
            calculation=calculation+trig;
        }

        return calculation;
    }

    @Override
    public void onClick(View view) {
        Button button=(MaterialButton) view;
        String buttonText = button.getText().toString();
        String calculation = solution_textView.getText().toString();
        if(buttonText.equals("AC")){
            solution_textView.setText("");
            result_textView.setText("0");
            return;
        }
        else if(buttonText.equals("=")){
            solution_textView.setText(result_textView.getText());
            return;
        }
        else if(buttonText.equals("C")){
            calculation= calculation.substring(0,calculation.length()-1);
        }
        else if(buttonText.equals("sin")){
                calculation=this.calculate_trig("sin",calculation);
            }
        else if(buttonText.equals("cos")){
            calculation=this.calculate_trig("cos",calculation);
        }
        else if(buttonText.equals("tan")){
            calculation=this.calculate_trig("tan",calculation);
        }

        else {
            calculation=calculation+buttonText;
        }

        solution_textView.setText(calculation);
        String finalResult=getResult(calculation);
        if(!finalResult.equals("Err")){
            result_textView.setText(finalResult);
        }
    }
    String getResult(String data){
        try{Context context= Context.enter();
        context.setOptimizationLevel(-1);
        Scriptable scriptable=context.initStandardObjects();
        String finalResult=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
        if (finalResult.endsWith(".0")){
            finalResult=finalResult.replace(".0","");
        }
        return finalResult;
        }
        catch (Exception e){
            return "Err";
        }
    }
}