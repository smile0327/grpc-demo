package com.kevin.study.grpc.client;

import com.kevin.study.grpc.proto.MyRequest;
import com.kevin.study.grpc.proto.MyResponse;
import com.kevin.study.grpc.proto.StudentServiceGrpc;
import com.kevin.study.grpc.service.StudentServiceImpl;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

/**
 * @Auther: kevin
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Version: 1.0.0
 * @Date: Created in 14:35 2020/11/24
 * @ProjectName: grpc-demo
 */
public class GrpcClient {

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("10.172.246.170", 8899)
                .usePlaintext()
                .build();

        MyRequest request = MyRequest.newBuilder().setUsername("client").build();

//        StudentServiceGrpc.StudentServiceBlockingStub stub = StudentServiceGrpc.newBlockingStub(managedChannel);
//        MyResponse response = stub.getRealNameByUsername(request);
//        System.out.println("接收返回值:" + response.getRealname());

        // 异步调用
        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(managedChannel);
        StreamObserver<MyResponse> streamObserver = new StreamObserver<MyResponse>(){

            @Override
            public void onNext(MyResponse myResponse) {
                System.out.println("接收返回值:" + myResponse.getRealname());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        };

        stub.getRealNameByUsername(request, streamObserver);

        Thread.sleep(10000);
    }

}
