package com.google.codelabs.mdc.java.shrine.design;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.codelabs.mdc.java.shrine.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class MainScreenFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_screen_fragment, container, false);

        return view;
    }
}
