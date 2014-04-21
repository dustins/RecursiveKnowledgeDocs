/*
 * Copyright
 */

package com.recursiveknowledge;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Dustin Sweigart <dustin@swigg.net>
 */
@Configuration
@Import({WebConfig.class})
public class MainConfig {
}
