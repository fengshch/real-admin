package cc.realtec.real.xxljob.executor.config;

import cc.realtec.real.xxljob.common.client.XxlJobClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class RestClientConfig {

    private final LoadBalancerInterceptor loadBalancerInterceptor;
    private final XxlJobConfig xxlJobConfig;

    @Bean
    public XxlJobClient xxlJobClient(RestClient restClient) {
        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        return HttpServiceProxyFactory
                .builderFor(restClientAdapter)
                .embeddedValueResolver(strVal -> strVal.replace("${xxl.job.server-url}",xxlJobConfig.getServerUrl()))
                .build().createClient(XxlJobClient.class);
    }

    @Bean
    public RestClient restClient(){
        return RestClient.builder()
                .requestInterceptors(interceptors->interceptors.add(loadBalancerInterceptor))
                .build();
    }
}
