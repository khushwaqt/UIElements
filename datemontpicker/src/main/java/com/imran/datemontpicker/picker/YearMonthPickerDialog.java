package com.imran.datemontpicker.picker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.imran.datemontpicker.R;
import com.imran.datemontpicker.custom_number_picker.NumberPickerWithColor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Creates a dialog for picking the year and month.
 */

public class YearMonthPickerDialog implements Dialog.OnClickListener {

    private static final int MIN_DATE = 1;
    private static final int MAX_DATE = 31;
    public static final int MonthYear = 1;
    public static final int DateMonth = 2;
    public static final int MonthOnly=3;
    private static int calenderType;
    private NumberPickerWithColor monthPicker;
    private TextView monthName;

    public static int getCalenderType() {
        return calenderType;
    }

    public static void setCalenderType(int calenderType) {
        YearMonthPickerDialog.calenderType = calenderType;
    }


    /**
     * The minimal year value.
     */
    private static final int MIN_YEAR = 1970;

    /**
     * The maximum year value.
     */
    private static final int MAX_YEAR = 2099;

    /**
     * Array of months.
     */
    private static String[] MONTHS_LIST = null;

    /**
     * Set Init Date.
     */
    private Calendar calendar;

    /**
     * Listener for user's date picking.
     */
    private OnDateSetListener mOnDateSetListener;

    /**
     * Application's context.
     */
    private final Context mContext;

    /**
     * Resulting dialog.
     */
    private AlertDialog mDialog;

    /**
     * Custom user's theme for dialog.
     */
    private int mTheme;

    /**
     * Custom user's color for title text.
     */
    private int mTextTitleColor;

    /**
     * Picked year.
     */
    private int mYear;

    /**
     * Picked month.
     */
    private int mMonth;

    /**
     * Picked Date
     */
    private int mDate;

    /**
     * Allow user to set custom date
     */
    private NumberPickerWithColor mYearPicker;
    private NumberPickerWithColor mDatePicker;
    private TextView mYearValue;
    private TextView mDateValue;

    /**
     * Creates a new YearMonthPickerDialog object that represents the dialog for
     * picking year and month.
     *
     * @param context           The application's context.
     * @param onDateSetListener Listener for user's date picking.
     */
    public YearMonthPickerDialog(Context context, Calendar calendar, OnDateSetListener onDateSetListener) {
        this(context, onDateSetListener, -1, -1, calendar);
    }

    public YearMonthPickerDialog(Context context, OnDateSetListener onDateSetListener, Calendar calendar) {
        this(context, onDateSetListener, -1, -1, calendar);
    }

    /**
     * Creates a new YearMonthPickerDialog object that represents the dialog for
     * picking year and month. Specifies custom user's theme
     *
     * @param context           The application's context.
     * @param onDateSetListener Listener for user's date picking.
     * @param theme             Custom user's theme for dialog.
     */
    public YearMonthPickerDialog(Context context, OnDateSetListener onDateSetListener, Calendar calendar, int theme) {
        this(context, onDateSetListener, theme, -1, calendar);
    }

    /**
     * Creates a new YearMonthPickerDialog object that represents the dialog for
     * picking year and month. Specifies custom user's theme and title text color
     *
     * @param context           The application's context.
     * @param onDateSetListener Listener for user's date picking.
     * @param theme             Custom user's theme for dialog.
     * @param titleTextColor    Custom user's color for title text.
     */
    public YearMonthPickerDialog(Context context, OnDateSetListener onDateSetListener, int theme, int titleTextColor, Calendar calendar) {
        mContext = context;
        mOnDateSetListener = onDateSetListener;
        mTheme = theme;
        mTextTitleColor = titleTextColor;
        this.calendar = calendar;

        //Builds the dialog using listed parameters.
        build();
    }

    /**
     * Listens for user's actions.
     *
     * @param dialog Current instance of dialog.
     * @param which  Id of pressed button.
     */
    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            //If user presses positive button
            case DialogInterface.BUTTON_POSITIVE:
                //Check if user gave us a listener
                if (mOnDateSetListener != null)
                    //Set picked year and month to the listener
                    mOnDateSetListener.onYearMonthSet(mYear, mMonth, mDate);
                break;

