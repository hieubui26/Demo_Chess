package com.mad.g1.bui_minh_hieu.demo_chess.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mad.g1.bui_minh_hieu.demo_chess.R;
import com.mad.g1.bui_minh_hieu.demo_chess.dbh.DBHandle;
import com.mad.g1.bui_minh_hieu.demo_chess.model.Match;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtName, txtDate, txtDescription;
    private Spinner txtLevel;
    private RadioButton txtMay, txtNguoi;
    private Button btnSave, btnDelete, btnCancel;
    private DBHandle sqLiteHelper;
    private Match match;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        initData();
    }

    private void initView() {
        txtName = findViewById(R.id.txtName);
        txtDate = findViewById(R.id.txtDate);
        txtDescription = findViewById(R.id.txtDescription);
        txtLevel = findViewById(R.id.spLevel);
        txtLevel.setAdapter(new ArrayAdapter<>(this, R.layout.item_spinner, getResources().getStringArray(R.array.level)));
        txtMay = findViewById(R.id.txtMay);
        txtNguoi = findViewById(R.id.txtNguoi);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        sqLiteHelper = new DBHandle(this);
        txtDate.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    private void initData() {
        match = (Match) getIntent().getSerializableExtra("matchs");
        txtName.setText(match.getName());
        txtDate.setText(match.getDate());
        txtDescription.setText(match.getDescription());
        for (int i = 0; i < txtLevel.getCount(); i++) {
            if (txtLevel.getItemAtPosition(i).toString().equalsIgnoreCase(match.getLevel())) {
                txtLevel.setSelection(i);
                break;
            }
        }
        if (!match.getStatus()) {
            txtMay.setChecked(true);
        } else {
            txtNguoi.setChecked(true);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == txtDate) {
            Calendar calendar = Calendar.getInstance();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH);
            int d = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                String date = "";

                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    if (m >= 9) {
                        if (d < 10) {
                            date = "0" + d + "/" + (m - 1) + "/" + y;
                        } else {
                            date = d + "/" + (m - 1) + "/" + y;
                        }
                    } else {
                        if (d < 10) {
                            date = "0" + d + "/0" + (m - 1) + "/" + y;
                        } else {
                            date = d + "/0" + (m - 1) + "/" + y;
                        }
                    }
                    txtDate.setText(date);
                }
            }, y, m, d);
            datePickerDialog.show();
        } else if (view == btnSave) {
            Integer id = match.getId();
            String name = txtName.getText().toString();
            String date = txtDate.getText().toString();
            String description = txtDescription.getText().toString();
            String level = txtLevel.getSelectedItem().toString();
            Boolean status = !txtMay.isChecked();
            if (name.isEmpty()) {
                Toast.makeText(this, "Tên trận không được bỏ trống", Toast.LENGTH_SHORT).show();
            } else if (description.isEmpty()) {
                Toast.makeText(this, "Mô tả không được bỏ trống", Toast.LENGTH_SHORT).show();
            } else if (date.isEmpty()) {
                Toast.makeText(this, "Ngày tạo không được bỏ trống", Toast.LENGTH_SHORT).show();
            } else if (!date.matches("\\d{2}/\\d{2}/\\d{4}")) {
                Toast.makeText(this, "Ngày tạo phải có định dạng(dd/MM/yyy)", Toast.LENGTH_SHORT).show();
            } else {
                Match match = new Match(id, name, date, description, level,status);
                long row = sqLiteHelper.update(match);
                if (row != 0) {
                    Toast.makeText(this, "Cập nhật trận đấu thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Cập nhật trận đấu thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (view == btnDelete) {
            long row = sqLiteHelper.delete(match.getId());
            if (row != 0) {
                Toast.makeText(this, "Xoá trận đấu thành công", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Xoá trận đấu thất bại", Toast.LENGTH_SHORT).show();
            }
            finish();
        } else if (view == btnCancel) {
            finish();
        }
    }
}

