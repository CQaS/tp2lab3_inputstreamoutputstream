package com.example.tp2lab3_inputstreamoutputstream.ui.registro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp2lab3_inputstreamoutputstream.R;
import com.example.tp2lab3_inputstreamoutputstream.model.Usuario;

public class RegistroActivity extends AppCompatActivity
{
    private EditText dni, apellido, nombre, email, password;
    private Button guardar;
    private RegistroActivityViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        iniciar();

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);

        vm.getmUsuario().observe(this, new Observer<Usuario>()
        {
            @Override
            public void onChanged(Usuario usuario)
            {
                dni.setText(String.valueOf(usuario.getDni()));
                nombre.setText(usuario.getNombre());
                apellido.setText(usuario.getApellido());
                email.setText(usuario.getEmail());
                password.setText(usuario.getPassword());
            }
        });

        guardar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                vm.guardarRegistro(Long.parseLong(((dni.getText().toString()))),(apellido.getText().toString()), (nombre.getText().toString()),(email.getText().toString()),(password.getText().toString()));
            }

        });

        Bundle datos = this.getIntent().getExtras();
        vm.verUsuarioRegistrado(datos);
    }

    public  void iniciar()
    {
        dni = findViewById(R.id.etDni);
        apellido = findViewById(R.id.etApellido);
        nombre = findViewById(R.id.etNombre);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        guardar = findViewById(R.id.btGuardar);
    }
}
