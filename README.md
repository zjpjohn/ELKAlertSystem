# ELKAlertSystem
结合logstash kafka  redis spring  email freemarker构建邮件报警系统
主要解决业务系统异常报警，Nginx超时请求报警
系统根据业务线进行区分，针对不同的业务线可以配置不同的报警异常和邮件发送人，同时可以设置不同邮件模板（邮件模板采用freemarker，以字符串的形式存储在数据库中，同时也加载到redis缓存中）
报警规则比较简单：字段匹配规则、异常出现频率规则
利用kafka分区的功能，可以部署多台机器，分担单台机器的压力
