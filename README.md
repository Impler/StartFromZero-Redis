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

## 4.6 HyperLogLog
Redis在2.8.9版本添加了HyperLogLog结构。HyperLogLog是用来做基数统计的算法，HyperLogLog的优点是，在输入元素的数量或者体积非常非常大时，计算基数所需的空间总是固定的、并且是很小的。每个HyperLogLog键只需要花费12KB内存，就可以计算接近2<sup>64</sup>个不同元素的基数。但是，因为HyperLogLog只会根据输入元素来计算基数，而不会存储输入元素本身，所以HyperLogLog不能像集合那样，返回输入的各个元素。  
基数：一组不重复元素的个数。比如{1, 3, 5, 5}的基数为3。  
示例：  
```python
127.0.0.1:6379> PFADD sets "a"
(integer) 1
127.0.0.1:6379> PFADD sets "b"
(integer) 1
127.0.0.1:6379> PFADD sets "b"
(integer) 0
127.0.0.1:6379> PFCOUNT sets
(integer) 2
```
HyperLogLog的常用命令：  

|序号|命令|描述|
|:-|:-|:-|
|1|PFADD key element [element...]|添加指定元素到HyperLogLog中|
|2|PFCOUNT key [key...]|返回给定HyperLogLog的基数估算值|
|3|PFMERGE destkey sourcekey [sourcekey...]|将多个HyperLogLog合并为一个HyperLogLog|

# 5 发布订阅
Redis 发布订阅（pub/sub）是一种消息通信模式：发送者（pub）发送消息，订阅者（sub）接收消息。Redis客户端可以订阅任意数量的频道。当有新消息通过PUBLISH命令发送给频道是，这个消息就会被发送给订阅它的客户端。  

发布订阅命令：  

|序号|命令|描述|
|:-|:-|:-|
|1|PSUBSCRIBE pattern [pattern...]|订阅一个或多个符合给定模式的频道|
|2|PUBSUB subcommand [argument[argument ...]]|查看订阅与发布系统状态|
|3|PUBLISH channel message|将消息发送到指定的频道|
|4|PUNSUBSCRIBE [pattern [pattern...]]|退订所有给定模式的频道|
|5|SUBSCRIBE channel [channel...]|订阅频道|
|6|UNSUBSCRIBE [channel [channel...]]|退订给定的频道|

# 5 事务
Redis事务可以一次执行多个命令，并且带有以下两个重要保证：  
- 事务是一个单独的隔离操作：事务中的所有命令都会序列化、按顺序的执行。事务在执行的过程中，不会被其他客户端发来的命令请求所打断
- 事务是一个原子操作：事务中的命令要么全部执行，要么全部不执行

一个事务从开始到执行会经历三个阶段：  
- 开始事务
- 命令入队
- 执行事务

使用MULTI命令开始事务，其后的命令都将放到事务队列中，指定执行EXEC命令，才一并执行十五中的所有命令。  
```redis
# 开始事务
127.0.0.1:6379> MULTI
OK
# 命令入队
127.0.0.1:6379> SET mykey 'aaa'
QUEUED
127.0.0.1:6379> GET mykey
QUEUED
# 触发事务
127.0.0.1:6379> EXEC
1) OK
2) "aaa"
127.0.0.1:6379>
```

事务命令：  

|序号|命令|描述|
|:-|:-|:-|
|1|DISCARD|取消事务，放弃执行事务块内的所有命令|
|2|EXEC|执行所有事务块内的所有命令|
|3|MULTI|标记一个事务快的开始|
|4|UNWATCH|取消WATCH命令对所有key的监视|
|5|WATCH key [key...]|监视一个或多个key，如果在事务执行之前这个key被其他命令所改动，那么事务将被打断|

# 6 脚本
Redis脚本使用Lua解释器来执行脚本。Redis2.6版本通过内嵌支持Lua环境。执行脚本的常用命令为EVAL。  
  
脚本常用命令：  

