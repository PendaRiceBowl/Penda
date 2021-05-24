# Penda
背景：
    平时在Android开发时，一定会遇到Activity之间跳转，一些跳转还需要带上参数，添加数据时一系列的xxxPut（），读数据的时候又一系列的xxx这些参数设置或获取的时候很容易出错，面对这个问题大家已经总结出一些不错的方法，
    比如将跳转相关逻辑写成一个静态方法，将要传递的参数的value设置成字符串常量。可是还是比较麻烦。之前了解了JavaPoe自动生成代码，刚好实践一番。
主要实现：
  1.定义注解，分为2类：
    1.作用于我们定义的Activity
    2.参数注解，作用于要传递的参数
    
  2。注解处理器：
    1.读取类上的注解，生成相对应的类
    2.读取参数注解，生成相应的常量值
    3.在生成类上生成startActivity（）方法，并将参数传递给Intent
    4.调用生成类的startActivity（）方法。
    
这个项目仅仅是我在了解JavaPoet的小小尝试，还有很多知识需要完善
     
 
