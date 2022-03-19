package com.app.pricepal.ui.stores;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.pricepal.R;
import com.app.pricepal.models.items_model;
import com.app.pricepal.ui.items.AdapterItems;
import java.util.ArrayList;
import java.util.List;

public class StoreProductsActivity extends AppCompatActivity {
    private final List<items_model> itemsList=new ArrayList<>();
    private List<items_model> filterItemsList=new ArrayList<>();
    private AdapterItems adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_products_activity);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        initViews();
    }

    @SuppressLint("SetTextI18n")
    private void initViews() {
        if (getIntent().getStringExtra("storeName") != null) {
            getSupportActionBar().setTitle(getIntent().getStringExtra("storeName"));
        }
        final TextView textView = findViewById(R.id.text_items);
        final RecyclerView recyclerView = findViewById(R.id.rvProductsList);
        searchItems();
        if(itemsList.size() !=0 ) {
            textView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            adapterItems = new AdapterItems(this, itemsList);
            recyclerView.setAdapter(adapterItems);
        }else{
            textView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
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

    private void searchItems()
    {
        itemsList.clear();
        for(int i=1;i<21;i++)
        {
            itemsList.add(new items_model(
                    i,"Item "+i,i+"mg",
                    (i*150.00)/5,"/img/item1.jpeg",
                    i,"Store" +i,true));
        }
    }

}