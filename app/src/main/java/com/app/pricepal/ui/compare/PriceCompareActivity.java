package com.app.pricepal.ui.compare;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.pricepal.R;
import com.app.pricepal.models.compare_price_model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PriceCompareActivity extends AppCompatActivity {
    ImageView itemImg;
    TextView tvItemName,tvItemQty,tvStoreName,tvPrice,tvDateFilter;
    RecyclerView recyclerView;
    private AdapterComparePriceItem adapterItems;

    final Calendar myCalendar = Calendar.getInstance();
    private String stDate="01/01/2022";

    private final List<compare_price_model> productsList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_compare_details);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        getSupportActionBar().setTitle("Price Comparison");
        initViews();
    }

    @SuppressLint("SetTextI18n")
    private void initViews() {
        itemImg = findViewById(R.id.itemImg);
        tvItemName = findViewById(R.id.tvItemName);
        tvItemQty = findViewById(R.id.tvItemQty);
        tvStoreName = findViewById(R.id.tvStoreName);
        tvPrice = findViewById(R.id.tvPrice);
        recyclerView = findViewById(R.id.rvPriceList);
        tvDateFilter = findViewById(R.id.tvDateFilter);

        if (getIntent().getStringExtra("itemName") != null) {
            tvItemName.setText(getIntent().getStringExtra("itemName"));
        }
        getProducts();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterItems = new AdapterComparePriceItem(this, productsList);
        recyclerView.setAdapter(adapterItems);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                stDate=dayOfMonth+"/"+monthOfYear +"/"+year;
                tvDateFilter.setText("Showing results for- "+stDate);
                getProducts();
            }};
        /*tvDateFilter.setOnClickListener(view -> {
            DatePickerDialog datePickerDialog=  new DatePickerDialog(PriceCompareActivity.this,R.style.DialogTheme, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
            datePickerDialog.show();
        });*/
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getProducts()
    {
        productsList.clear();
        for(int i=1;i<5;i++)
        {
            productsList.add(new compare_price_model(
                    i,"Item "+i,i+"mg",
                    (i*150.00)/5,(i*150.00)/5,
                    "/img/item1.jpeg",((i*150.00)-10)/5,true));
        }
    }
}