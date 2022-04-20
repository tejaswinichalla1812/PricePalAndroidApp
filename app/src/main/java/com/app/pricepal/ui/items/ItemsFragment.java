package com.app.pricepal.ui.items;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.pricepal.databinding.FragmentItemsBinding;
import com.app.pricepal.models.items_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ItemsFragment extends Fragment {

    private FragmentItemsBinding binding;
    private final List<items_model> itemsList=new ArrayList<>();
    private List<items_model> filterItemsList=new ArrayList<>();
    private AdapterItems adapterItems;
    private TextView textView;
    private ItemsViewModel homeViewModel;
    private RecyclerView recyclerView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(ItemsViewModel.class);
        binding = FragmentItemsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        textView = binding.textItems;
        recyclerView= binding.rvProductsList;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Products");
//        addStore();
        fetchProducts();
        updateUi();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void fetchProducts()
    {
        itemsList.clear();
        databaseReference.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    try {
                        int id = ds.child("id").getValue(Integer.class);
                        String itemName = ds.child("itemName").getValue(String.class);
                        String itemQty = ds.child("itemQty").getValue(String.class);
                        double itemPrice = ds.child("itemPrice").getValue(double.class);
                        String itemImg = ds.child("itemImg").getValue(String.class);
                        int storeId = ds.child("storeId").getValue(int.class);
                        String storeName = ds.child("storeName").getValue(String.class);
                        boolean itemStatus = ds.child("itemStatus").getValue(boolean.class);
                        itemsList.add(new items_model(
                                id, itemName, itemQty,
                                itemPrice, itemImg,
                                storeId, storeName, itemStatus)
                        );

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                updateUi();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private void addStore(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Date now = new Date();
                int id = Integer.parseInt(new SimpleDateFormat("ddHHmmss",  Locale.US).format(now));
                databaseReference.child(id+"").setValue(new items_model(
                        id,
                        "water bottle",
                        "250ml",
                        25.00,
                        "https://www.kindpng.com/picc/m/39-393575_mineral-water-bottle-png-transparent-png.png",
                        26003156,
                        "Walmart",
                        true));
                // after adding this data we are showing toast message.
                Toast.makeText(getContext(), "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(getContext(), "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUi(){
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
    }
}