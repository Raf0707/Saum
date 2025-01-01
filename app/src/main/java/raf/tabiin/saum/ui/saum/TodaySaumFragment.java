package raf.tabiin.saum.ui.saum;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import raf.tabiin.saum.R;
import raf.tabiin.saum.databinding.FragmentTodaySaumBinding;

public class TodaySaumFragment extends Fragment {

    private static final String PREFS_NAME = "SaumPrefs";
    private static final String FAJR_HOUR_KEY = "fajrHour";
    private static final String FAJR_MINUTE_KEY = "fajrMinute";
    private static final String MAGHRIB_HOUR_KEY = "maghribHour";
    private static final String MAGHRIB_MINUTE_KEY = "maghribMinute";
    private static final String FAJR_KEY = "fajrTime";
    private static final String MAGHRIB_KEY = "maghribTime";

    private boolean isUserTyping = false;

    FragmentTodaySaumBinding b;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable updateTimeTask;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        b = FragmentTodaySaumBinding.inflate(getLayoutInflater());

        // Initialize update time task
        loadTime();
        updateTimeTask = new Runnable() {
            @Override
            public void run() {
                if (!fieldsNotEmpty()) {
                    Snackbar.make(b.getRoot(), "Заполните все поля",Snackbar.LENGTH_SHORT).show();
                } else {
                    updateRemainingTime();
                    handler.postDelayed(this, 1000);
                }
            }
        };

        handler.post(updateTimeTask);

        setCursorVisibilityListeners();

        b.calculateBtn.setOnClickListener(v -> {

            if (
                    b.hoursFajr.getText().toString().isEmpty()
                            || b.minuteFajr.getText().toString().isEmpty()
                            || b.hoursMagrib.getText().toString().isEmpty()
                            || b.minuteMagrib.getText().toString().isEmpty()
            ) {
                Snackbar.make(v, "Заполните все поля", Snackbar.LENGTH_SHORT)
                        .setAction("А что не так?", view -> onMaterialAlert())
                        .show();

            } else {
                try {
                    if (!fieldsNotEmpty()) {
                        if (isContentLastTime()) {
                            loadTimeFromClipboard();
                            updateRemainingTime();
                        } else {
                            showSnackbar(v, "Введите время фаджра и магриба");
                        }
                    }
                    calculateIftar(v);
                    b.hoursFajr.setCursorVisible(false);
                    b.minuteFajr.setCursorVisible(false);
                    b.hoursMagrib.setCursorVisible(false);
                    b.minuteMagrib.setCursorVisible(false);
                    updateRemainingTime();
                } catch (NumberFormatException e) {
                    b.hoursMagrib.setText(b.hoursMagrib.getText().toString().replaceAll("[\\.\\-,\\s]+", ""));
                    b.minuteMagrib.setText(b.minuteMagrib.getText().toString().replaceAll("[\\.\\-,\\s]+", ""));
                    b.hoursFajr.setText(b.hoursFajr.getText().toString().replaceAll("[\\.\\-,\\s]+", ""));
                    b.minuteFajr.setText(b.minuteFajr.getText().toString().replaceAll("[\\.\\-,\\s]+", ""));
                    Snackbar.make(v, "Заполните все поля", Snackbar.LENGTH_SHORT)
                            .setAction("А что не так?", view -> onMaterialAlert())
                            .show();
                    e.printStackTrace();
                }

                saveTime();
                updateRemainingTime();
            }

            saveTime();
            loadTime();
            updateRemainingTime();
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

        b.buttonResetToday.setOnClickListener(v -> {
            b.hoursFajr.getText().clear();
            b.minuteFajr.getText().clear();
            b.hoursMagrib.getText().clear();
            b.minuteMagrib.getText().clear();
        });

        setCursorVisibilityListeners();

        return b.getRoot();
    }

    public void calculateIftar(View v) {

    }

    private boolean isContentLastTime() {
        SharedPreferences prefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.contains(FAJR_KEY) && prefs.contains(MAGHRIB_KEY);
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



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void updateRemainingTime() {
        // Если пользователь вводит данные, не обновляем время
        /*if (isUserTyping) {
            return;
        }*/

        // Проверяем, заполнены ли все поля
        if (!fieldsNotEmpty()) {
            b.suhsrIftarTimeMessage.setText("Введите время фаджра и магриба полностью");
            b.timeRemaining.setText("");
            b.suhurMessage.setVisibility(View.INVISIBLE);
            return;
        }

        try {
            int fajrHour = Integer.parseInt(b.hoursFajr.getText().toString());
            int fajrMinute = Integer.parseInt(b.minuteFajr.getText().toString());
            int maghribHour = Integer.parseInt(b.hoursMagrib.getText().toString());
            int maghribMinute = Integer.parseInt(b.minuteMagrib.getText().toString());

            LocalTime now = LocalTime.now();
            LocalTime fajrTime = LocalTime.of(fajrHour, fajrMinute);
            LocalTime maghribTime = LocalTime.of(maghribHour, maghribMinute);

            String remainingTimeText;
            if (now.isAfter(fajrTime) && now.isBefore(maghribTime)) {
                long secondsUntilMaghrib = now.until(maghribTime, ChronoUnit.SECONDS);
                remainingTimeText = formatRemainingTime(secondsUntilMaghrib);
                b.suhsrIftarTimeMessage.setText("До разговения осталось: ");
                b.timeRemaining.setText(remainingTimeText);
                b.suhurMessage.setVisibility(View.INVISIBLE);
            } else {
                long secondsUntilFajr = now.until(fajrTime, ChronoUnit.SECONDS);
                if (secondsUntilFajr < 0) {
                    secondsUntilFajr += 24 * 60 * 60; // add a day in seconds
                }
                remainingTimeText = formatRemainingTime(secondsUntilFajr);
                b.suhsrIftarTimeMessage.setText("До начала поста осталось: ");
                b.timeRemaining.setText(remainingTimeText);
                b.suhurMessage.setVisibility(View.VISIBLE);
            }
        } catch (NumberFormatException e) {
            // Обработка ошибки, если поля содержат некорректные данные
            b.suhsrIftarTimeMessage.setText("Ошибка: некорректные данные");
            b.timeRemaining.setText("");
            b.suhurMessage.setVisibility(View.INVISIBLE);
            e.printStackTrace();
        }
    }

    private boolean fieldsNotEmpty() {
        return !b.hoursFajr.getText().toString().isEmpty()
                || !b.minuteFajr.getText().toString().isEmpty()
                || !b.hoursMagrib.getText().toString().isEmpty()
                || !b.minuteMagrib.getText().toString().isEmpty();
    }
    private String formatRemainingTime(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long remainingSeconds = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }

    private void showSnackbar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    private void setCursorVisibilityListeners() {
        View.OnFocusChangeListener focusListener = new View.OnFocusChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                isUserTyping = hasFocus;
                if (!hasFocus) {
                    // Когда пользователь закончил ввод, обновляем время
                    updateRemainingTime();
                }
            }
        };

        b.hoursFajr.setOnFocusChangeListener(focusListener);
        b.minuteFajr.setOnFocusChangeListener(focusListener);
        b.hoursMagrib.setOnFocusChangeListener(focusListener);
        b.minuteMagrib.setOnFocusChangeListener(focusListener);
    }

