import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class A {
    @Test
    public void jedisTest1(){
        //获得连接对象，设置ip地址和端口
        Jedis jedis = new Jedis("192.168.56.101",6379);
        //2.操作jedis
        jedis.set("username","wanger");
        String username = jedis.get("username");
        System.out.println(username);

         jedis.sadd("myset", "a", "b", "c");
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);

        //连接池连接
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //最大连接数
        poolConfig.setMaxTotal(30);
        //最大空闲连接数
        poolConfig.setMaxIdle(10);
        //获得连接池
        JedisPool pool = new JedisPool(poolConfig, "192.168.56.101", 6379);
        //获得核心对象
        Jedis resource = pool.getResource();
        resource.set("name","dd");
        String name = resource.get("name");
        System.out.println(name);
        //释放资源
        resource.close();
        //虚拟机关闭时，释放pool资源
        pool.close();
    }
}
