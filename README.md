# 1 简介
Redis(Remote Dictionary Server)是一个开源的使用ANSI C语言编写、遵循BSD协议、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。  
Redis通常被称为数据结构服务器，因为值可以使字符串（String）、哈希（Map）、列表（list）、集合（sets）和有序集合（sorted sets）等类型。  
Redis的特点：  
- 支持数据的持久化，可以将内存中的数据保存在磁盘中，重启的时候可以再次加载进行使用
- 不仅仅支持简单的key-value类型的数据，同时还提供list，set，zset，hash等数据结构存储
- 支持数据备份，即master-slave模式的数据备份
- 性能极高，读速度110000次/s，写速度81000次/s
- 所有操作都是原子性的，同时还支持多个操作合并后的原子性执行
- 特性丰富，支持publish/subscribe，通知，key过期等等特性

# 2 安装和配置
# 3 命令
Redis命令用于在redis服务上执行操作。要在redis服务上执行命令需要一个redis客户端。  
启动redis客户端：  
```redis
# 输入命令redis-cli连接到本地redis服务
redis-cli
# 执行ping命令，检测redis服务是否启动
ping

# 响应PONG表示启动正常
```
如果需要在远程redis服务商执行命令，需要使用redis-cli连接远程redis服务：  
```redis
redis-cli -h hostIp -p port -a password
```

Redis keys命令

|序号|命令|描述|
|:-|:-|:-|
|1|DEL key|在key存在时删除key|
|2|DUMP key|序列化给定的key，并返回被序列化后的值|
|3|EXISTS key|检查给定key是否存在|
|4|EXPIRE key seconds|为给定key设置过期时间|
|5|EXPIREAT key timestamp|与EXPIRE类似，都是为key设置过期时间，不同之处是时间参数为UNIX时间戳|
|6|PEXPIRE key milliseconds|设置key的过期时间以毫秒计|
|7|PEXPIREAT key milliseconds-timestamp|设置key的过期时间的毫秒级时间戳|
|8|KEYS pattern|查找所有符合给定模式（pattern）的key|
|9|MOVE key db|将当前数据库的key移动到给定的数据库中|
|10|PTTL key|以毫秒为单位返回key的剩余的过期时间|
|11|PERSIST key|移除key的过期时间，key将永久保存|
|12|TTL key|以秒为单位，返回给定key的剩余生存时间（TTL, time to live）|
|13|RANDOMKEY|从当前数据库中随机返回一个key|
|14|RENAME key newkey|重命名key|
|15|RENAMENX key newkey|仅当newkey不存在时，将key改名为newkey|
|16|TYPE key|返回key所存储的值的类型|

# 4 数据类型
## 4.1 字符串（String）
Redis字符串数据类型的相关命令用于管理redis字符串值。例如：  
```redis
127.0.0.1:6379> SET mykey redis
OK
127.0.0.1:6379> GET mykey
"redis"
```
常用的字符串命令：  

|序号|命令|描述|
|:-|:-|:-|
|1|SET key value|设置指定key对应的值|
|2|GET key|获取指定key对应的值|
|3|GETRANGE key start end|返回key对应的value的子串|
|4|GETSET key value|将给定key对应的值设为value，并返回key的旧值|
|5|GETBIT key offset|获取key对应的值指定偏移量上的位(bit)|
|6|MGET key1 [key2..]|获取所有给定key对应的值|
|7|SETBIT key offset value|对key的值设置或清除指定偏移量上的位|
|8|SETEX key seconds value|将value关联到key，并设置过期时间（秒为单位）|
|9|SETNX key value|只有在key不存在时设置key的值|
|10|SETRANGE key offset value|用value覆盖key对应的值，从偏移量offset开始|
|11|STRLEN key|返回key对应值的字符串长度|
|12|MSET key value [key value...]|同时设置多个key-value对|
|13|MSETNX key value [key value...]|同时设置多个key-value对，当且仅当所有key都不存在|
|14|PSETEX key milliseconds value|以毫秒为单位设置key的过期时间|
|15|INCR key|将key中存储的数字值曾一|
|16|INCRBY key increment|将key所存储的值加上给定的浮点增量值|
|17|INCRBYFLOAT key increment|将key所存储的值加上给定的浮点增量值|
|18|DECR key|将key中存储的数字值减一|
|19|DECRBY key decrement|将key中存储的值减去给定的减量值|
|20|APPEND key value|如果key已经存在并且是一个字符串，APPEND命令将value追加在原来只的末尾|

