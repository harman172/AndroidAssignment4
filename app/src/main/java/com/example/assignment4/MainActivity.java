package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    double prices[] = {2.5, 3.7, 8.0, 2.3, 4, 6, 1.5, 3.3, 6.8, 5.5};
    int images[] = {R.drawable.burger, R.drawable.coffee, R.drawable.tea, R.drawable.coke, R.drawable.pizza, R.drawable.pasta, R.drawable.salad, R.drawable.muffins, R.drawable.wrap, R.drawable.coffee};

    Spinner spinnerMeals;
    EditText etPrice, etTotal;
    TextView tvQuantity;
    RadioGroup radioGroup;
    CheckBox checkBox;
    SeekBar seekBar;
    ImageView ivMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spinnerMeals = findViewById(R.id.spinner_meals);
        ivMeal = findViewById(R.id.iv_meal);
        etPrice = findViewById(R.id.et_price);
        etTotal = findViewById(R.id.et_total);
        seekBar = findViewById(R.id.quantity);
        tvQuantity = findViewById(R.id.tv_quantity);
        radioGroup = findViewById(R.id.rg_tip);
        checkBox = findViewById(R.id.cb_confirm);


        spinnerMeals.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) {
                    etPrice.setText(String.valueOf(prices[position-1]));
                    ivMeal.setImageResource(images[position-1]);
                } else{
                    etPrice.setText("");
                    ivMeal.setImageResource(R.drawable.default_img);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                tvQuantity.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void tipSelected(View view){

        double total = (Double.valueOf(tvQuantity.getText().toString()) * Double.valueOf(etPrice.getText().toString())) + (13/100.0);
        Log.i("DIVISION", "tipSelected: " + (13/100.0));
//        total += (13/100);
        Log.i("TOTAL", "tipSelected: " + total);
        switch (view.getId()){
            case R.id.rb_10:
                total = total + (10/100.0);
                etTotal.setText(String.format("%.2f ",total));
                break;
            case R.id.rb_15:
                total = total + (15/100.0);
                etTotal.setText(String.format("%.2f ",total));
                break;
            case R.id.rb_20:
                total = total + (20/100.0);
                etTotal.setText(String.format("%.2f ",total));
                break;
        }


    }

    public void btnOrder(View view){
        if (validations()){
            Toast.makeText(this, "Your order has been placed successfully!!!", Toast.LENGTH_SHORT).show();
            spinnerMeals.setSelection(0);
            radioGroup.clearCheck();
            seekBar.setProgress(1);
            etTotal.setText("");
            checkBox.setSelected(false);
        }
    }

    private boolean validations(){
        if (spinnerMeals.getSelectedItemPosition() > 0){
            if (radioGroup.getCheckedRadioButtonId() != -1){
                if (checkBox.isChecked()){
                    return true;
                } else{
                    Toast.makeText(this, "Confirm your order first.", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
            else{
                Toast.makeText(this, "Please select a tip.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        else{
            Toast.makeText(this, "Please choose a meal.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }










}
