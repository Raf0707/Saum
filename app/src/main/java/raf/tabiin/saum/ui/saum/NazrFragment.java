package raf.tabiin.saum.ui.saum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import raf.tabiin.saum.R;
import raf.tabiin.saum.adapters.NazrAdapter;
import raf.tabiin.saum.databinding.FragmentNazrBinding;
import raf.tabiin.saum.domain.database.SaumDatabase;
import raf.tabiin.saum.domain.models.NazrItem;
import raf.tabiin.saum.viewmodel.NazrViewModel;

public class NazrFragment extends Fragment {
    FragmentNazrBinding b;
    private NazrAdapter nazrAdapter;

    private NazrItem nazrItem;

    private List<NazrItem> nazrItems = new ArrayList<>();
    private NazrViewModel nazrViewModel;
    private NazrItem nazrForEdit;
    private SaumDatabase saumDatabase;
    private NazrAdapter.MyViewHolder holder;
    private boolean edit;
    private boolean done;
    private String title;
    private String text;
    private int progress;
    private int id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        b = FragmentNazrBinding.inflate(getLayoutInflater());

        nazrAdapter = new NazrAdapter(getContext(), new NazrAdapter.HandleCounterClick() {
            @Override
            public void itemClick(NazrItem nazrItem) {

            }

            @Override
            public void deleteItem(NazrItem nazrItem) {
                nazrViewModel.delete(nazrItem);
            }

            @Override
            public void editItem(NazrItem nazrItem) {
                nazrForEdit = nazrItem;
                onMaterialAlert(true);
            }

            @Override
            public void updateItem(NazrItem nazrItem) {
                nazrViewModel.update(nazrItem);
            }
        });

        nazrViewModel = new ViewModelProvider(this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getActivity().getApplication()))
                .get(NazrViewModel.class);

        b.fabAddNazr.setOnClickListener(v -> {
            onMaterialAlert(false);
        });

        b.searchNazrs.clearFocus();
        b.searchNazrs.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //filterList(s);
                nazrViewModel.findByNames(s);
                return true;
            }
        });

        b.recycleNazr.addOnScrollListener (
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        if (dy < 0 && !b.fabAddNazr.isShown())
                            b.fabAddNazr.show();
                        else if (dy > 0 && b.fabAddNazr.isShown())
                            b.fabAddNazr.hide();
                    }
                }
        );

        b.searchNazrs.setOnClickListener(v -> {
            b.searchNazrs.clearFocus();
        });

        initRecycleView();
        initViewModel();
        nazrViewModel.getNazrlistObserver();

        return b.getRoot();
    }

    public void initRecycleView() {
        b.recycleNazr.setLayoutManager(new LinearLayoutManager(getContext()));
        b.recycleNazr.setHasFixedSize(true);
        b.recycleNazr.setAdapter(nazrAdapter);
    }

    public void initViewModel() {
        nazrViewModel = new ViewModelProvider(this)
                .get(NazrViewModel.class);
        nazrViewModel.getNazrlistObserver()
                .observe(requireActivity(), nazrItems -> {
                    if (nazrItems == null) {
                        b.noRes.setVisibility(View.VISIBLE);
                        b.recycleNazr.setVisibility(View.GONE);
                    } else {
                        nazrAdapter.setNazrList(nazrItems);
                        b.recycleNazr.setVisibility(View.VISIBLE);
                        b.noRes.setVisibility(View.GONE);
                    }
                });
    }

    public void onMaterialAlert(boolean isForEdit) {
        MaterialAlertDialogBuilder alert =
                new MaterialAlertDialogBuilder(getContext());

        View dialogView = getLayoutInflater()
                .inflate(R.layout.create_nazr_dialog, null);

        alert.setTitle("Невыполненное обещание");
        alert.setMessage("введите название и описание");
        alert.setCancelable(true);

        EditText nazrTitle = dialogView.findViewById(R.id.nazrTitleCreate);
        EditText nazrText = dialogView.findViewById(R.id.nazrTextCreate);
        TextView nazrProgress = dialogView.findViewById(R.id.nazrProgress);
        CheckBox day1 = dialogView.findViewById(R.id.nazrDay1);
        CheckBox day2 = dialogView.findViewById(R.id.nazrDay2);
        CheckBox day3 = dialogView.findViewById(R.id.nazrDay3);
        CheckBox nazrDone = dialogView.findViewById(R.id.nazrDone);

        if (isForEdit) {
            alert.setTitle("Изменить данные");
            nazrTitle.setText(nazrForEdit.titleNazr);
            nazrText.setText(nazrForEdit.textNazr);
            nazrProgress.setText(String.valueOf(nazrForEdit.progress));
            nazrDone.setChecked(nazrForEdit.completed);

            //nazrViewModel.update(nazrForEdit);
        }

        alert.setNegativeButton("Отмена", (dialogInterface, i) -> {

        });


        alert.setPositiveButton("ОК", (dialogInterface, i) -> {
            if (nazrTitle.getText().toString().length() == 0) {
                nazrTitle.setText("Невыполненное обещание " + id);
            }

            if (nazrText.getText().toString().length() == 0) {
                nazrText.setText("Введите текст невыполненного обещания");
            }

            if (isForEdit) {
                nazrForEdit.titleNazr = nazrTitle.getText().toString();
                nazrForEdit.textNazr = nazrText.getText().toString();
                nazrForEdit.progress = Integer.parseInt(nazrProgress.getText().toString());
                nazrForEdit.completed = nazrDone.isChecked();

                nazrViewModel.update(nazrForEdit);
            } else {
                nazrViewModel.insert(nazrTitle.getText().toString(), nazrText.getText().toString());
            }
        });

        alert.setView(dialogView);
        alert.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Проверяем по коду нужный результат
        if(requestCode == 120) {
            if(resultCode == Activity.RESULT_OK) {
                //Действия при возврате результата
                String updateTitle = data.getStringExtra("updateDay");
                String updateText = data.getStringExtra("updateMonth");
                int updateProgress = data.getIntExtra("updateProgress", progress);
                boolean updateDone = data.getBooleanExtra("updateDone", done);

                NazrItem nazrItem = new NazrItem(nazrForEdit.getId(), updateTitle, updateText, updateProgress, updateDone);

                title =  updateTitle;
                text = updateText;
                progress = updateProgress;
                done = updateDone;

                nazrViewModel.update(nazrItem);
            }
        }
    }

    /*@Override
    public void itemClick(NazrItem nazrItem) {

    }

    @Override
    public void deleteItem(NazrItem nazrItem) {
        nazrViewModel.delete(nazrItem);
    }

    @Override
    public void editItem(NazrItem nazrItem) {
        this.nazrForEdit = nazrItem;
        onMaterialAlert(true);
    }*/
}