## 4.2 哈希（Hash）
Redis hash是一个string类型的field和value的映射表，hash特别适合用于存储对象。  
Redis中每个hash可以存储2<sup>32</sup>-1个键值对。  
```redis
127.0.0.1:6379> HMSET stu1 name zhangsan age 12 score 90
OK
127.0.0.1:6379> HGETALL stu1
1) "name"
2) "zhangsan"
3) "age"
4) "12"
5) "score"
6) "90"
```

|序号|命令|描述|
|:-|:-|:-|
|1|HDEL key field1 [field2]|删除一个或多个哈希表字段|
|2|HEXISTS key filed|查看hash表key中，指定的字段是否存在|
|3|HGET key filed|获取存储在哈希表中指定字段的值|
|4|HGETALL key|获取在哈希表中指定key的所有字段的值|
|5|HINCRBY key field increment|为哈希表key中指定字段的整数值加上增量increment|
|6|HINCRBYFLOAT key field increment |为哈希表key中的指定字段的浮点数值加上增量increment|
|7|HKEYS key|获取所有哈希表中的字段|
|8|HLEN key|获取哈希表中字段的数量|
|9|HMGET key field1 [field2..]|获取所有给定字段的值|
|10|HMSET key field1 value1 [field2 value2...]|同时将多个field-value对设置到hash表的key中|
|11|HSET key field value|将哈希表key中的字段field的值设为value|
|12|HSETNX key field value|只有在字段field不存在时，设置哈希表的字段值|
|13|HVALS key|获取哈希表中所有的值|
|14|HSCAN key cursor [MATCH pattern] [COUNT count]|迭代哈希表中的键值对|

## 4.3 列表（List）
Redis列表是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部（左边）或者尾部（右边）。  
一个列表最多可以包含2<sup>32</sup>-1个元素。  
示例：  
```redis
127.0.0.1:6379> LPUSH score 90
(integer) 1
127.0.0.1:6379> LPUSH score 99
(integer) 2
127.0.0.1:6379> LPUSH score 85
(integer) 3
127.0.0.1:6379> LRANGE score 0 3
1) "85"
2) "99"
3) "90"
```

Redis常用列表命令：  

|序号|命令|描述|
|:-|:-|:-|
|1|BLPOP key1 [key2] timeout|移除并获取列表的第一个元素，如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止|
|2|BRPOP key1 [key2] timeout|移除并获取列表的最后一个元素，如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止|
|3|BRPOPLPUSH source destination timeout|从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它；如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止|
|4|LINDEX key index|通过索引获取列表中的元素|
|5|LINSERT key BEFORE|AFTER pivot value|在列表元素的前或后插入元素|
|6|LLEN key|获取列表长度|
|7|LPOP key|移出并获取列表的第一个元素|
|8|LPUSH key value1 [value2]|将一个或多个值插入到列表头部|
|9|LPUSHX key value|将一个或多个值插入到已存在的列表头部|
|10|LRANGE key start stop|获取列表指定范围内的元素|
|11|LREM key count value|移除列表元素|
|12|LSET key index vlaue|通过所以设置列表元素的值|
|13|LTRIM key start stop|对一个列表进行修剪，让列表只保留指定区间内的元素，其他元素将被删除|
|14|RPOP key|移除并获取列表最后一个元素|
|15|RPOPLPUSH source destination|移除列表的最后一个元素，并将该元素添加到另一个列表并返回|
|16|RPUSH key value1 [value2]|在列表中添加一个或多个值|
|17|RPUSHX key value|为已存在的列表添加值|

## 4.4 集合（Set）
Redis的Set是string类型的无序集合。集合成员是唯一的，不能重复。  
Redis中集合是通过哈希表实现的，所以添加、删除、查找的复杂的都是O(1)。  
集合中最大的成员数为2<sup>32</sup>-1。  
示例：  
```redis
127.0.0.1:6379> SADD dbs redis
(integer) 1
127.0.0.1:6379> SADD dbs mongodb
(integer) 1
127.0.0.1:6379> SADD dbs mysql
(integer) 1
127.0.0.1:6379> SADD dbs mysql
(integer) 0
```
Redis常用集合命令：  

