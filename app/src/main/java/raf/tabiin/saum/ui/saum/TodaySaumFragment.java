package raf.tabiin.saum.ui.saum;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import raf.tabiin.saum.R;
import raf.tabiin.saum.databinding.FragmentTodaySaumBinding;

public class TodaySaumFragment extends Fragment {
    FragmentTodaySaumBinding b;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        b = FragmentTodaySaumBinding.inflate(getLayoutInflater());

        b.calculateBtn.setOnClickListener(v -> {
            try {
                calculateIftar(v);
                b.hoursFajr.setCursorVisible(false);
                b.minuteFajr.setCursorVisible(false);
                b.hoursMagrib.setCursorVisible(false);
                b.minuteMagrib.setCursorVisible(false);
            } catch (NumberFormatException e){
                b.hoursMagrib.setText(b.hoursMagrib.getText().toString().replaceAll("[\\.\\-,\\s]+", ""));
                b.minuteMagrib.setText(b.minuteMagrib.getText().toString().replaceAll("[\\.\\-,\\s]+", ""));
                b.hoursFajr.setText(b.hoursFajr.getText().toString().replaceAll("[\\.\\-,\\s]+", ""));
                b.minuteFajr.setText(b.minuteFajr.getText().toString().replaceAll("[\\.\\-,\\s]+", ""));
                Snackbar.make(v, "Заполните все поля", Snackbar.LENGTH_SHORT)
                        .setAction("А что не так?", view -> onMaterialAlert())
                        .show();
                e.printStackTrace();
            }

            //saveText();
        });

        b.hoursMagrib.setOnClickListener(v -> {
            b.hoursFajr.setCursorVisible(true);
            b.minuteFajr.setCursorVisible(true);
            b.hoursMagrib.setCursorVisible(true);
            b.minuteMagrib.setCursorVisible(true);
        });

        b.minuteMagrib.setOnClickListener(v -> {
            b.hoursFajr.setCursorVisible(true);
            b.minuteFajr.setCursorVisible(true);
            b.hoursMagrib.setCursorVisible(true);
            b.minuteMagrib.setCursorVisible(true);
        });

        b.hoursFajr.setOnClickListener(v -> {
            b.hoursFajr.setCursorVisible(true);
            b.minuteFajr.setCursorVisible(true);
            b.hoursMagrib.setCursorVisible(true);
            b.minuteMagrib.setCursorVisible(true);
        });

        b.minuteFajr.setOnClickListener(v -> {
            b.hoursFajr.setCursorVisible(true);
            b.minuteFajr.setCursorVisible(true);
            b.hoursMagrib.setCursorVisible(true);
            b.minuteMagrib.setCursorVisible(true);
        });


        return b.getRoot();
    }

    public void calculateIftar(View v) {

    }

    public void onMaterialAlert() {
        MaterialAlertDialogBuilder alert =
                new MaterialAlertDialogBuilder(getContext());

        View dialogView = getLayoutInflater()
                .inflate(R.layout.create_pamyatka_dialog, null);

        alert.setTitle("А что не так? Хмм... хороший вопрос)");
        alert.setMessage(R.string.pamyatka);
        alert.setCancelable(true);

        alert.setNegativeButton("не понял", (dialogInterface, i) -> {
            Snackbar.make(b.getRoot(), "Прочти памятку еще раз", Snackbar.LENGTH_LONG)
                    .setAction("ну ок, давай еще раз", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onMaterialAlert();
                        }
                    })
                    .show();
        });


        alert.setPositiveButton("Понял", (dialogInterface, i) -> {
            Snackbar.make(b.getRoot(), "Молодец", Snackbar.LENGTH_LONG)
                    .setAction("Спасибо", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getContext(), "Да не за что, наслаждайся", Toast.LENGTH_LONG).show();
                        }
                    })
                    .show();
        });

        alert.setView(dialogView);
        alert.show();
    }
}