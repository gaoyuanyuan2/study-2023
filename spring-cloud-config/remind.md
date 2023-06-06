# 配置

## Apache Commons Configuration API

Apache Commons通用工程集合,它对jJDK API做出一定的补充，commons-lang StringUtils,
commons-collection CollectionUtils, commons-dbcp BasicDataSource

commons-configuration提供了统-配置API,提供通用数据类型转换、Integer、 Long、 BigDecimal

## 核心接口

* org.apache.commons.configuration.Configuration
  * 关系型数据库 org.apache.commons.configuration.DatabaseConfiguration
  * Properties 配置文件-org.apache.commons.configuration.PropertiesConfiguration
  * XML配置文件-org.apache commons.configuration.XMLConfiguration
  * OS环境变量-org.apache.commons.configuration.EnvironmentConfiguration
  * Java System Properties-org.apache.commons.configuration.SystemConfiguration
  * 
以上实现均有外部提供配置的来源

## 通用需求

* 读取配置源
* 合并配置源
* 定义顺序
