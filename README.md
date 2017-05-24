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
|15|INCR kye|将key中存储的数字值曾一|
|16|INCRBY key increment|将key所存储的值加上给定的浮点增量值|
|17|INCRBYFLOAT key increment|将key所存储的值加上给定的浮点增量值|
|18|DECR key|将key中存储的数字值减一|
|19|DECRBY key decrement|将key中存储的值减去给定的减量值|
|20|APPEND key value|如果key已经存在并且是一个字符串，APPEND命令将value追加在原来只的末尾|

## 4.2 哈希（Hash）




||||
||||
||||
||||
||||
||||
||||
||||
||||
||||











|序号|命令|描述|
|:-|:-|:-|
||||
||||
||||
||||
||||
||||
||||
||||
||||
||||