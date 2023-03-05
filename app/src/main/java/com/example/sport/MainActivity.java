package com.example.sport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> workoutList;
    private LinearLayout workoutListView;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;

        // Ініціалізуємо список тренувань
        workoutList = new ArrayList<>();

        // Отримуємо посилання на віджети
        workoutListView = findViewById(R.id.workout_list);
        addButton = findViewById(R.id.add_workout_button);

        // Додаємо обробник натискання для кнопки "Додати"
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddWorkoutDialog();
            }
        });
    }

    // Метод для показу діалогового вікна "Додати тренування"
    private void showAddWorkoutDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_workout_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        final EditText workoutNameEditText = dialog.findViewById(R.id.workout_name);
        Button addButton = dialog.findViewById(R.id.dialog_add_button);
        Button cancelButton = dialog.findViewById(R.id.dialog_cancel_button);

        // Встановлюємо фокус на поле вводу та відкриваємо клавіатуру при відкритті діалогового вікна
        workoutNameEditText.requestFocus();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        // Додаємо відступ між кнопками
        cancelButton.setPadding(0, 0, 20, 0);

        // Додаємо обробники натискання для кнопок діалогового вікна
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workoutName = workoutNameEditText.getText().toString();

                if (!workoutName.isEmpty()) {
                    // Додаємо нове тренування до списку та оновлюємо адаптер
                    workoutList.add(workoutName);
                    updateWorkoutList();
                }

                dialog.dismiss();
            }
        });

        dialog.show();
    }


    // Метод для додавання нового тренування до списку та оновлення адаптера
    private void updateWorkoutList() {
        workoutListView.removeAllViews();

        // Отримуємо ширину екрану
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;

        // Створюємо LinearLayout з вертикальною орієнтацією та параметрами, щоб розташувати його по центру екрану
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);

        // Задаємо параметри для LinearLayout
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.gravity = Gravity.CENTER;

        for (String workout : workoutList) {
            Button workoutButton = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (screenWidth * 0.75), (int) (screenWidth * 0.75 * 2 / 5));
            workoutButton.setLayoutParams(params);

            workoutButton.setText(workout);

            // Додаємо кнопку до макету
            layout.addView(workoutButton);

            workoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Обробка натискання кнопки
                }
            });
        }

        // Додаємо LinearLayout з кнопками до workoutListView
        workoutListView.addView(layout, layoutParams);
    }



}
