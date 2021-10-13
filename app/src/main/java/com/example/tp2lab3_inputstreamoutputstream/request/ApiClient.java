package com.example.tp2lab3_inputstreamoutputstream.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.tp2lab3_inputstreamoutputstream.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;


public class ApiClient
{
    /*private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context)
    {
        if(sp == null)
        {
            sp = context.getSharedPreferences("datos",0);
        }
        return sp;
    }*/

    public static void guardar(Context context, Usuario u)
    {
        File archivo = new File(context.getFilesDir(), "usuarios.txt");
        //nodo
        try
        {
            FileOutputStream FOS = new FileOutputStream(archivo);
            BufferedOutputStream BOS = new BufferedOutputStream(FOS);
            ObjectOutputStream OOS = new ObjectOutputStream(BOS);

            OOS.writeObject(u);
            OOS.flush();
            OOS.close();

            Toast.makeText(context, "Successfully registered!!", Toast.LENGTH_LONG).show();
        }
        catch (FileNotFoundException ex)
        {
            Toast.makeText(context, "Not Found", Toast.LENGTH_LONG).show();
        }
        catch (IOException io)
        {
            Toast.makeText(context, "Not Registered", Toast.LENGTH_LONG).show();
        }

    }

    public static Usuario leer(Context context)
    {
        Usuario us = null;
        File archivo = new File(context.getFilesDir(), "usuarios.txt");

        try
        {
            FileInputStream FIS = new FileInputStream(archivo);
            BufferedInputStream BIS = new BufferedInputStream(FIS);
            ObjectInputStream OIS = new ObjectInputStream(BIS);

            //while (OIS.available()> -1)           {
                us = (Usuario) OIS.readObject();
            //}

            return us;

        }
        catch (FileNotFoundException ex)
        {

            Toast.makeText(context, "Not Found", Toast.LENGTH_LONG).show();
            return us;

        }
        catch (IOException ioe)
        {
            Toast.makeText(context, "Was not read!", Toast.LENGTH_LONG).show();
            return us;
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return us;
        }

    }

    public static Usuario login(Context context, String mail, String passw)
    {
        Usuario us = null;
        File archivo = new File(context.getFilesDir(), "usuarios.txt");

        try
        {
            FileInputStream FIS = new FileInputStream(archivo);
            BufferedInputStream BIS = new BufferedInputStream(FIS);
            ObjectInputStream OIS = new ObjectInputStream(BIS);

            //while (OIS.available()>0)            {
                us = (Usuario) OIS.readObject();
            //}
            if(mail.equals(us.getEmail()) && passw.equals(us.getPassword()))
            {
                return us;

            }
            else
            {
                Toast.makeText(context, "Usuario no existe", Toast.LENGTH_LONG).show();
                return us;
            }

        }
        catch (FileNotFoundException ex)
        {

            Toast.makeText(context, "Not Found", Toast.LENGTH_LONG).show();
            return us;

        }
        catch (IOException ioe)
        {
            Toast.makeText(context, "Was not read!", Toast.LENGTH_LONG).show();
            return us;
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return us;
        }

    }


}
