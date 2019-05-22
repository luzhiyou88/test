
设置环境变量：
classpath配置:MAVEN_HOME
path配置:%MAVEN_HOME%\bin

打开发包：
windows系统进入cmd, cd到项目目录下，执行
mvn clean install -P dev

打test包：
windows系统进入cmd, cd到项目目录下，执行
mvn clean install -P test 

打生产包：
windows系统进入cmd, cd到项目目录下，执行
mvn clean install -P prod 