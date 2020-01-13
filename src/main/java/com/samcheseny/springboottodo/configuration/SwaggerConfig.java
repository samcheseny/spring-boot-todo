package com.samcheseny.springboottodo.configuration;

import com.google.common.collect.Multimap;
import java.util.Map;
import javax.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableConfigurationProperties(SwaggerConfig.ApiProperties.class)
public class SwaggerConfig {

  @Bean
  public static ApiProperties apiProperties() {
    return new ApiProperties();
  }

  @Bean
  public Docket apiDocket() {

    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.samcheseny.springboottodo"))
        .build()
        .pathMapping("/")
        .forCodeGeneration(true)
        .genericModelSubstitutes(ResponseEntity.class)
        .directModelSubstitute(Multimap.class, Map.class)
        .apiInfo(buildApiInfo());

  }

  private static ApiInfo buildApiInfo() {

    final ApiProperties apiProperties = apiProperties();

    return new ApiInfoBuilder()
        .title(apiProperties.getTitle())
        .description(apiProperties.getDescription())
        .version(apiProperties.getVersion())
        .termsOfServiceUrl(apiProperties.getTermsOfServiceUrl())
        .contact(
            new Contact(apiProperties.getName(), apiProperties.getUrl(), apiProperties.getEmail())
        )
        .license(apiProperties.getLicense())
        .licenseUrl(apiProperties.getLicenseUrl())
        .build();
  }

  @ConfigurationProperties(prefix = "api.info")
  @Validated
  public static class ApiProperties {

    @NotNull
    private String title;

    private String description;

    private String version;

    private String email;

    private String name;

    private String termsOfServiceUrl;//X

    private String url;

    private String license;

    private String licenseUrl;

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getVersion() {
      return version;
    }

    public void setVersion(String version) {
      this.version = version;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getTermsOfServiceUrl() {
      return termsOfServiceUrl;
    }

    public void setTermsOfServiceUrl(String termsOfServiceUrl) {
      this.termsOfServiceUrl = termsOfServiceUrl;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public String getLicense() {
      return license;
    }

    public void setLicense(String license) {
      this.license = license;
    }

    public String getLicenseUrl() {
      return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
      this.licenseUrl = licenseUrl;
    }
  }

}
