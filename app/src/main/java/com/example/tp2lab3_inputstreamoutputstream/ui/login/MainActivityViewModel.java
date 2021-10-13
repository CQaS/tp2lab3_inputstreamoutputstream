package com.example.tp2lab3_inputstreamoutputstream.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp2lab3_inputstreamoutputstream.model.Usuario;
import com.example.tp2lab3_inputstreamoutputstream.request.ApiClient;
import com.example.tp2lab3_inputstreamoutputstream.ui.registro.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel
{
    Context context;
    MutableLiveData<Usuario> mUsuario;
    MutableLiveData<String> mMensaje;

    public MainActivityViewModel(@NonNull Application application)
    {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Usuario> getmUsuario()
    {
        if(mUsuario == null){
            mUsuario = new MutableLiveData<>();
        }
        return mUsuario;
    }

    public LiveData<String> getmMensaje()
    {
        if(mMensaje == null){
            mMensaje = new MutableLiveData<>();
        }
        return mMensaje;
    }

    public void registrar()
    {
        Intent in = new Intent(context, RegistroActivity.class);
        in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(in);
    }

    public void verificar(String mail, String pass)
    {
        if(mail != null && pass != null)
        {
            Usuario u = ApiClient.login(context, mail, pass);

            if(u != null)
            {
                mUsuario.setValue(u);
            }
            else
            {
                mMensaje.setValue("Datos incorrectos!");
            }

        }
        else
        {
            mMensaje.setValue("Datos incorrectos!");
        }

    }
}
