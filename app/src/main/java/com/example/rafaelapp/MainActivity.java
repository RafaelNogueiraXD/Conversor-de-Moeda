package com.example.rafaelapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public String recebeTexto;
    public EditText tv_filter;
    public RadioGroup grupoBtn;
    public TextView mostraConteudo;

    public RadioButton radioRealDolar;
    public RadioButton radioRealPeso;

    public RadioButton radioDolarReal;
    public RadioButton radioDolarPeso;

    public RadioButton radioPesoDolar;
    public RadioButton radioPesoReal;
    public float valor2 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Menu novo!");

        tv_filter = (EditText) findViewById(R.id.pegaValor);
        grupoBtn = (RadioGroup) findViewById(R.id.radioGroupMoney);

        mostraConteudo = (TextView) findViewById(R.id.mostraValor);
        tv_filter.addTextChangedListener(fieldValidatorTextWatcher);
        grupoBtn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                calculaValor();
            }
        });
        radioDolarReal = (RadioButton) findViewById(R.id.radioButtonDolar);
        radioDolarPeso = (RadioButton) findViewById(R.id.radioButtonDolarPeso);

        radioRealDolar = (RadioButton) findViewById(R.id.radioButtonReais);
        radioRealPeso = (RadioButton) findViewById(R.id.radioButtonReaisPeso);

        radioPesoDolar = (RadioButton) findViewById(R.id.radioButtonPeso);
        radioPesoReal = (RadioButton) findViewById(R.id.radioButtonPesoReal);
    }
    TextWatcher fieldValidatorTextWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (filterLongEnough()) {
//                populateList();
            }
            calculaValor();

        }

        private boolean filterLongEnough() {
            return tv_filter.getText().toString().trim().length() > 2;
        }
    };
    public void calculaValor(){
        recebeTexto = tv_filter.getText().toString();
        //  recebeTexto.isNullOrEmpty("");
        if(!recebeTexto.equals("")){
            float valor1 = Float.parseFloat(recebeTexto);
            if(radioDolarReal.isChecked()){
                valor2 = valor1 * 5;
            }
            if(radioDolarPeso.isChecked()){
                valor2 = valor1 * 42.81f;
            }
            if(radioRealDolar.isChecked()){
                valor2 = valor1/5;
            }
            if(radioRealPeso.isChecked()){
                valor2 = valor1 * 8.55f;
            }
            if(radioPesoDolar.isChecked()){
                valor2 = valor1 * 0.023f;
            }
            if(radioPesoReal.isChecked()){
                valor2 = valor1 * 0.012f;
            }

            mostraConteudo.setText("Valor convertido: " + valor2);
        }else{
            mostraConteudo.setText("Valor convertido: 0 ");
        }

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return  true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id  = item.getItemId();
        if(id == R.id.action_search){
            Toast.makeText(this,"Clicou no Search!",Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.action_refresh){
            Toast.makeText(this,"clicou no Refresh!",Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.action_settings){
            Toast.makeText(this,"Clicou no Settings!",Toast.LENGTH_SHORT).show();
            return true;
        }
        return  super.onOptionsItemSelected(item);

    }
}
