package com.sinhvien.midtermexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDelete extends AppCompatActivity {
    EditText etname, etphone, etaddress, etdepart;
    EmployeeModel em;
    EmployeeDAO ed;
    Button update, delete;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        Intent intent = getIntent();
        em = (EmployeeModel) intent.getSerializableExtra("employ");

        ed = new EmployeeDAO(this);

        etname = (EditText) findViewById(R.id.etname);
        etphone = (EditText) findViewById(R.id.etphone);
        etaddress = (EditText) findViewById(R.id.etaddress);
        etdepart = (EditText) findViewById(R.id.etdepart);

        update = (Button) findViewById(R.id.btnupdate);
        delete = (Button) findViewById(R.id.btndelete);

        etname.setText(em.getName());
        etphone.setText(em.getPhone());
        etaddress.setText(em.getAddress());
        etdepart.setText(em.getDepartment());

        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ed.updateUser(em.getId(),etname.getText().toString(),etphone.getText().toString(),etaddress.getText().toString(), etdepart.getText().toString());
                Toast.makeText(UpdateDelete.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateDelete.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed.deleteUSer(em.getId());
                Toast.makeText(UpdateDelete.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateDelete.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}