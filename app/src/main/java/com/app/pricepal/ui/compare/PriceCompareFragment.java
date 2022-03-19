package com.app.pricepal.ui.compare;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.app.pricepal.databinding.FragmentCompareBinding;

public class PriceCompareFragment extends Fragment {
    private FragmentCompareBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCompareBinding.inflate(inflater, container, false);
        Button searchbtn = binding.searchBTN;
        searchbtn.setOnClickListener(view -> startActivity(new Intent(getContext(),PriceCompareActivity.class)));
        View root = binding.getRoot();
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}