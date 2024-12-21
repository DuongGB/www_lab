/*
 * @ {#} AppConfig.java   1.0     12/21/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.jpasecurity_customer.backend.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   12/21/2024
 * @version:    1.0
 */
// Dùng để cấu hình các bean của ứng dụng Spring Boot
@Configuration
public class AppConfig {
    // Tạo một RestTemplate để gửi các request HTTP đến các API khác nhau
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

