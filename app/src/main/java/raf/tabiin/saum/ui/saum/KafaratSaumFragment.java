package raf.tabiin.saum.ui.saum;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

import raf.tabiin.saum.R;
import raf.tabiin.saum.databinding.FragmentSaumKafaratBinding;
import raf.tabiin.saum.domain.models.KaffaratDay;
import raf.tabiin.saum.adapters.KaffaratDaysAdapter;
import raf.tabiin.saum.util.FileUtils;
import raf.tabiin.saum.viewmodel.KaffaratDaysViewModel;

public class KafaratSaumFragment extends Fragment {
    private FragmentSaumKafaratBinding binding;
    private KaffaratDaysAdapter adapter;
    private KaffaratDaysViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(KaffaratDaysViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSaumKafaratBinding.inflate(inflater, container, false);

        binding.recyclerViewKaffaratDays.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        List<KaffaratDay> kaffaratDaysList = viewModel.getKaffaratDaysList();
        adapter = new KaffaratDaysAdapter(kaffaratDaysList, new KaffaratDaysAdapter.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(KaffaratDay KaffaratDay) {
                saveKaffaratDaysToJson();
            }

            @Override
            public void onCheckedCountChanged(int count) {
                binding.postKaffaratProgressBar.setProgress(count);
                binding.itogPost.setText(String.valueOf(count));
            }
        });
        binding.recyclerViewKaffaratDays.setAdapter(adapter);

        binding.buttonReset.setOnClickListener(v -> {
            binding.postKaffaratProgressBar.setProgress(0);
            binding.itogPost.setText(String.valueOf(0));
            onAlert();
            loadKaffaratDays();
            adapter.notifyDataSetChanged();
        });

        loadKaffaratDays();

        int checkedCount = adapter.getCheckedCount();
        binding.postKaffaratProgressBar.setProgress(checkedCount);
        binding.itogPost.setText(String.valueOf(checkedCount));

        binding.buttonInfo.setOnClickListener(v -> {
            infoKafaratAlertDialog();
        });

