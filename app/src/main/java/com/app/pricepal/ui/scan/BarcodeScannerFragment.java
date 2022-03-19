package com.app.pricepal.ui.scan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.app.pricepal.databinding.FragmentBarcodeBinding;
import com.google.android.material.snackbar.Snackbar;

public class BarcodeScannerFragment extends Fragment {

    private FragmentBarcodeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BarcodeScannerViewModel barcodeScannerViewModel =
                new ViewModelProvider(this).get(BarcodeScannerViewModel.class);
        binding = FragmentBarcodeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.tvEmptyData;
        final TextView tvSearchProduct = binding.tvSearchProduct;
        final EditText etSearchProduct= binding.etSearchProduct;
        tvSearchProduct.setOnClickListener(view -> {
            String intentData=etSearchProduct.getText().toString().trim();
            if(!intentData.isEmpty())
                startActivity(new Intent(getContext(), ProductDetailsActivity.class).putExtra("barcode", intentData));
            else
                Snackbar.make(view, "barcode should not be empty!", Snackbar.LENGTH_SHORT)
                        .setAction("CLOSE", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        })
                        .setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                        .show();
        });
        final ImageView ivScanCode= binding.tvBarcodeScan;
        ivScanCode.setOnClickListener(view -> startActivity(new Intent(getContext(), BarcodeScannerActivity.class)));
        barcodeScannerViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}