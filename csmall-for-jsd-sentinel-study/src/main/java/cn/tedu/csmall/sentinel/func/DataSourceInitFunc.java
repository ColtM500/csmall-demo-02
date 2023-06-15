package cn.tedu.csmall.sentinel.func;

import com.alibaba.csp.sentinel.datasource.FileRefreshableDataSource;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.List;

/**
 * 完成读取flow.rules 加载到当前项目中的
 * 这些特定功能 需要实现一个接口
 */
@Slf4j
public class DataSourceInitFunc implements InitFunc {

    /**
     * 初始化的方法 将当前项目中能读取到的所有规则配置文件
     * 在init中生成，我们自定义的List对象 元素就是对应文件的类型
     * 有限流规则和熔断规则
     *
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        //打个日志 观察当前init方法何时加载
        log.info("DatasourceInitFunc loaded");
        //读取文件路径 当前类的类加载器 直接读取resource
        Class clazz = DataSourceInitFunc.class;
        ClassLoader classLoader = clazz.getClassLoader();
        URL resource = classLoader.getResource("flow.rules");
        //从数据文件源URL对象获取文件的路径file
        String file = resource.getFile();
        log.info("读取到文件，路径path:{}", file);
        //转化json称为Rule对象 这段代码使用了Sentinel提供的API来读取和注册流控规则
        ReadableDataSource<String, List<FlowRule>> flowDataSource =
                //FileRefreshableDataSource是一个可刷新的数据源，可以根据文件内容自动更新规则配置
                //同时，也需要确保资源文件“flow.rules”符合JSON格式，且规则内容能够正确解析为FlowRule对象。
                new FileRefreshableDataSource<List<FlowRule>>(
                        file, source -> JSON.parseArray(source, FlowRule.class)
                );
        //将FlowRule对象列表注册到Sentinel的流控规则管理器中
        //通过调用FlowRuleManager.register2Property()方法把对象列表转化为规则属性并注册
        FlowRuleManager.register2Property(flowDataSource.getProperty());
        //sentinel提供了文件SPI/API
        //SPI(Service Provider Interface)和API(Application Programming Interface)读取方式
    }
}
