package com.example.tp2lab3_inputstreamoutputstream.ui.registro;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp2lab3_inputstreamoutputstream.model.Usuario;
import com.example.tp2lab3_inputstreamoutputstream.request.ApiClient;
import com.example.tp2lab3_inputstreamoutputstream.ui.login.MainActivity;

public class RegistroActivityViewModel extends AndroidViewModel
{
    Context context;
    MutableLiveData<Usuario> mUsuario;

    public RegistroActivityViewModel(@NonNull Application application)
    {
        super(application);
        context= application.getApplicationContext();
    }

    public LiveData<Usuario> getmUsuario()
    {
        if(mUsuario == null){
            mUsuario = new MutableLiveData<>();
        }
        return mUsuario;
    }

    public void guardarRegistro (long dni, String apellido , String nombre, String email, String password)
    {

        Usuario usuario= new Usuario(dni,apellido,nombre,email,password);
        usuario.setDni(dni);
        usuario.setApellido(apellido);
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);

        ApiClient.guardar(context, usuario);

        volverLogin();

    }


    public void verUsuarioRegistrado(Bundle d)
    {
        if(d != null)
        {
            Usuario usuario = ApiClient.leer(context);
            mUsuario.setValue(usuario);
        }

    }



    public void volverLogin()
    {
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
