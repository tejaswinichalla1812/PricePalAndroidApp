package com.app.pricepal.ui.stores;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.pricepal.databinding.FragmentStoresBinding;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import  com.app.pricepal.models.stores_model;
public class StoresFragment extends Fragment {

    private FragmentStoresBinding binding;
    private TextView textView;
    private RecyclerView recyclerView;
    private AdapterStores adapterStores;
    private List<stores_model> storesList=new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StoresViewModel storesViewModel =
                new ViewModelProvider(this).get(StoresViewModel.class);
        binding = FragmentStoresBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        textView= binding.textStores;
        recyclerView= binding.rvStoresList;
        getStores();
        if(storesList.size() !=0 ) {
            textView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            adapterStores = new AdapterStores(getContext(), storesList);
            recyclerView.setAdapter(adapterStores);
        }else{
            storesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
            textView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getStores()
    {
        final DecimalFormat df = new DecimalFormat("0.00");
        storesList.clear();
        for(int i=1;i<5;i++)
        {
            storesList.add(new stores_model(
                    i,"Store "+i,"street "+i,
                    (i*143.12)/45,(i*243.12)/81,
                    "images/stores/store"+i+".jpeg",true));
        }
    }
}