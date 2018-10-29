package com.yotrio.pound.utils;


import com.alibaba.fastjson.JSON;
import com.yotrio.common.utils.AESUtil;
import com.yotrio.common.utils.PropertiesFileUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Redis 工具类
 * Created by wangyq on 2016/11/26.
 */
public class RedisUtil {

    protected static ReentrantLock lockPool = new ReentrantLock();
    protected static ReentrantLock lockJedis = new ReentrantLock();

    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    // Redis服务器IP
    private static String IP = PropertiesFileUtil.getInstance("redis").get("master.redis.ip");

    // Redis的端口号
    private static int PORT = PropertiesFileUtil.getInstance("redis").getInt("master.redis.port");

    // 访问密码
    private static String PASSWORD = AESUtil.AESDecode(PropertiesFileUtil.getInstance("redis").get("master.redis.password"));
    // 可用连接实例的最大数目，默认值为8；
    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = PropertiesFileUtil.getInstance("redis").getInt("master.redis.max_active");

    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = PropertiesFileUtil.getInstance("redis").getInt("master.redis.max_idle");

    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = PropertiesFileUtil.getInstance("redis").getInt("master.redis.max_wait");

    // 超时时间
    private static int TIMEOUT = PropertiesFileUtil.getInstance("redis").getInt("master.redis.timeout");

    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = false;

    private static JedisPool jedisPool = null;

    /**
     * 成功,"OK"
     */
    private static final String SUCCESS_OK = "OK";
    /**
     * 成功,1L
     */
    private static final Long SUCCESS_STATUS_LONG = 1L;
    /**
     * 只用key不存在时才设置。Only set the key if it does not already exist
     */
    private static final String NX = "NX";
    /**
     * XX -- 只有key存在时才设置。和NX相反。Only set the key if it already exist.
     */
    private static final String XX = "XX";
    /**
     * EX|PX, 时间单位，EX是秒，PX是毫秒。expire time units: EX = seconds; PX = milliseconds
     */
    private static final String EX = "EX";
    /**
     * EX|PX, 时间单位，EX是秒，PX是毫秒。expire time units: EX = seconds; PX = milliseconds
     * <p>
     * /**
     * redis过期时间,以秒为单位
     */
    public final static int EXRP_HOUR = 60 * 60;            //一小时
    public final static int EXRP_DAY = 60 * 60 * 24;        //一天
    public final static int EXRP_MONTH = 60 * 60 * 24 * 30;    //一个月

