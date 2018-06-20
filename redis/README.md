Redis
===================

这个模块是对feign的调用拦截的服务信息转存于Redis供页面查询监控

```java
GitHub github = Feign.builder()
                     .client(new OkHttpClient())
                     .target(GitHub.class, "https://api.github.com");
```
