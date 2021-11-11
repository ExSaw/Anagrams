package com.rickrip.anagrams.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rickrip.anagrams.AnagramMaker;
import com.rickrip.anagrams.databinding.FragmentTabOneBinding;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

public class PageFragmentTabOne extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    private FragmentTabOneBinding binding;
    private int pageInt; // index of a page
    private String filterStr;
    AnagramMaker anagramMaker = new AnagramMaker();

    public static PageFragmentTabOne newInstance(int page) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragmentTabOne fragment = new PageFragmentTabOne();
        fragment.setArguments(args);

        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                pageInt = getArguments().getInt(ARG_PAGE);
            }

            getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
                @Override
                public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {

                    filterStr = bundle.getString("bundleKey");
                }
            });

        }

    @Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        binding = FragmentTabOneBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final AppCompatEditText editTextIn = binding.etMainOne;
        final TextView textViewOut = binding.tvLabelOut;
        final Button buttonConvert = binding.btnConvert;

            buttonConvert.setVisibility(View.VISIBLE);

        buttonConvert.setOnClickListener(v -> {
            textViewOut.setText(anagramMaker.getAnagram((Objects.requireNonNull(editTextIn.getText().toString())), filterStr));
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
