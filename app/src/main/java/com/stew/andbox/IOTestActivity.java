package com.stew.andbox;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IOTestActivity extends AppCompatActivity {

    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.button_data_input_stream)
    Button buttonDataInputStream;
    @BindView(R.id.edit_text_byte)
    EditText editTextByte;
    @BindView(R.id.button_file_output_stream)
    Button buttonFileOutputStream;
    private String APP_ROOT_PATH;
    private TextView txConsole;
    private SimpleDateFormat format = new SimpleDateFormat("MM-dd/hh:mm:ss:SSS", Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_io);
        ButterKnife.bind(this);
        APP_ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getApplication().getPackageName() + "/";
        Button buttonFileInputStream = findViewById(R.id.button_file_input_stream);
        Button buttonBufferedInputStream = findViewById(R.id.button_buffer_input_stream);
        Button buttonClear = findViewById(R.id.button_clear);
        txConsole = findViewById(R.id.tx_console);
        File dir = new File(APP_ROOT_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        buttonFileInputStream.setOnClickListener(v -> dealFileInputStream());
        buttonBufferedInputStream.setOnClickListener(v -> dealBufferedInputStream());
        buttonDataInputStream.setOnClickListener(v -> dealDataInputStream());
        buttonFileOutputStream.setOnClickListener(v -> dealFileOutputStream());
        buttonClear.setOnClickListener(v -> {
            txConsole.setText("");
            _new = "Console:";
        });
    }

    private void dealFileInputStream() {

        if(TextUtils.isEmpty(editTextByte.getText())){
            return;
        }

        File file = new File(APP_ROOT_PATH, "text.txt");
        addFile(file);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] b = new byte[Integer.valueOf(editTextByte.getText().toString())];
            while (inputStream.read(b) != -1) {
                for (int i = 0; i < b.length; i++) {
                    dealConsoleStr("position: " + i + " / value: " + b[i]);
                }
            }
            inputStream.close();
            txConsole.setText(_new);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dealBufferedInputStream() {

        if(TextUtils.isEmpty(editTextByte.getText())){
            return;
        }

        File file = new File(APP_ROOT_PATH, "text.txt");
        addFile(file);
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] b = new byte[Integer.valueOf(editTextByte.getText().toString())];
            while (bufferedInputStream.read(b) != -1) {
                for (int i = 0; i < b.length; i++) {
                    dealConsoleStr("position: " + i + " / value: " + b[i]);
                }
            }
            bufferedInputStream.close();
            txConsole.setText(_new);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dealDataInputStream() {
        File file = new File(APP_ROOT_PATH, "text.txt");
        addDataFile(file);
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            dealConsoleStr(String.valueOf(dis.readInt()));
            dealConsoleStr(String.valueOf(dis.readChar()));
            dealConsoleStr(dis.readBoolean() + "");
            dealConsoleStr(dis.readUTF());
            dis.close();
            txConsole.setText(_new);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dealFileOutputStream() {

        if(TextUtils.isEmpty(editText.getText())){
            return;
        }

        File file = new File(APP_ROOT_PATH, "text.txt");
        addDataFile(file);
        try {

            FileOutputStream fos = new FileOutputStream(file);
            String content = editText.getText().toString();
            dealConsoleStr(this.getString(R.string.content) + ":" + content);
            fos.write(content.getBytes());
            fos.close();
            txConsole.setText(_new);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addDataFile(File file) {
        if (file.exists()) {
            if (!file.delete()) {
                return;
            }
        }
        try {
            if (file.createNewFile()) {
                dealConsoleStr(this.getString(R.string.file_create_success));
            }
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
            dos.writeInt(1000);
            dos.writeChar('a');
            dos.writeBoolean(true);
            dos.writeUTF("哈哈");
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addFile(File file) {

        if(TextUtils.isEmpty(editText.getText())){
            return;
        }

        if (file.exists()) {
            if (!file.delete()) {
                return;
            }
        }
        try {
            if (file.createNewFile()) {
                dealConsoleStr(this.getString(R.string.file_create_success));
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            String content = editText.getText().toString();
            dealConsoleStr(this.getString(R.string.content) + ":" + content);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String _new = "Console:";

    private void dealConsoleStr(String msg) {
        _new = _new + "\n" + format.format(new Date(System.currentTimeMillis())) + "------" + msg;
    }

}
