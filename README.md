# activeMQ
String mark = Long.toHexString(Long k), 将Long类型的k转化为16进制的字符串。

String s = "abc".concat("123");
s的结果为abc123                         

Maps.newHashMap() 和 new HashMap() 一样， 只是为了简化代码

RedeliveryPolicy   消息的重试策略

ExecutorService的关闭

   shutdown方法：平滑的关闭ExecutorService，当此方法被调用时，ExecutorService停止接收新的任务并且等待已经提交的任务
   （包含提交正在执行和提交未执行）执行完成。当所有提交任务执行完毕，线程池即被关闭。

   awaitTermination方法：接收人timeout和TimeUnit两个参数，用于设定超时时间及单位。
   当等待超过设定时间时，会监测ExecutorService是否已经关闭，若关闭则返回true，否则返回false。一般情况下会和shutdown方法组合使用。