        return binding.getRoot();
    }

    private void loadKaffaratDays() {
        List<KaffaratDay> kaffaratDaysList = viewModel.getKaffaratDaysList();
        try {
            File internalDir = getActivity().getFilesDir();
            File KaffaratDaysFile = new File(internalDir, "kaffarat_days.json");
            String json = FileUtils.readFileToString(KaffaratDaysFile);

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("kaffarat_days");

            // Очистка списка перед загрузкой новых данных
            kaffaratDaysList.clear();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject dayObject = jsonArray.getJSONObject(i);
                String day = dayObject.getString("day");
                boolean isChecked = dayObject.getBoolean("is_checked");
                kaffaratDaysList.add(new KaffaratDay(day, isChecked));
            }

            adapter.notifyDataSetChanged();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveKaffaratDaysToJson() {
        List<KaffaratDay> KaffaratDaysList = viewModel.getKaffaratDaysList();
        try {
            JSONArray jsonArray = new JSONArray();
            for (KaffaratDay KaffaratDay : KaffaratDaysList) {
                JSONObject dayObject = new JSONObject();
                dayObject.put("day", KaffaratDay.getDay());
                dayObject.put("is_checked", KaffaratDay.isChecked());
                jsonArray.put(dayObject);
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("kaffarat_days", jsonArray);

            String json = jsonObject.toString();
            FileUtils.writeStringToFile(getActivity(), json, "kaffarat_days.json");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    private void resetJsonFile() {
        List<KaffaratDay> kaffaratDaysList = viewModel.getKaffaratDaysList();
        try {
            JSONArray jsonArray = new JSONArray();
            for (int i = 1; i <= 61; i++) {
                JSONObject dayObject = new JSONObject();
                dayObject.put("day", String.valueOf(i));
                dayObject.put("is_checked", false);
                jsonArray.put(dayObject);
                // Добавляем в список kaffaratDaysList
                kaffaratDaysList.add(new KaffaratDay(String.valueOf(i), false));
            }

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("kaffarat_days", jsonArray);

            String json = jsonObject.toString();
            FileUtils.writeStringToFile(getActivity(), json, "kaffarat_days.json");

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    public void onAlert() {
        new MaterialAlertDialogBuilder(requireContext(),
                R.style.AlertDialogTheme)
                .setTitle("Reset")
                .setMessage("Обновить счетчик дней?")
                .setPositiveButton("Да", (dialogInterface, i) -> {

                    binding.postKaffaratProgressBar.setProgress(0);
                    binding.itogPost.setText(String.valueOf(0));

                    resetJsonFile();
                    loadKaffaratDays();
                })

                .setNeutralButton("Отмена",
                        (dialogInterface, i) ->
                                dialogInterface.cancel())
                .show();
    }

    private void infoKafaratAlertDialog() {
        new MaterialAlertDialogBuilder(requireContext(),
                R.style.AlertDialogTheme)
                .setTitle("Что такое Пост Кафарат")
                .setMessage("Каффарат — это предписание за намеренное нарушение поста во время Рамадана. Лексическим значением слова «каффарат» является «укрытие, стирание». В терминологии шариата это слово означает определенный набор действий, установленных Всевышним Аллахом, к которым прибегает человек с целью заслужить прощение Аллаха за некоторые совершенные грехи и ошибки. \n" +
                        "- Каффара – налагается в пяти следующих случаях:\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "1. Есть или пить сознательно.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "2. Курение, употребление насвая, прием лекарств.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "3. Намеренное проглатывание капель дождя. Доказательство этому следующий хадис: «Пост нарушается при входе в организм чего-либо, а не при выходе».\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "4. Интимная близость супругов (т.е. нельзя иметь половое сношение с рассвета до заката).\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "5. Сознательно есть или вводить в организм питательные вещества.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Общее правило звучит примерно так: если форма нарушения поста (то есть проникновение чего-то внутрь тела) совпадает со смыслом нарушения поста (удовлетворение некой страсти тела), то требуется каффараn. Если присутствует только одна составляющая (форма или смысл), то каффара не обязательна.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Поэтому проглатывание дольки апельсина требует каффару, а проглатывание, к примеру, пластикового шарика – нет (пластиком невозможно утолить голод, значит, страсть тела не удовлетворяется). \n\n\n" +
                        "Каффарат заключается в посте в течение 60 дней подряд без перерыва (помимо возмещения того дня поста, который человек нарушил). Если эти 60 дней поста прерываются без уважительных причин (хайд или нифас для женщин, болезнь или путешествие), то эти дни необходимо восполнить вновь.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Если человек не в состоянии поститься в течение 60 дней, к примеру, в следствие старости или болезни, он может сделать следующее:\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "- Единоразово накормить 60 бедняков два раза в день.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "- Накормить одного бедняка в течение 60 дней два раза в день.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "- Дать 60 беднякам 1,6 кг пшеницы или пшеничной муки (или их стоимость) (или давать одному бедняку в течение 60 дней стоимость этих продуктов).\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "- Дать 60 беднякам 3,5 кг сухих фиников или ячменя, или их стоимость (или давать одному бедняку в течение 60 дней стоимость этих продуктов).\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Пища, которой следует кормить бедняков, должна рассчитываться исходя из среднего уровня потребления в данной местности, она не должна быть слишком плохого качества или, наоборот, слишком роскошной.\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Также необходимо смотреть на материальное состояние человека, на момент его выплаты. Если в момент нарушения поста человек был богат, а на момент выплаты каффарата, он обеднел, то в этом случае следует соблюдать пост в течение двух месяцев. Но если до истечения этих двух месяцев поста он снова разбогатеет и будет иметь возможность освободить раба, то сможет выплатить искупление освободив раба или накормив нуждающихся\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Если же мусульманин пропускает дни поста во время Рамадана, то их необходимо возместить один к одному без каффарата (соответственно, если вы не постились много лет, вам необходимо подсчитать пропущенные дни поста и постепенно возместить их, необязательно это делать подряд без перерыва).\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "Если человек нарушает более чем один день поста в Рамадан (едой, питьем или совокуплением с супругой), их необходимо возместить только одним каффаратом (60 дней поста). \n\n\n" +
                        "По ханафитскому мазхабу, если вы постились и нарушили пост употреблением пищи либо питьем, то это также требует совершения каффары – 60 дней поста непрерывно, а затем надо восполнить нарушенный день поста. \n\n\n" +
                        "Итого: 61 день поста")
                .setPositiveButton("Ok", (dialogInterface, i) -> {
                    dialogInterface.cancel();
                })

                .show();
    }
}