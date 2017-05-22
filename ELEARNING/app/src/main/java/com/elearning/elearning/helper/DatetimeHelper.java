package com.elearning.elearning.helper;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by MinhQuan on 20/05/2017.
 */

public class DatetimeHelper {
    public static void addDatePicker(final EditText editText, final SimpleDateFormat dateFormat, final String save, final String cancel) {

        editText.setFocusable(false);
        editText.setCursorVisible(false);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatePicker datePicker = new DatePicker(editText.getContext());
                datePicker.setCalendarViewShown(false);
                datePicker.setMaxDate(new Date().getTime());
                if (!editText.getText().toString().equals("")) {
                    try {
                        Date date = dateFormat.parse(editText.getText().toString());
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        int day = cal.get(Calendar.DAY_OF_MONTH);
                        int month = cal.get(Calendar.MONTH);
                        int year = cal.get(Calendar.YEAR);
                        datePicker.updateDate(year, month, day);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    datePicker.updateDate(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                }
                new AlertDialog.Builder(editText.getContext())
                        .setView(datePicker)
                        .setNegativeButton(cancel, null)
                        .setPositiveButton(save, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Calendar cal = Calendar.getInstance();
                                cal.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                                Date date = cal.getTime();
                                editText.setText(dateFormat.format(date));
                            }
                        })
                        .show();
            }
        });
    }
}
