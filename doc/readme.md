1.将本地jar包添加到本地maven仓库 
        

前提条件：maven已经加入环境变量中
mvn install:install-file -DgroupId=gnu.io -DartifactId=RXTXcomm -Dversion=1.0 -Dpackaging=jar -Dfile=E:\Work\Yotrio\libs\RXTXcomm.jar

//使用时再pom.xml文件中添加
<dependency>
    <groupId>gnu.io</groupId>
    <artifactId>RXTXcomm</artifactId>
    <version>1.0</version>
</dependency>

注意：如果引用失败，看下idea中是否应用本地maven仓库