|序号|命令|描述|
|:-|:-|:-|
|1|SADD key memeber1 [menber2]|向集合中添加一个或多个成员|
|2|SCARD key|获取集合的成员数|
|3|SDIFF key1 [key2]|返回给定所有集合的差集|
|4|SDIFFSTORE destination key1 [key2]|返回给定所有集合的差集并存储在destination中|
|5|SINTER key1 [key2]|返回给定所有集合的交集|
|6|SINTERSTORE destination key1 [key2]|返回给定所有集合的交集并存储在destination中|
|7|SISMEMBER key member|判断member是否是集合key的成员|
|8|SMEMBERS key|返回集合的所有成员|
|9|SMOVE source destination member|将member元素从source集合移动到destination集合|
|10|SPOP|移除并返回集合中的一个随机元素|
|11|SRANDMEMBER key [count]|返回集合中一个或多个随机数|
|12|SREM key member1 [member2]|移除集合中一个或多个成员|
|13|SUNION key1 [key2]|返回所有给定集合的并集|
|14|SUNIONSTORE destination key1 [key2]|所有给定集合的并集存储在destination集合中|
|15|SSCAN key cursor [MATCH pattern] [COUNT count]|迭代集合中的元素|

## 4.5 有序集合(sorted set)
Redis有序集合和集合一样也是string类型元素的集合，且不允许重复的成员。  
不同的是每个元素都会关联一个double类型的分数。redis正式通过分数来为集合中的成员进行从小到大排序的。  
有序结合的成员是唯一的，但分数却可以重复。  
集合是通过哈希表实现的，所以添加、删除、查找的复杂度都是O(1)。集合中的最大成员数数为2<sup>32</sup>-1。  
示例：  
```redis
127.0.0.1:6379> ZADD lans 1 java
(integer) 1
127.0.0.1:6379> ZADD lans 2 javascript
(integer) 1
127.0.0.1:6379> ZADD lans 4 C
(integer) 1
127.0.0.1:6379> ZADD lans 3 Python
(integer) 1
127.0.0.1:6379> ZRANGE lans 0 4 WITHSCORES
1) "java"
2) "1"
3) "javascript"
4) "2"
5) "Python"
6) "3"
7) "C"
8) "4"
```

有序集合常用命令：  
|序号|命令|描述|
|:-|:-|:-|
|1|ZADD key score1 member1 [score2 member2...]|向有序集合添加一个或多个数据成员，或者更新已存在成员的分数|
|2|ZCARD key|获取有序集合的成员数|
|3|ZCOUNT key min max|计算在有序集合中指定区间分数的成员数|
|4|ZINCRBY key increment member|有序集合中对指定成员的分数加上增量increment|
|5|ZINTERSTORE destination numkeys key1 [key2...]|计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合key中|
|6|ZLEXCOUNT key min max|在有序集合中计算指定字典区间内成员数量|
|7|ZRANGE key start stop [WITHSCORES]|通过索引区间返回有序集合指定区间内的成员|
|8|ZRANGEBYLEX key min max [LIMIT offset count]|通过字典区间返回有序集合的成员|
|9|ZRANGEBYSCORE key min max [WITHSCORES] [LIMIT]|通过分数返回有序集合指定区间内的成员|
|10|ZRANK key member|返回有序集合中指定成员的索引|
|11|ZREM key member [member...]|移除有序集合中的一个过多个成员|
|12|ZREMRANGEBYLEX key min max|移除有序集合中给定的字典区间的所有成员|
|13|ZREMRANGEBYRANK key start stop|移除有序集合中给定的排名区间的所有成员|
|14|ZREMRANGEBYSCORE key min max|移除有序集合中给定的分数区间的所有成员|
|15|ZREVRANGE key start stop [WITHSCORES]|返回有序集中指定区间内的成员，通过索引，分数从高到低|
|16|ZREVRANGEBYSCORE key max min [WITHSCORES]|返回有序集中指定分数区间内的成员，分数从高到低排序|
|17|ZREVRANK key member|返回有序集合中指定成员的排名，有序集成员按分数值从高到低排序|
|18|ZSCORE key member|返回有序集中，成员的分数值|
|19|ZUNIONSTORE destination numkeys key [key...]|计算给定的一个或多个有序集的并集，并存储在新的key中|
|20|ZSCAN key cursor [MATCH pattern] [COUNT count]|迭代有序集合中的元素（包括成员和分值）|
