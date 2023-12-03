package com.example.imageview;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

public class MyDialog extends AlertDialog.Builder {

    public MyDialog(Context context) {
        super(context);
        initDialog(context);
    }

    private void initDialog(final Context context) {
        setTitle("操作选择");
        setPositiveButton("保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 处理保存按钮点击事件
                Toast.makeText(context, "保存", Toast.LENGTH_SHORT).show();
            }
        });

        setNeutralButton("另存为", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 处理另存为按钮点击事件
                Toast.makeText(context, "另存为", Toast.LENGTH_SHORT).show();
            }
        });

        setNegativeButton("分享", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 处理分享按钮点击事件
                Toast.makeText(context, "分享", Toast.LENGTH_SHORT).show();
            }
        });

        setCancelable(true); // 设置是否可以通过点击对话框外部取消对话框
    }

    public AlertDialog create() {
        return super.create();
    }
}
