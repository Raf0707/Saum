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
import raf.tabiin.saum.adapters.SaumAdapter;
import raf.tabiin.saum.databinding.FragmentMTBinding;
import raf.tabiin.saum.domain.database.SaumDatabase;
import raf.tabiin.saum.domain.models.SaumItem;
import raf.tabiin.saum.viewmodel.SaumViewModel;


public class MTFragment extends Fragment implements SaumAdapter.HandleCounterClick {
    FragmentMTBinding b;
    private SaumAdapter saumAdapter;

    private SaumItem saumItem;

    private List<SaumItem> saumItems = new ArrayList<>();
    private SaumViewModel saumViewModel;
    private SaumItem saumForEdit;
    private SaumDatabase saumDatabase;
    private SaumAdapter.MyViewHolder holder;
    private boolean edit;
    private boolean done;
    private String day;
    private String month;
    private int progress;
    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        b = FragmentMTBinding.inflate(getLayoutInflater());

        saumAdapter = new SaumAdapter(getContext(), this);

        saumViewModel = new ViewModelProvider(this,
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getActivity().getApplication()))
                .get(SaumViewModel.class);

        b.fabAddSaum.setOnClickListener(v -> {
            onMaterialAlert(false);
        });

        b.searchSaums.clearFocus();
        b.searchSaums.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //filterList(s);
                saumViewModel.findByNames(s);
                return true;
            }
        });

        b.recycleSaum.addOnScrollListener (
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        if (dy < 0 && !b.fabAddSaum.isShown())
                            b.fabAddSaum.show();
                        else if (dy > 0 && b.fabAddSaum.isShown())
                            b.fabAddSaum.hide();
                    }
                }
        );

        b.searchSaums.setOnClickListener(v -> {
            b.searchSaums.clearFocus();
        });

        initRecycleView();
        initViewModel();
        saumViewModel.getAllSaumList();

        return b.getRoot();
    }

    public void initRecycleView() {
        b.recycleSaum.setLayoutManager(new LinearLayoutManager(getContext()));
        b.recycleSaum.setHasFixedSize(true);
        b.recycleSaum.setAdapter(saumAdapter);
    }

    public void initViewModel() {
        saumViewModel = new ViewModelProvider(this)
                .get(SaumViewModel.class);
        saumViewModel.getSaumlistObserver()
                .observe(requireActivity(), saumItems -> {
                    if (saumItems == null) {
                        b.noRes.setVisibility(View.VISIBLE);
                        b.recycleSaum.setVisibility(View.GONE);
                    } else {
                        saumAdapter.setSaumList(saumItems);
                        b.recycleSaum.setVisibility(View.VISIBLE);
                        b.noRes.setVisibility(View.GONE);
                    }
                });
    }

    public void onMaterialAlert(boolean isForEdit) {
        MaterialAlertDialogBuilder alert =
                new MaterialAlertDialogBuilder(getContext());

        View dialogView = getLayoutInflater()
                .inflate(R.layout.create_saum_dialog, null);

        alert.setTitle("Новый день поста");
        alert.setMessage("введите день и месяц");
        alert.setCancelable(true);

        EditText saumDay = dialogView.findViewById(R.id.saumDay);
        EditText saumMonth = dialogView.findViewById(R.id.saumMonth);
        TextView saumProgress = dialogView.findViewById(R.id.saumProgress);
        CheckBox saumDone = dialogView.findViewById(R.id.saumDone);

        if (isForEdit) {
            alert.setTitle("Изменить данные о посте");
            saumDay.setText(saumForEdit.day);
            saumMonth.setText(saumForEdit.month);
            saumProgress.setText(String.valueOf(saumForEdit.progress));
            saumDone.setChecked(saumForEdit.completed);

            //saumViewModel.update(saumForEdit);
        }

        alert.setNegativeButton("Отмена", (dialogInterface, i) -> {

        });


        alert.setPositiveButton("ОК", (dialogInterface, i) -> {
            if (saumDay.getText().toString().length() == 0) {
                saumDay.setText("пн / чт");
            }

            if (saumMonth.getText().toString().length() == 0) {
                saumMonth.setText("Введите месяц (по хиджре)");
            }

            if (isForEdit) {
                saumForEdit.day = saumDay.getText().toString();
                saumForEdit.month = saumMonth.getText().toString();
                saumForEdit.progress = Integer.parseInt(saumProgress.getText().toString());
                saumForEdit.completed = saumDone.isChecked();

                saumViewModel.update(saumForEdit);
            } else {
                saumViewModel.insert(saumDay.getText().toString(), saumMonth.getText().toString());
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
                String updateDay = data.getStringExtra("updateDay");
                String updateMonth = data.getStringExtra("updateMonth");
                int updateProgress = data.getIntExtra("updateProgress", progress);
                boolean updateDone = data.getBooleanExtra("updateDone", done);

                SaumItem saumItem = new SaumItem(saumForEdit.getId(), updateDay, updateMonth, updateProgress, updateDone);

                day =  updateDay;
                month = updateMonth;
                progress = updateProgress;
                done = updateDone;

                saumViewModel.update(saumItem);
            }
        }
    }

    @Override
    public void itemClick(SaumItem counterItem) {

    }

    @Override
    public void deleteItem(SaumItem saumItem) {
        saumViewModel.delete(saumItem);
    }

    @Override
    public void editItem(SaumItem saumItem) {
        this.saumForEdit = saumItem;
        onMaterialAlert(true);
    }
}