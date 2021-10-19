package com.example.testmaddafakka.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import com.example.testmaddafakka.model.ICategory;
import com.example.testmaddafakka.R;
import com.example.testmaddafakka.viewmodel.PreferencesViewModel;

import java.util.List;

/**
 * A view ....
 *
 * @author Albin Sundström
 */

public class PreferencesView extends Fragment {
    private View view;
    private PreferencesViewModel viewModel;
    private SearchResultsView searchResults;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_preferences_view, container, false);
        FragmentContainerView fcv = view.findViewById(R.id.fcvPrefs);

        fcv.setVisibility(View.INVISIBLE);

        searchResults = new SearchResultsView();



        viewModel = new ViewModelProvider(this).get(PreferencesViewModel.class);
        viewModel.init(requireContext());
        viewModel.getCategories().observe(getViewLifecycleOwner(), this::updateMediaDisplayed);



        SearchView actorSearchView = view.findViewById(R.id.actorSearch);
        actorSearchView.setIconifiedByDefault(false);
        actorSearchView.setInputType(61); // 61 corresponds to InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME.
        actorSearchView.setQueryHint("Search");
        //actorSearchView.setSubmitButtonEnabled(true);



        actorSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String name) {
                // This method gets query after search button or enter is pressed
                viewModel.search(name);
                fcv.setVisibility(View.VISIBLE);
                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                transaction.replace(R.id.fcvPrefs, searchResults).commit();
                fcv.bringToFront();

                System.out.println(name + " submit");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String name) {
                // This method gets query after every change
                return false;
            }
        });

        SearchView directorSearchView = view.findViewById(R.id.directorSearch);
        directorSearchView.setIconifiedByDefault(false);
        directorSearchView.setQueryHint("Search");

        directorSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String name) {
                viewModel.search(name);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String name) {
                return false;
            }
        });


        Spinner spinner = view.findViewById(R.id.genreSpinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String genre = spinner.getSelectedItem().toString();
                viewModel.loadSelectedCategory(genre);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }
    public void setSpinner(Spinner spinner, ArrayAdapter<CharSequence> content){
        // Specify the layout to use when the list of choices appears
        content.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(content);
    }

    private void updateMediaDisplayed(List<ICategory> categories) {
        Spinner spinner = view.findViewById(R.id.genreSpinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        for(int i = 0; i < categories.size(); i++){
            spinnerAdapter.add(categories.get(i).getName());
        }
        spinnerAdapter.notifyDataSetChanged();
    }


}