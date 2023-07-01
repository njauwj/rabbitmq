### Rabbitmq工作模型
![img.png](img.png)
> 一个交换机可以绑定多个队列，也可以指定多个RoutingKey
### 消息队列核心三要素
消息生产者、消息队列、消息消费者
### 交换机的种类
1. Fanout Exchange（扇形）绑定和发送时不需要指定 RoutingKey
2. Direct Exchange（直连）绑定和发送时需要指定 RoutingKey
3. Topic Exchange（主题）绑定和发送时需要指定 RoutingKey （RoutingKey可以使用通配符 * 表示一个字符 # 表示多个字符）
4. Headers Exchange（头部）
### 消息的过期
1. 针对单条消息设置过期时间
2. 针对整个队列设置过期时间（队列里的所有消息都会有过期时间）
> 如果队列和消息都设置了过期时间，则时间小的为准
### 死信队列
![img_1.png](img_1.png)
> 本质上就是消息过期后不是直接消失，而是发送给一个死信交换机（就是普通交换机）从而接收到死信队列（就是普通队列）
> 使用场景，当用户下单后把订单ID放入队列,(该队列没有消费者)设置队列过期时间为30分钟，30分钟后放入死信队列，从死信队列拿到订单ID查询数据库，如果用户还未支付修改该订单状态，例如将未支付改为已过期