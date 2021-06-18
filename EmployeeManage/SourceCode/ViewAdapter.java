package com.sinhvien.midtermexam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewAdapter extends BaseAdapter
{
    public Context context;
    public ArrayList<EmployeeModel> em;

    public ViewAdapter(Context context, ArrayList<EmployeeModel> em)
    {
        this.context = context;
        this.em = em;
    }

    @Override
    public int getCount() { return em.size(); }

    @Override
    public Object getItem(int position) { return em.get(position); }

    @Override
    public long getItemId(int position) { return 0;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;

        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_em, null, true);

            viewHolder.tvName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.tvPhone = (TextView) convertView.findViewById(R.id.phone);
            viewHolder.tvAddress = (TextView) convertView.findViewById(R.id.address);
            viewHolder.tvDepartment = (TextView) convertView.findViewById(R.id.department);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText("Name: " + em.get(position).getName());
        viewHolder.tvPhone.setText("Name: " + em.get(position).getPhone());
        viewHolder.tvAddress.setText("Name: " + em.get(position).getAddress());
        viewHolder.tvDepartment.setText("Name: " + em.get(position).getDepartment());

        return convertView;
    }

    private class ViewHolder
    {
        protected TextView tvName, tvPhone, tvAddress, tvDepartment;
    }
}
