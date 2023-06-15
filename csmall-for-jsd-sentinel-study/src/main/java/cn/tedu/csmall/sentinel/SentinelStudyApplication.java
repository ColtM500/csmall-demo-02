package cn.tedu.csmall.sentinel;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前程序启动时，使用sentinel的api方法
 * 加载读取针对资源的规则对象Rule 就可以让限流逻辑生效
 */
@SpringBootApplication
public class SentinelStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentinelStudyApplication.class, args);
//        //编写 当前sentinel中唯一资源sayHi的流控规则/熔断规则
//        //针对资源的规则，一个程序可以有多个规则对象，流控规则FlowRule
//          List<DegradeRule> degradeRules = new ArrayList<>();
          //添加一个熔断规则
//        List<FlowRule> rules = new ArrayList<>();
//        //定义一个具体的流控规则 放到list中
//        FlowRule flowRule = new FlowRule();
//        flowRule.setResource("sayHi");
//        //设置qps上限为1 count为阈值
//        flowRule.setCount(1);
//        //设置限流策略，有2种 0=qps 1=并发
//        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        //总结上述规则的含义： 对sayHi的资源 设置一个流控规则 qps数值超过1/s就实现限流
//        //抛出调用资源的BlockException异常
//        rules.add(flowRule);
//        //将规则列表加载到程序内容
//        FlowRuleManager.loadRules(rules);
    }
}
