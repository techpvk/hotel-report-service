package com.prakash.report.generator.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.server.NIOServerCnxnFactory;
import org.apache.zookeeper.server.ZooKeeperServer;

public class EmbeddedZookeeper {
    String snapshotDir = "/tmp/kzsnap";
    String logDir = "/tmp/zklog";
    ZooKeeperServer zk;
    ZkClient client;
    NIOServerCnxnFactory factory;
    int port;

    public EmbeddedZookeeper(int port) {
        this.port = port;
    }

    public void start() throws IOException, InterruptedException {
        File sn = new File(snapshotDir);
        sn.mkdir();
        File lg = new File(logDir);
        lg.mkdir();
        zk = new ZooKeeperServer(sn, lg, 3000);
        factory = new NIOServerCnxnFactory();
        factory.configure(new InetSocketAddress("localhost", port), 1000);
        factory.startup(zk);
        client = new ZkClient("localhost:" + port);
        // client.setZkSerializer(new StringSerializer());
    }

    public void prepair() throws Exception {
        this.delete(new File(this.logDir));
        this.delete(new File(this.snapshotDir));
    }

    public void shutdown() {
        factory.shutdown();
//        zk.shutdown();
    }

    public static void delete(File f) throws IOException {
        if (!f.exists()) {
            return;
        }
        if (f.isDirectory()) {
            for (File c : f.listFiles()) {
                delete(c);
            }
        }
        if (!f.delete()) {
            throw new FileNotFoundException("Failed to delete file: " + f);
        }
    }
}
