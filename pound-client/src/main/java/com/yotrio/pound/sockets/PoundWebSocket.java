package com.yotrio.pound.sockets;

import com.yotrio.common.exceptions.*;
import com.yotrio.common.utils.SerialPortUtil;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 处理地磅数据websocket逻辑
 * 模块名称：projects-parent com.yotrio.pound.sockets
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-10-11 13:20
 * 系统版本：1.0.0
 **/

@ServerEndpoint(value = "/websocket") //接受websocket请求路径
@Component
public class PoundWebSocket {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //保存所有在线socket连接
    private static Map<String, PoundWebSocket> webSocketMap = new LinkedHashMap<>();

    //记录当前在线数目
    private static int count = 0;

    //当前连接（每个websocket连入都会创建一个MyWebSocket实例
    private Session session;

    //创建监听串口
    private static SerialPort serialPort = null;

    //监听串口
    private static final String PORT_NAME = "COM5";

    //监听串口波特率
    private static final int BAUDRATE = 9600;

    /**
     * 处理连接建立
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketMap.put(session.getId(), this);
        addCount();
        logger.info("新的连接加入：{}", session.getId());

        try {
            //创建串口 COM5位串口名称 9600波特率
            if (serialPort == null) {
                serialPort = SerialPortUtil.openPort(PORT_NAME, BAUDRATE);

                //设置串口监听
                SerialPortUtil.addListener(serialPort, new SerialPortEventListener() {
                    @Override
                    public void serialEvent(SerialPortEvent serialPortEvent) {
                        if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                            try {
                                byte[] bytes = SerialPortUtil.readFromPort(serialPort);
//                                System.out.println("收到的数据：" + new String(bytes, "GB2312"));

                                sendMessage(new String(bytes, "GB2312")+ new Date().getSeconds());
                            } catch (ReadDataFromSerialPortFailure readDataFromSerialPortFailure) {
                                logger.error(readDataFromSerialPortFailure.toString());
                            } catch (SerialPortInputStreamCloseFailure serialPortInputStreamCloseFailure) {
                                logger.error(serialPortInputStreamCloseFailure.toString());
                            } catch (UnsupportedEncodingException e) {
                                logger.error(e.toString());
                            } catch (IOException e) {
                                logger.error(e.toString());
                            }
                        }
                    }
                });
            }
        } catch (SerialPortParameterFailure serialPortParameterFailure) {
            logger.error(serialPortParameterFailure.toString());
        } catch (NotASerialPort notASerialPort) {
            logger.error(notASerialPort.toString());
        } catch (NoSuchPort noSuchPort) {
            logger.error(noSuchPort.toString());
        } catch (PortInUse portInUse) {
            logger.error(portInUse.toString());
        } catch (TooManyListeners tooManyListeners) {
            logger.error(tooManyListeners.toString());
        }
    }

    /**
     * 接受消息
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("收到客户端{}消息：{}", session.getId(), message);
        try {
            this.sendMessage(message);
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    /**
     * 处理错误
     *
     * @param error
     * @param session
     */
    @OnError
    public void onError(Throwable error, Session session) {
        logger.info("发生错误{},{}", session.getId(), error.getMessage());
    }

    /**
     * 处理连接关闭
     */
    @OnClose
    public void onClose() {
        webSocketMap.remove(this.session.getId());
        reduceCount();
        logger.info("连接关闭:{}", this.session.getId());

        //连接关闭后关闭串口，下一次打开连接重新监听串口
        if (serialPort != null) {
            SerialPortUtil.closePort(serialPort);
            serialPort = null;
        }
    }

    /**
     * 群发消息
     *
     * @param message
     */
    public void sendMessageToAll(String message) {
        for (int i = 0; i < webSocketMap.size(); i++) {
            try {
//                logger.info("session:id=" + session.getId());
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送消息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        logger.info("session:id=" + session.getId());
        this.session.getBasicRemote().sendText(message);
    }

    //广播消息
    public static void broadcast() {
        PoundWebSocket.webSocketMap.forEach((k, v) -> {
            try {
                v.sendMessage("这是一条测试广播");
            } catch (Exception e) {
            }
        });
    }

    //获取在线连接数目
    public static int getCount() {
        return count;
    }

    //操作count，使用synchronized确保线程安全
    public static synchronized void addCount() {
        PoundWebSocket.count++;
    }

    public static synchronized void reduceCount() {
        PoundWebSocket.count--;
    }

}