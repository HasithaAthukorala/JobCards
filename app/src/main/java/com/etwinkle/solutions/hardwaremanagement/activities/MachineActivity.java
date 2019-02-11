package com.etwinkle.solutions.hardwaremanagement.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.etwinkle.solutions.hardwaremanagement.R;
import com.etwinkle.solutions.hardwaremanagement.models.MachineData;
import com.etwinkle.solutions.hardwaremanagement.utils.CustomApplication;

public class MachineActivity extends AppCompatActivity {

    private TextView department, location, serialNumber, supervisor;
    private Button addInquiry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine);

        Intent intent = getIntent();

        getSupportActionBar().setTitle("Machine details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        department = (TextView) findViewById(R.id.department);
        location = (TextView) findViewById(R.id.location);
        serialNumber = (TextView) findViewById(R.id.serial_number);
        supervisor = (TextView) findViewById(R.id.supervisor);

        addInquiry = (Button) findViewById(R.id.add_inquiry);

        MachineData machineData = ((CustomApplication) getApplication()).getGsonObject().fromJson(intent.getStringExtra("Json"),MachineData.class);
        department.setText(machineData.getDepartmentName());
        location.setText(machineData.getLocation());
        serialNumber.setText(machineData.getSerialNumber());
        supervisor.setText(machineData.getSupervisorID());

        addInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MachineActivity.this,ImageUpload.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
