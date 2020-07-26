package com.noah;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.time.LocalDateTime;

public class DistributeServer {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        DistributeServer server = new DistributeServer();
        //1.连接zookeeper集群
        server.getConnect();
        //2.注册节点
        server.register(args[0]);
        //3.业务逻辑处理
        server.business();
    }

    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    private void register(String hostname) throws KeeperException, InterruptedException {
        String path = zkClient.create("/servers/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        System.out.println(hostname+"is online ");
    }

    private String connectString = "192.168.56.101:2181,192.168.56.103:2181,192.168.56.104:2181";
    private int sessionTimeout = 60000;
    private ZooKeeper zkClient;

    private void getConnect() throws IOException {

        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }
}
