package com.example.assignment_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Declare RadioGroup, CheckBox, Button, and TextView variables
    private RadioGroup RG_1, RG_2, RG_3, RG_4, RG_5, RG_6, RG_7;
    private CheckBox q11, q12, q13, q14, q21, q22, q23, q24, q31, q32, q33, q34;
    private Button submit, submited_result, delete,rating;
    private TextView t1, t2, t3, t4, t5, t6, t7, t8, t9, t10;

    // ArrayLists to hold questions, answers, and correct answers
    private ArrayList<String> Question = new ArrayList<>();
    private ArrayList<String> Result = new ArrayList<>();
    private ArrayList<String> Ans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the RadioGroups
        RG_1 = findViewById(R.id.q_1);
        RG_2 = findViewById(R.id.q_2);
        RG_3 = findViewById(R.id.q_3);
        RG_4 = findViewById(R.id.q_4);
        RG_5 = findViewById(R.id.q_5);
        RG_6 = findViewById(R.id.q_8);
        RG_7 = findViewById(R.id.q_9);

        // Initialize the CheckBoxes
        q11 = findViewById(R.id.q_61);
        q12 = findViewById(R.id.q_62);
        q13 = findViewById(R.id.q_63);
        q14 = findViewById(R.id.q_64);

        q21 = findViewById(R.id.q_71);
        q22 = findViewById(R.id.q_72);
        q23 = findViewById(R.id.q_73);
        q24 = findViewById(R.id.q_74);

        q31 = findViewById(R.id.q_101);
        q32 = findViewById(R.id.q_102);
        q33 = findViewById(R.id.q_103);
        q34 = findViewById(R.id.q_104);

        // Initialize the Buttons and TextViews
        submit = findViewById(R.id.submit);
        submited_result = findViewById(R.id.show_result);
        delete = findViewById(R.id.delete);
        rating = findViewById(R.id.rating);

        t1 = findViewById(R.id.question_1);
        t2 = findViewById(R.id.question_2);
        t3 = findViewById(R.id.question_3);
        t4 = findViewById(R.id.question_4);
        t5 = findViewById(R.id.question_5);
        t6 = findViewById(R.id.question_6);
        t7 = findViewById(R.id.question_7);
        t8 = findViewById(R.id.question_8);
        t9 = findViewById(R.id.question_9);
        t10 = findViewById(R.id.question_10);

        // Add correct answers to the Ans array
        Ans.add("Swift");
        Ans.add("SQLite");
        Ans.add("AndroidManifest.xml");
        Ans.add("Intent");
        Ans.add("TabLayout");
        Ans.add("onCreate(),onStart(),onDestroy(),onResume()");
        Ans.add("RelativeLayout,GridLayout,LinearLayout");
        Ans.add("layout_width");
        Ans.add("To retrieve a view by its ID");
        Ans.add("Volley,Retrofit,OkHttp");


        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Rating_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        // Handle the submit button click event
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question.clear(); // Clear previous questions
                Result.clear();   // Clear previous results

                // Check if all questions are answered
                if (areAllQuestionsAnswered()) {
                    // Store answers from RadioGroups and CheckBoxes
                    store_Question_And_Answer(RG_1, t1);
                    store_Question_And_Answer(RG_2, t2);
                    store_Question_And_Answer(RG_3, t3);
                    store_Question_And_Answer(RG_4, t4);
                    store_Question_And_Answer(RG_5, t5);

                    storeCheckboxAnswer(new CheckBox[]{q11, q12, q13, q14}, t6);
                    storeCheckboxAnswer(new CheckBox[]{q21, q22, q23, q24}, t7);

                    store_Question_And_Answer(RG_6, t8);
                    store_Question_And_Answer(RG_7, t9);

                    storeCheckboxAnswer(new CheckBox[]{q31, q32, q33, q34}, t10);

                    Toast.makeText(MainActivity.this, "Answer submitted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please answer all questions.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Handle showing results when result button is clicked
        submited_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Question.isEmpty() && !Result.isEmpty()) {
                    ShowResult(); // Show results in a dialog
                } else {
                    Toast.makeText(MainActivity.this, "No result is found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Hanle clearing responses when delete button is clicked
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllRadioGroups(); // Clear all radio group selections
                clearAllCheckboxes();  // Clear all checkbox selections
                Question.clear();      // Clear question list
                Result.clear();        // Clear result list
            }
        });
    }

    // show results in an AlertDialog
    private void ShowResult() {
        StringBuilder result = new StringBuilder();
        int correctCount = 0;
        int wrongCount = 0;

        // Loop through all questions and check if answers are correct
        for (int i = 0; i < Question.size(); i++) {
            boolean isCorrect = Result.get(i).equals(Ans.get(i)); // Compare answer

            result.append("Question: ").append(Question.get(i)).append("\n")
                    .append("Answer: ").append(Result.get(i))
                    .append(" - ").append(isCorrect ? "Correct" : "Wrong")
                    .append("\n\n");

            if (isCorrect) {
                correctCount++; // Increment correct answer count
            } else {
                wrongCount++;  // Increment wrong answer count
            }
        }

        // final result counts
        result.append("Total Correct Answers: ").append(correctCount).append("\n");
        result.append("Total Wrong Answers: ").append(wrongCount).append("\n");

        // Create and display the dialog with results
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Submitted Result");
        builder.setMessage(result.toString());

        AlertDialog dialog = builder.create();
        dialog.show(); // Show dialog
    }

    //check if all questions are answered
    public boolean areAllQuestionsAnswered() {
        // Ensure all RadioGroups and CheckBoxes have selected values
        return RG_1.getCheckedRadioButtonId() != -1 &&
                RG_2.getCheckedRadioButtonId() != -1 &&
                RG_3.getCheckedRadioButtonId() != -1 &&
                RG_4.getCheckedRadioButtonId() != -1 &&
                RG_5.getCheckedRadioButtonId() != -1 &&
                RG_6.getCheckedRadioButtonId() != -1 &&
                RG_7.getCheckedRadioButtonId() != -1 &&
                (q11.isChecked() || q12.isChecked() || q13.isChecked() || q14.isChecked()) &&
                (q21.isChecked() || q22.isChecked() || q23.isChecked() || q24.isChecked()) &&
                (q31.isChecked() || q32.isChecked() || q33.isChecked() || q34.isChecked());
    }

    // Function to store RadioGroup question and selected answer
    private void store_Question_And_Answer(RadioGroup radioGroup, TextView textView) {
        String question = textView.getText().toString();
        Question.add(question); // Add question to the list

        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        String answer = selectedRadioButton.getText().toString();

        Result.add(answer); // Add answer to the list
    }

    // store CheckBox answers
    private void storeCheckboxAnswer(CheckBox[] checkBoxes, TextView textView) {
        StringBuilder answer = new StringBuilder();
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                if (answer.length() > 0) answer.append(",");
                answer.append(checkBox.getText().toString());
            }
        }

        Question.add(textView.getText().toString()); // Add question to the list
        Result.add(answer.toString()); // Add combined answer to the list
    }

    // clear all RadioGroup selections
    private void clearAllRadioGroups() {
        RG_1.clearCheck();
        RG_2.clearCheck();
        RG_3.clearCheck();
        RG_4.clearCheck();
        RG_5.clearCheck();
        RG_6.clearCheck();
        RG_7.clearCheck();
    }

    //clear all CheckBox selections
    private void clearAllCheckboxes() {
        q11.setChecked(false);
        q12.setChecked(false);
        q13.setChecked(false);
        q14.setChecked(false);

        q21.setChecked(false);
        q22.setChecked(false);
        q23.setChecked(false);
        q24.setChecked(false);

        q31.setChecked(false);
        q32.setChecked(false);
        q33.setChecked(false);
        q34.setChecked(false);
    }
}
