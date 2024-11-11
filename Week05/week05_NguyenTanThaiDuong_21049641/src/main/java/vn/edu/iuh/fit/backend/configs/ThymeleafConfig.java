/*
 * @ {#} ThymeleafConfig.java   1.0     11/11/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.backend.configs;

import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   11/11/2024
 * @version:    1.0
 */
public class ThymeleafConfig {
    // Dùng để cấu hình template engine cho Thymeleaf để sử dụng các template mà chúng ta đã tạo
    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }
}

