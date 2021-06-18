package com.sinhvien.midtermexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class GetAllEmployeeInfo extends AppCompatActivity
{
    private ListView list;
    private ArrayList<EmployeeModel> em;
    private ViewAdapter va;
    private EmployeeDAO edao;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_employee_info);

        list = (ListView) findViewById(R.id.emList);

        edao = new EmployeeDAO(this);

        em = edao.GetAllEmployeeInfo();

       va = new ViewAdapter(this,em);
        list.setAdapter(va);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(GetAllEmployeeInfo.this, UpdateDelete.class);
                intent.putExtra("employ", em.get(position));
                startActivity(intent);
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                edao.deleteUSer(em.get(position).getId());
                Toast.makeText(GetAllEmployeeInfo.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(GetAllEmployeeInfo.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return false;
            }
        });
    }
}