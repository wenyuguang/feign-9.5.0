package feign.redis.domain;

import java.io.Serializable;

public class Statistics implements Serializable {

    private String invokeDate;
    private String serviceName;
    private String serviceMethod;
    private String requestMethod;
    private long invokeNanoseconds;
    private long invokeTime;
}
