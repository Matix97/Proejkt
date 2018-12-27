package com.example.projekt.car;


import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.example.projekt.car.Exceptions.PersonDoesNotExist;
import com.example.projekt.car.LoginAndRegister.Login;
import com.example.projekt.car.LoginAndRegister.Register;
import com.example.projekt.car.LoginAndRegister.User;
import com.example.projekt.car.LoginAndRegister.UserService;
import com.example.projekt.car.data.Person;
//import com.google.android.gms.common.api.Response;


import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.http.Header;
/*więc będzie coś typu var token = request.authenticateWith("somelogin","somepasword")*/

public class    RequestManager  {

    private UserService userService;
    private Retrofit retrofit;


    RequestManager(){
        Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://104.214.72.121:8080/")
                .addConverterFactory(GsonConverterFactory.create());
        retrofit=builder.build();
        userService=retrofit.create(UserService.class);
    }
    public Boolean register(String name, String email, String password, String verifyPassword){
        Register register=new Register(name,email,password,verifyPassword);
        Call<Person> call= userService.register(register);
        final Boolean[] ifRegister = {false};
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                if(response.isSuccessful())
                {

                    ifRegister[0] =true;

                }
                else{

                    ifRegister[0]=false;
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {

                ifRegister[0]=false;
            }
        });
        return ifRegister[0];
    }
    public String login(String email, String password)  {


        final String[] token = {""};

        String autHeader="Basic "+Base64.encodeToString((email+":"+password).getBytes(), Base64.NO_WRAP);
        Log.i("tag",autHeader);
        Call<String> call=userService.getUser(autHeader);
        try {
            Response<String> response=call.execute();
            if(response.isSuccessful()){
                token[0]=response.body();

            }
        } catch (IOException e) {
            e.printStackTrace();

        }

return token[0];

    }



}
