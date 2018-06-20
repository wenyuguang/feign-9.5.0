package feign.redis;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class RedisUtil {

    private JedisCluster jedisCluster;

    public RedisUtil() {
        String redisString = "150.0.2.46:6379,150.0.2.46:7000,150.0.2.46:7001,150.0.2.47:6379,150.0.2.47:7000,150.0.2.47:7001";
        String[] hostArray = redisString.split(",");
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();

        //配置redis集群
        for (String host : hostArray) {
            String[] detail = host.split(":");
            nodes.add(new HostAndPort(detail[0], Integer.parseInt(detail[1])));
        }

        jedisCluster = new JedisCluster(nodes, 1000, 1000, 1, "sgyRedis", new GenericObjectPoolConfig());
    }

    public static void main(String args[]) {
        long start = System.nanoTime();
        String value = new RedisUtil().get("r.l.service-gaoxin");
        System.out.println(value);
        System.out.println(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start));
    }

    /**
     * 获取redis中指定key的值，value类型为String的使用此方法
     */
    public String get(String key) {
        return jedisCluster.get(key);
    }

    /**
     * 设置redis中指定key的值，value类型为String的使用此方法
     */
    public void set(String key, String value) {
        jedisCluster.set(key, value);
    }

    /**
     * 获取redis中指定key的值,对应的value，value类型为MAP的使用此方法
     */
    public Map<String, String> getMap(String key) {
        return jedisCluster.hgetAll(key);
    }

    /**
     * 删除redis中指定key的值项
     */
    public void del(String key) {
        jedisCluster.del(key);
    }

    /**
     * 关闭redis连接
     * @throws IOException
     */
    public void colose() throws IOException {
        jedisCluster.close();
    }
}