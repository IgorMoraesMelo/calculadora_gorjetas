package com.example.gorjetas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText txtValorConta;
    private TextView txtPorcentagem, txtGorjeta, txtTotal;
    private SeekBar barraProgresso;

    double guardarPorcentagem = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtValorConta = findViewById(R.id.txtValorConta);
        txtPorcentagem = findViewById(R.id.txtPorcentagem);
        txtGorjeta = findViewById(R.id.txtGorjeta);
        txtTotal = findViewById(R.id.txtTotal);
        barraProgresso = findViewById(R.id.barraProgresso);

        // Set da barra de progresso para calculo dos valores baseados na porcentagem da barra
        barraProgresso.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                guardarPorcentagem = progress;
                txtPorcentagem.setText(Math.round(guardarPorcentagem) + "%");
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    // Metodo para calculo dos valores informados
    @SuppressLint("SetTextI18n")
    public void calcular() {

        String valorConta = txtValorConta.getText().toString();

        // Validação de campo
        if (valorConta == null || valorConta.equals("")) {
            Toast.makeText(getApplicationContext(), "Digite o valor da conta!!", Toast.LENGTH_SHORT).show();
        } else {

            // Calculo de valor
            double vConta = Double.parseDouble(valorConta);
            double vGorjeta = vConta * (guardarPorcentagem / 100);
            double vTotal = vConta + vGorjeta;

            // Retorno do calculo
            txtGorjeta.setText("R$ " + vGorjeta);
            txtTotal.setText("R$ " + vTotal);
        }
    }
}