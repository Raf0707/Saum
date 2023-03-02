package ru.tabiin.ramadan.ui.dua;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import ru.tabiin.ramadan.R;
import ru.tabiin.ramadan.databinding.FragmentDuaBinding;

public class DuaFragment extends Fragment {
    FragmentDuaBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDuaBinding.inflate(getLayoutInflater());

        binding.duaSuhur.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager)
                    v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("",
                    binding.duaSuhurTitle.getText().toString() + "\n"
                    + binding.duaSuhurArabic.getText().toString() + "\n"
                            + binding.duaSuhurTranscript.getText().toString() + "\n"
                    + binding.duaSuhurTranslate.getText().toString());
            clipboard.setPrimaryClip(clip);

            Snackbar.make(v, binding.duaSuhurTitle.getText().toString()
                            + " скопировано в буфер обмена",
                    Snackbar.LENGTH_SHORT).show();
        });



        binding.duaIftar1.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager)
                    v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("",
                    binding.duaIftar1Title.getText().toString() + "\n"
                            + binding.duaIftar1Arabic.getText().toString() + "\n"
                            + binding.duaIftar1Transcript.getText().toString() + "\n"
                            + binding.duaIftar1Translate.getText().toString());
            clipboard.setPrimaryClip(clip);

            Snackbar.make(v, binding.duaIftar1Title.getText().toString()
                            + " скопировано в буфер обмена",
                    Snackbar.LENGTH_SHORT).show();
        });

        binding.duaIftar2.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager)
                    v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("",
                    binding.duaIftar2Title.getText().toString() + "\n"
                            + binding.duaIftar2Arabic.getText().toString() + "\n"
                            + binding.duaIftar2Transcript.getText().toString() + "\n"
                            + binding.duaIftar2Translate.getText().toString());
            clipboard.setPrimaryClip(clip);

            Snackbar.make(v, binding.duaIftar2Title.getText().toString()
                            + " скопировано в буфер обмена",
                    Snackbar.LENGTH_SHORT).show();
        });

        binding.duaIftar3.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager)
                    v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("",
                    binding.duaIftar3Title.getText().toString() + "\n"
                            + binding.duaIftar3Arabic.getText().toString() + "\n"
                            + binding.duaIftar3Transcript.getText().toString() + "\n"
                            + binding.duaIftar3Translate.getText().toString());
            clipboard.setPrimaryClip(clip);

            Snackbar.make(v, binding.duaIftar3Title.getText().toString()
                            + " скопировано в буфер обмена",
                    Snackbar.LENGTH_SHORT).show();
        });

        binding.duaIftar4.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager)
                    v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("",
                    binding.duaIftar4Title.getText().toString() + "\n"
                            + binding.duaIftar4Arabic.getText().toString() + "\n"
                            + binding.duaIftar4Transcript.getText().toString() + "\n"
                            + binding.duaIftar4Translate.getText().toString());
            clipboard.setPrimaryClip(clip);

            Snackbar.make(v, binding.duaIftar4Title.getText().toString()
                            + " скопировано в буфер обмена",
                    Snackbar.LENGTH_SHORT).show();
        });

        binding.duaIftar5.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager)
                    v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("",
                    binding.duaIftar5Title.getText().toString() + "\n"
                            + binding.duaIftar5Arabic.getText().toString() + "\n"
                            + binding.duaIftar5Transcript.getText().toString() + "\n"
                            + binding.duaIftar5Translate.getText().toString());
            clipboard.setPrimaryClip(clip);

            Snackbar.make(v, binding.duaIftar5Title.getText().toString()
                            + " скопировано в буфер обмена",
                    Snackbar.LENGTH_SHORT).show();
        });

        binding.duaIftar6.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager)
                    v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("",
                    binding.duaIftar6Title.getText().toString() + "\n"
                            + binding.duaIftar6Arabic.getText().toString() + "\n"
                            + binding.duaIftar6Transcript.getText().toString() + "\n"
                            + binding.duaIftar6Translate.getText().toString());
            clipboard.setPrimaryClip(clip);

            Snackbar.make(v, binding.duaIftar6Title.getText().toString()
                            + " скопировано в буфер обмена",
                    Snackbar.LENGTH_SHORT).show();
        });

        binding.duaIftar7.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager)
                    v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("",
                    binding.duaIftar7Title.getText().toString() + "\n"
                            + binding.duaIftar7Arabic.getText().toString() + "\n"
                            + binding.duaIftar7Transcript.getText().toString() + "\n"
                            + binding.duaIftar7Translate.getText().toString());
            clipboard.setPrimaryClip(clip);

            Snackbar.make(v, binding.duaIftar7Title.getText().toString()
                            + " скопировано в буфер обмена",
                    Snackbar.LENGTH_SHORT).show();
        });

        binding.duaQadarNight.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager)
                    v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("",
                    binding.duaQadarNightTitle.getText().toString() + "\n"
                            + binding.duaQadarNightArabic.getText().toString() + "\n"
                            + binding.duaQadarNightTranscript.getText().toString() + "\n"
                            + binding.duaQadarNightTranslate.getText().toString());
            clipboard.setPrimaryClip(clip);

            Snackbar.make(v, binding.duaQadarNightTitle.getText().toString()
                            + " скопировано в буфер обмена",
                    Snackbar.LENGTH_SHORT).show();
        });

        binding.duaTarawih.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager)
                    v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("",
                    binding.duaTarawihTitle.getText().toString() + "\n"
                            + binding.duaTarawihArabic.getText().toString() + "\n"
                            + binding.duaTarawihTranscript.getText().toString() + "\n"
                            + binding.duaTarawihTranslate.getText().toString());
            clipboard.setPrimaryClip(clip);

            Snackbar.make(v, binding.duaTarawihTitle.getText().toString()
                            + " скопировано в буфер обмена",
                    Snackbar.LENGTH_SHORT).show();
        });

        return binding.getRoot();
    }


}