package com.liangzilixueha.liangzipage;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class LiangziFiles extends AppCompatActivity {
    private String TAG = "Liangzi";

    public void log(String what, String msg) {
        Log.e(TAG, what + ": " + msg);
    }

    /**
     * 错误文档日志<br/>
     * 建议使用 <pre class="prettyprint">时间+包名+函数名+错误信息</pre>生成错误日志
     * 可以使用以下模板生成错误日志
     * <pre class="prettyprint">
     *     String time = new Date().toString() + " ";
     *     String filename = this.getClass().getName() + " ";
     *     String functionname = " ";
     *     String error = "这是错误信息";
     *     String string = time + filename + functionname + error;
     *     liangziFiles.writeLog(context, string);
     *     </pre>
     *
     * @param context 上下文
     * @param msg     错误本身消息
     * @return void
     */
    public void writeLog(Context context, String msg) {
        //获取私有目录
        String path = context.getFilesDir().getAbsolutePath();
        //获取私有目录下的文件
        String fileName = "error.txt";
        File file = new File(path, fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //写入追加数据msg
        try {
            //创建输出流对象
            java.io.FileOutputStream fos = new java.io.FileOutputStream(file, true);
            //写入数据
            fos.write(msg.getBytes());
            fos.write("\n".getBytes());
            //关闭输出流对象
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearLog(Context context) {
        //获取私有目录
        String path = context.getFilesDir().getAbsolutePath();
        //获取私有目录下的文件
        String fileName = "error.txt";
        File file = new File(path, fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    public String readLog(Context context) {
        //获取私有目录
        String path = context.getFilesDir().getAbsolutePath();
        //获取私有目录下的文件
        String fileName = "error.txt";
        File file = new File(path, fileName);
        if (file.exists()) {
            //读取文件内容
            try {
                //创建输入流对象
                java.io.FileInputStream fis = new java.io.FileInputStream(file);
                //创建字节数组
                byte[] bytes = new byte[fis.available()];
                //读取数据
                fis.read(bytes);
                //关闭输入流对象
                fis.close();
                //输出数据
                return new String(bytes);
            } catch (Exception e) {
                e.printStackTrace();
                return "读取错误";
            }
        } else {
            return "文件不存在";
        }
    }
}