    /**
     * 初始化Redis连接池
     */
    private static void initialPool() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, IP, PORT, TIMEOUT);
        } catch (Exception e) {
            logger.error("First create JedisPool error : " + e);
        }
    }

    /**
     * 在多线程环境同步初始化
     */
    private static synchronized void poolInit() {
        if (null == jedisPool) {
            initialPool();
        }
    }


    /**
     * 同步获取Jedis实例
     *
     * @return Jedis
     */
    public synchronized static Jedis getJedis() {
        poolInit();
        Jedis jedis = null;
        try {
            if (null != jedisPool) {
                jedis = jedisPool.getResource();
                try {
                    jedis.auth(PASSWORD);
                } catch (Exception e) {

                }
            }
        } catch (Exception e) {
            logger.error("Get jedis error : " + e);
        }
        return jedis;
    }

    /**
     * 设置 String
     *
     * @param key
     * @param value
     */
    public synchronized static void set(String key, String value) {
        try {
            value = StringUtils.isBlank(value) ? "" : value;
            Jedis jedis = getJedis();
            jedis.set(key, value);
            jedis.close();
        } catch (Exception e) {
            logger.error("Set key error : " + e);
        }
    }

    /**
     * 设置 byte[]
     *
     * @param key
     * @param value
     */
    public synchronized static void set(byte[] key, byte[] value) {
        try {
            Jedis jedis = getJedis();
            jedis.set(key, value);
            jedis.close();
        } catch (Exception e) {
            logger.error("Set key error : " + e);
        }
    }

    /**
     * 设置 Map
     *
     * @param key
     * @param map
     */
    public synchronized static void setHashMap(String key, Map<String, String> map) {
        try {
            Jedis jedis = getJedis();
            jedis.hmset(key, map);
            jedis.close();
        } catch (Exception e) {
            logger.error("Set key error : " + e);
        }
    }

    /**
     * 向缓存中设置对象
     *
     * @param key
     * @param value
     * @return
     */
    public static void setObj(String key, Object value) {
        Jedis jedis = null;
        try {
            String objectJson = JSON.toJSONString(value);
            jedis = jedisPool.getResource();
            jedis.set(key, objectJson);
            jedis.close();
        } catch (Exception e) {
            logger.error("setObj key error : " + e);
        }
    }

    /**
     * 删除缓存中得对象，根据key
     *
     * @param key
     * @return
     */
    public static void delObj(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
            jedis.close();
        } catch (Exception e) {
            logger.error("delObj key error : " + e);
        }
    }

    /**
     * 根据key 获取内容
     *
     * @param key
     * @return
     */
    public static Object getObj(String key) {
        Jedis jedis = getJedis();
        if (jedis == null) {
            return null;
        }
        Object value = jedis.get(key);
        jedis.close();
        return value;
    }


    /**
     * 根据key 获取对象
     *
     * @param key
     * @return
     */
    public static <T> T getObj(String key, Class<T> clazz) {
        Jedis jedis = getJedis();
        if (jedis == null) {
            return null;
        }
        jedis = jedisPool.getResource();
        String value = jedis.get(key);
        jedis.close();
        return JSON.parseObject(value, clazz);
    }

    /**
     * 设置 String 过期时间
     *
     * @param key
     * @param value
     * @param seconds 以秒为单位
     */
    public synchronized static void set(String key, String value, int seconds) {
        try {
            value = StringUtils.isBlank(value) ? "" : value;
            Jedis jedis = getJedis();
            jedis.setex(key, seconds, value);
            jedis.close();
        } catch (Exception e) {
            logger.error("Set keyex error : " + e);
        }
    }

    /**
     * 设置 byte[] 过期时间
     *
     * @param key
     * @param value
     * @param seconds 以秒为单位
     */
    public synchronized static void set(byte[] key, byte[] value, int seconds) {
        try {
            Jedis jedis = getJedis();
            jedis.set(key, value);
            jedis.expire(key, seconds);
            jedis.close();
        } catch (Exception e) {
            logger.error("Set key error : " + e);
        }
    }

    /**
     * 设置 map 过期时间
     *
     * @param key
     * @param map
     * @param seconds 以秒为单位
     */
    public synchronized static void setHashMap(String key, Map<String, String> map, int seconds) {
        try {
            Jedis jedis = getJedis();
            jedis.hmset(key, map);
            jedis.expire(key, seconds);
            jedis.close();
        } catch (Exception e) {
            logger.error("Set key error : " + e);
        }
    }

    /**
     * 获取String值
     *
     * @param key
     * @return value
     */
    public synchronized static String get(String key) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        String value = jedis.get(key);
        jedis.close();
        return value;
    }

    /**
     * 获取map值
     *
     * @param key
     * @return value List<String>
     */
    public synchronized static List<String> getHashMap(String key) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        List<String> value = jedis.hmget(key);
        jedis.close();
        return value;
    }

    /**
     * 获取map值
     *
     * @param key
     * @return value
     */
    public synchronized static byte[] get(byte[] key) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        byte[] value = jedis.get(key);
        jedis.close();
        return value;
    }

    /**
     * 删除值
     *
     * @param key
     */
    public synchronized static void remove(String key) {
        try {
            Jedis jedis = getJedis();
            jedis.del(key);
            jedis.close();
        } catch (Exception e) {
            logger.error("Remove keyex error : " + e);
        }
    }

    /**
     * 删除值
     *
     * @param key
     */
    public synchronized static void remove(byte[] key) {
        try {
            Jedis jedis = getJedis();
            jedis.del(key);
            jedis.close();
        } catch (Exception e) {
            logger.error("Remove keyex error : " + e);
        }
    }

    /**
     * sadd
     *
     * @param key
     * @param value
     * @param seconds
     */
    public synchronized static void sadd(String key, String value, int seconds) {
        try {
            Jedis jedis = RedisUtil.getJedis();
            jedis.sadd(key, value);
            jedis.expire(key, seconds);
            jedis.close();
        } catch (Exception e) {
            logger.error("sadd error : " + e);
        }
    }

    /**
     * 向有序集合中加入元素
     *
     * @param key
     * @param score
     * @param value
     */
    public synchronized static void zadd(String key, double score, String value) {
        try {
            Jedis jedis = RedisUtil.getJedis();
            jedis.zadd(key, score, value);
            jedis.close();
        } catch (Exception e) {
            logger.error("zadd error : " + e);
        }
    }

    /**
     * 向有序集合中加入元素
     *
     * @param key
     * @param score
     * @param value
     * @param seconds
     */
    public synchronized static void zadd(String key, double score, String value, int seconds) {
        try {
            Jedis jedis = RedisUtil.getJedis();
            jedis.zadd(key, score, value);
            jedis.expire(key, seconds);
            jedis.close();
        } catch (Exception e) {
            logger.error("zadd error : " + e);
        }
    }

    /**
     * 统计元素个数
     *
     * @param key
     * @return value
     */
    public synchronized static Long zcard(String key) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        Long value = jedis.zcard(key);
        jedis.close();
        return value;
    }

    /**
     * 统计某个权重范围内元素个数
     *
     * @param key
     * @param min
     * @param max
     * @return value
     */
    public synchronized static Long zcount(String key, double min, double max) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        Long value = jedis.zcount(key, min, max);
        jedis.close();
        return value;
    }

    /**
     * 按权重排序后读取索引范围内元素及权重
     *
     * @param key
     * @return
     */
    public synchronized static Set<Tuple> zrangeWithScores(String key) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        Set<Tuple> tuples = jedis.zrangeWithScores(key, 0, -1);
        jedis.close();
        return tuples;
    }

    /**
     * 按权反向排序后读取索引范围内元素及权重
     *
     * @param key
     * @return
     */
    public synchronized static Set<Tuple> zrevrangeWithScores(String key) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        Set<Tuple> tuples = jedis.zrevrangeWithScores(key, 0, -1);
        jedis.close();
        return tuples;
    }

    /**
     * 按权重排序后读取索引范围内元素
     *
     * @param key
     * @return value
     */
    public synchronized static Set<String> zrange(String key, Long start, Long end) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        Set<String> set = jedis.zrange(key, start, end);
        jedis.close();
        return set;
    }

    /**
     * 按权重反向排序后读取索引范围内元素
     *
     * @param key
     * @param start
     * @param end
     * @return value
     */
    public synchronized static Set<String> zrevrange(String key, Long start, Long end) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        Set<String> set = jedis.zrevrange(key, start, end);
        jedis.close();
        return set;
    }

    /**
     * 读取权重在指定范围内的元素及其权重并按权重排序
     *
     * @param key
     * @param start
     * @param end
     * @return value
     */
    public synchronized static Set<Tuple> zrangeByScoreWithScores(String key, double start, double end) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        Set<Tuple> tuples = jedis.zrangeByScoreWithScores(key, start, end);
        jedis.close();
        return tuples;
    }

    /**
     * 读取权重在指定范围内的元素及其权重并按权重反向排序
     *
     * @param key
     * @param start
     * @param end
     * @return value
     */
    public synchronized static Set<Tuple> zrevrangeByScoreWithScores(String key, double start, double end) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        Set<Tuple> tuples = jedis.zrevrangeByScoreWithScores(key, start, end);
        jedis.close();
        return tuples;
    }

    /**
     * 读取权重在指定范围内的元素并按权重排序
     *
     * @param key
     * @param start
     * @param end
     * @return value
     */
    public synchronized static Set<String> zrangeByScore(String key, double start, double end) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        Set<String> set = jedis.zrangeByScore(key, start, end);
        jedis.close();
        return set;
    }

    /**
     * 读取权重在指定范围内的元素并按权重反向排序
     *
     * @param key
     * @param start
     * @param end
     * @return value
     */
    public synchronized static Set<String> zrevrangeByScore(String key, double start, double end) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        Set<String> set = jedis.zrevrangeByScore(key, start, end);
        jedis.close();
        return set;
    }


    /**
     * 查看集合中指定元素的权重
     *
     * @param key
     * @param value
     * @return
     */
    public synchronized static Double zscore(String key, String value) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        Double zscore = jedis.zscore(key, value);
        jedis.close();
        return zscore;
    }

    /**
     * 修改指定元素权重
     *
     * @param key
     * @param score
     * @param value
     * @return
     */
    public synchronized static Double zincrby(String key, double score, String value) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        Double zincrby = jedis.zincrby(key, score, value);
        jedis.close();
        return zincrby;
    }

    /**
     * 删除元素 by value
     *
     * @param key
     * @param value
     * @return
     */
    public synchronized static Long zrem(String key, String value) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        Long zrem = jedis.zrem(key, value);
        jedis.close();
        return zrem;
    }

    /**
     * 删除元素 by 权重范围
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public synchronized static Long zremrangeByScore(String key, Double min, Double max) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        Long zrem = jedis.zremrangeByScore(key, min, max);
        jedis.close();
        return zrem;
    }

    /**
     * 查看排名
     *
     * @param key
     * @param member
     * @return
     */
    public synchronized static Long zrank(String key, String member) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        Long value = jedis.zrank(key, member);
        jedis.close();
        return value;
    }

    /**
     * 查看倒数排名
     *
     * @param key
     * @param member
     * @return
     */
    public synchronized static Long zrevrank(String key, String member) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        Long value = jedis.zrevrank(key, member);
        jedis.close();
        return value;
    }

    /**
     * incr
     *
     * @param key
     * @return value
     */
    public synchronized static Long incr(String key) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        long value = jedis.incr(key);
        jedis.close();
        return value;
    }

    /**
     * decr
     *
     * @param key
     * @return value
     */
    public synchronized static Long decr(String key) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            return null;
        }
        long value = jedis.decr(key);
        jedis.close();
        return value;
    }

    /**
     * 设置key值和过期时间
     *
     * @param key
     * @param value
     * @param seconds 秒数，不能小于0
     * @return
     */
    public static boolean setByTime(String key, String value, int seconds) {
        if (seconds < 0) {
            return false;
        }
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String statusCode = jedis.setex(key, seconds, value);
            if (SUCCESS_OK.equalsIgnoreCase(statusCode)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * @param key
     * @param value
     * @param nxxx  NX|XX  是否存在
     *              <li>NX -- Only set the key if it does not already exist.</li>
     *              <li>XX -- Only set the key if it already exist.</li>
     * @param expx  EX|PX, expire time units ，时间单位格式，秒或毫秒
     *              <li>EX = seconds;</li>
     *              <li>PX = milliseconds</li>
     * @param time  expire time in the units of expx，时间（long型），不能小于0
     * @return
     */
    public static boolean set(String key, String value, String nxxx, String expx, long time) {
        if (time < 0) {
            return false;
        }

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String statusCode = jedis.set(key, value, nxxx, expx, time);
            if (SUCCESS_OK.equalsIgnoreCase(statusCode)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 设置key
     *
     * @param key
     * @param value
     * @param nxxx  NX|XX 是否需要存在
     *              <li>NX -- Only set the key if it does not already exist.</li>
     *              <li>XX -- Only set the key if it already exist.</li>
     * @return
     */
    public static boolean set(String key, String value, String nxxx) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String statusCode = jedis.set(key, value, nxxx);
            if (SUCCESS_OK.equalsIgnoreCase(statusCode)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 当key不存在时，设置值，成功返回true
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean setIfNotExists(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long statusCode = jedis.setnx(key, value);
            if (SUCCESS_STATUS_LONG.equals(statusCode)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 当key不存在时，设置值，成功返回true，同setIfNotExists
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean setNX(String key, String value) {
        return setIfNotExists(key, value);
    }

    /**
     * 仅当key不存在时则设置值，成功返回true，存在时不设置值
     *
     * @param key
     * @param value
     * @param seconds，秒数，不能小于0
     * @return
     */
    public static boolean setIfNotExists(String key, String value, long seconds) {
        if (seconds < 0) {
            return false;
        }
        return set(key, value, NX, EX, seconds);
    }

    /**
     * 仅当key不存在时则设置值，成功返回true，存在时不设置值，同setIfNotExists(key, value, seconds)
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public static boolean setNX(String key, String value, Long seconds) {
        return setIfNotExists(key, value, seconds);
    }

    /**
     * 当key存在时则设置值，成功返回true，不存在不设置值
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean setIfExists(String key, String value) {
        return set(key, value, XX);
    }

    /**
     * 当key存在时则设置值，成功返回true，不存在不设置值，同setIfExists
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean setXX(String key, String value) {
        return setIfExists(key, value);
    }

    /**
     * 仅当key存在时则设置值，成功返回true，不存在不设置值
     *
     * @param key
     * @param value
     * @param seconds，秒数，不能小于0
     * @return
     */
    public static boolean setIfExists(String key, String value, long seconds) {
        if (seconds < 0) {
            return false;
        }
        return set(key, value, XX, EX, seconds);
    }

    /**
     * 仅当key存在时则设置值，成功返回true，不存在不设置值
     *
     * @param key
     * @param value
     * @param seconds，秒数，不能小于0
     * @return
     */
    public static boolean setXX(String key, String value, long seconds) {
        return setIfExists(key, value, seconds);
    }

    /**
     * 设置超期时间
     *
     * @param key
     * @param seconds 为Null时，将会马上过期。可以设置-1，0，表示马上过期
     * @return
     */
    public static boolean setTime(String key, Integer seconds) {
        Jedis jedis = null;
        try {
            if (seconds == null) {
                seconds = -1;
            }
            jedis = jedisPool.getResource();
            Long statusCode = jedis.expire(key, seconds);
            if (SUCCESS_STATUS_LONG.equals(statusCode)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 设置超期时间
     *
     * @param key
     * @param seconds 为Null时，将会马上过期。可以设置-1，0，表示马上过期
     * @return
     */
    public static boolean setOutTime(String key, Integer seconds) {
        return setTime(key, seconds);
    }

    /**
     * 设置超期时间
     *
     * @param key
     * @param seconds 秒数，为Null时，将会马上过期。可以设置-1，0，表示马上过期
     * @return
     */
    public static boolean expire(String key, Integer seconds) {
        return setTime(key, seconds);
    }

    /**
     * 判断key是否存在，存在返回true
     *
     * @param key
     * @return
     */
    public static boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 判断key是否存在，存在返回true
     *
     * @param key
     * @return
     */
    public static boolean isExists(String key) {
        return exists(key);
    }

    /**
     * 将key设置为永久
     *
     * @param key
     * @return
     */
    public static boolean persist(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            long time = getTime(key);
            if (time == -1) {
                return true;
            }
            //已经是永久的，返回0
            Long statusCode = jedis.persist(key);
            jedis.close();
            if (SUCCESS_STATUS_LONG.equals(statusCode)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 获取剩余时间（秒）
     *
     * @param key
     * @return
     */
    public static Long getTime(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.ttl(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return -1L;
    }


    /**
     * 随机获取一个key
     *
     * @return
     */
    public static String randomKey() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.randomKey();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 随机获取一个key
     *
     * @return
     */
    public static String random() {
        return randomKey();
    }

    /**
     * 修改 key 的名称，成功返回true，如果不存在该key，则会抛错：ERR no such key
     * 注：如果newKey已经存在，则会进行覆盖。建议使用renameNX
     *
     * @param oldkey 原来的key
     * @param newKey 新的key
     * @return
     */
    public static boolean rename(String oldkey, String newKey) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String statusCode = jedis.rename(oldkey, newKey);
            if (SUCCESS_OK.equalsIgnoreCase(statusCode)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 仅当 newkey 不存在时，将 key 改名为 newkey 。成功返回true
     *
     * @param oldkey
     * @param newKey
     * @return
     */
    public static boolean renameNX(String oldkey, String newKey) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long statusCode = jedis.renamenx(oldkey, newKey);
            if (SUCCESS_STATUS_LONG.equals(statusCode)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 仅当 newkey 不存在时，将 key 改名为 newkey 。成功返回true
     *
     * @param oldkey
     * @param newKey
     * @return
     */
    public static boolean renameIfNotExists(String oldkey, String newKey) {
        return renameNX(oldkey, newKey);
    }

    /**
     * 返回 key 所储存的值的类型。
     *
     * @param key
     * @return
     */
    public static String type(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.type(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 返回 key 所储存的值的类型。
     *
     * @param key
     * @return
     */
    public static String getType(String key) {
        return type(key);
    }

    /**
     * 删除key及值
     *
     * @param key
     * @return
     */
    public static boolean del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long statusCode = jedis.del(key);
            if (SUCCESS_STATUS_LONG.equals(statusCode)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 删除key及值
     *
     * @param key
     * @return
     */
    public static boolean delete(String key) {
        return del(key);
    }

    /**
     * 批量删除key及值
     *
     * @param keys
     * @return
     */
    public static boolean del(String[] keys) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long statusCode = jedis.del(keys);
            if (statusCode > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 批量删除key及值
     *
     * @param keys
     * @return
     */
    public static boolean delete(String[] keys) {
        return del(keys);
    }

    /**
     * 批量删除key及值
     *
     * @param keys
     * @return
     */
    public static boolean remove(String[] keys) {
        return del(keys);
    }


    /**************************** redis 列表List start***************************/

    /**
     * 将一个值插入到列表头部，value可以重复，返回列表的长度
     *
     * @param key
     * @param value String
     * @return 返回List的长度
     */
    public static Long lpush(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Long length = jedis.lpush(key, value);
        jedis.close();
        return length;
    }

    /**
     * 将多个值插入到列表头部，value可以重复
     *
     * @param key
     * @param values String[]
     * @return 返回List的数量size
     */
    public static Long lpush(String key, String[] values) {
        Jedis jedis = jedisPool.getResource();
        Long size = jedis.lpush(key, values);
        jedis.close();
        //System.out.println(result);
        return size;
    }

    /**
     * 获取List列表
     *
     * @param key
     * @param start long，开始索引
     * @param end   long， 结束索引
     * @return List<String>
     */
    public static List<String> lrange(String key, long start, long end) {
        Jedis jedis = jedisPool.getResource();
        List<String> list = jedis.lrange(key, start, end);
        jedis.close();
        return list;
    }

    /**
     * 通过索引获取列表中的元素
     *
     * @param key
     * @param index，索引，0表示最新的一个元素
     * @return String
     */
    public static String lindex(String key, long index) {
        Jedis jedis = jedisPool.getResource();
        String str = jedis.lindex(key, index);
        jedis.close();
        return str;
    }

    /**
     * 获取列表长度，key为空时返回0
     *
     * @param key
     * @return Long
     */
    public static Long llen(String key) {
        Jedis jedis = jedisPool.getResource();
        Long length = jedis.llen(key);
        jedis.close();
        return length;
    }

    /**
     * 在列表的元素前或者后插入元素，返回List的长度
     *
     * @param key
     * @param where LIST_POSITION
     * @param pivot 以该元素作为参照物，是在它之前，还是之后（pivot：枢轴;中心点，中枢;[物]支点，支枢;[体]回转运动。）
     * @param value
     * @return Long
     */
    public static Long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value) {
        Jedis jedis = jedisPool.getResource();
        Long length = jedis.linsert(key, where, pivot, value);
        jedis.close();
        return length;
    }

    /**
     * 将一个或多个值插入到已存在的列表头部，当成功时，返回List的长度；当不成功（即key不存在时，返回0）
     *
     * @param key
     * @param value String
     * @return Long
     */
    public static Long lpushx(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Long length = jedis.lpushx(key, value);
        jedis.close();
        return length;
    }

    /**
     * 将一个或多个值插入到已存在的列表头部，当成功时，返回List的长度；当不成功（即key不存在时，返回0）
     *
     * @param key
     * @param values String[]
     * @return Long
     */
    public static Long lpushx(String key, String[] values) {
        Jedis jedis = jedisPool.getResource();
        Long length = jedis.lpushx(key, values);
        jedis.close();
        return length;
    }

    /**
     * 移除列表元素，返回移除的元素数量
     *
     * @param key
     * @param count，标识，表示动作或者查找方向 <li>当count=0时，移除所有匹配的元素；</li>
     *                            <li>当count为负数时，移除方向是从尾到头；</li>
     *                            <li>当count为正数时，移除方向是从头到尾；</li>
     * @param value               匹配的元素
     * @return Long
     */
    public static Long lrem(String key, long count, String value) {
        Jedis jedis = jedisPool.getResource();
        Long length = jedis.lrem(key, count, value);
        jedis.close();
        return length;
    }

    /**
     * 通过索引设置列表元素的值，当超出索引时会抛错。成功设置返回true
     *
     * @param key
     * @param index 索引
     * @param value
     * @return boolean
     */
    public static boolean lset(String key, long index, String value) {
        Jedis jedis = jedisPool.getResource();
        String statusCode = jedis.lset(key, index, value);
        jedis.close();
        if (SUCCESS_OK.equalsIgnoreCase(statusCode)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
     *
     * @param key
     * @param start <li>可以为负数（-1是列表的最后一个元素，-2是列表倒数第二的元素。）</li>
     *              <li>如果start大于end，则返回一个空的列表，即列表被清空</li>
     * @param end   <li>可以为负数（-1是列表的最后一个元素，-2是列表倒数第二的元素。）</li>
     *              <li>可以超出索引，不影响结果</li>
     * @return boolean
     */
    public static boolean ltrim(String key, long start, long end) {
        Jedis jedis = jedisPool.getResource();
        String statusCode = jedis.ltrim(key, start, end);
        jedis.close();
        if (SUCCESS_OK.equalsIgnoreCase(statusCode)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 移出并获取列表的第一个元素，当列表不存在或者为空时，返回Null
     *
     * @param key
     * @return String
     */
    public static String lpop(String key) {
        Jedis jedis = jedisPool.getResource();
        String value = jedis.lpop(key);
        jedis.close();
        return value;
    }

    /**
     * 移除并获取列表最后一个元素，当列表不存在或者为空时，返回Null
     *
     * @param key
     * @return String
     */
    public static String rpop(String key) {
        Jedis jedis = jedisPool.getResource();
        String value = jedis.rpop(key);
        jedis.close();
        return value;
    }

    /**
     * 在列表中的尾部添加一个个值，返回列表的长度
     *
     * @param key
     * @param value
     * @return Long
     */
    public static Long rpush(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Long length = jedis.rpush(key, value);
        jedis.close();
        return length;
    }

    /**
     * 在列表中的尾部添加多个值，返回列表的长度
     *
     * @param key
     * @param values
     * @return Long
     */
    public static Long rpush(String key, String[] values) {
        Jedis jedis = jedisPool.getResource();
        Long length = jedis.rpush(key, values);
        jedis.close();
        return length;
    }

    /**
     * 仅当列表存在时，才会向列表中的尾部添加一个值，返回列表的长度
     *
     * @param key
     * @param value
     * @return Long
     */
    public static Long rpushx(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Long length = jedis.rpushx(key, value);
        jedis.close();
        return length;
    }

    /**
     * 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
     *
     * @param sourceKey 源列表的key，当源key不存在时，结果返回Null
     * @param targetKey 目标列表的key，当目标key不存在时，会自动创建新的
     * @return String
     */
    public static String rpopLpush(String sourceKey, String targetKey) {
        Jedis jedis = jedisPool.getResource();
        String value = jedis.rpoplpush(sourceKey, targetKey);
        jedis.close();
        return value;
    }

    /**
     * 移出并获取列表的【第一个元素】， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param timeout 单位为秒
     * @param keys    <li>当有多个key时，只要某个key值的列表有内容，即马上返回，不再阻塞。</li>
     *                <li>当所有key都没有内容或不存在时，则会阻塞，直到有值返回或者超时。</li>
     *                <li>当超期时间到达时，keys列表仍然没有内容，则返回Null</li>
     * @return List<String>
     */
    public static List<String> blpop(int timeout, String... keys) {
        Jedis jedis = jedisPool.getResource();
        List<String> values = jedis.blpop(timeout, keys);
        jedis.close();
        return values;
    }

    /**
     * 移出并获取列表的【最后一个元素】， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param timeout 单位为秒
     * @param keys    <li>当有多个key时，只要某个key值的列表有内容，即马上返回，不再阻塞。</li>
     *                <li>当所有key都没有内容或不存在时，则会阻塞，直到有值返回或者超时。</li>
     *                <li>当超期时间到达时，keys列表仍然没有内容，则返回Null</li>
     * @return List<String>
     */
    public static List<String> brpop(int timeout, String... keys) {
        Jedis jedis = jedisPool.getResource();
        List<String> values = jedis.brpop(timeout, keys);
        jedis.close();
        return values;
    }

    /**
     * 从列表中弹出列表最后一个值，将弹出的元素插入到另外一个列表中并返回它；
     * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param sourceKey 源列表的key，当源key不存在时，则会进行阻塞
     * @param targetKey 目标列表的key，当目标key不存在时，会自动创建新的
     * @param timeout   单位为秒
     * @return String
     */
    public static String brpopLpush(String sourceKey, String targetKey, int timeout) {
        Jedis jedis = jedisPool.getResource();
        String value = jedis.brpoplpush(sourceKey, targetKey, timeout);
        jedis.close();
        return value;
    }

}