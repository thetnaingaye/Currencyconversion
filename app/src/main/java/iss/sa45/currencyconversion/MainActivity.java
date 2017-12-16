package iss.sa45.currencyconversion;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import static java.lang.Double.*;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText t1 = (EditText) findViewById(R.id.editText1);
        final EditText t2 = (EditText) findViewById(R.id.editText2);

        t1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    double f = getConversion();
                    convert(t1,1/f,t2);


                }
            }
        });

        t2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    double f = getConversion();
                    convert(t2, f, t1);
                }
            }
        });

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!t1.getText().toString().isEmpty() )
                {
                    double f = getConversion();
                    convert(t1,1/f,t2);
                }
            }
        });

        Button btnNextLayout = (Button) findViewById(R.id.button2);
        btnNextLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });

    }


    private double getConversion(){
        final Spinner s =(Spinner) findViewById(R.id.spinner1);
        int i = s.getSelectedItemPosition();

        Resources res = getResources();
        String[] va = res.getStringArray(R.array.currencyV);
        return(Double.parseDouble(va[i]));

    }

    private void convert(EditText t, double factor,EditText r)
    {
        double i = Double.parseDouble(t.getText().toString());
        r.setText(String.format("%5f",i*factor));
    }


}
