package com.example.tinhtiendien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.tinhtiendien.databinding.ActivityMainBinding;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        View view = binding.getRoot();
        setContentView(view);

        addEvents();
    }
    public void addEvents()
    {
        // Yêu cầu 1: Khi nhập chỉ số cũ hoặc chỉ số mới thì số kWh sử dụng sẽ tự động cập nhật theo
        binding.edtOldnum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // thêm vào để không bị crash khi vừa chạy app
                if (binding.edtOldnum.getText().length() >= 1 && binding.edtNewnum.getText().length() >= 1)
                {
                    double a = Double.parseDouble(binding.edtOldnum.getText().toString());
                    double b = Double.parseDouble(binding.edtNewnum.getText().toString());
                    if (a > b)
                    {
                        Toast.makeText(MainActivity.this, "Số mới không thể nhỏ hơn số cũ!", Toast.LENGTH_SHORT).show();
                    }
                    binding.txtTongsudung.setText("" + (b - a));
                }
                // xóa luôn kết quả khi giá trị bị xóa đi
                if (binding.edtOldnum.getText().length() == 0)
                {
                    binding.txtTongsudung.setText("");
                }
            }
        });
        binding.edtNewnum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (binding.edtOldnum.getText().length() >= 1 && binding.edtNewnum.getText().length() >= 1) {
                    double a = Double.parseDouble(binding.edtOldnum.getText().toString());
                    double b = Double.parseDouble(binding.edtNewnum.getText().toString());
                    if (a > b)
                    {
                        Toast.makeText(MainActivity.this, "Số mới không thể nhỏ hơn số cũ!", Toast.LENGTH_SHORT).show();
                    }
                    binding.txtTongsudung.setText("" + (b - a));
                }
                if (binding.edtNewnum.getText().length() == 0)
                {
                    binding.txtTongsudung.setText("");
                }
            }
        });

        //event cho button SHBT
        binding.btnShbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    double a = Double.parseDouble(binding.edtOldnum.getText().toString());
                    double b = Double.parseDouble(binding.edtNewnum.getText().toString());
                    double c = Double.parseDouble(binding.edtSoho.getText().toString());
                    DecimalFormat df=new DecimalFormat("#,###");

                    if (a>b)
                    {
                        Toast.makeText(MainActivity.this, "Số mới không thể nhỏ hơn số cũ!", Toast.LENGTH_SHORT).show();
                    } else if (b-a <= 50*c)
                    {
                        binding.txtThanhtoan.setText("Tổng số tiền điện giá sinh hoạt: " + df.format((b-a) * 1484));
                    } else if (b-a <= 100*c)
                    {
                        binding.txtThanhtoan.setText("Tổng số tiền điện giá sinh hoạt: " + df.format((50*c * 1484) + ((b-a) - 50*c) * 1533));
                    } else if (b-a <= 200*c)
                    {
                        binding.txtThanhtoan.setText("Tổng số tiền điện giá sinh hoạt: " + df.format((50*c*1484) + (50*c*1533) + ((b-a) - 100*c) * 1786));
                    } else if (b-a <= 300*c)
                    {
                        binding.txtThanhtoan.setText("Tổng số tiền điện giá sinh hoạt: " + df.format((50*c*1484) + (50*c*1533) + (100*c*1786) + ((b-a) - 200*c) * 2242));
                    } else if (b-a <= 400*c)
                    {
                        binding.txtThanhtoan.setText("Tổng số tiền điện giá sinh hoạt: " + df.format((50*c*1484) + (50*c*1533) + (100*c*1786) + (100*c*2242) + ((b-a) - 300*c) * 2503));
                    } else
                    {
                        binding.txtThanhtoan.setText("Tổng số tiền điện giá sinh hoạt: " + df.format((50*c*1484) + (50*c*1533) + (100*c*1786) + (100*c*2242) + (100*c*2503) + ((b-a) - 400*c) * 2587));
                    }
                } catch(Exception e)
                {
                    Toast.makeText(MainActivity.this, "Đã có lỗi xảy ra! Kiểm tra lại thông tin!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //event cho button KD
        binding.btnKd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = Double.parseDouble(binding.edtOldnum.getText().toString());
                double b = Double.parseDouble(binding.edtNewnum.getText().toString());
                DecimalFormat df=new DecimalFormat("#,###");

                if (a>b)
                {
                    Toast.makeText(MainActivity.this, "Số mới không thể nhỏ hơn số cũ!", Toast.LENGTH_SHORT).show();
                } else
                {
                    binding.txtThanhtoan.setText("Tổng số tiền điện giá kinh doanh: " + df.format((b - a) * 2320));
                }
            }
        });

        //event cho button SX
        binding.btnSx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double a = Double.parseDouble(binding.edtOldnum.getText().toString());
                double b = Double.parseDouble(binding.edtNewnum.getText().toString());
                DecimalFormat df=new DecimalFormat("#,###");

                if (a>b)
                {
                    Toast.makeText(MainActivity.this, "Số mới không thể nhỏ hơn số cũ!", Toast.LENGTH_SHORT).show();
                } else {
                    binding.txtThanhtoan.setText("Tổng số tiền điện giá sản xuất: " + df.format((b - a) * 1518));
                }
            }
        });

        //event cho button XÓA
        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.edtOldnum.setText("");
                binding.edtNewnum.setText("");
                binding.edtSoho.setText("");
                binding.txtTongsudung.setText("");
                binding.txtThanhtoan.setText("");
                binding.edtOldnum.requestFocus();
            }
        });

        //event cho button THOÁT
        binding.btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}