|序号|命令|描述|
|:-|:-|:-|
|1|EVAL script numkeys key [key...] arg [arg...]|执行Lua脚本|
|2|EVALSHA sha1 numkeys key [key...] arg [arg...]|执行Lua脚本|
|3|SCRIPT EXISTS script [script]|查看指定的脚本是否已经被保存在缓存当中|
|4|SCRIPT FLUSH|从脚本缓存中移除所有脚本|
|5|SCRIPT KILL|杀死当前正在运行的Lua脚本|
|6|SCRIPT LOAD script|将脚本script添加到脚本缓存中，但并不立即执行这个脚本|

# 7 连接
Redis连接命令用于连接redis服务。  

|序号|命令|描述|
|:-|:-|:-|
|1|AUTH password|验证密码是否正确|
|2|ECHO message|打印字符串|
|3|PING|查看服务器是否运行|
|4|QUIT|关闭当前连接|
|5|SELECT index|切换到指定数据库|


# 8 服务器
Redis服务器命令主要用于管理redis服务。  

|序号|命令|描述|
|:-|:-|:-|
|1|BGREWRITEAOP|异步执行一个AOF（AppendOnly File）文件重写操作|
|2|BGSAVE|异步保存当前数据库的数据到磁盘|
|3|CLIENT KILL [ip:port] [ID clientId]|关闭客户端连接|
|4|CLIENT LIST|获取连接到服务器的客户端连接列表|
|5|CLIENT GETNAME|获取连接的名称|
|6|CLIENT PAUSE timeout|在指定时间内终止运行来自客户端的命令|
|7|CLIENT SETNAME connectionName|设置当前连接的名称|
|8|CLUSTER SLOTS|获取集群节点的映射数组|
|9|COMMAND|获取Redis命令详情数组|
|10|COMMAND COUNT|获取Redis命令总数|
|11|COMMAND GETKEYS|获取给定命令的所有键|
|12|TIME|返回当前服务器时间|
|13|COMMAND INFO command-name [command-name]|获取指定Redis命令描述的数组|
|14|CONFIG GET parameter|获取指定配置参数的值|
|15|CONFIG REWRITE|对启动Redis服务器时所指定的redis.conf配置文件进行改写|
|16|CONFIG SET parameter value|修改redis配置参数，无需重启|
|17|CONFIG RESETSTAT|重置INFO命令中的某些统计数据|
|18|DBSIZE|返回当前数据库的key的数量|
|19|DEBUG OBJECT key|获取key的调试信息|
|20|DEBUG SEGFAULT|让Redis服务崩溃|
|21|FLUSHALL|删除所有数据库的所有key|
|22|FLUSHDB|删除当前数据库的所有key|
|23|INFO [section]|获取Redis服务器的各种信息和统计数值|
|24|LASTSAVE|返回最近一次Redis成功将数据保存到磁盘上的时间，以UNIX时间戳格式表示|
|25|MONITOR|实时打印出Redis服务器接收到的命令，调试用|
|26|ROLE|返回主从实例所属的角色|
|27|SAVE|异步保存数据到磁盘|
|28|SHUTDOWN [NOSAVE] [SAVE]|异步保存数据到磁盘，并关闭服务器|
|29|SLAVEOF host port|将当前服务器转变为指定服务器的从属服务器|
|30|SLOWLOG subcommand [argument]|管理redis的慢日志|
|31|SYNC|用于复制功能（replication）的内部命令|

# 9 数据备份与恢复
## 9.1 备份
使用SAVE命令创建当前数据库备份。该命令将在redis安装目录中创建dump.rdb文件。  
```redis
127.0.0.1:6379> SAVE
OK

# server log
[5856] 26 May 14:19:22.579 * DB saved on disk
```
也可以使用BGSAVE命令备份。该命令在后台执行备份操作。  
```redis
127.0.0.1:6379> bgsave
Background saving started

# server log
[5856] 26 May 14:19:28.962 * Background saving started by pid 8124
[5856] 26 May 14:19:29.077 # fork operation complete
[5856] 26 May 14:19:29.077 * Background saving terminated with success
```

## 9.2 恢复
如果需要恢复数据，只需要将备份文件（dump.rdb）移动到redis安装目录并启动服务即可。获取redis目录可以使用`CONFIG GET dir`命令。  


