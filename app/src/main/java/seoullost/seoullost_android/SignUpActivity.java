package seoullost.seoullost_android;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private ApiApplication apiApplication;
    private ApiService apiService;

    @BindView(R.id.username_input)
    protected EditText usernameInput;
    @BindView(R.id.password_input)
    protected EditText passwordInput;
    @BindView(R.id.password_confirmation_input)
    protected EditText passwordConfirmationInput;
    @BindView(R.id.nickname_input)
    protected EditText nicknameInput;
    @BindView(R.id.email_input)
    protected EditText emailInput;
    @BindView(R.id.address_input)
    protected EditText addressInput;
    @BindView(R.id.contact_input)
    protected EditText contactInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        apiApplication = (ApiApplication) getApplicationContext();
        apiService = apiApplication.getApiService();
        ButterKnife.bind(this);
    }

    public void handleSignUp(View view) {
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        String passwordConfirmation = passwordConfirmationInput.getText().toString();
        String nickname = nicknameInput.getText().toString();
        String email = emailInput.getText().toString();
        String address = addressInput.getText().toString();
        String contact = contactInput.getText().toString();

        if (!password.equals(passwordConfirmation)) {
            AlertDialog.Builder alert = new AlertDialog.Builder(SignUpActivity.this);
            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            alert.setMessage("비밀번호가 일치하지 않습니다.");
            alert.show();
            return;
        }

        Call<Void> signUpCall = apiService.signUp(new User(username, password, nickname, email, address, contact));
        signUpCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(SignUpActivity.this);
                    alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            finish();
                        }
                    });
                    alert.setMessage("회원가입 되었습니다.");
                    alert.show();
                } else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(SignUpActivity.this);
                    alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alert.setMessage("회원가입에 실패했습니다. 다시 확인해주세요.");
                    alert.show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                return;
            }
        });
    }
}
