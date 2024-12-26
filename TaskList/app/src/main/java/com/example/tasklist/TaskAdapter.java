package com.example.tasklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.AdapterView;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    private Context context;
    private List<Task> taskList;
    private TaskDatabase taskDatabase;

    public TaskAdapter(Context context, List<Task> taskList) {
        super(context, 0, taskList);
        this.context = context;
        this.taskList = taskList;
        taskDatabase = new TaskDatabase(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        }

        Task task = taskList.get(position);

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView descriptionTextView = convertView.findViewById(R.id.descriptionTextView);
        CheckBox statusCheckBox = convertView.findViewById(R.id.statusCheckBox);

        titleTextView.setText(task.getTitle());
        descriptionTextView.setText(task.getDescription());
        statusCheckBox.setChecked(task.getStatus() == 1);

        // Обработчик изменения состояния задачи (отметить как выполненную)
        statusCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setStatus(isChecked ? 1 : 0);
            taskDatabase.updateTaskStatus(task.getId(), task.getStatus());
        });

        // Обработчик долгого нажатия для удаления задачи
        convertView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Удалить задачу?")
                    .setMessage("Вы уверены, что хотите удалить эту задачу?")
                    .setPositiveButton("Да", (dialog, which) -> {
                        taskDatabase.deleteTask(task.getId());
                        taskList.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Задача удалена", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Нет", null)
                    .show();
            return true;
        });

        return convertView;
    }
}
