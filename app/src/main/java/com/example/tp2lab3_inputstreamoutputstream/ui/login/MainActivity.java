package com.example.tp2lab3_inputstreamoutputstream.ui.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp2lab3_inputstreamoutputstream.R;
import com.example.tp2lab3_inputstreamoutputstream.model.Usuario;
import com.example.tp2lab3_inputstreamoutputstream.ui.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity
{
    private Button inicia, registro;
    private EditText mail, pass;
    private MainActivityViewModel model;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        iniciar();

        model.getmUsuario().observe(this, new Observer<Usuario>()
        {
            @Override
            public void onChanged(Usuario usuario)
            {
                verUsuarioActual();
            }
        });

        model.getmMensaje().observe(this, new Observer<String>()
        {
            @Override
            public void onChanged(String s)
            {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("ALERT").setMessage(s)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {

                            }

                        }).show();
            }
        });
    }

    private void iniciar()
    {
        inicia = findViewById(R.id.btInicio);
        registro = findViewById(R.id.btRegistro);
        mail = findViewById(R.id.etUsuario);
        pass = findViewById(R.id.etClave);

        inicia.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                model.verificar(mail.getText().toString(), pass.getText().toString());
            }
        });

        registro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                model.registrar();
            }
        });
    }

    public void verUsuarioActual()
    {
        Intent in = new Intent(this, RegistroActivity.class);
        in.putExtra("login", "usuarioActual");
        startActivity(in);
    }
}
