package com.app.pricepal.ui.compare;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.app.pricepal.R;
import com.app.pricepal.databinding.FragmentCompareBinding;
import com.app.pricepal.ui.stores.StoresViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PriceCompareFragment extends Fragment {

    private FragmentCompareBinding binding;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StoresViewModel storesViewModel =
                new ViewModelProvider(this).get(StoresViewModel.class);

        String[] items_list = getResources().getStringArray(R.array.items_cat);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Products");

        binding = FragmentCompareBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getContext(),android.R.layout.select_dialog_item,items_list);
        //Getting the instance of AutoCompleteTextView
        AutoCompleteTextView actv =  binding.searchText;
        actv.setThreshold(3);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getContext(), PriceCompareActivity.class)
                        .putExtra("itemName", adapterView.getAdapter().getItem(i).toString())
                );

            }
        });

        ArrayList<String> list = new ArrayList<String>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren())
                {
                    if(dsp!= null){
                        String name = dsp.child("itemName").getValue(String.class);
                        // Toast.makeText(getContext(), "item:"+name, Toast.LENGTH_SHORT).show();
                        list.add(name);
                        actv.setThreshold(3);//will start working from first character
                        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}