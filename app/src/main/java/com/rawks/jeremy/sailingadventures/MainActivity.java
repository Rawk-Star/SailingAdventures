package com.rawks.jeremy.sailingadventures;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
{
    private TextView txtReservation;                                // Reservation set message text
    private Calendar reservationDate;                               // Reservation date
    private DateFormat formatDate;                                  // Date format for locale
    private DatePickerDialog.OnDateSetListener datePickerListener;  // Listener for user selecting date

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtReservation = findViewById(R.id.txtReservation);
        Button btnDate = findViewById(R.id.btnDate);
        btnDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Open the date picker dialog with current reservation date selected
                int year = reservationDate.get(Calendar.YEAR);
                int month = reservationDate.get(Calendar.MONTH);
                int dayOfMonth = reservationDate.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(MainActivity.this, datePickerListener, year, month, dayOfMonth).show();
            }
        });

        // Default the reservation date to the current date
        reservationDate = Calendar.getInstance();

        // Get the local date format
        formatDate = DateFormat.getDateInstance();

        // Create a listener to handle the user selecting a date on the date picker dialog
        datePickerListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
                // Set the reservation date to the date selected by the user
                reservationDate.set(Calendar.YEAR, year);
                reservationDate.set(Calendar.MONTH, month);
                reservationDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                // Print a message indicating that the reservation was set to the selected date
                String strReservation =  getString(R.string.txtReservation) + " " + formatDate.format(reservationDate.getTime());
                txtReservation.setText(strReservation);
            }
        };
    }
}