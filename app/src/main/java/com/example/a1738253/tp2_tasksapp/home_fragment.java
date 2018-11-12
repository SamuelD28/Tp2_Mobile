package com.example.a1738253.tp2_tasksapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class home_fragment extends Fragment {

    private FragmentTransaction FragTransaction;
    private FragmentManager FragManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.home_fragment, container, false);
        FragTransaction = getChildFragmentManager().beginTransaction();
        FragManager = getFragmentManager();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        GenerateFragments();
    }

    private void GenerateFragments()
    {
        for(int i =0 ; i < 4 ; i++)
        {
            Fragment fragment = new carte_fragment();
            FragTransaction.add(R.id.CarteFragmentContainer, fragment);
        }
        FragTransaction.commit();

    }
}
