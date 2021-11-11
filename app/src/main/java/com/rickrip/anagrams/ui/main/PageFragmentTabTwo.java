package com.rickrip.anagrams.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rickrip.anagrams.MyTextWatcher;
import com.rickrip.anagrams.R;
import com.rickrip.anagrams.databinding.FragmentTabTwoBinding;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

@SuppressLint("RestrictedApi")
public class PageFragmentTabTwo extends Fragment{

    public static final String ARG_PAGE = "ARG_PAGE";

    private FragmentTabTwoBinding binding;
    private int pageInt; // index of a page
    public AppCompatEditText tabTwoEditableText;
    MyTextWatcher textWatcher = new MyTextWatcher();

    public static PageFragmentTabTwo newInstance(int page) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragmentTabTwo fragment = new PageFragmentTabTwo();
        fragment.setArguments(args);

        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                pageInt = getArguments().getInt(ARG_PAGE);
            }

    }

    @Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {

        binding = FragmentTabTwoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tabTwoEditableText = binding.etMainTwo;
        tabTwoEditableText.setText(getResources().getString(R.string.default_filter_symbols)); // setting default filter
        tabTwoEditableText.addTextChangedListener(textWatcher);

        root.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {

            @Override
            public void onLayoutChange(View v, int left, int top, int right,
                                       int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                Bundle result = new Bundle();
                result.putString("bundleKey", binding.etMainTwo.getText().toString());
                getParentFragmentManager().setFragmentResult("requestKey", result);
            }});

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
