package ru.kpfu.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
public class RedisSessionRepositoryConfig {

    private String host;
    private int port;
    private String password;

    @Autowired
    public RedisSessionRepositoryConfig(@Value("${security.session-store.host}") String host,
                                        @Value("${security.session-store.port}") int port,
                                        @Value("${security.session-store.password}") String password) {
        this.host = host;
        this.port = port;
        this.password = password;
    }

    @Bean("redisConnectionFactory")
    public LettuceConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory factory = new LettuceConnectionFactory(host, port);
        factory.setPassword(password);

        return factory;
    }
}
