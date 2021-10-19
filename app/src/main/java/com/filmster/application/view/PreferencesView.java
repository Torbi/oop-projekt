package com.filmster.application.view;

import android.app.appsearch.SearchResults;
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

import com.filmster.application.R;
import com.filmster.application.model.ICategory;
import com.filmster.application.model.IMedia;
import com.filmster.application.viewmodel.PreferencesViewModel;

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

        searchResults = new SearchResultsView();

        FragmentContainerView fcv = view.findViewById(R.id.fcvPrefs);
        fcv.setVisibility(View.INVISIBLE);

        initAndListen2ViewModel();
        initActorSearchView();
        initDirectorSearchView();
        initSpinnerListener();

        return view;
    }

    private void initDirectorSearchView() {
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
    }




    private void initActorSearchView() {
        FragmentContainerView fcv = view.findViewById(R.id.fcvPrefs);

        fcv.setVisibility(View.INVISIBLE);
        SearchView actorSearchView = view.findViewById(R.id.actorSearch);
        actorSearchView.setIconifiedByDefault(false);
        actorSearchView.setInputType(61); // 61 corresponds to InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME.
        actorSearchView.setQueryHint("Search");
        //actorSearchView.setSubmitButtonEnabled(true);

        initSearchViewListener(actorSearchView, fcv);

    }
    private void initSearchViewListener(SearchView actorSearchView, FragmentContainerView fcv) {
        actorSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String name) {
                // This method gets query after search button or enter is pressed

                viewModel.search(name);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                searchResults = new SearchResultsView();
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
    }

    private void initAndListen2ViewModel() {
        viewModel = new ViewModelProvider(this).get(PreferencesViewModel.class);
        viewModel.init(requireContext());
        viewModel.getCategories().observe(getViewLifecycleOwner(), this::populateSpinner);

    }

    private void initSpinnerListener() {
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

    }

    private void populateSpinner(List<ICategory> categories) {
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