            //If user presses negative button
            case DialogInterface.BUTTON_NEGATIVE:
                //Exit the dialog
                dialog.cancel();
                break;
        }
    }

    /**
     * Creates and customizes a dialog.
     */
    private void build() {
        //Applying user's theme
        int currentTheme = mTheme;
        //If there is no custom theme, using default.
        if (currentTheme == -1) currentTheme = R.style.MyDialogTheme;

        //Initializing dialog builder.
        /*
      The builder for our dialog.
     */
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(mContext, currentTheme);

        //Creating View inflater.
        final LayoutInflater layoutInflater =
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Inflating custom title view.
        final View titleView = layoutInflater.inflate(R.layout.view_dialog_title, null, false);
        //Inflating custom content view.
        final View contentView = layoutInflater.inflate(R.layout.view_month_year_picker, null, false);

        //Initializing year and month pickers.
        mYearPicker = (NumberPickerWithColor) contentView.findViewById(R.id.year_picker);
        monthPicker = (NumberPickerWithColor) contentView.findViewById(R.id.month_picker);
        mDatePicker = (NumberPickerWithColor) contentView.findViewById(R.id.date_picker);

        //Initializing title text views
        monthName = (TextView) titleView.findViewById(R.id.month_name);
        mYearValue = (TextView) titleView.findViewById(R.id.year_name);
        mDateValue = (TextView) titleView.findViewById(R.id.date_name);
        //If there is user's title color,
        if (mTextTitleColor != -1) {
            //Then apply it.
            setTextColor(monthName);
            setTextColor(mYearValue);
        }

        //Setting custom title view and content to dialog.
        mDialogBuilder.setCustomTitle(titleView);
        mDialogBuilder.setView(contentView);

        //Setting year's picker min and max value
        mYearPicker.setMinValue(MIN_YEAR);
        mYearPicker.setMaxValue(MAX_YEAR);

        //Setting month's picker min and max value
        monthPicker.setMinValue(0);
        monthPicker.setMaxValue(monthsList().length - 1);

        mDatePicker.setMinValue(MIN_DATE);
        mDatePicker.setMaxValue(MAX_DATE);

        //Setting month list.
        monthPicker.setDisplayedValues(monthsList());
        mDatePicker.setDisplayedValues(dateLIst());

        //Applying current date.
        setCurrentDate(mYearPicker, monthPicker, mDatePicker, monthName, mYearValue, mDateValue);

        //Setting all listeners.
        setListeners(mYearPicker, monthPicker, mDatePicker, monthName, mYearValue, mDateValue);

        //Setting titles and listeners for dialog buttons.
        mDialogBuilder.setPositiveButton("OK", this);
        mDialogBuilder.setNegativeButton("CANCEL", this);


        //Creating dialog.
        mDialog = mDialogBuilder.create();

    }

    /**
     * Sets color to given TextView.
     *
     * @param titleView Given TextView.
     */
    private void setTextColor(TextView titleView) {
        titleView.setTextColor(ContextCompat.getColor(mContext, mTextTitleColor));
    }

    /**
     * Sets current date for title and pickers.
     *
     * @param yearPicker  year picker.
     * @param monthPicker month picker.
     * @param monthName   month name in the dialog title.
     * @param yearValue   year value in the dialog title.
     */
    private void setCurrentDate(NumberPickerWithColor yearPicker, NumberPickerWithColor monthPicker, NumberPickerWithColor datePicker, TextView monthName, TextView yearValue, TextView dateValue) {
        //Getting current date values from Calendar instance.
        ////Calendar calendar = Calendar.getInstance();
        mMonth = calendar.get(Calendar.MONTH);
        mYear = calendar.get(Calendar.YEAR);
        mDate = calendar.get(Calendar.DATE);

        //Setting output format.
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");

        //Setting current date values to dialog title views.
        monthName.setText(monthFormat.format(calendar.getTime()));
        yearValue.setText(String.valueOf(mYear));
        dateValue.setText(String.valueOf(mDate));

        //Setting current date values to pickers.
        monthPicker.setValue(mMonth);
        yearPicker.setValue(mYear);
        datePicker.setValue(mDate);
    }

    /**
     * Sets current date for title and pickers.
     *
     * @param yearPicker  year picker.
     * @param monthPicker month picker.
     * @param monthName   month name in the dialog title.
     * @param yearValue   year value in the dialog title.
     */
    private void setListeners(final NumberPickerWithColor yearPicker, final NumberPickerWithColor monthPicker, final NumberPickerWithColor datePicker, final TextView monthName, final TextView yearValue, final TextView dateValue) {
        //Setting listener to month name view.
        monthName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If there's no month picker visible
                if (monthPicker.getVisibility() == View.GONE) {
                    //Set it visible
                    monthPicker.setVisibility(View.VISIBLE);

                    //And hide year picker.
                    yearPicker.setVisibility(View.GONE);
                    datePicker.setVisibility(View.GONE);
                    //Change title views alpha to picking effect.
                    yearValue.setAlpha(0.39f);
                    dateValue.setAlpha(0.39f);
                    monthName.setAlpha(1f);
                }
            }
        });

        //Setting listener to year value view.
        yearValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If there's no year picker visible
                if (yearPicker.getVisibility() == View.GONE) {
                    //Set it visible
                    yearPicker.setVisibility(View.VISIBLE);

                    //And hide year picker.
                    monthPicker.setVisibility(View.GONE);
                    datePicker.setVisibility(View.GONE);

                    //Change title views alpha to picking effect.
                    monthName.setAlpha(0.39f);
                    dateValue.setAlpha(0.39f);
                    yearValue.setAlpha(1f);

                }
            }
        });

        dateValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (datePicker.getVisibility() == View.GONE) {
                    datePicker.setVisibility(View.VISIBLE);
                    monthPicker.setVisibility(View.GONE);
                    yearPicker.setVisibility(View.GONE);

                    monthName.setAlpha(0.39f);
                    yearValue.setAlpha(0.39f);
                    dateValue.setAlpha(1f);
                }
            }
        });

        //Setting listener to month picker. So it can change title text value.
        monthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mMonth = newVal;

                //Set title month text to picked month.
                monthName.setText(monthsList()[newVal]);
            }
        });

        //Setting listener to year picker. So it can change title text value.
        yearPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mYear = newVal;

                //Set title year text to picked year.
                yearValue.setText(String.valueOf(newVal));
            }
        });

        datePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                mDate = newVal;

                //Set title year text to picked year.
                dateValue.setText(String.valueOf(newVal));
            }
        });
    }

    /**
     * Allows user to show created dialog.
     */
    public void show() {
        mDialog.show();

        if (getCalenderType() == MonthYear) {
            mDatePicker.setVisibility(View.GONE);
            mDateValue.setVisibility(View.GONE);
            monthPicker.setVisibility(View.VISIBLE);
            monthName.setAlpha(1f);
        }
        if (getCalenderType() == DateMonth) {
            mYearPicker.setVisibility(View.GONE);
            mYearValue.setVisibility(View.GONE);
        }
        if(getCalenderType()==MonthOnly){
            mYearPicker.setVisibility(View.GONE);
            mYearValue.setVisibility(View.GONE);
            mDatePicker.setVisibility(View.GONE);
            mDateValue.setVisibility(View.GONE);
            monthPicker.setVisibility(View.VISIBLE);
            monthName.setAlpha(1f);
        }
    }

    /**
     * Sets min value of year picker widget.
     *
     * @param minYear The min value inclusive.
     */
    public void setMinYear(int minYear) {
        if (mYearPicker != null) {
            if (mYearPicker.getValue() < minYear) {
                mYearPicker.setValue(minYear);
                mYearValue.setText(String.valueOf(minYear));
            }
            mYearPicker.setMinValue(Math.min(minYear, mYearPicker.getMaxValue()));
        }
    }

    /**
     * Sets max value of year picker widget.
     *
     * @param maxYear The max value inclusive.
     */
    public void setMaxYear(int maxYear) {
        if (mYearPicker != null) {
            if (mYearPicker.getValue() > maxYear) {
                mYearPicker.setValue(maxYear);
                mYearValue.setText(String.valueOf(maxYear));
            }
            mYearPicker.setMaxValue(Math.max(maxYear, mYearPicker.getMinValue()));
        }
    }

    public void setMaxDate(int maxDate) {
        if (mDatePicker != null) {
            if (mDatePicker.getValue() > maxDate) {
                mDatePicker.setValue(maxDate);
                mDateValue.setText(String.valueOf(maxDate));

            }
            mDatePicker.setMaxValue(Math.max(maxDate, mDatePicker.getMinValue()));
        }
    }

    public void setMinDate(int minDate) {
        if (mDatePicker != null) {
            if (mDatePicker.getValue() < minDate) {
                mDatePicker.setValue(minDate);
                mDateValue.setText(Integer.toString(minDate));
            }
            mDatePicker.setMinValue(Math.min(minDate, mDatePicker.getMaxValue()));
        }
    }

    /**
     * Interface for implementing user's pick listener.
     */
    public interface OnDateSetListener {
        /**
         * Listens for user's actions.
         */
        void onYearMonthSet(int year, int month, int date);
    }


    /**
     * Capitalize string
     */
    private static String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    /**
     * Get month name with specified locale
     */
    private static String[] monthsList() {
        if (MONTHS_LIST == null) {
            int[] months = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
            String[] stringMonths = new String[months.length];

            for (int i = 0; i < months.length; i++) {
                Calendar calendar = Calendar.getInstance();

                SimpleDateFormat monthDate = new SimpleDateFormat("MMMM", Locale.getDefault());

                calendar.set(Calendar.MONTH, months[i]);
                String monthName = monthDate.format(calendar.getTime());


                stringMonths[i] = capitalize(monthName);
            }

            MONTHS_LIST = stringMonths;
        }

        return MONTHS_LIST;
    }

    private static String[] dateLIst() {
        String[] DATE_LIST = new String[31];
        for (int i = 0; i < DATE_LIST.length; i++) {
            DATE_LIST[i] = String.valueOf(i + 1);
        }
        return DATE_LIST;
    }
}
