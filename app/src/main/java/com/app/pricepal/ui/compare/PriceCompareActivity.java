package com.app.pricepal.ui.compare;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.pricepal.R;
import com.app.pricepal.models.compare_price_model;
import java.util.ArrayList;
import java.util.List;

public class PriceCompareActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private AdapterComparePriceItem adapterItems;

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
        recyclerView = findViewById(R.id.rvPriceList);
        getProducts();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterItems = new AdapterComparePriceItem(this, productsList);
        recyclerView.setAdapter(adapterItems);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
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
                    i,"Store "+i,"1 Ltr",
                    (i*150.00)/5,(i*150.00)/5,
                    "/img/item1.jpeg",((i*15.00)-1)/5,true));
        }
    }
}