package com.app.pricepal.ui.items;

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
import com.app.pricepal.databinding.FragmentItemsBinding;
import com.app.pricepal.models.items_model;
import java.util.ArrayList;
import java.util.List;

public class ItemsFragment extends Fragment {

    private FragmentItemsBinding binding;
    private final List<items_model> itemsList=new ArrayList<>();
    private List<items_model> filterItemsList=new ArrayList<>();
    private AdapterItems adapterItems;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ItemsViewModel homeViewModel =
                new ViewModelProvider(this).get(ItemsViewModel.class);
        binding = FragmentItemsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textItems;
        RecyclerView recyclerView = binding.rvProductsList;
        searchItems();
        if(itemsList.size() !=0 ) {
            textView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            adapterItems = new AdapterItems(getContext(), itemsList);
            recyclerView.setAdapter(adapterItems);
        }else{
            homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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

    private void searchItems()
    {
        itemsList.clear();
        for(int i=1;i<21;i++)
        {
            itemsList.add(new items_model(
                    i,"Item "+i,i+"mg",
                    (i*150.00)/5,"/img/item1.jpeg",
                    i,"Store "+i,true));
        }
    }
}