    private boolean isTimeValid(int hours, int minutes) {
        return hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60;
    }

    private void saveTime() {

        if (!fieldsNotEmpty()) {
            Snackbar.make(b.getRoot(), "Заполните все поля", Snackbar.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences prefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        try {
            String fajrTime = String.format("%02d:%02d",
                    Integer.parseInt(b.hoursFajr.getText().toString()),
                    Integer.parseInt(b.minuteFajr.getText().toString()));

            String maghribTime = String.format("%02d:%02d",
                    Integer.parseInt(b.hoursMagrib.getText().toString()),
                    Integer.parseInt(b.minuteMagrib.getText().toString()));

            editor.putString(FAJR_KEY, fajrTime);
            editor.putString(MAGHRIB_KEY, maghribTime);
            editor.apply();
        } catch (NumberFormatException e) {
            // Если возникло исключение, сохраняем пустые строки
            editor.putString(FAJR_KEY, "");
            editor.putString(MAGHRIB_KEY, "");
            editor.apply();
        }
    }

    private void loadTime() {
        SharedPreferences prefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Получаем сохраненные значения
        String fajrTime = prefs.getString(FAJR_KEY, ""); // По умолчанию пустая строка
        String maghribTime = prefs.getString(MAGHRIB_KEY, ""); // По умолчанию пустая строка

        // Если есть сохраненное время для фаджра, загружаем его
        if (!fajrTime.isEmpty()) {
            try {
                String[] fajrParts = fajrTime.split(":");
                if (fajrParts.length == 2) {
                    b.hoursFajr.setText(fajrParts[0]);
                    b.minuteFajr.setText(fajrParts[1]);
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Если что-то пошло не так, оставляем поле пустым
                b.hoursFajr.setText("");
                b.minuteFajr.setText("");
            }
        } else {
            // Если данных нет, оставляем поле пустым
            b.hoursFajr.setText("");
            b.minuteFajr.setText("");
        }

        // Если есть сохраненное время для магриба, загружаем его
        if (!maghribTime.isEmpty()) {
            try {
                String[] maghribParts = maghribTime.split(":");
                if (maghribParts.length == 2) {
                    b.hoursMagrib.setText(maghribParts[0]);
                    b.minuteMagrib.setText(maghribParts[1]);
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Если что-то пошло не так, оставляем поле пустым
                b.hoursMagrib.setText("");
                b.minuteMagrib.setText("");
            }
        } else {
            // Если данных нет, оставляем поле пустым
            b.hoursMagrib.setText("");
            b.minuteMagrib.setText("");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            updateRemainingTime();
        }
    }

    private void saveTimeToClipboard(String fajrTime, String maghribTime) {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("FajrMaghribTimes", fajrTime + ";" + maghribTime);
        clipboard.setPrimaryClip(clip);
    }

    private boolean loadTimeFromClipboard() {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard.hasPrimaryClip() && clipboard.getPrimaryClipDescription().hasMimeType(android.content.ClipDescription.MIMETYPE_TEXT_PLAIN)) {
            android.content.ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
            String[] times = item.getText().toString().split(";");
            if (times.length == 2) {
                String[] fajrParts = times[0].split(":");
                String[] maghribParts = times[1].split(":");

                b.hoursFajr.setText(fajrParts[0]);
                b.minuteFajr.setText(fajrParts[1]);
                b.hoursMagrib.setText(maghribParts[0]);
                b.minuteMagrib.setText(maghribParts[1]);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        saveTime();
        handler.removeCallbacks(updateTimeTask);
    }

    @Override
    public void onDestroy() {
        saveTime();
        super.onDestroy();
    }

    @Override
    public void onStop() {
        saveTime();
        super.onStop();
    }

    @Override
    public void onPause() {
        saveTime();
        super.onPause();
    }

    @Override
    public void onResume() {
        saveTime();
        super.onResume();
    }
}