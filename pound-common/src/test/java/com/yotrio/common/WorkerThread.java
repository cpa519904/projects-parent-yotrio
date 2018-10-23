package com.yotrio.common;

import com.yotrio.common.exceptions.ReadDataFromSerialPortFailure;
import com.yotrio.common.exceptions.SerialPortInputStreamCloseFailure;
import com.yotrio.common.utils.SerialPortUtil;
import gnu.io.SerialPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

import static java.lang.Thread.sleep;

/**
 * 模块名称：projects-parent com.yotrio.common
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-09-20 10:05
 * 系统版本：1.0.0
 **/

public class WorkerThread implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerThread.class);
    private SerialPort serialPort;

    public WorkerThread(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    @Override
    public void run() {
        //通过串口接收信息
        byte[] bytes = new byte[0];
        try {
            while (true) {
                bytes = SerialPortUtil.readFromPort(serialPort);
                if (bytes != null && bytes.length > 0) {
                    String receiveStr = new String(bytes, "GB2312");
                    System.out.println("receiveStr={}" + receiveStr);
                    LOGGER.info("receiveStr={}" + receiveStr);
                }
                sleep(3000);
            }
        } catch (ReadDataFromSerialPortFailure readDataFromSerialPortFailure) {
            readDataFromSerialPortFailure.printStackTrace();
        } catch (SerialPortInputStreamCloseFailure serialPortInputStreamCloseFailure) {
            serialPortInputStreamCloseFailure.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}