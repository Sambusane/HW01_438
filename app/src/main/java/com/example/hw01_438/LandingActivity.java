package com.example.hw01_438;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LandingActivity extends AppCompatActivity {
    private TextView textViewResult;
    int uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        textViewResult = findViewById(R.id.text_view_result);
        getDataFromIntent();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        Call<List<Post>> call = jsonPlaceHolderAPI.getPosts(uId);

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    textViewResult.setText("Code: "+ response.code());
                    return;
                }
                List<Post> posts = response.body();
                for (Post post: posts){
                    String content="";
                    content += "User ID: "+ post.getUserId() + "\n";
                    content += "ID: "+ post.getId() + "\n";
                    content += "Title: "+ post.getTitle() + "\n";
                    content += "Text: "+ post.getText() + "\n";

                    textViewResult.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        uId = intent.getIntExtra("USER_ID",0);
    }

    public static Intent intentFactory(Context context){
        return new Intent(context,LandingActivity.class);
    }
}