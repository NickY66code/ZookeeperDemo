package com.noah;

import javafx.scene.input.InputMethodTextRun;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestZooKeeper {

    private String connectString = "192.168.56.101:2181,192.168.56.103:2181,192.168.56.104:2181";
    private int sessionTimeout = 60000;
    private ZooKeeper zkClient;

    @Before
    public void init() throws IOException {

        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {


            public void process(WatchedEvent watchedEvent) {
//                List<String> children;
//                try{
//                    children= zkClient.getChildren("/", true);
//                    for(String child:children){
//                        System.out.println(child);
//                    }
//                }catch (KeeperException e){
//                    e.printStackTrace();
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
            }
        });
    }

    //1.创建节点
    @Test
    public void createNode() throws KeeperException, InterruptedException {
        String path = zkClient.create("/len", "kuaichuan".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        System.out.println(path);
    }

    //2.获取子节点并监控数值的变化
    @Test
    public void getDataAndWatch() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/", true);

//        for(String child:children){
//            System.out.println(child);
//        }

        Thread.sleep(Long.MAX_VALUE);
    }

    //3.判断节点是否存在
    @Test
    public void exist() throws KeeperException, InterruptedException {
        Stat exists = zkClient.exists("/noah", false);
        System.out.println(exists==null?"not exist":"exist");
    }
}
