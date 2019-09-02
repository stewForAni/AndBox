package com.stew.andbox;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
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
    @BindView(R.id.button_print_stream)
    Button buttonPrintStream;
    @BindView(R.id.button_Piped_input_output_stream)
    Button buttonPipedInputOutputStream;
    @BindView(R.id.button_object_output_stream)
    Button buttonObjectOutputStream;
    @BindView(R.id.button_object_input_stream)
    Button buttonObjectInputStream;
    @BindView(R.id.button_writer_reader)
    Button buttonWriterReader;
    @BindView(R.id.button_random_access_file)
    Button buttonRandomAccessFile;


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
        buttonPrintStream.setOnClickListener(v -> dealPrintStream());
        buttonPipedInputOutputStream.setOnClickListener(v -> dealPipedStream());
        buttonObjectOutputStream.setOnClickListener(v -> dealObjectOutputStream());
        buttonObjectInputStream.setOnClickListener(v -> dealObjectInputStream());
        buttonWriterReader.setOnClickListener(v -> dealWriterReader());
        buttonRandomAccessFile.setOnClickListener(v -> dealRandomAccessFile());

        buttonClear.setOnClickListener(v -> {
            txConsole.setText("");
            _new = "Console:";
        });
    }

    private void dealFileInputStream() {

        if (TextUtils.isEmpty(editTextByte.getText())) {
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

        if (TextUtils.isEmpty(editTextByte.getText())) {
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

        if (TextUtils.isEmpty(editText.getText())) {
            return;
        }

        File file = new File(APP_ROOT_PATH, "text.txt");
        addFile(file);
        try {

            FileOutputStream fos = new FileOutputStream(file, true);
            String content = editText.getText().toString();
            dealConsoleStr(this.getString(R.string.content) + ":" + content);

            //写入换行
            String lineSeparator = System.getProperty("line.separator");
            fos.write(lineSeparator.getBytes());

            fos.write(content.getBytes());
            fos.close();
            txConsole.setText(_new);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dealPrintStream() {
        File file = new File(APP_ROOT_PATH, "text.txt");
        addFile(file);

        try {
            PrintStream prinStream = new PrintStream(file);
            prinStream.print("hh");
            prinStream.print("jj");
            prinStream.print("kk");
            prinStream.print("ll");
            prinStream.close();
            dealConsoleStr(this.getString(R.string.content) + ":" + new File(APP_ROOT_PATH + "text.txt").getAbsolutePath());
            txConsole.setText(_new);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void dealPipedStream() {

        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = new PipedOutputStream();

        try {
            pos.connect(pis);
            for (int i = 1; i <= 20; i++) {
                pos.write((byte) i);
            }
            pos.close();

            int data;
            while ((data = pis.read()) != -1) {
                dealConsoleStr(String.valueOf(data));
            }
            pis.close();
            txConsole.setText(_new);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dealObjectOutputStream() {

        Person p1 = new Person("John", "Male", 1.7);
        Person p2 = new Person("Wally", "Male", 1.7);
        Person p3 = new Person("Katrina", "Female", 1.4);

        File file = new File(APP_ROOT_PATH, "text.txt");
        addFile(file);

        try {

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(p1);
            oos.writeObject(p2);
            oos.writeObject(p3);
            oos.close();

            dealConsoleStr(p1.toString());
            dealConsoleStr(p2.toString());
            dealConsoleStr(p3.toString());
            txConsole.setText(_new);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void dealObjectInputStream() {
        File file = new File(APP_ROOT_PATH + "text.txt");

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            Person p1 = (Person) ois.readObject();
            Person p2 = (Person) ois.readObject();
            Person p3 = (Person) ois.readObject();
            ois.close();

            dealConsoleStr(p1.toString());
            dealConsoleStr(p2.toString());
            dealConsoleStr(p3.toString());
            txConsole.setText(_new);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void dealWriterReader() {
        File file = new File(APP_ROOT_PATH, "text.txt");
        addFile(file);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.append("one");
            writer.newLine();
            writer.append("two");
            writer.newLine();
            writer.append("three");
            writer.newLine();
            writer.append("four");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text;
            while ((text = reader.readLine()) != null) {
                dealConsoleStr(text);
            }
            txConsole.setText(_new);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream is = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String text;
            while ((text = reader.readLine()) != null) {
                dealConsoleStr(text);
            }
            txConsole.setText(_new);
            reader.close();
            is.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dealRandomAccessFile() {

        File file = new File(APP_ROOT_PATH, "text.txt");
        addFile(file);

        RandomAccessFile raf;
        try {
            raf = new RandomAccessFile(file, "rw");
            raf.writeInt(0);
            raf.writeUTF("Hello world!");
            raf.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            raf = new RandomAccessFile(file, "rw");
            dealConsoleStr(raf.readInt() + "");
            dealConsoleStr(raf.readUTF());
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

        if (TextUtils.isEmpty(editText.getText())) {
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
