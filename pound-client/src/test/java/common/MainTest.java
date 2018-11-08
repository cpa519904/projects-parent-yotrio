package common;

import com.yotrio.common.exceptions.*;
import com.yotrio.common.utils.SerialPortUtil;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * 模块名称：projects-parent com.yotrio.common
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-09-19 16:36
 * 系统版本：1.0.0
 **/

public class MainTest {

    public static void main(String[] args) {
        ArrayList<String> ports = SerialPortUtil.findPort();
        System.out.println("prots:" + ports.toString());

        try {
            //创建串口 COM5位串口名称 9600波特率
            SerialPort serialPort = SerialPortUtil.openPort("COM5", 9600);
            //通过串口发送信息
            SerialPortUtil.sendToPort(serialPort, "你好".getBytes());

            //创建一个线程对象读串口数据
//            ExecutorService executor = Executors.newSingleThreadExecutor();
//            Runnable worker = new WorkerThread(serialPort);
//            executor.execute(worker);

            //设置串口监听
            SerialPortUtil.addListener(serialPort, new SerialPortEventListener() {
                @Override
                public void serialEvent(SerialPortEvent serialPortEvent) {
                    if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                        try {
                            byte[] bytes = SerialPortUtil.readFromPort(serialPort);
                            System.out.println("收到数据长度：" + bytes.length);
                            System.out.println("收到的数据：" + new String(bytes, "GB2312"));
                        } catch (ReadDataFromSerialPortFailure readDataFromSerialPortFailure) {
                            readDataFromSerialPortFailure.printStackTrace();
                        } catch (SerialPortInputStreamCloseFailure serialPortInputStreamCloseFailure) {
                            serialPortInputStreamCloseFailure.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (SerialPortParameterFailure serialPortParameterFailure) {
            serialPortParameterFailure.printStackTrace();
        } catch (NotASerialPort notASerialPort) {
            notASerialPort.printStackTrace();
        } catch (NoSuchPort noSuchPort) {
            noSuchPort.printStackTrace();
        } catch (PortInUse portInUse) {
            portInUse.printStackTrace();
        } catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
            sendDataToSerialPortFailure.printStackTrace();
        } catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
            serialPortOutputStreamCloseFailure.printStackTrace();
        } catch (TooManyListeners tooManyListeners) {
            tooManyListeners.printStackTrace();
        }
    }


}