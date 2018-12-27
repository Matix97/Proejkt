package com.example.projekt.car;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projekt.car.Exceptions.PersonDoesNotExist;
import com.example.projekt.car.LoginAndRegister.Login;
import com.example.projekt.car.LoginAndRegister.Register;
import com.example.projekt.car.LoginAndRegister.UserService;
import com.example.projekt.car.data.PeopleDataBase;
import com.example.projekt.car.data.Person;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends Activity {
    Button b1, b2;
    EditText ed1, ed2;
    //some magic....
    Retrofit.Builder builder=new Retrofit.Builder()
                .baseUrl("http://104.214.72.121:8080/")
            .addConverterFactory(GsonConverterFactory.create());
    Retrofit retrofit=builder.build();
    UserService userService=retrofit.create(UserService.class);
//end of magic...(currently)
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_z_internet);

        b1 = findViewById(R.id.button);
        ed1 = findViewById(R.id.editText);
        ed2 = findViewById(R.id.editText2);
        b2 = findViewById(R.id.button2);

        b1.setOnClickListener(v -> {
            RequestManager requestManager=new RequestManager();
            String tokenFromRequest=requestManager.login(ed1.getText().toString(),ed2.getText().toString());
            if( !tokenFromRequest.equals("")){
               Toast.makeText(LoginActivity.this,"Successful login "+tokenFromRequest,Toast.LENGTH_SHORT).show();
           }
           else
               Toast.makeText(LoginActivity.this,"Faulty login",Toast.LENGTH_SHORT).show();
          /*  try {
                tryLogin();
            } catch (PersonDoesNotExist personDoesNotExist) {
                personDoesNotExist.printStackTrace();
            }*/
        });
        b2.setOnClickListener(v ->{ RequestManager requestManager=new RequestManager();
        if(requestManager.register(ed1.getText().toString(),ed1.getText().toString(),ed2.getText().toString(),ed2.getText().toString()))
        {
            Toast.makeText(LoginActivity.this,"Successful register",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(LoginActivity.this,"Faulty register",Toast.LENGTH_SHORT).show();
        /*tryRegister()*/ });
    }
/*private void tryRegister(){
    Register register=new Register(ed1.getText().toString(),ed1.getText().toString(),ed2.getText().toString(),ed2.getText().toString());
    Call<Person> call= userService.register(register);

    call.enqueue(new Callback<Person>() {
        @Override
        public void onResponse(Call<Person> call, Response<Person> response) {
            if(response.isSuccessful())
            {
                token=response.body().getToken();
                Toast.makeText(LoginActivity.this,token,Toast.LENGTH_SHORT).show();

            }
            else
                Toast.makeText(LoginActivity.this,"error",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(Call<Person> call, Throwable t) {
            Toast.makeText(LoginActivity.this,"error",Toast.LENGTH_SHORT).show();
        }
    });
}*/
   /* private void tryLogin() throws PersonDoesNotExist {



        Login login=new Login(ed1.getText().toString(),ed2.getText().toString());
        Call<Person> call= userService.login(login);
        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                if(response.isSuccessful())
                {
                    token=response.body().getToken();
                    Toast.makeText(LoginActivity.this,token,Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(LoginActivity.this,"error",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                    Toast.makeText(LoginActivity.this,"error",Toast.LENGTH_SHORT).show();
            }
        });

        *//*Boolean loginOK = false;

        PeopleDataBase peopleDataBase = new PeopleDataBase();
        for (int i = 0; i < peopleDataBase.size(); i++) {
            if (peopleDataBase.isExist(ed1.getText().toString())) {
                if (peopleDataBase.getPerson(ed1.getText().toString()).getPassword().equals(ed2.getText().toString())) {
                    loginOK = true;
                    break;
                }
            }
        }

        if (loginOK) {
            newActivity();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "You haven't account or password is incorrect", Toast.LENGTH_LONG);
            toast.show();
        }
*//*

    }*/

    private void newActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Value1", ed1.getText().toString());
        startActivity(intent);
    }
}
