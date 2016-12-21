package com.gdmec07150650.filedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText edit;
    private Button button;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*File file = new File("/mnt/sdcard/test");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(MainActivity.this, "文件已经存在", Toast.LENGTH_SHORT).show();
        }
        file.delete();*/

        //File file = this.getFilesDir(); //这个目录是当前应用程序默认的数据存储目录
        //File file = this.getCacheDir(); //这个目录是当前应用程序默认的缓存文件的存放位置
        //把一些不是非常重要的文件在此创建使用
        //如果手机内存不足时，系统会自动去删除APP的cache目录的数据

        //File file = this.getDir("aa",MODE_PRIVATE);
        //得到一个app_aa文件夹，权限为保护

        //File file = this.getExternalFilesDir("aa");
        // Log.i("ifo",file.toString());
        // 创建了一个文件夹 目录为：/storage/sdcard/Android/data/com.gdmec07150650.filedemo/files/aa

        //File file = this.getExternalCacheDir();
        //Log.i("ifo",file.toString());
        // 可以得到外部的存储位置，该外置存储位置跟内置的使用方法是一样的
        // 如果APP卸载了，这里面的数据也会自动清除掉
        // 如果开发者不遵循这样的规则 data/data/<包名>  /storage/sdcard/Android/data/<包名>
        // 续上句：卸载之后数据将不会自动清除掉，造成所谓的数据垃圾

        edit = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        text = (TextView) findViewById(R.id.text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeFiles(edit.getText().toString());
                text.setText(readFiles());
            }
        });
    }

    public void writeFiles(String content){
        try {
            FileOutputStream fos = openFileOutput("a.txt", MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFiles(){
        String content = null;
        try {
            FileInputStream fis = openFileInput("a.txt");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            content = baos.toString();
            fis.close();
            baos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
