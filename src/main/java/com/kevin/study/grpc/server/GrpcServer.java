package com.kevin.study.grpc.server;

import com.kevin.study.grpc.service.StudentServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

/**
 * @Auther: kevin
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Version: 1.0.0
 * @Date: Created in 14:04 2020/11/24
 * @ProjectName: grpc-demo
 */
public class GrpcServer {

    private Server server;

    private void start() throws Exception{
        this.server = ServerBuilder.forPort(8899).addService(new StudentServiceImpl()).build().start();
        System.out.println("server started!");

        //在服务端jvm关闭之前主动退出grpc服务，且关闭其相应的资源
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("关闭JVM");
            GrpcServer.this.stop();
        }));
    }

    private void stop(){
        if (this.server != null){
            this.server.shutdown();
        }
    }

    //让服务启动后处于等待状态，否则服务启动立马就停止了
    private void awaitTermination() throws InterruptedException{
        if (this.server != null){
            this.server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception{
        GrpcServer grpcServer = new GrpcServer();
        grpcServer.start();
        grpcServer.awaitTermination();
    }

}
