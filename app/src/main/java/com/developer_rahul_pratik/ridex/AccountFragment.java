package com.developer_rahul_pratik.ridex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AccountFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        setupMenu(view);

        return view;
    }

    private void setupMenu(View view) {
        // Find the included layouts and set their data
        setupMenuItem(view.findViewById(R.id.menuEditProfile), R.drawable.ic_launcher_foreground, getString(R.string.edit_profile));
        setupMenuItem(view.findViewById(R.id.menuSavedPlaces), android.R.drawable.ic_menu_save, getString(R.string.saved_places));
        setupMenuItem(view.findViewById(R.id.menuPaymentMethods), android.R.drawable.ic_menu_agenda, getString(R.string.payment_methods));
        setupMenuItem(view.findViewById(R.id.menuRideHistory), android.R.drawable.ic_menu_recent_history, getString(R.string.ride_history));
        setupMenuItem(view.findViewById(R.id.menuRefer), android.R.drawable.ic_menu_send, getString(R.string.refer_earn));
        setupMenuItem(view.findViewById(R.id.menuHelp), android.R.drawable.ic_menu_help, getString(R.string.help_support));
        setupMenuItem(view.findViewById(R.id.menuSettings), android.R.drawable.ic_menu_preferences, getString(R.string.settings));

        view.findViewById(R.id.btnLogout).setOnClickListener(v -> 
            Toast.makeText(getContext(), "Logout Clicked", Toast.LENGTH_SHORT).show()
        );
    }

    private void setupMenuItem(View itemView, int iconRes, String title) {
        ImageView icon = itemView.findViewById(R.id.ivMenuIcon);
        TextView tvTitle = itemView.findViewById(R.id.tvMenuTitle);

        icon.setImageResource(iconRes);
        tvTitle.setText(title);

        itemView.setOnClickListener(v -> 
            Toast.makeText(getContext(), title + " Clicked", Toast.LENGTH_SHORT).show()
        );
    }
}