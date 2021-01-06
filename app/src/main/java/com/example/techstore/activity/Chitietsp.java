package com.example.techstore.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.techstore.R;
import com.example.techstore.model.Giohang;
import com.example.techstore.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class Chitietsp extends AppCompatActivity {

    Toolbar tbChitiet;
    ImageView ivChitiet;
    TextView tvTen,tvGia, tvMota;
    Spinner spinner;
    Button btdatmua, btGiohang;

    int id=0;
    String Tenchitiet="";
    int Giachitiet=0;
    String Hinhanhchitiet="";
    String Motachitiet="";
    int idsanpham=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsp);
        tbChitiet = findViewById(R.id.tbChitietsp);
        ivChitiet = findViewById(R.id.ivChitietsp);
        tvTen = findViewById(R.id.tvtenchitietsp);
        tvGia = findViewById(R.id.tvgiachitietsp);
        tvMota = findViewById(R.id.tvmotachitietsp);
        spinner = findViewById(R.id.spinner);
        btdatmua = findViewById(R.id.btdatmua);
        btGiohang = findViewById(R.id.btGiohang);



        ActionToolbar();
        GetInformation();
        CatchEventSpinner();
        EventButton();
        Giohang();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public void Giohang(){
        btGiohang.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), com.example.techstore.activity.Giohang.class);
            startActivity(intent);
        });
    }

    private void EventButton() {
        btdatmua.setOnClickListener(v -> {
            if (MainActivity.manggiohang.size()>0){
                int sl= Integer.parseInt(spinner.getSelectedItem().toString());
                boolean exists = false;
                for (int i=0; i<MainActivity.manggiohang.size(); i++){
                    if (MainActivity.manggiohang.get(i).getIdsp() == id){
                        MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp() + sl);
                        if (MainActivity.manggiohang.get(i).getSoluongsp()>=10){
                            MainActivity.manggiohang.get(i).setSoluongsp(10);
                        }
                        MainActivity.manggiohang.get(i).setGiasp(Giachitiet * MainActivity.manggiohang.get(i).getSoluongsp());
                        exists = true;
                    }
                }
                if (exists == false){
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long Giamoi = soluong * Giachitiet;
                    MainActivity.manggiohang.add(new Giohang(id,Tenchitiet,Giamoi,Hinhanhchitiet,soluong));
                }
            }else {
                int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                long Giamoi = soluong * Giachitiet;
                MainActivity.manggiohang.add(new Giohang(id,Tenchitiet,Giamoi,Hinhanhchitiet,soluong));
            }
            Intent intent = new Intent(getApplicationContext(), com.example.techstore.activity.Giohang.class);
            startActivity(intent);
        });
    }

    private void CatchEventSpinner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void GetInformation() {

        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanpham.getID();
        Tenchitiet = sanpham.getTensanpham();
        Giachitiet = sanpham.getGiasanpham();
        Hinhanhchitiet = sanpham.getHinhanhsanpham();
        Motachitiet = sanpham.getMotasanpham();
        idsanpham = sanpham.getIDSanpham();

        tvTen.setText(Tenchitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvGia.setText("Giá: " + decimalFormat.format(Giachitiet) +"₫");
        tvMota.setText(Motachitiet);
        Picasso.get().load(Hinhanhchitiet)
                .placeholder(R.drawable.noimg)
                .error(R.drawable.error)
                .into(ivChitiet);
    }

    private void ActionToolbar(){
        tbChitiet.setNavigationIcon(R.drawable.ic_back);
        tbChitiet.setNavigationOnClickListener(v -> finish());
    }

}