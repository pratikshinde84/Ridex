package com.developer_rahul_pratik.ridex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class WalletFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);

        initViews(view);

        return view;
    }

    private void initViews(View view) {
        MaterialButton btnAddMoney = view.findViewById(R.id.btnAddMoney);
        btnAddMoney.setOnClickListener(v -> 
            Toast.makeText(getContext(), "Add Money Clicked", Toast.LENGTH_SHORT).show()
        );

        view.findViewById(R.id.toolbar).setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().onBackPressed();
            }
        });
    }
}