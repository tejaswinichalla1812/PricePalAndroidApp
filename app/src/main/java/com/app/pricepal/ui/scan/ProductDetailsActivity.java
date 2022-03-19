package com.app.pricepal.ui.scan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.pricepal.R;

public class ProductDetailsActivity extends AppCompatActivity {
    ImageView itemImg;
    TextView tvItemName,tvItemQty,tvStoreName,tvPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        getSupportActionBar().setTitle("Product Details");
        initViews();
    }

    @SuppressLint("SetTextI18n")
    private void initViews() {
        itemImg = findViewById(R.id.itemImg);
        tvItemName = findViewById(R.id.tvItemName);
        tvItemQty = findViewById(R.id.tvItemQty);
        tvStoreName = findViewById(R.id.tvStoreName);
        tvPrice = findViewById(R.id.tvPrice);
        if (getIntent().getStringExtra("barcode") != null) {
            tvItemName.setText("id : " + getIntent().getStringExtra("barcode"));
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
}