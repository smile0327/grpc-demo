package com.kevin.study.grpc.service;

import com.kevin.study.grpc.proto.MyRequest;
import com.kevin.study.grpc.proto.MyResponse;
import com.kevin.study.grpc.proto.StudentProto;
import com.kevin.study.grpc.proto.StudentServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @Auther: kevin
 * @Description:
 * @Company: 上海博般数据技术有限公司
 * @Version: 1.0.0
 * @Date: Created in 10:16 2020/11/24
 * @ProjectName: grpc-demo
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase{

    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收客户端消息:" + request.getUsername());
        //构造返回结果，并返回给客户端
        responseObserver.onNext(MyResponse.newBuilder().setRealname("response_" + request.getUsername()).build());
        //告诉客户端方法执行完毕
        responseObserver.onCompleted();
    }

}
