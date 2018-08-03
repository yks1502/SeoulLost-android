package seoullost.seoullost_android;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ApiApplication apiApplication;
    private ApiService apiService;
    private EditText usernameInput, passwordInput;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiApplication = (ApiApplication) getApplicationContext();
        apiService = apiApplication.getApiService();
        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        editor = user.edit();
        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
    }

    public void handleLogin(View view) {
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        Call<Token> tokenCall = apiService.login(new User(username, password));
        tokenCall.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Token token = response.body();
                if (token == null) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                    alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alert.setMessage("아이디 또는 비밀번호가 올바르지 않습니다.");
                    alert.show();
                } else {
                    editor.putString("token", "Token " + token.getToken());
                    editor.commit();
                    AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                    alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            finish();
                        }
                    });
                    alert.setMessage("로그인 되었습니다.");
                    alert.show();
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                return;
            }
        });
    }
}