# 10 安全
可以通过redis的配置文件设置密码。  
```redis
# 查看是否设置了密码验证，默认为空，即没有设置密码
127.0.0.1:6379> CONFIG GET requirepass
1) "requirepass"
2) ""
# SET命令设置密码
127.0.0.1:6379> CONFIG SET requirepass "root"
OK
127.0.0.1:6379> CONFIG GET requirepass
1) "requirepass"
2) "root"
```
这样客户端连接到redis服务就要通过AUTH命令来验证密码。  
```redis
127.0.0.1:6379> SET mykey aaa
(error) NOAUTH Authentication required.
127.0.0.1:6379> AUTH root
OK
127.0.0.1:6379> SET mykey aaa
OK
```

# 11 性能测试
Redis 性能测试的基本命令如下:  
```redis
redis-benchmark [option] [option value]
```

redis 性能测试工具可选参数如下所示：  

|序号|选项|描述|默认值|
|:-|:-|:-|:-|
|1|-h|指定服务器主机名|127.0.0.1|
|2|-p|指定服务器端口|6379|
|3|-s|指定服务器socket||
|4|-c|指定并发连接数|50|
|5|-n|指定请求数|1000|
|6|-d|以字节的形式指定SET/GET值的数据大小|2|
|7|-k|1=keep alive 0=reconnect|1|
|8|-r|SET/GET/INCR使用随机key，SADD使用随机值||
|9|-P|通过管道传输<numreq>请求|1|
|10|-q|强制退出redis，仅显示query/sec值|1|
|11|-csv|以CSV格式输出||
|12|-i|生成循环，永久执行测试||
|13|-t|仅运行以逗号分隔的测试命令列表||
|14|-I|Idle模式。仅打开N个idle连接并等待||

# 12 客户端连接
Redis通过监听一个TCP端口或者Unix socket的方式来接收来自客户端的连接，当一个连接建立后，Redis内部会进行以下一些操作：  
- 客户端socket会被设置为非阻塞模式，因为redis在网络事件处理上采用的是非阻塞多路复用模型
- 为这个socket设置TCP_NODELAY属性，禁用Nagle算法
- 建立一个可读的文件时间用于监听这个客户端socket的数据发送

客户端常用命令：  

|序号|命令|描述|
|:-|:-|:-|
|1|CLIENT LIST|返回连接到redis服务的客户端列表|
|2|CLIENT SETNAME|设置当前连接的名称|
|3|CLIENT GETNAME|获取通过CLIENT SETNAME命令设置的服务名称|
|4|CLIENT PAUSE|挂起客户端连接，指定挂起的时间以毫秒计|
|5|CLIENT KILL|关闭客户端连接|

# 13 管道技术
Redis是一种基于客户端-服务端模型以及请求/响应协议的TCP服务。这意味着通常情况下一个请求会遵循以下步骤：
- 客户端向服务器端发送一个查询请求，并监听Socket返回，通常是以阻塞模式，等待服务端响应
- 服务端处理命令，并将结果返回个客户端

Redis管道技术可以在服务端未响应时，客户端可以继续向服务端发送请求，并最终一次性读取所有服务的响应，提高redis服务的性能。  

# 14 分区
分区是分割数据到多个Redis实例的处理过程，因此每个实例值保存key的一个子集。  
分区的优势：  
- 通过利用多台计算机内存的和值，允许我们构造更大的数据库
- 通过多核和多台计算机，允许我们扩展计算能力；通过多台计算机和网络适配器，允许我们扩展网络带宽
分区的不足：  
- 设计多个key的操作通常是不被支持的。举例来说，当两个set映射到不同的redis实例上时，你就不能对这两个set执行交集操作
- 设计多个key的redis事务不能使用
- 数据处理较为复杂，比如需要处理多个rdb/aof文件，并且从多个实例和主机备份持久化文件
- 增加或删除容量也比较复杂。redis集群大多数支持在运行时增加、删除节点的透明数据平衡的能力，但是类似于客户端分区、代理等其他系统则不支持这项特性。然而，一种叫做presharding的技术对此是有帮助的

分区类型：  
- 范围分区：映射一定范围的兑现管道特定的Redis实例
- 哈希分区：通过哈希算法，例如crc32 hash函数，对key进行分区

