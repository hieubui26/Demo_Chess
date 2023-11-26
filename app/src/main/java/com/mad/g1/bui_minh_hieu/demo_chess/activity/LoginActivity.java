package com.mad.g1.bui_minh_hieu.demo_chess.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mad.g1.bui_minh_hieu.demo_chess.R;
import com.mad.g1.bui_minh_hieu.demo_chess.dbh.DBHandle;
import com.mad.g1.bui_minh_hieu.demo_chess.model.Member;

import javax.crypto.Mac;

public class LoginActivity extends AppCompatActivity {
    private EditText txtUsername, txtPassword;
    private Button btnLogin;
    private DBHandle dbMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.txtusername);
        txtPassword = findViewById(R.id.txtpassword);
        btnLogin = findViewById(R.id.btnlogin);

        dbMember = new DBHandle(this);
        // Thêm dữ liệu vào DBMember
        Member adminMember = new Member(1, "admin", "123");

        dbMember.addMember(adminMember);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                boolean loginSuccessful = dbMember.checkLogin(username, password);

                if (loginSuccessful) {
                    // Đăng nhập thành công
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // Đăng nhập thất bại
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
