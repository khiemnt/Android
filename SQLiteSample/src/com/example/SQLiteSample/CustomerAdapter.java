package com.example.SQLiteSample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomerAdapter extends ArrayAdapter<Student>
{

    private Context mContext;
    private int row;
    private List<Student> list;

    public static class ViewHolder
    {
        private TextView id;
        public TextView name;
        public TextView age;

    }

    public CustomerAdapter(Context context, int textViewResourceId, List<Student> list)
    {
        super(context, textViewResourceId, list);

        this.mContext = context;
        this.row = textViewResourceId;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        ViewHolder holder;
        if (view == null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);

            holder = new ViewHolder();
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }


        if ((list == null) || ((position + 1) > list.size()))
        {
            return view; // Can't extract item
        }

        Student obj = list.get(position);

        holder.name = (TextView) view.findViewById(R.id.tvName);
        holder.age = (TextView) view.findViewById(R.id.tvAge);
        holder.id = (TextView) view.findViewById(R.id.tvId);

        if (null != holder.name && null != obj && obj.getStudentName().length() != 0)
        {
            holder.id.setText(obj.getStudentId() + "");
            holder.name.setText(obj.getStudentName());
            holder.age.setText(obj.getStudentAge() + "");
        }
        return view;
    }


}
