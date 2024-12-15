package br.com.cesarsicas.springstore.domain.order;

import br.com.cesarsicas.springstore.domain.order.dto.AuthorizePaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentAuthorizationApiClient {
    private static final String PAYMENT_SERVICE_ID = "SPRINGSTORE-PAYMENT-SERVICE";

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;


    public Boolean authorizePayment(AuthorizePaymentDto authorizePaymentDto) {

        ServiceInstance serviceInstance = loadBalancerClient.choose(PaymentAuthorizationApiClient.PAYMENT_SERVICE_ID);

        String uri = serviceInstance.getUri().toString();

        System.out.println("Uri >>>>> " + uri);

        var result = restTemplate.postForEntity(
                uri + "/payments/api/authorization",
                authorizePaymentDto, String.class);

        return result.getStatusCode() == HttpStatus.OK;

    }
}
