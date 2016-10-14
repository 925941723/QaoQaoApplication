package com.qiaoqiao.qaoqaoapplication.socket;

import com.qiaoqiao.qaoqaoapplication.asynctask.SimpleTask;
import com.qiaoqiao.qaoqaoapplication.config.Constants;
import com.qiaoqiao.qaoqaoapplication.listener.SocketListener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * 作者：VULCAN
 * 版本：1.0
 * 创建日期：2016/9/20
 * 描述：
 * 修订历史：
 */

public class QAOSocket {
    private SocketListener socketListener;
    private boolean qaoSocketIsBegin = false;

    private Socket qaoSocket = null;
    private DataInputStream dataInputStream = null;
    private DataOutputStream dataOutputStream =null;

    public QAOSocket(SocketListener socketListener) {
        this.socketListener = socketListener;
    }

    public void start(){
        qaoSocketIsBegin = true;
        stop();
        newSocket();
    }

    private void newSocket() {
        SimpleTask simpleTask = new SimpleTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    qaoSocket = new Socket(Constants.IP, Constants.PORT);
                    dataInputStream = new DataInputStream(qaoSocket.getInputStream());
                    dataOutputStream = new DataOutputStream(qaoSocket.getOutputStream());
                    int length;
                    byte buffer[] = new byte[1024];
                    if (qaoSocket!=null && qaoSocket.isConnected() && !qaoSocket.isClosed() && !qaoSocket.isInputShutdown()){
                        while (!qaoSocket.isInputShutdown()){
                            if (null!=socketListener){
                                socketListener.onDataCallback(dataInputStream.readUTF());
                            }
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    //加入重连方法，还有重连时要判断是否有网络
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
            }
        };
        simpleTask.execute();
    }

    public void send(String string){
        if (qaoSocket != null && qaoSocket.isConnected() && !qaoSocket.isOutputShutdown()) {
            try {
                dataOutputStream.writeUTF(string);
                dataOutputStream.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        closeInputStream();
        closeOutputStream();
        closeSocket();
    }

    private void closeOutputStream(){
        try {
            if (null!=dataOutputStream){
                dataOutputStream.flush();
                dataOutputStream.close();
                dataOutputStream = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void closeInputStream(){
        try {
            if (null!=dataInputStream){
                dataInputStream.close();
                dataInputStream = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void closeSocket(){
        try {
            if (null!=qaoSocket){
                qaoSocket.shutdownOutput();
                qaoSocket.shutdownInput();
                qaoSocket.close();
                qaoSocket = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
