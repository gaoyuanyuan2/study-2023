package com.yan.spring.boot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Cache
 *
 * @author : Y
 * @since 2023/5/22 20:50
 */
public class CacheController {

    @RequestMapping("/cache")
    public ResponseEntity<String> cachedHelloWorld(
            @RequestParam(required = false, defaultValue = "false") boolean cached) {
        if (cached) {
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        } else {
            return ResponseEntity.ok("Hello,World");
        }
    }
}