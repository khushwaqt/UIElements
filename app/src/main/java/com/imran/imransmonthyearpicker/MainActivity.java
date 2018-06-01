package com.imran.imransmonthyearpicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.imran.datemontpicker.picker.YearMonthPickerDialog;
import com.imran.datemontpicker.toastme.CustomToast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private TextView yearMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yearMonth = findViewById(R.id.yearMonth);
        Button btn = findViewById(R.id.toast);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomToast.ShowDefault(getApplicationContext(), "hello   asafasdfasdfaf").show();
                CustomToast.ShowToastError(getApplicationContext(), "This is error").show();
                CustomToast.ShowToastSuccess(getApplicationContext(), "This is success").show();
                CustomToast.ShowToastInfo(getApplicationContext(), "THis is info").show();
                CustomToast.ShowToastWarning(getApplicationContext(), "This is warning").show();

            }
        });
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));

        YearMonthPickerDialog yearMonthPickerDialog = new YearMonthPickerDialog(this, calendar,
                new YearMonthPickerDialog.OnDateSetListener() {
                    @Override
                    public void onYearMonthSet(int year, int month, int date) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DATE, date);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");

                        yearMonth.setText(dateFormat.format(calendar.getTime()));
                    }
                });
        yearMonthPickerDialog.setMinYear(2000);
        yearMonthPickerDialog.setMaxYear(2020);
        yearMonthPickerDialog.setMinDate(1);
        yearMonthPickerDialog.setMaxDate(31);
        YearMonthPickerDialog.setCalenderType(YearMonthPickerDialog.MonthOnly);
        yearMonthPickerDialog.show();
    }
}
