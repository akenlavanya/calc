package com.example.calc;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {
    TextView workingsTV;
    TextView resultTV;
    String workings="";
    String formula="";
    String temp="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
    }

    private void initTextViews() {
        workingsTV=(TextView)findViewById(R.id.workingTextView);
        resultTV=(TextView) findViewById(R.id.resultTextView);
    }
    private void setWorkings(String givenValue){
        workings=workings+givenValue;
        workingsTV.setText(workings);
    }
    public void equalOnClick(View view) {
        Double result=null;
        ScriptEngine engine=new ScriptEngineManager().getEngineByName("rhino");
        checkForpower();
        try {
            result=(double)engine.eval(formula);
        } catch (ScriptException e) {
            Toast.makeText(this,"Invalid Input",Toast.LENGTH_SHORT).show();
        }
        if(result!=null)
            resultTV.setText(String.valueOf(result.doubleValue()));
    }

    private void checkForpower()
    {
        ArrayList<Integer> indexOfPowers=new ArrayList<Integer>();
        for(int i=0;i<workings.length();i++) {
            if (workings.charAt(i) =='^')
                indexOfPowers.add(i);
        }
        formula=workings;
        temp=workings;
        for(Integer index:indexOfPowers)
        {
            changeFormula(index);
        }
        formula=temp;
    }

    private void changeFormula(Integer index) {
        String numberLeft="";
        String numberRight="";
        for(int i=index+1;i<workings.length();i++){
            if(isNumeric(workings.charAt(i)))
                numberRight=numberRight+workings.charAt(i);
            else
                break;
        }
        for(int i=index-1;i>=0;i--){
            if(isNumeric(workings.charAt(i)))
                numberLeft=numberLeft+workings.charAt(i);
            else
                break;
        }
        String original=numberLeft+"^"+numberRight;
        String changed="Math.pow("+numberLeft+","+numberRight+")";
        temp=temp.replace(original,changed);

    }
    private boolean isNumeric(char c){
        if(c<='9'&&c>='0'||c=='.')
            return true;
        return false;
    }

    public void clearOnClick(View view) {
        workingsTV.setText("");
        workings="";
        resultTV.setText("");
        leftBracket=true;
    }
    boolean leftBracket=true;

    public void bracketsOnClick(View view) {
        if(leftBracket){
            setWorkings("(");
            leftBracket=false;
        }
        else{
            setWorkings(")");
            leftBracket=true;
        }

    }

    public void powerOnClick(View view) {
        setWorkings("^");
    }

    public void divideOnClick(View view) {
        setWorkings("/");
    }

    public void sevenOnClick(View view) {
        setWorkings("7");
    }

    public void eightOnClick(View view) {
        setWorkings("8");
    }

    public void nineOnClick(View view) {
        setWorkings("9");
    }

    public void multiplyOnClick(View view) {
        setWorkings("*");
    }

    public void fourOnClick(View view) {
        setWorkings("4");
    }

    public void fiveOnClick(View view) {
        setWorkings("5");
    }

    public void sixOnClick(View view) {
        setWorkings("6");
    }

    public void minusOnClick(View view) {
        setWorkings("-");
    }

    public void oneOnClick(View view) {
        setWorkings("1");
    }

    public void twoOnClick(View view) {
        setWorkings("2");
    }

    public void threeOnClick(View view) {
        setWorkings("3");
    }

    public void addOnClick(View view) {
        setWorkings("+");
    }

    public void pointOnClick(View view) {
        setWorkings(".");
    }

    public void ZeroOnClick(View view) {
        setWorkings("0");
    }

}